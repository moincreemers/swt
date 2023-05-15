package com.philips.dmis.swt.ui.toolkit.statement.method;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.dto.Hash;
import com.philips.dmis.swt.ui.toolkit.js.JsParameter;
import com.philips.dmis.swt.ui.toolkit.js.JsType;
import com.philips.dmis.swt.ui.toolkit.js.JsWriter;
import com.philips.dmis.swt.ui.toolkit.js.global.JsGlobalModule;
import com.philips.dmis.swt.ui.toolkit.js.global.SetDocumentHashFunction;
import com.philips.dmis.swt.ui.toolkit.js.global.SetSessionValueFunction;
import com.philips.dmis.swt.ui.toolkit.reflect.DtoUtil;
import com.philips.dmis.swt.ui.toolkit.statement.value.ValueStatement;
import com.philips.dmis.swt.ui.toolkit.widgets.Page;
import com.philips.dmis.swt.ui.toolkit.widgets.ViewType;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;
import com.philips.dmis.swt.ui.toolkit.widgets.WidgetConfigurationException;

import java.util.List;

public class OpenPageStatement extends MethodStatement {
    private final Class<? extends Page> pageClass;
    private final ValueStatement valueStatement;

    public OpenPageStatement(Class<? extends Page> pageClass) {
        this(pageClass, null);
    }

    public OpenPageStatement(Class<? extends Page> pageClass, ValueStatement valueStatement) {
        this.pageClass = pageClass;
        this.valueStatement = valueStatement;
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
        Page targetPage = toolkit.getPage(pageClass);
        boolean isDialog = targetPage.getViewType() == ViewType.DIALOG
                || targetPage.getViewType() == ViewType.SIDEBAR_DIALOG;
        Hash hash = new Hash(targetPage.getId(), widget.getPageId(), null);
        js.append("const hash=%s;", DtoUtil.valueOf(hash));
        js.append("const data={value:%s};", ValueStatement.valueOf(toolkit, valueStatement, widget));
        //js.append("if(data.value!=null){"); // if
        js.append("data.type=typeof data.value;");
        js.append("const key=(new Date().getTime()).toString(16);");
        js.append("%s(key,JSON.stringify(data));", JsGlobalModule.getQualifiedId(SetSessionValueFunction.class));
        //js.append("};");
        js.append("hash.d.push(key);");
        js.append("%s(hash,%s);",
                JsGlobalModule.getQualifiedId(SetDocumentHashFunction.class),
                isDialog ? "true" : "false");
    }

    @Override
    public void validate(Toolkit toolkit) throws WidgetConfigurationException {
        if (validated) {
            return;
        }
        validated = true;
        if (toolkit.getPage(pageClass) == null) {
            throw new WidgetConfigurationException("missing page reference " + pageClass.getName());
        }
        if (valueStatement != null) {
            valueStatement.validate(toolkit);
        }
    }
}
