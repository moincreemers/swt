package com.philips.dmis.swt.ui.toolkit.statement;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.js.JsParameter;
import com.philips.dmis.swt.ui.toolkit.js.JsType;
import com.philips.dmis.swt.ui.toolkit.js.JsWriter;
import com.philips.dmis.swt.ui.toolkit.widgets.JsRenderException;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;
import com.philips.dmis.swt.ui.toolkit.widgets.WidgetConfigurationException;

import java.util.ArrayList;
import java.util.List;

public abstract class Statement {
    public static final List<JsParameter> NO_PARAMETERS = new ArrayList<>();

    protected boolean validated;

    public abstract JsType getType();

    public abstract List<JsParameter> getParameters();

    public abstract void renderJs(Toolkit toolkit, Widget widget, JsWriter js) throws JsRenderException;

    public abstract void validate(Toolkit toolkit) throws WidgetConfigurationException;
}
