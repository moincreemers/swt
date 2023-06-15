package com.philips.dmis.swt.ui.toolkit.data;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.dto.EventContext;
import com.philips.dmis.swt.ui.toolkit.js.JsWriter;
import com.philips.dmis.swt.ui.toolkit.reflect.DtoUtil;
import com.philips.dmis.swt.ui.toolkit.statement.Statement;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given a service response object provided by a data source,
 * this Data Adapter performs a method on each value encountered.
 */
public class ExtractDataAdapter extends DataAdapter {
    private static class Field {
        final String field;
        final List<Statement> statements = new ArrayList<>();

        public Field(String field, Statement... statements) {
            this.field = field;
            this.statements.addAll(Arrays.asList(statements));
        }
    }

    private final List<Field> fields = new ArrayList<>();

    public ExtractDataAdapter() {
        this(DEFAULT_PATH);
    }

    public ExtractDataAdapter(String path) {
        super(path);
    }

    public ExtractDataAdapter map(String field, Statement... statements) {
        if (field == null || field.isEmpty()) {
            throw new IllegalArgumentException("invalid field");
        }
        fields.add(new Field(field, statements));
        return this;
    }

    @Override
    public void renderJs(Toolkit toolkit, Widget widget, JsWriter js) {
        js.append("(serviceResponse,unmodifiedResponse)=>{"); // begin function
        js.trace(this);

        js.append("const eventContext=%s;", DtoUtil.getDefault(EventContext.class, false));

        js.append("const obj=serviceResponse%s;", getPath());
        js.append("if(obj==null||obj==undefined){");
        js.throwError("path not found", "serviceResponse");
        js.append("};");

        for (Field field : fields) {
            js.append("this.%s=obj['%s'];", field.field, field.field);
            js.append("if(obj!=null){"); // if
            for (Statement statement : field.statements) {
                statement.renderJs(toolkit, widget, js);
            }
            js.append("};"); // end if
        }

        js.append("return serviceResponse;");

        js.append("}"); // end function
    }
}
