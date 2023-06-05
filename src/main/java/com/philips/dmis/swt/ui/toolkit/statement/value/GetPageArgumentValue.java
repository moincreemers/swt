package com.philips.dmis.swt.ui.toolkit.statement.value;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.js.JsParameter;
import com.philips.dmis.swt.ui.toolkit.js.JsType;
import com.philips.dmis.swt.ui.toolkit.js.JsWriter;
import com.philips.dmis.swt.ui.toolkit.js.global.GetDocumentHashFunction;
import com.philips.dmis.swt.ui.toolkit.js.global.GetSessionValueFunction;
import com.philips.dmis.swt.ui.toolkit.js.global.JsGlobalModule;
import com.philips.dmis.swt.ui.toolkit.statement.Statement;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;
import com.philips.dmis.swt.ui.toolkit.widgets.WidgetConfigurationException;

import java.util.List;

public class GetPageArgumentValue extends ValueStatement {
    @Override
    public void renderJs(Toolkit toolkit, Widget widget, JsWriter js) {

        // todo: this does not always work, because d is an array
        js.append("JSON.parse(%s(%s().d,null)).value",
                JsGlobalModule.getQualifiedId(GetSessionValueFunction.class),
                JsGlobalModule.getQualifiedId(GetDocumentHashFunction.class));
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
    public void validate(Toolkit toolkit) throws WidgetConfigurationException {
        if (validated) {
            return;
        }
        validated = true;
    }

    @Override
    public void getReferences(List<Statement> statements) {
    }
}
