package com.philips.dmis.swt.ui.toolkit.data;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.js.JsWriter;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;

public class DebugDataAdapter extends DataAdapter {
    private final String name;

    public DebugDataAdapter(String name) {
        super(DEFAULT_PATH);
        this.name = name;
    }

    @Override
    public void renderJs(Toolkit toolkit, Widget widget, JsWriter js) {
        js.append("(serviceResponse,unmodifiedResponse)=>{");
        js.trace(this);

        js.info("console.log('*********** %s ***********');", name);
        js.append("return serviceResponse;");

        js.append("}"); // end function
    }
}
