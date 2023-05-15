package com.philips.dmis.swt.ui.toolkit.statement.value;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.js.JsParameter;
import com.philips.dmis.swt.ui.toolkit.js.JsType;
import com.philips.dmis.swt.ui.toolkit.js.JsWriter;
import com.philips.dmis.swt.ui.toolkit.js.global.JsGlobalModule;
import com.philips.dmis.swt.ui.toolkit.js.global.ResolveObjectMemberOrPath;
import com.philips.dmis.swt.ui.toolkit.statement.Statement;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;
import com.philips.dmis.swt.ui.toolkit.widgets.WidgetConfigurationException;

import java.util.List;

public class ObjectMemberValue extends ValueStatement {
    private final ValueStatement object;
    private final ValueStatement memberNameOrPath;

    public ObjectMemberValue(ValueStatement object, ValueStatement memberNameOrPath) {
        this.object = object;
        this.memberNameOrPath = memberNameOrPath;
    }

    @Override
    public JsType getType() {
        return JsType.NUMBER;
    }

    @Override
    public List<JsParameter> getParameters() {
        return NO_PARAMETERS;
    }

    @Override
    public void renderJs(Toolkit toolkit, Widget widget, JsWriter js) {
        js.append("%s(", JsGlobalModule.getQualifiedId(ResolveObjectMemberOrPath.class));
        object.renderJs(toolkit, widget, js);
        js.append(",");
        memberNameOrPath.renderJs(toolkit, widget, js);
        js.append(")");
    }

    @Override
    public void validate(Toolkit toolkit) throws WidgetConfigurationException {
        if (validated) {
            return;
        }
        validated = true;
        object.validate(toolkit);
        memberNameOrPath.validate(toolkit);
    }

    @Override
    public void getReferences(List<Statement> statements) {
        statements.add(object);
        statements.add(memberNameOrPath);
    }
}
