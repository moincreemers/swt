package com.philips.dmis.swt.ui.toolkit.data;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.dto.*;
import com.philips.dmis.swt.ui.toolkit.js.JsWriter;
import com.philips.dmis.swt.ui.toolkit.reflect.DtoUtil;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;

/**
 * Produces a view using the given Data Transfer Object (DTO).
 * The view Name is the simple class name of the DTO.
 */
public class DtoViewDataAdapter extends DataAdapter {
    private final Class<?> dto;
    private final ViewBuilder viewBuilder;

    public DtoViewDataAdapter(Class<?> dto) {
        this(DEFAULT_PATH, dto);
    }

    public DtoViewDataAdapter(String path, Class<?> dto) {
        super(path);
        this.dto = dto;
        viewBuilder = new ViewBuilder(dto);
    }

    public DtoViewDataAdapter setFormat(String nameOrSource, Format format) {
        viewBuilder.setFormat(nameOrSource, format);
        return this;
    }

    public DtoViewDataAdapter setAppearance(String nameOrSource, ViewAppearance appearance) {
        viewBuilder.setAppearance(nameOrSource, appearance);
        return this;
    }

    public DtoViewDataAdapter applyToAllFields(Format format) {
        viewBuilder.applyToAllFields(format);
        return this;
    }

    public DtoViewDataAdapter applyToAllFields(ViewAppearance appearance) {
        viewBuilder.applyToAllFields(appearance);
        return this;
    }

    public DtoViewDataAdapter setOrderSource(String nameOrSource, String orderSource) {
        viewBuilder.setOrderSource(nameOrSource, orderSource);
        return this;
    }

    public DtoViewDataAdapter addAction(String nameOrSource, String name) {
        viewBuilder.addAction(nameOrSource, name);
        return this;
    }

    @Override
    public void renderJs(Toolkit toolkit, Widget widget, JsWriter js) {
        js.append("(serviceResponse)=>{");

        js.debug("console.log('DtoViewDataAdapter, before',serviceResponse);");

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
