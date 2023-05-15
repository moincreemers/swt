package com.philips.dmis.swt.ui.toolkit.data;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.dto.*;
import com.philips.dmis.swt.ui.toolkit.js.JsType;
import com.philips.dmis.swt.ui.toolkit.js.JsWriter;
import com.philips.dmis.swt.ui.toolkit.js.global.AddViewFieldFunction;
import com.philips.dmis.swt.ui.toolkit.js.global.CreateViewFunction;
import com.philips.dmis.swt.ui.toolkit.js.global.IsObjectFunction;
import com.philips.dmis.swt.ui.toolkit.js.global.JsGlobalModule;
import com.philips.dmis.swt.ui.toolkit.reflect.DtoUtil;
import com.philips.dmis.swt.ui.toolkit.statement.aggregate.AggregateStatement;
import com.philips.dmis.swt.ui.toolkit.statement.reduce.ReducerStatement;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;

import java.util.ArrayList;
import java.util.List;

/**
 * Creates a new ServiceResponse that contains a new data set and adds a new view.
 * Groups data by one column and adds zero or more aggregate columns to the view.
 * This data adapter does not modify the inbound ServiceResponse but outputs a new one.
 */
public class GroupByDataAdapter extends DataAdapter {
    private interface Field {
        String getName();

        String getField();

        JsType getType();

        DataType getFormatType();

        Format getFormat();

        void renderJs(Toolkit toolkit, Widget widget, JsWriter js);
    }

    private static class GroupByField implements Field {
        final String name;
        final String field;
        final JsType type;
        final Format format;

        @Override
        public String getName() {
            return name;
        }

        @Override
        public String getField() {
            return field;
        }

        @Override
        public JsType getType() {
            return type;
        }

        @Override
        public DataType getFormatType() {
            return format == null ? DataType.fromJsType(type) : format.getFormatType();
        }

        @Override
        public Format getFormat() {
            return format;
        }

        public GroupByField(String name, String field, Format format, JsType type) {
            this.name = name;
            this.field = field;
            this.format = format;
            this.type = type;
        }

        @Override
        public void renderJs(Toolkit toolkit, Widget widget, JsWriter js) {
            js.append("'");
            js.append(field);
            js.append("'");
        }
    }

    private static class AggregateField implements Field {
        final String name;
        final String field;
        final Format format;
        final ReducerStatement reducerStatement;
        final AggregateStatement aggregateStatement;
        final String functionType;

        @Override
        public String getName() {
            return name;
        }

        @Override
        public String getField() {
            return field;
        }

        @Override
        public JsType getType() {
            return JsType.NUMBER;
        }

        @Override
        public DataType getFormatType() {
            return format == null ? DataType.NUMBER : format.getFormatType();
        }

        @Override
        public Format getFormat() {
            return format;
        }

        public ReducerStatement getReducerStatement() {
            return reducerStatement;
        }

        public AggregateStatement getAggregateStatement() {
            return aggregateStatement;
        }

        public AggregateField(String name, String field, Format format, ReducerStatement reducerStatement) {
            this.name = name;
            this.field = field;
            this.format = format;
            this.reducerStatement = reducerStatement;
            this.aggregateStatement = null;
            this.functionType = "reducer";
        }

        public AggregateField(String name, String field, Format format, AggregateStatement aggregateStatement) {
            this.name = name;
            this.field = field;
            this.format = format;
            this.reducerStatement = null;
            this.aggregateStatement = aggregateStatement;
            this.functionType = "aggregate";
        }

        @Override
        public void renderJs(Toolkit toolkit, Widget widget, JsWriter js) {
            js.append("{");
            js.append("field:'%s',", field);
            js.append("functionType:'%s',", functionType);
            js.append("fn:");
            if (reducerStatement != null) {
                reducerStatement.renderJs(toolkit, widget, js);
            } else if (aggregateStatement != null) {
                aggregateStatement.renderJs(toolkit, widget, js);
            } else {
                js.append("null");
            }
            js.append("}");
        }
    }

