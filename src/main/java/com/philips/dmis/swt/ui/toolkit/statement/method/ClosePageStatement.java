package com.philips.dmis.swt.ui.toolkit.statement.method;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.js.JsParameter;
import com.philips.dmis.swt.ui.toolkit.js.JsType;
import com.philips.dmis.swt.ui.toolkit.js.JsWriter;
import com.philips.dmis.swt.ui.toolkit.js.global.GetDocumentHashFunction;
import com.philips.dmis.swt.ui.toolkit.js.global.JsGlobalModule;
import com.philips.dmis.swt.ui.toolkit.js.global.SetDocumentHashFunction;
import com.philips.dmis.swt.ui.toolkit.js.global.SetSessionValueFunction;
import com.philips.dmis.swt.ui.toolkit.statement.Statement;
import com.philips.dmis.swt.ui.toolkit.statement.value.ValueStatement;
import com.philips.dmis.swt.ui.toolkit.widgets.Page;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;
import com.philips.dmis.swt.ui.toolkit.widgets.WidgetConfigurationException;

import java.util.List;

public class ClosePageStatement extends MethodStatement {
    private final Class<? extends Page> defaultPageClass;
    private final ValueStatement valueStatement;
    private final boolean useDefaultPage;

    public ClosePageStatement() {
        this(null, null, true);
    }

    public ClosePageStatement(boolean useDefaultPage) {
        this(null, null, useDefaultPage);
    }

    public ClosePageStatement(ValueStatement valueStatement) {
        this(valueStatement, null, true);
    }

    public ClosePageStatement(ValueStatement valueStatement, boolean useDefaultPage) {
        this(valueStatement, null, useDefaultPage);
    }

    public ClosePageStatement(ValueStatement valueStatement, Class<? extends Page> defaultPageClass, boolean useDefaultPage) {
        this.valueStatement = valueStatement;
        this.defaultPageClass = defaultPageClass;
        this.useDefaultPage = useDefaultPage;
    }

    @Override
    public JsType getType() {
        return JsType.VOID;
    }

    @Override
    public List<JsParameter> getParameters() {
        return NO_PARAMETERS;
    }

    @Override
    public void renderJs(Toolkit toolkit, Widget widget, JsWriter js) {
        js.append("let h=%s();", JsGlobalModule.getQualifiedId(GetDocumentHashFunction.class));
        js.append("if(h.p.length==0){");
        js.throwError("no current page found", "h");
        js.append("};");
        js.append("if(h.o.length==0){");
        Class<? extends Page> defaultPageClass = this.defaultPageClass;
        ;
        if (useDefaultPage && defaultPageClass == null) {
            defaultPageClass = toolkit.getDefaultPage().getClass();
        }
        if (defaultPageClass == null) {
            js.throwError("no previous/default page found");
        } else {
            js.append("h.p=['%s'];", toolkit.getPage(defaultPageClass).getId());
        }
        js.append("}else{");
        js.append("h.p=h.o.pop();");
        js.append("};");
        if (valueStatement != null) {
            js.append("const data={value:%s};", ValueStatement.valueOf(toolkit, valueStatement, widget));
            js.append("data.type=typeof data.value;");
            js.append("const key=(new Date().getTime()).toString(16);");
            js.append("%s(key,JSON.stringify(data));", JsGlobalModule.getQualifiedId(SetSessionValueFunction.class));
            js.append("h.d.push(key);");
        }
        js.append("%s(h);", JsGlobalModule.getQualifiedId(SetDocumentHashFunction.class));
    }

    @Override
    public void validate(Toolkit toolkit) throws WidgetConfigurationException {
        if (validated) {
            return;
        }
        validated = true;
        if (defaultPageClass != null) {
            if (toolkit.getPage(defaultPageClass) == null) {
                throw new WidgetConfigurationException("missing default page reference " + defaultPageClass.getName());
            }
        }
        if (valueStatement != null) {
            valueStatement.validate(toolkit);
        }
    }

    @Override
    public void getReferences(List<Statement> statements) {
        if (valueStatement != null) {
            statements.add(valueStatement);
        }
    }
}
