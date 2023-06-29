package com.philips.dmis.swt.ui.toolkit.data;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.dto.ServiceResponse;
import com.philips.dmis.swt.ui.toolkit.dto.TransformationMetadata;
import com.philips.dmis.swt.ui.toolkit.js.JsWriter;
import com.philips.dmis.swt.ui.toolkit.reflect.DtoUtil;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;

/**
 * Transforms a ServiceResponse by flattening two arrays into one.
 * <p>
 * The transformation starts by referencing the array at [path].
 * Then, for each element in the array, the data adapter takes [memberName],
 * which must also correspond to an array, and flattens the data structure.
 * <pre>
 * For example, given the response:
 * {
 *   "items": [
 *     {
 *       "id": "123",
 *       "array": [
 *          {
 *              "color": "red"
 *          },
 *          {
 *              "color": "blue"
 *          }
 *       ]
 *     }
 *   ]
 * }
 * </pre>
 * The path would be: ".items", memberName would be "array".
 * </p>
 * <br/>
 * <p>
 * The output will be:
 * <pre>
 * {
 *   "items": [
 *     {
 *       "id": "123",
 *       "color": "red"
 *     },
 *     {
 *        "id": "123",
 *        "color": "blue"
 *     }
 *   ]
 * }
 * </pre>
 * </p>
 */
public class FlattenArrayDataAdapter extends DataAdapter {
    private final String memberName;

    public FlattenArrayDataAdapter(String memberName) {
        this(DEFAULT_PATH, memberName);
    }

    /**
     * @param path       A path to a field in the JSON document that contains the array.
     * @param memberName A property name that contains the Array that should be flattened.
     */
    public FlattenArrayDataAdapter(String path, String memberName) {
        super(path);
        this.memberName = memberName;
    }

    public String getMemberName() {
        return memberName;
    }

    @Override
    public void renderJs(Toolkit toolkit, Widget widget, JsWriter js) {
        js.append("(serviceResponse,unmodifiedResponse)=>{");
        js.trace(this);

        // re-create data structure
        js.append("const copy=structuredClone(serviceResponse);");

        // clear all data in the serviceResponse
        js.append("var m=serviceResponse%s;", getPath());
        js.append("m.length=0;");

        js.append("const dataItems=copy%s;", getPath());
        js.append("for(const i in dataItems){"); // for
        js.append("var dataItem=dataItems[i];");
        js.append("var parent={};");
        js.append("for(const p in dataItem){"); // for
        js.append("if(p=='%s'){", memberName); // if
        js.append("continue;");
        js.append("};"); // end if
        js.append("parent[p]=dataItem[p];");
        js.append("};"); // end for
        js.append("var array=dataItem['%s'];", memberName);
        js.append("for(const a in array){"); // for
        js.append("var child=array[a];");
        js.append("var newDataItem=Object.assign(structuredClone(parent),child);");
        js.append("m.push(newDataItem);");
        js.append("};"); // end for
        js.append("};"); // end for

        js.append("serviceResponse.meta['%s']=Object.assign([],serviceResponse.meta['%s']);",
                ServiceResponse.META_TRANSFORMATIONS, ServiceResponse.META_TRANSFORMATIONS);
        js.append("serviceResponse.meta['%s'].push(%s);",
                ServiceResponse.META_TRANSFORMATIONS,
                DtoUtil.valueOf(new TransformationMetadata(getId(), getClass().getSimpleName())));

        js.debug("console.log('FlattenDataAdapter after',serviceResponse);");

        js.append("return serviceResponse;");
        js.append("}");
    }
}
