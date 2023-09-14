package com.philips.dmis.swt.ui.toolkit.widgets;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.js.*;

import java.util.List;
import java.util.*;

public abstract class Code extends Widget implements HasCode {
    public static JsFunction createFunction(String name, JsType returnType, String jsCode, JsParameter... jsParameters) {
        return createFunction(true, name, returnType, jsCode, jsParameters);
    }

    public static JsFunction createPrivateFunction(String name, JsType returnType, String jsCode, JsParameter... jsParameters) {
        return createFunction(false, name, returnType, jsCode, jsParameters);
    }

    private static JsFunction createFunction(boolean pub, String name, JsType returnType, String jsCode, JsParameter... jsParameters) {
        if (jsCode == null || jsCode.isEmpty()) {
            throw new JsRenderException("invalid code");
        }

        return new JsFunction() {
            @Override
            public void getDependencies(List<Class<? extends JsMember>> dependencies) {
            }

            @Override
            public void getParameters(List<JsParameter> parameters) {
                if (jsParameters != null) {
                    parameters.addAll(Arrays.asList(jsParameters));
                }
            }

            @Override
            public boolean isMemberOf(Widget widget, WidgetType widgetType) {
                return false;
            }

            @Override
            public boolean isPublic() {
                return pub;
            }

            @Override
            public String getPublicName(String id) {
                return name;
            }

            @Override
            public JsType getType() {
                return returnType;
            }

            @Override
            public void renderJs(Toolkit toolkit, JsWriter js) throws JsRenderException {
                js.append(jsCode);
            }
        };
    }

    public JsFunction getFunction(String name) {
        for (JsFunction jsFunction : functions) {
            String publicName = jsFunction.getPublicName("");
            if (publicName.equals(name)) {
                return jsFunction;
            }
        }
        throw new IllegalArgumentException("not found function " + name);
    }

    private final String moduleName;
    private final java.util.List<JsFunction> functions = new ArrayList<>();

    public Code(WidgetConfigurator widgetConfigurator, String moduleName) {
        super(widgetConfigurator, WidgetType.CODE);
        this.moduleName = moduleName;
        build();
    }

    public Code(String moduleName) {
        super(WidgetType.CODE);
        this.moduleName = moduleName;
        build();
    }

    public String getModuleName() {
        return moduleName;
    }

    protected void build() {
    }

    @Override
    public void getHtmlAttributes(Map<String, String> htmlAttributes) {
        super.getHtmlAttributes(htmlAttributes);
        StyleAttribute style = new StyleAttribute(htmlAttributes, "style");
        style.add("display", "none");
    }

    public Code add(JsFunction jsFunction) {
        functions.add(jsFunction);
        return this;
    }

    @Override
    public void renderJs(Toolkit toolkit, JsWriter js) throws JsRenderException {
        toolkit.registerCode(this);
        super.renderJs(toolkit, js);
    }

    @Override
    public void isolatedRenderJs(Toolkit toolkit, JsWriter js) {
        // note: ensure this is in its own Module to protect/prevent
        // leakage into other code

        js.append("var %s=(function(){", moduleName);

        Map<String, String> names = new HashMap<>();
        int i = 0;
        for (JsFunction jsFunction : functions) {
            String privateName = "f" + i;
            String publicName = jsFunction.getPublicName(privateName);
            names.put(privateName, publicName);
            // note: we are using public name internally as well
            js.append("const %s=", publicName);
            jsFunction.renderJs(toolkit, js);
            i++;
        }

        // export
        js.append("return {");
        int j = 0;
        for (String privateName : names.keySet()) {
            String publicName = names.get(privateName);
            if (j > 0) {
                js.append(",");
            }
            js.append("%s:%s", publicName, publicName);
            j++;
        }
        js.append("}");

        js.append("})();");
    }
}
