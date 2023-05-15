package com.philips.dmis.swt.ui.toolkit.data;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.dto.ServiceResponse;
import com.philips.dmis.swt.ui.toolkit.dto.TransformationMetadata;
import com.philips.dmis.swt.ui.toolkit.js.JsWriter;
import com.philips.dmis.swt.ui.toolkit.reflect.DtoUtil;
import com.philips.dmis.swt.ui.toolkit.statement.mapper.MapStatement;
import com.philips.dmis.swt.ui.toolkit.widgets.JsRenderException;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;

import java.util.ArrayList;
import java.util.List;

/**
 * This data adapter modifies the schema of the data.
 */
public class ModifySchemaAdapter extends DataAdapter {
    private static class MappedField {
        final String field;
        final MapStatement mapStatement;

        public MappedField(String field, MapStatement mapStatement) {
            this.field = field;
            this.mapStatement = mapStatement;
        }
    }

    private final List<MappedField> mappedFields = new ArrayList<>();

    public ModifySchemaAdapter() {
        super(DEFAULT_PATH);
    }

    public ModifySchemaAdapter map(String field, MapStatement mapStatement) {
        if (field == null || field.isEmpty()) {
            throw new IllegalArgumentException("invalid field");
        }
        mappedFields.add(new MappedField(field, mapStatement));
        return this;
    }

    @Override
    public void renderJs(Toolkit toolkit, Widget widget, JsWriter js) throws JsRenderException {
        js.append("(serviceResponse)=>{");

        js.debug("console.log('ModifySchemaAdapter before',serviceResponse);");

        // get data array from path
        js.append("const items=serviceResponse%s;", getPath());

        // EXIT
        js.append("if(data.length==0){");
        js.debug("console.log('AggregateDataAdapter exit');");
        js.append("return serviceResponse;");
        js.append("};");

        js.append("for(const row in data){"); // for
        js.append("var item=data[row];");

        // todo: implement

//        for (MappedField mappedField : mappedFields) {
//
//        }
        js.append("};"); // end for

        js.append("serviceResponse.meta['%s']=Object.assign([],serviceResponse.meta['%s']);",
                ServiceResponse.META_TRANSFORMATIONS, ServiceResponse.META_TRANSFORMATIONS);
        js.append("serviceResponse.meta['%s'].push(%s);",
                ServiceResponse.META_TRANSFORMATIONS,
                DtoUtil.valueOf(new TransformationMetadata(getId(), getClass().getSimpleName())));

        js.debug("console.log('ModifySchemaAdapter after',serviceResponse);");
        js.append("return serviceResponse;");

        js.append("}"); // end function
    }
}
