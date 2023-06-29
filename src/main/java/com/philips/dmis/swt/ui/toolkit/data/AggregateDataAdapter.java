package com.philips.dmis.swt.ui.toolkit.data;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.dto.*;
import com.philips.dmis.swt.ui.toolkit.js.JsWriter;
import com.philips.dmis.swt.ui.toolkit.js.global.AddViewFieldFunction;
import com.philips.dmis.swt.ui.toolkit.js.global.CreateViewFunction;
import com.philips.dmis.swt.ui.toolkit.js.global.IsObjectFunction;
import com.philips.dmis.swt.ui.toolkit.js.global.JsGlobalModule;
import com.philips.dmis.swt.ui.toolkit.reflect.DtoUtil;
import com.philips.dmis.swt.ui.toolkit.statement.aggregate.AggregateStatement;
import com.philips.dmis.swt.ui.toolkit.widgets.JsRenderException;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;

import java.util.ArrayList;
import java.util.List;

/**
 * Creates a new ServiceResponse that contains a new data set and adds a new view.
 * Records are added using an aggregate function. The aggregate is fed using the data
 * set from the given serviceResponse. Aggregates only work with numerical fields.
 * This data adapter does not modify the inbound ServiceResponse but outputs a new one.
 */
public class AggregateDataAdapter extends DataAdapter {
    private interface Field {
        String getName();

        String getField();

        DataType getFormatType();

        Format getFormat();

        void renderJs(Toolkit toolkit, Widget widget, JsWriter js);
    }

    private static class AggregateField implements Field {
        final String name;
        final String field;
        final Format format;
        final AggregateStatement aggregateStatement;

        @Override
        public String getName() {
            return name;
        }

        @Override
        public String getField() {
            return field;
        }

        @Override
        public DataType getFormatType() {
            return format == null ? DataType.NUMBER : format.getFormatType();
        }

        @Override
        public Format getFormat() {
            return format;
        }

        public AggregateStatement getAggregateStatement() {
            return aggregateStatement;
        }

        public AggregateField(String name, String field, Format format, AggregateStatement aggregateStatement) {
            this.name = name;
            this.field = field;
            this.format = format;
            this.aggregateStatement = aggregateStatement;
        }

        @Override
        public void renderJs(Toolkit toolkit, Widget widget, JsWriter js) {
            js.append("{");
            js.append("field:'%s',", field);
            js.append("fn:");
            aggregateStatement.renderJs(toolkit, widget, js);
            js.append("}");
        }
    }

    private final List<AggregateField> aggregateFields = new ArrayList<>();
    private final boolean inheritSelectedView;

    public AggregateDataAdapter(boolean inheritSelectedView) {
        this(DEFAULT_PATH, inheritSelectedView);
    }

    public AggregateDataAdapter(String path, boolean inheritSelectedView) {
        super(path);
        this.inheritSelectedView = inheritSelectedView;
    }

    public AggregateDataAdapter addAggregate(String field, Format format, AggregateStatement aggregateStatement) {
        return addAggregate(field, field, format, aggregateStatement);
    }

    public AggregateDataAdapter addAggregate(String name, String field, Format format, AggregateStatement aggregateStatement) {
        if (field == null || field.isEmpty()) {
            throw new IllegalArgumentException("invalid field");
        }
        aggregateFields.add(new AggregateField(name, field, format, aggregateStatement));
        return this;
    }

    @Override
    public void renderJs(Toolkit toolkit, Widget widget, JsWriter js) throws JsRenderException {
        js.append("(serviceResponse,unmodifiedResponse)=>{");
        js.trace(this);

        js.append("const eventContext=%s;", DtoUtil.getDefault(EventContext.class, false));

        // re-create data structure
        js.append("const output=structuredClone(serviceResponse);");
        js.append("var m=output%s;", getPath());
        js.append("m.length=0;");

        js.append("const aggregateArrays={};");
        js.append("const aggregateFields=[];");

        String rootViewId = "view-" + getId();
        js.append("const viewTop=%s(output,'%s','%s',%s);",
                JsGlobalModule.getQualifiedId(CreateViewFunction.class),
                rootViewId,
                getClass().getSimpleName(),
                inheritSelectedView ? "true" : "false");

        // render aggregate fields
        for (AggregateField aggregateField : aggregateFields) {
            js.append("const af=");
            aggregateField.renderJs(toolkit, widget, js);
            js.append(";");
            js.append("aggregateFields.push(af);");
            js.append("aggregateArrays[af.field]=[];");

            js.append("%s(output,viewTop,'%s','%s','%s',%s,'%s','%s',%s);",
                    JsGlobalModule.getQualifiedId(AddViewFieldFunction.class),
                    aggregateField.getName(),
                    aggregateField.getField(),
                    aggregateField.getFormatType(),
                    Format.valueOf(aggregateField.getFormat()),
                    DataType.NUMBER,
                    ViewAppearance.DEFAULT.name(),
                    inheritSelectedView ? "true" : "false");
        }

        // the new row
        js.append("var newRow=null;");

        // get data array from path
        js.append("const data=Object.assign([],serviceResponse%s);", getPath());
        js.debug("console.log('AggregateDataAdapter data from path',data)");

        // EXIT
        js.append("if(data.length==0){");
        js.debug("console.log('AggregateDataAdapter exit');");
        js.append("return output;");
        js.append("};");

        js.append("for(var row=0;row<data.length;row++){"); // for
        js.append("if(!%s(data[row])){", // if
                JsGlobalModule.getQualifiedId(IsObjectFunction.class));
        js.append("continue;");
        js.append("};");
        js.append("const rowData=data[row];");

        // ensure the new row has the same properties
        js.append("if(newRow==null){");
        js.append("newRow=structuredClone(rowData);");
        js.append("for(const c in newRow){newRow[c]='';};");
        js.append("};");

        js.append("");

        // filter operation

        js.append("for(const a in aggregateFields){"); // for
        js.append("const aggregate=aggregateFields[a];");
        js.append("const value=rowData[aggregate.field];");
        js.append("aggregateArrays[aggregate.field].push(value);");
        js.append("};"); // end for

        // end filter operation

        js.append("};"); // end for

        // process aggregates
        js.append("for(const a in aggregateFields){"); // for
        js.append("const aggregate=aggregateFields[a];");
        js.append("newRow[aggregate.field]=aggregate.fn(aggregateArrays[aggregate.field]);");
        js.append("};"); // end for


        // add row to output
        js.append("m.push(newRow);");

        js.append("output.meta['%s']=Object.assign([],output.meta['%s']);",
                ServiceResponse.META_TRANSFORMATIONS, ServiceResponse.META_TRANSFORMATIONS);
        js.append("output.meta['%s'].push(%s);",
                ServiceResponse.META_TRANSFORMATIONS,
                DtoUtil.valueOf(new TransformationMetadata(getId(), getClass().getSimpleName())));

        js.debug("console.log('AggregateDataAdapter after',output);");
        js.append("return output;");

        js.append("}"); // end function
    }
}