    private final List<GroupByField> groupByFields = new ArrayList<>();
    private final List<AggregateField> aggregateFields = new ArrayList<>();

    public GroupByDataAdapter() {
        this(DEFAULT_PATH);
    }

    public GroupByDataAdapter(String path) {
        super(path);
    }

    public GroupByDataAdapter addGroupBy(String field, JsType type, Format format) {
        return addGroupBy(field, field, format, type);
    }

    public GroupByDataAdapter addGroupBy(String name, String field, Format format, JsType type) {
        if (field == null || field.isEmpty()) {
            throw new IllegalArgumentException("invalid field");
        }
        groupByFields.add(new GroupByField(name, field, format, type));
        return this;
    }

    public GroupByDataAdapter addAggregate(String field, Format format, ReducerStatement reducerStatement) {
        return addAggregate(field, field, format, reducerStatement);
    }

    public GroupByDataAdapter addAggregate(String name, String field, Format format, ReducerStatement reducerStatement) {
        if (field == null || field.isEmpty()) {
            throw new IllegalArgumentException("invalid field");
        }
        aggregateFields.add(new AggregateField(name, field, format, reducerStatement));
        return this;
    }

    public GroupByDataAdapter addAggregate(String field, Format format, AggregateStatement aggregateStatement) {
        return addAggregate(field, field, format, aggregateStatement);
    }

    public GroupByDataAdapter addAggregate(String name, String field, Format format, AggregateStatement aggregateStatement) {
        if (field == null || field.isEmpty()) {
            throw new IllegalArgumentException("invalid field");
        }
        aggregateFields.add(new AggregateField(name, field, format, aggregateStatement));
        return this;
    }

