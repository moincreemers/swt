package com.philips.dmis.swt.ui.toolkit.data;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.js.JsWriter;
import com.philips.dmis.swt.ui.toolkit.js.global.JsGlobalModule;
import com.philips.dmis.swt.ui.toolkit.js.global.ProperCaseFunction;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;

/**
 * Given an array of objects this data adapter will find every distinct property name.
 * Example:
 * <pre>
 * [
 *  {
 *      firstName: John,
 *      lastName: Doe,
 *      gender: male
 *  },
 *  {
 *      firstName: Jane,
 *      lastName: Doe,
 *      age: 30,
 *  }
 * ]</pre>
 * Result:
 * <pre>[firstName, lastName, gender, age]</pre>
 * <p>
 * Optionally, property names can be cased properly.
 */
public class PropertyNameDataAdapter extends DataAdapter {
    private final boolean properCase;

    /**
     * Adapter with the default path.
     */
    public PropertyNameDataAdapter() {
        this(DEFAULT_PATH, false);
    }

    /**
     * Adapter with the default path.
     */
    public PropertyNameDataAdapter(boolean properCase) {
        this(DEFAULT_PATH, properCase);
    }


    /**
     * @param path given a json object that provided by a data source, the path to a field.
     */
    public PropertyNameDataAdapter(String path) {
        this(path, false);
    }

    /**
     * @param path       given a json object that provided by a data source, the path to a field.
     * @param properCase true to automatically convert property names to proper case.
     */
    public PropertyNameDataAdapter(String path, boolean properCase) {
        super(path);
        this.properCase = properCase;
    }

    @Override
    public void renderJs(Toolkit toolkit, Widget widget, JsWriter js) {
        js.append("(serviceResponse,unmodifiedResponse)=>{");
        js.trace(this);

        js.append("let data=serviceResponse%s;", getPath());
        js.append("let names=[];");
        js.append("let properCase=%s;", properCase ? "true" : "false");
        js.append("for(const i in data){");
        js.append("let keys=Object.keys(data[i]);");
        js.append("for(const j in keys){");
        js.append("if(!names.includes(keys[j])){");
        js.append("names.push(keys[j]);");
        js.append("}");
        js.append("};");
        js.append("};");
        js.append("if(properCase){");
        js.append("for(const i in names){");
        js.append("names[i]=%s(names[i]);", JsGlobalModule.getQualifiedId(ProperCaseFunction.class));
        js.append("}");
        js.append("};");
        js.append("return names;");

        js.append("}"); // end function
    }
}
