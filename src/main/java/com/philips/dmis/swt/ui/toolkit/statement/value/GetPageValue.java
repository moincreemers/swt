package com.philips.dmis.swt.ui.toolkit.statement.value;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.dto.Hash;
import com.philips.dmis.swt.ui.toolkit.js.JsParameter;
import com.philips.dmis.swt.ui.toolkit.js.JsType;
import com.philips.dmis.swt.ui.toolkit.js.JsWriter;
import com.philips.dmis.swt.ui.toolkit.reflect.DtoUtil;
import com.philips.dmis.swt.ui.toolkit.statement.Statement;
import com.philips.dmis.swt.ui.toolkit.widgets.Page;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;
import com.philips.dmis.swt.ui.toolkit.widgets.WidgetConfigurationException;

import java.util.List;

public class GetPageValue extends ValueStatement {
    private final Class<? extends Page> pageClass;
    private final ValueStatement valueStatement;

    public GetPageValue(Class<? extends Page> pageClass) {
        this(pageClass, null);
    }

    public GetPageValue(Class<? extends Page> pageClass, ValueStatement valueStatement) {
        this.pageClass = pageClass;
        this.valueStatement = valueStatement;
    }

    public Class<? extends Page> getPageClass() {
        return pageClass;
    }

    @Override
    public JsType getType() {
        return JsType.STRING;
    }

    @Override
    public List<JsParameter> getParameters() {
        return NO_PARAMETERS;
    }

    @Override
    public void renderJs(Toolkit toolkit, Widget widget, JsWriter js) {
        Page targetPage = toolkit.getPage(pageClass);
        Hash hash = new Hash(targetPage.getId(), widget.getPageId(),
                ValueStatement.valueOf(toolkit, valueStatement, widget));
        js.append("'/#'+new URLSearchParams(%s).toString()", DtoUtil.valueOf(hash));
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

    @Override
    public void getReferences(List<Statement> statements) {
        if (valueStatement != null) {
            statements.add(valueStatement);
        }
    }
}
