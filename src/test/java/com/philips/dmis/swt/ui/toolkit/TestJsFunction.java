package com.philips.dmis.swt.ui.toolkit;

import com.philips.dmis.swt.ui.toolkit.js.*;
import org.mozilla.javascript.*;

import java.util.ArrayList;
import java.util.List;

public class TestJsFunction implements java.util.function.Function<List<Object>, Object> {
    public interface DependencyResolver {
        JsMember resolve(Class<? extends JsMember> memberClass);

        String getId(Class<? extends JsMember> memberClass);
    }

    private final Toolkit toolkit;
    private final List<JsFunction> functions = new ArrayList<>();
    private final DependencyResolver dependencyResolver;
    private static JsWriter js = new JsWriter();
    private final Window window = new Window();
    private final JsFunction functionUnderTest;
    private final String functionUnderTestId;

    public TestJsFunction(Toolkit toolkit, DependencyResolver dependencyResolver,
                          Class<? extends JsMember> memberClass) {
        this.toolkit = toolkit;
        this.dependencyResolver = dependencyResolver;

        JsMember jsMember = dependencyResolver.resolve(memberClass);
        if (!(jsMember instanceof JsFunction)) {
            throw new IllegalArgumentException("member not a function: " + memberClass.getName());
        }
        functionUnderTest = (JsFunction) jsMember;
        functionUnderTestId = dependencyResolver.getId(memberClass);

        js.append("var Test=(function(){");
        List<String> functionIds = new ArrayList<>();
        addDependency(functionUnderTest, functionIds);

        // export
        js.append("return {");
        int i = 0;
        for (String functionId : functionIds) {
            if (i > 0) {
                js.append(",");
            }
            js.append("%s:%s", functionId, functionId);
            i++;
        }
        js.append("}");

        js.append("})();");
    }

    void addDependency(JsMember jsMember, List<String> memberIds) {
        String memberId = dependencyResolver.getId(jsMember.getClass());
        memberIds.add(memberId);

        if (jsMember instanceof JsFunction) {
            JsFunction jsFunction = (JsFunction) jsMember;
            List<Class<? extends JsMember>> dependencies = new ArrayList<>();
            jsFunction.getDependencies(dependencies);
            for (Class<? extends JsMember> memberClass : dependencies) {
                if (memberIds.contains(dependencyResolver.getId(memberClass))) {
                    // prevent adding duplicates = overflow
                    continue;
                }
                addDependency(dependencyResolver.resolve(memberClass), memberIds);
            }
        }

        // NOTE: different than in the browser but great for readability
        // In case of errors, Rhino will automatically select the line on which the error was encountered
        // so separating logical code blocks makes sense.
        if (!js.isEmpty()) {
            js.append("\n");
        }

        js.append("const %s=", memberId);
        jsMember.renderJs(toolkit, js);
        js.append(";");
    }

    @Override
    public Object apply(List<Object> arguments) {
        Context cx = Context.enter();
        try {
            // important: this ensures that a wrapped Java object
            // has members with correct data types.
            // Input: class obj { public String field = "value"; }
            // Without: typeof obj.field == 'object'.
            // With: typeof obj.field == 'string'.
            cx.getWrapFactory().setJavaPrimitiveWrap(false);

            Scriptable scope = cx.initStandardObjects();

            arguments = validateArguments(cx, scope, arguments);

            addGlobalObjects(scope);
            cx.evaluateString(scope, js.toString(), "script", 1, null);
            ScriptableObject moduleScope = (ScriptableObject) scope.get("Test", scope);
            Function function = (Function) moduleScope.get(functionUnderTestId, moduleScope);
            Object returnValue = function.call(cx, moduleScope, moduleScope, arguments.toArray());

            // todo: how to interpret return values?

            return returnValue;

        } catch (EvaluatorException evaluatorException) {
            System.out.println("method: " + functionUnderTestId);
            System.out.println("compile error: " + evaluatorException.getMessage());
            String lineSource = evaluatorException.lineSource();
            if (lineSource != null) {
                System.out.println("\t[" + evaluatorException.lineNumber() + ":" + evaluatorException.columnNumber() + "] " + lineSource);
                System.out.println("\tfragment: " + getCodeFragment(lineSource, evaluatorException.columnNumber()));
            } else {
                System.out.println("dump: " + js.toString());
            }
            throw evaluatorException;

        } catch (EcmaError ecmaError) {
            System.out.println("method: " + functionUnderTestId);
            System.out.println("dump: " + js.toString());
            throw ecmaError;
        } finally {
            cx.close();
        }
    }

    private List<Object> validateArguments(Context cx, Scriptable scope, List<Object> arguments) {
        List<Object> a = new ArrayList<>();
        if (arguments != null) {
            a.addAll(arguments);
        }
        List<JsParameter> parameters = new ArrayList<>();
        functionUnderTest.getParameters(parameters);
        if (parameters.size() != a.size()) {
            throw new IllegalArgumentException("function expects " + parameters.size() + " parameters");
        }
        for (int i = 0; i < parameters.size(); i++) {
            boolean typeMismatch = false;
            if (a.get(i) == null) {
                continue;
            }

            switch (parameters.get(i).getType()) {
                case ARRAY:
                    typeMismatch = !a.get(i).getClass().isArray();
                    break;
            }
            if (typeMismatch) {
                throw new IllegalArgumentException("expected array at parameter: " + i + " (" + parameters.get(i).getName() + ")");
            }

            switch (parameters.get(i).getType()) {
                case STRING:
                    typeMismatch = !(a.get(i) instanceof String);
                    break;
                case NUMBER:
                    typeMismatch = !(a.get(i) instanceof Number);
                    break;
                case BIGINT:
                    // todo:?
                    break;
                case BOOLEAN:
                    typeMismatch = !(a.get(i) instanceof Boolean);
                    break;
                case SYMBOL:
                    // todo:?
                    break;
//                case OBJECT:
//                    break;
//                case OBJECT_ARRAY:
//                    break;
                case FUNCTION:
                    // todo:?
                    break;
                case ARRAY:
                    typeMismatch = (!a.get(i).getClass().isArray());
                    break;
            }
        }

        for (int i = 0; i < parameters.size(); i++) {
            switch (parameters.get(i).getType()) {
                case OBJECT:
                case ARRAY:
                    a.set(i, Context.javaToJS(a.get(i), scope));
                    break;
            }
        }

        return a;
    }

    void addGlobalObjects(Scriptable scope) {
        ScriptableObject.putProperty(scope, "window",
                Context.javaToJS(window, scope));
        ScriptableObject.putProperty(scope, "document",
                Context.javaToJS(window.document, scope));
        ScriptableObject.putProperty(scope, "console",
                Context.javaToJS(window.console, scope));
    }

    String getCodeFragment(String source, int columnNumber) {
        if (source == null || source.isEmpty()) {
            return "";
        }
        int i0 = Math.max(0, columnNumber - 30);
        String left = source.substring(i0, columnNumber);
        int i1 = Math.min(columnNumber + 30, source.length());
        String right = source.substring(columnNumber, i1);
        return left + " <--" + columnNumber + "--> " + right;
    }

    public Window getWindow() {
        return window;
    }

    public static class Window {
        public final Document document = new Document();
        public final Console console = new Console();
    }

    public static class Document {
        public final Location location = new Location();
    }

    public static class Location {
        public String hash = "";
    }

    public static class Console {
        public void log(Object... values) {
            for (Object value : values) {
                System.out.print(value);
                System.out.print("\t");
            }
            System.out.println("");
        }
    }
}