    @Override
    public void renderJs(Toolkit toolkit, Widget widget, JsWriter js) {
        js.append("(serviceResponse)=>{");

        js.info("console.log('GroupByDataAdapter before',serviceResponse);");

        // re-create data structure
        js.append("const output=structuredClone(serviceResponse);");
        js.append("var m=output%s;", getPath());
        js.append("m.length=0;");

        js.append("const groups={};");
        js.append("const aggregateArrays={};");
        js.append("const rowTemplate={};");
        js.append("const groupByFields=[];");
        js.append("const aggregateFields=[];");

        String rootViewId = "view-" + getId();
        js.append("const viewTop=%s(output,'%s','%s',false);",
                JsGlobalModule.getQualifiedId(CreateViewFunction.class),
                rootViewId,
                getClass().getSimpleName());

        // render group by fields
        for (GroupByField groupByField : groupByFields) {
            js.append("var gf=");
            groupByField.renderJs(toolkit, widget, js);
            js.append(";");
            js.append("groupByFields.push(gf);");
            js.append("rowTemplate[gf]=null;");

            js.append("%s(output,viewTop,'%s','%s','%s',%s,'%s','%s',false);",
                    JsGlobalModule.getQualifiedId(AddViewFieldFunction.class),
                    groupByField.getName(),
                    groupByField.getField(),
                    groupByField.getFormatType(),
                    Format.valueOf(groupByField.getFormat()),
                    DataType.fromJsType(groupByField.getType()).name(),
                    ViewAppearance.DEFAULT.name());

            // note: only one group supported right now
            break;
        }

        js.info("console.log('groupByFields',groupByFields);");

        // render aggregate fields
        for (AggregateField aggregateField : aggregateFields) {
            js.append("const af=");
            aggregateField.renderJs(toolkit, widget, js);
            js.append(";");
            js.append("aggregateFields.push(af);");

            js.append("%s(output,viewTop,'%s','%s','%s',%s,'%s','%s',false);",
                    JsGlobalModule.getQualifiedId(AddViewFieldFunction.class),
                    aggregateField.getName(),
                    aggregateField.getField(),
                    aggregateField.getFormatType(),
                    Format.valueOf(aggregateField.getFormat()),
                    DataType.fromJsType(aggregateField.getType()).name(),
                    ViewAppearance.DEFAULT.name());

            if (aggregateField.getReducerStatement() != null) {
                // add a property for every aggregate to the template
                // and set the initial value.
                Object initialValue = aggregateField.getReducerStatement().getInitialValue();
                String initialValueString = initialValue.toString();
                if (aggregateField.getReducerStatement().getType() == JsType.STRING) {
                    initialValueString = "'" + initialValueString + "'";
                }
                js.append("rowTemplate[af.field]=%s;", initialValueString);
            } else {
                js.append("rowTemplate[af.field]='';");
            }

            js.append("aggregateArrays[af.field]=[];");
        }

        js.info("console.log('aggregateFields',aggregateFields);");


        // get data array from path
        js.append("const data=Object.assign([],serviceResponse%s);", getPath());
        js.debug("console.log('GroupByDataAdapter data from path',data)");

        // EXIT
        js.append("if(data.length==0){");
        js.debug("console.log('GroupByDataAdapter exit');");
        js.append("return output;");
        js.append("};");

        js.append("for(var row=0;row<data.length;row++){"); // for
        js.append("if(!%s(data[row])){", // if
                JsGlobalModule.getQualifiedId(IsObjectFunction.class));
        js.append("continue;");
        js.append("};");
        js.append("const rowData=data[row];");

        // filter operation

        js.append("for(const g in groupByFields){"); // for
        js.append("const groupField=groupByFields[g];");
        js.append("const groupValue=rowData[groupField];");
        js.append("if(groupValue==undefined){"); // if
        js.throwError("unknown field", "rowData", "groupField");
        js.append("};"); // end if

        // create a new row if not exists using the row template
        js.append("var gg=groups[groupValue];");
        js.append("if(gg==null||gg==undefined){"); // if
        js.append("gg=Object.assign({},rowTemplate);");
        js.append("gg[groupField]=groupValue;");
        js.append("groups[groupValue]=gg;");
        js.append("};"); // end if

        js.append("for(const a in aggregateFields){"); // for
        js.append("const aggregate=aggregateFields[a];");
        js.append("const value=rowData[aggregate.field];");
        js.append("const currentValue=gg[aggregate.field];");
        js.append("switch(aggregate.functionType){"); // switch
        js.append("case 'reducer':");
        js.append("gg[aggregate.field]=aggregate.fn(currentValue,value);");
        js.append("break;");
        js.append("case 'aggregate':");
        js.append("aggregateArrays[aggregate.field].push(value);");
        js.append("break;");
        js.append("};"); // end switch
        js.append("};"); // end for

        // note: currently only one group is supported!
        js.append("break;");
        js.append("};"); // end for

        // end filter operation

        js.append("};"); // end for

        // process aggregates
        js.append("for(const a in aggregateFields){"); // for
        js.append("const aggregate=aggregateFields[a];");
        js.append("switch(aggregate.type){"); // switch
        js.append("case 'aggregate':");
        js.append("gg[aggregate.field]=aggregate.fn(aggregateArrays[aggregate.field]);");
        js.append("break;");
        js.append("};"); // end switch
        js.append("};"); // end for


        // add groups to output
        js.append("for(const g in groups){"); // for
        js.append("const gg=groups[g];");
        js.append("m.push(gg);");
        js.append("};"); // end for

        js.append("output.meta['%s']=Object.assign([],output.meta['%s']);",
                ServiceResponse.META_TRANSFORMATIONS, ServiceResponse.META_TRANSFORMATIONS);
        js.append("output.meta['%s'].push(%s);",
                ServiceResponse.META_TRANSFORMATIONS,
                DtoUtil.valueOf(new TransformationMetadata(getId(), getClass().getSimpleName())));

        js.info("console.log('GroupByDataAdapter after',output);");
        js.append("return output;");

        js.append("}"); // end function
    }
}
