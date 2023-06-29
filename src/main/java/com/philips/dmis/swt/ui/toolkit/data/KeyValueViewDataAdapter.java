package com.philips.dmis.swt.ui.toolkit.data;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.dto.*;
import com.philips.dmis.swt.ui.toolkit.js.JsWriter;
import com.philips.dmis.swt.ui.toolkit.reflect.DtoUtil;
import com.philips.dmis.swt.ui.toolkit.widgets.DataSourceUsage;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;

/**
 * Produces a view for data created with the DataBuilder.
 */
public class KeyValueViewDataAdapter extends DataAdapter {
    static class KeyValue {
        public String key;
        public String value;
    }

    private final ViewBuilder viewBuilder;

    public KeyValueViewDataAdapter() {
        super(DEFAULT_PATH);
        viewBuilder = new ViewBuilder(KeyValue.class);
    }

    public KeyValueViewDataAdapter setFormat(String nameOrSource, Format format) {
        viewBuilder.setFormat(nameOrSource, format);
        return this;
    }

    public KeyValueViewDataAdapter setAppearance(String nameOrSource, ViewAppearance appearance) {
        viewBuilder.setAppearance(nameOrSource, appearance);
        return this;
    }

    public KeyValueViewDataAdapter applyToAllFields(Format format) {
        viewBuilder.applyToAllFields(format);
        return this;
    }

    public KeyValueViewDataAdapter applyToAllFields(ViewAppearance appearance) {
        viewBuilder.applyToAllFields(appearance);
        return this;
    }

    public KeyValueViewDataAdapter setOrderSource(String nameOrSource, String orderSource) {
        viewBuilder.setOrderSource(nameOrSource, orderSource);
        return this;
    }

    public KeyValueViewDataAdapter addAction(String nameOrSource, String name) {
        viewBuilder.addAction(nameOrSource, name);
        return this;
    }

    @Override
    public DataSourceUsage getInitialDataSourceUsage() {
        return DataSourceUsage.VIEW;
    }

    @Override
    public void renderJs(Toolkit toolkit, Widget widget, JsWriter js) {
        js.append("(serviceResponse,unmodifiedResponse)=>{");
        js.trace(this);

        for (View view : viewBuilder.getViews()) {
            js.append("var view=");
            js.append(DtoUtil.valueOf(view));
            js.append(";");
            js.append("serviceResponse.views[view.id]=view;");
        }
        js.append("serviceResponse.meta['%s']='%s';",
                ServiceResponse.META_DEFAULT_VIEW_ID,
                viewBuilder.getId());
        js.append("serviceResponse.meta['%s']='%s';",
                ServiceResponse.META_SELECTED_VIEW_ID,
                viewBuilder.getId());

        js.append("serviceResponse.meta['%s']=Object.assign([],serviceResponse.meta['%s']);",
                ServiceResponse.META_TRANSFORMATIONS, ServiceResponse.META_TRANSFORMATIONS);
        js.append("serviceResponse.meta['%s'].push(%s);",
                ServiceResponse.META_TRANSFORMATIONS,
                DtoUtil.valueOf(new TransformationMetadata(getId(), getClass().getSimpleName())));

        js.debug("console.log('DtoViewDataAdapter, after',serviceResponse);");

        js.append("return serviceResponse;");

        js.append("}"); // end function
    }
}
