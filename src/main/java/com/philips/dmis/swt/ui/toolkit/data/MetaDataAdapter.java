package com.philips.dmis.swt.ui.toolkit.data;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.dto.ServiceResponse;
import com.philips.dmis.swt.ui.toolkit.dto.TransformationMetadata;
import com.philips.dmis.swt.ui.toolkit.js.JsWriter;
import com.philips.dmis.swt.ui.toolkit.reflect.DtoUtil;
import com.philips.dmis.swt.ui.toolkit.statement.mapper.MapStatement;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given a service response object provided by a data source,
 * this Data Adapter extracts values from the response and stores
 * them as meta data entries.
 */
public class MetaDataAdapter extends DataAdapter {
    private static class MappedField {
        final String field;
        final FieldMapping fieldMapping;
        final List<MapStatement> mapStatements = new ArrayList<>();

        public MappedField(String field, FieldMapping fieldMapping, MapStatement... mapStatements) {
            this.field = field;
            this.fieldMapping = fieldMapping;
            this.mapStatements.addAll(Arrays.asList(mapStatements));
        }
    }

    private final List<MappedField> mappedFields = new ArrayList<>();
    private final String metaDataProperty;

    public MetaDataAdapter(String metaDataProperty) {
        this(DEFAULT_PATH, metaDataProperty);
    }

    public MetaDataAdapter(String path, String metaDataProperty) {
        super(path);
        this.metaDataProperty = metaDataProperty;
    }

    public MetaDataAdapter map(String field, FieldMapping fieldMapping, MapStatement... mapStatements) {
        if (field == null || field.isEmpty()) {
            throw new IllegalArgumentException("invalid field");
        }
        mappedFields.add(new MappedField(field, fieldMapping, mapStatements));
        return this;
    }

    @Override
    public void renderJs(Toolkit toolkit, Widget widget, JsWriter js) {
        js.append("(serviceResponse,unmodifiedResponse)=>{"); // begin function
        js.trace(this);

        js.append("const obj=unmodifiedResponse%s;", getPath());
        js.append("if(obj==null||obj==undefined){");
        js.throwError("path not found", "unmodifiedResponse");
        js.append("};");

        js.append("serviceResponse.meta['%s']={};", metaDataProperty);

        for (MappedField mappedField : mappedFields) {
            js.append("var s=obj;");
            String[] fieldsInPath = mappedField.fieldMapping.getFrom().substring(1).split("\\.");
            for (String fieldInPath : fieldsInPath) {
                js.append("if(s!==null&&s.hasOwnProperty('%s')){s=s.%s;}", fieldInPath, fieldInPath);
                js.append("else{s=null;}");
            }
            js.append("if(s!=null){"); // if
            for (MapStatement mapStatement : mappedField.mapStatements) {
                // todo
            }
            js.append("};"); // end if
            js.append("serviceResponse.meta['%s']['%s']=s;",
                    metaDataProperty, mappedField.field, mappedField.field);
        }

        js.append("serviceResponse.meta['%s']=Object.assign([],serviceResponse.meta['%s']);",
                ServiceResponse.META_TRANSFORMATIONS, ServiceResponse.META_TRANSFORMATIONS);
        js.append("serviceResponse.meta['%s'].push(%s);",
                ServiceResponse.META_TRANSFORMATIONS,
                DtoUtil.valueOf(new TransformationMetadata(getId(), getClass().getSimpleName())));

        js.debug("console.log('MetaDataAdapter, after',serviceResponse);");

        js.append("return serviceResponse;");

        js.append("}"); // end function
    }
}
