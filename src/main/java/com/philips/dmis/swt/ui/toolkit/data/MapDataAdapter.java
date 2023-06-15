package com.philips.dmis.swt.ui.toolkit.data;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.dto.EventContext;
import com.philips.dmis.swt.ui.toolkit.dto.ServiceResponse;
import com.philips.dmis.swt.ui.toolkit.dto.TransformationMetadata;
import com.philips.dmis.swt.ui.toolkit.js.JsWriter;
import com.philips.dmis.swt.ui.toolkit.reflect.DtoUtil;
import com.philips.dmis.swt.ui.toolkit.statement.mapper.MapStatement;
import com.philips.dmis.swt.ui.toolkit.widgets.JsRenderException;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This data adapter uses Map statements to convert or 'map' values.
 */
public class MapDataAdapter extends DataAdapter {
    private static class MappedField {
        final List<String> sourceFields = new ArrayList<>();
        final String targetField;
        final MapStatement mapStatement;

        public MappedField(String targetField, MapStatement mapStatement) {
            this.sourceFields.add(targetField);
            this.targetField = targetField;
            this.mapStatement = mapStatement;
        }

        public MappedField(String targetField, MapStatement mapStatement, String... sourceFields) {
            this.sourceFields.addAll(Arrays.asList(sourceFields));
            this.targetField = targetField;
            this.mapStatement = mapStatement;
        }
    }

    private final List<MappedField> mappedFields = new ArrayList<>();

    public MapDataAdapter() {
        super(DEFAULT_PATH);
    }

    public MapDataAdapter map(String field, MapStatement mapStatement) {
        if (field == null || field.isEmpty()) {
            throw new IllegalArgumentException("invalid field");
        }
        mappedFields.add(new MappedField(field, mapStatement));
        return this;
    }

    public MapDataAdapter map(String targetField, MapStatement mapStatement, String... sourceFields) {
        if (targetField == null || targetField.isEmpty()) {
            throw new IllegalArgumentException("invalid target field");
        }
        if (sourceFields == null || sourceFields.length == 0) {
            throw new IllegalArgumentException("invalid source field");
        }
        mappedFields.add(new MappedField(targetField, mapStatement, sourceFields));
        return this;
    }

    @Override
    public void renderJs(Toolkit toolkit, Widget widget, JsWriter js) throws JsRenderException {
        js.append("(serviceResponse,unmodifiedResponse)=>{");
        js.trace(this);

        js.append("const eventContext=%s;", DtoUtil.getDefault(EventContext.class, false));

        // get data array from path
        js.append("const data=Object.assign([],serviceResponse%s);", getPath());

        // EXIT
        js.append("if(data.length==0){");
        js.debug("console.log('AggregateDataAdapter exit');");
        js.append("return serviceResponse;");
        js.append("};");

        js.append("for(const row in data){"); // for
        js.append("var item=data[row];");
        for (MappedField mappedField : mappedFields) {
            js.append("var fn=%s;", MapStatement.valueOf(toolkit, mappedField.mapStatement, widget));
            js.append("try{");
            js.append("item['%s']=fn(", mappedField.targetField);
            js.append("serviceResponse,");
            js.append("'%s',", mappedField.targetField);
            js.append("{");
            int i = 0;
            for (String sourceField : mappedField.sourceFields) {
                if (i > 0) {
                    js.append(",");
                }
                js.append("'%s':item['%s']", sourceField, sourceField);
                i++;
            }
            js.append("}");
            for (String sourceField : mappedField.sourceFields) {
                js.append(",item['%s']", sourceField);
            }
            js.append(");");
            js.append("}catch(e){");
            js.append("console.log('error',e);");
            js.append("item['%s']=null;", mappedField.targetField);
            js.append("};");
        }
        js.append("};"); // end for

        js.append("serviceResponse.meta['%s']=Object.assign([],serviceResponse.meta['%s']);",
                ServiceResponse.META_TRANSFORMATIONS, ServiceResponse.META_TRANSFORMATIONS);
        js.append("serviceResponse.meta['%s'].push(%s);",
                ServiceResponse.META_TRANSFORMATIONS,
                DtoUtil.valueOf(new TransformationMetadata(getId(), getClass().getSimpleName())));

        js.debug("console.log('ValueParserDataAdapter after',serviceResponse);");
        js.append("return serviceResponse;");

        js.append("}"); // end function
    }
}
