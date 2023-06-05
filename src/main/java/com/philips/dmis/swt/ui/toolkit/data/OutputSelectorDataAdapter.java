package com.philips.dmis.swt.ui.toolkit.data;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.dto.ServiceResponse;
import com.philips.dmis.swt.ui.toolkit.dto.TransformationMetadata;
import com.philips.dmis.swt.ui.toolkit.dto.View;
import com.philips.dmis.swt.ui.toolkit.js.JsWriter;
import com.philips.dmis.swt.ui.toolkit.reflect.DtoUtil;
import com.philips.dmis.swt.ui.toolkit.widgets.JsRenderException;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;

/**
 * This data adapter 'selects' an alternate array and makes it the primary.
 * Note that the primary data source is always ".data.items" but using the ImportDataAdapter,
 * it is possible to import a secondary data set using a different output name.
 */
public class OutputSelectorDataAdapter extends DataAdapter {
    private final String viewId;

    public OutputSelectorDataAdapter(ImportArrayDataAdapter importArrayDataAdapter) {
        this(importArrayDataAdapter.getOutputPath(), View.getRootViewId(importArrayDataAdapter.getId()));
    }

    public OutputSelectorDataAdapter(String path, String viewId) {
        super(path);
        if (DEFAULT_PATH.equals(path)) {
            throw new IllegalArgumentException("expected a different path than: " + DEFAULT_PATH);
        }
        this.viewId = viewId;
    }

    @Override
    public void renderJs(Toolkit toolkit, Widget widget, JsWriter js) throws JsRenderException {
        js.append("(serviceResponse,unmodifiedResponse)=>{");
        js.trace(this);

        // get data array from path
        js.append("const output=structuredClone(serviceResponse);");
        js.append("output%s=output%s;", DEFAULT_PATH, getPath());
        js.append("output%s=null;", getPath());

        if (viewId != null && !viewId.isEmpty()) {
            js.append("output.meta['%s']='%s';",
                    ServiceResponse.META_SELECTED_VIEW_ID,
                    viewId);
        }

        js.append("output.meta['%s']=Object.assign([],output.meta['%s']);",
                ServiceResponse.META_TRANSFORMATIONS, ServiceResponse.META_TRANSFORMATIONS);
        js.append("output.meta['%s'].push(%s);",
                ServiceResponse.META_TRANSFORMATIONS,
                DtoUtil.valueOf(new TransformationMetadata(getId(), getClass().getSimpleName())));

        js.debug("console.log('OutputSelectorDataAdapter after',output);");
        js.append("return output;");

        js.append("}"); // end function
    }
}
