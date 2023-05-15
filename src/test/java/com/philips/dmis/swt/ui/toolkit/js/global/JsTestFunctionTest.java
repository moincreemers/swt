package com.philips.dmis.swt.ui.toolkit.js.global;

import org.mozilla.javascript.*;

import java.util.ArrayList;
import java.util.List;

public class JsTestFunctionTest {
    //@Test
    public void test() {
        Context cx = Context.enter();
        try {
            Scriptable scope = cx.initStandardObjects();

            String code = "";
            List<Object> arguments = new ArrayList<>();

            code += "var Test=(function(){";
            code += "function x(){return 123;}";
            code += "return {x:x};";
            code += "})();";

            cx.evaluateString(scope, code, "script", 1, null);
            ScriptableObject moduleScope = (ScriptableObject) scope.get("Test", scope);
            Function function = (Function) moduleScope.get("x", moduleScope);
            Object result = function.call(cx, moduleScope, moduleScope, arguments.toArray());

            System.out.println(result);
        } catch (EvaluatorException evaluatorException) {
            System.out.println("compile error: " + evaluatorException.getMessage());
            System.out.println("\t[" + evaluatorException.lineNumber() + ":" + evaluatorException.columnNumber() + "] " + evaluatorException.lineSource());
            System.out.println("\tfragment: " + getCodeFragment(getLine(evaluatorException.lineSource(), evaluatorException.lineNumber()), evaluatorException.columnNumber()));
            throw evaluatorException;

        } finally {
            cx.close();
        }
    }

    String getCodeFragment(String source, int columnNumber) {
        int i0 = Math.max(0, columnNumber - 30);
        String left = source.substring(i0, columnNumber);
        int i1 = Math.min(columnNumber + 30, source.length());
        String right = source.substring(columnNumber, i1);
        return left + "<" + columnNumber + ">" + right;
    }

    String getLine(String source, int lineNumber) {
        String[] lines = source.split("\n");
        if (lineNumber - 1 >= lines.length) {
            return "";
        }
        return lines[lineNumber - 1];
    }
}
