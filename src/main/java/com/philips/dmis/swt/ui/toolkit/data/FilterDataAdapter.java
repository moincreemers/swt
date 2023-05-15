package com.philips.dmis.swt.ui.toolkit.data;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.dto.ServiceResponse;
import com.philips.dmis.swt.ui.toolkit.dto.TransformationMetadata;
import com.philips.dmis.swt.ui.toolkit.reflect.DtoUtil;
import com.philips.dmis.swt.ui.toolkit.statement.predicate.PredicateStatement;
import com.philips.dmis.swt.ui.toolkit.statement.value.ValueStatement;
import com.philips.dmis.swt.ui.toolkit.js.JsWriter;
import com.philips.dmis.swt.ui.toolkit.js.global.IsObjectFunction;
import com.philips.dmis.swt.ui.toolkit.js.global.JsGlobalModule;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;

import java.util.ArrayList;
import java.util.List;

public class FilterDataAdapter extends DataAdapter {
    private interface Condition {
        String getField();

        void renderJs(Toolkit toolkit, Widget widget, JsWriter js);
    }

    private static class PredicateCondition implements Condition {
        String field;
        PredicateStatement predicateStatement;

        @Override
        public String getField() {
            return field;
        }

        public PredicateCondition(String field, PredicateStatement predicateStatement) {
            this.field = field;
            this.predicateStatement = predicateStatement;
        }

        @Override
        public void renderJs(Toolkit toolkit, Widget widget, JsWriter js) {
            predicateStatement.renderJs(toolkit, widget, js);
        }
    }

    private static class ValueCondition implements Condition {
        String field;
        ValueStatement valueStatement;

        @Override
        public String getField() {
            return field;
        }

        public ValueCondition(String field, ValueStatement valueStatement) {
            this.field = field;
            this.valueStatement = valueStatement;
        }

        @Override
        public void renderJs(Toolkit toolkit, Widget widget, JsWriter js) {
            valueStatement.renderJs(toolkit, widget, js);
        }
    }

    private final List<Condition> conditions = new ArrayList<>();
    private final boolean inverted;
    private final boolean exclusive;

    public FilterDataAdapter() {
        this(DEFAULT_PATH, false, true);
    }

    public FilterDataAdapter(String path) {
        this(path, false, true);
    }

    public FilterDataAdapter(boolean inverted) {
        this(DEFAULT_PATH, inverted, true);
    }

    public FilterDataAdapter(boolean inverted, boolean exclusive) {
        this(DEFAULT_PATH, inverted, exclusive);
    }

    public FilterDataAdapter(String path, boolean inverted, boolean exclusive) {
        super(path);
        this.inverted = inverted;
        this.exclusive = exclusive;
    }

    public FilterDataAdapter addPredicate(String field, PredicateStatement predicate) {
        if (field == null || field.isEmpty()) {
            throw new IllegalArgumentException("invalid field");
        }
        if (predicate == null) {
            throw new IllegalArgumentException("invalid predicate");
        }
        conditions.add(new PredicateCondition(field, predicate));
        return this;
    }

    public FilterDataAdapter addValueStatement(String field, ValueStatement value) {
        if (field == null || field.isEmpty()) {
            throw new IllegalArgumentException("invalid field");
        }
        if (value == null) {
            throw new IllegalArgumentException("invalid value");
        }
        conditions.add(new ValueCondition(field, value));
        return this;
    }

    @Override
    public void renderJs(Toolkit toolkit, Widget widget, JsWriter js) {
        js.append("(serviceResponse)=>{");

        js.debug("console.log('FilterDataAdapter before',serviceResponse);");
        js.append("var processed=false;");

        js.append("const conditions=[];");
        for (Condition condition : conditions) {
            js.append("var condition={field:'%s',fn:", condition.getField());
            condition.renderJs(toolkit, widget, js);
            js.append("};");
            js.append("conditions.push(condition);");
        }
        js.debug("console.log('FilterDataAdapter conditions',conditions);");

        // re-create data structure
        js.append("const output=structuredClone(serviceResponse);");
        js.append("var m=output%s;", getPath());
        js.append("m.length=0;");

        js.append("const data=Object.assign([],serviceResponse%s);", getPath());
        js.debug("console.log('FilterDataAdapter data from path',data)");

        // EXIT
        js.append("if(data.length==0){");
        js.debug("console.log('FilterDataAdapter exit');");
        js.append("return serviceResponse;");
        js.append("};");

        js.append("for(var row=0;row<data.length;row++){"); // for
        js.append("if(%s(data[row])){", // if
                JsGlobalModule.getQualifiedId(IsObjectFunction.class));
        js.append("const rowData=data[row];");
        js.append("var match=%s;", exclusive ? "true" : "false");

        // filter operation

        js.append("for(const i in conditions){"); // for
        js.append("const value=rowData[conditions[i].field];");
        js.append("if(value==undefined){");
        js.throwError("unknown field", "rowData", "conditions[i]");
        js.append("};");
        if (exclusive) {
            // AND
            js.append("if(conditions[i].fn(value)===%s){", inverted ? "true" : "false");
            js.append("match=false;");
            js.append("break;"); // stop
            js.append("};"); // end if
        } else {
            // OR
            js.append("if(conditions[i].fn(value)===%s){", inverted ? "false" : "true");
            js.append("match=true;");
            js.append("break;"); // stop
            js.append("};"); // end if
        }
        js.append("};"); // end for


        js.append("if(match){"); // if
        js.append("m.push(rowData);");
        js.append("};"); // end if
        js.append("};"); // end if
        js.append("};"); // end for

        js.append("output.meta['%s']=Object.assign([],output.meta['%s']);",
                ServiceResponse.META_TRANSFORMATIONS, ServiceResponse.META_TRANSFORMATIONS);
        js.append("output.meta['%s'].push(%s);",
                ServiceResponse.META_TRANSFORMATIONS,
                DtoUtil.valueOf(new TransformationMetadata(getId(), getClass().getSimpleName())));


        js.debug("console.log('FilterDataAdapter after',output);");
        js.append("return output;");

        js.append("}"); // end function
    }
}
