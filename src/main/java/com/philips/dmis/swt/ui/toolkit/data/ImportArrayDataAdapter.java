package com.philips.dmis.swt.ui.toolkit.data;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.dto.*;
import com.philips.dmis.swt.ui.toolkit.js.JsWriter;
import com.philips.dmis.swt.ui.toolkit.js.global.AddViewFieldFunction;
import com.philips.dmis.swt.ui.toolkit.js.global.CreateViewFunction;
import com.philips.dmis.swt.ui.toolkit.js.global.JsGlobalModule;
import com.philips.dmis.swt.ui.toolkit.reflect.DtoUtil;
import com.philips.dmis.swt.ui.toolkit.widgets.DataSourceUsage;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Transforms any JSON document that contains an ARRAY of similar objects
 * into the ServiceResponse data structure.
 * <p>
 * The transformation starts by referencing the array at [path].
 * Then, field level mappings are used to reference column values.
 * <p>
 * The field mappings also contain information to create a view.
 *
 * <p>
 * <pre>
 * For example, given the response:
 * {
 *   "kind": "books#volumes",
 *   "totalItems": 593,
 *   "items": [
 *     {
 *       "kind": "books#volume",
 *       "id": "gDUQCwAAQBAJ",
 *       "etag": "f5nIToGKZ6A",
 *       "selfLink": "https://www.googleapis.com/books/v1/volumes/gDUQCwAAQBAJ",
 *       "volumeInfo": {
 *         "title": "Harry Potter and the Philosophers Stone",
 *         "authors": ["J.K. Rowling"],
 *         "publisher": "Pottermore Publishing",
 *         "publishedDate": "2015-12-08",
 *         "description": "..."
 *       }
 *     }
 *   ]
 * }
 * </pre>
 * The path would be: ".items".
 * </p>
 * <br/>
 * <p>
 * If we then want the output to be:
 * <pre>
 * [
 *  {
 *      "link": "https://www.googleapis.com/books/v1/volumes/gDUQCwAAQBAJ",
 *      "title": "Harry Potter and the Philosopher's Stone",
 *      "authors": "J.K. Rowling"
 *  }
 * ]
 * </pre>
 * <br/>
 * Then we need to use the following field mappings:
 * <table style="width:400px">
 *  <thead>
 *      <tr>
 *          <th>Mapping Type</th>
 *          <th>From</th>
 *          <th>To</th>
 *      </tr>
 *  </thead>
 *  <tbody>
 *      <tr>
 *          <td>Simple</td>
 *          <td>selfLink</td>
 *          <td>link</td>
 *      </tr>
 *      <tr>
 *          <td>Simple</td>
 *          <td>volumeInfo.title</td>
 *          <td>title</td>
 *      </tr>
 *      <tr>
 *          <td>Simple</td>
 *          <td>volumeInfo.authors</td>
 *          <td>authors</td>
 *      </tr>
 *  </tbody>
 * </table>
 *
 * </p>
 */
public class ImportArrayDataAdapter extends DataAdapter {
    private final List<FieldMapping> fieldMappings = new ArrayList<>();

    /**
     * @param path          A path to a field in the JSON document that contains the array.
     * @param fieldMappings Field mappings are used to extract information from the JSON document into a tabular format and to generate the view.
     */
    public ImportArrayDataAdapter(String path, FieldMapping... fieldMappings) {
        super(path);
        this.fieldMappings.addAll(Arrays.asList(fieldMappings));
    }

    public ImportArrayDataAdapter add(FieldMapping fieldMapping) {
        this.fieldMappings.add(fieldMapping);
        return this;
    }

    @Override
    public DataSourceUsage getInitialDataSourceUsage() {
        return DataSourceUsage.IMPORT;
    }

    @Override
    public boolean isDataSourceUsageAllowed(DataSourceUsage dataSourceUsage) {
        return dataSourceUsage == getInitialDataSourceUsage();
    }

    @Override
    public void renderJs(Toolkit toolkit, Widget widget, JsWriter js) {
        js.append("(serviceResponse,unmodifiedResponse)=>{");
        js.trace(this);

        // create new ServiceResponse or use existing service response (this is only true if this data adapter is doing
        // a secondary import on the same data source)
        js.append("const newServiceResponse=(serviceResponse!==undefined&&serviceResponse!==null)?serviceResponse:%s;", DtoUtil.getDefault(ServiceResponse.class, false));

        // note: we always import from the unmodified response provided by the data source
        js.append("let array=unmodifiedResponse%s;", getPath());

        js.append("if(!Array.isArray(array)){");
        js.append("const path='%s';", getPath());
        js.throwError("failed to transform array because path does not map to a valid array", "array", "path");
        js.append("};");

        js.append("let outputArray=[];");
        js.append("for(var row=0;row<array.length;row++){");
        js.append("let rowData=array[row];");
        js.append("let outputRow={};");
        for (FieldMapping fieldMapping : fieldMappings) {
            js.append("var s=rowData;");
            String[] fieldsInPath = fieldMapping.getFrom().substring(1).split("\\.");
            for (String fieldInPath : fieldsInPath) {
                js.append("if(s!==''&&s.hasOwnProperty('%s')){s=s.%s;}else{s='';}", fieldInPath, fieldInPath);
            }
            switch (fieldMapping.getFieldMappingType()) {
                case INDEX_ARRAY:
                    js.append("if(Array.isArray(s)){"); // if
                    js.append("const targetObj={};");
                    js.append("for(const k in s){"); // for
                    js.append("var arrayElement=s[k];");
                    js.append("var propertyName=arrayElement['%s'];", fieldMapping.getArrayIndexer());
                    js.append("propertyName=propertyName.replace('.','');"); // remove dots
                    js.append("targetObj[propertyName]=arrayElement;");
                    js.append("};"); // end for
                    js.append("outputRow['%s']=targetObj;", fieldMapping.getTo());
                    js.append("};"); // end if
                    break;

                default:
                case SIMPLE:
                    js.append("outputRow['%s']=s;", fieldMapping.getTo());
                    break;
            }
        }
        js.append("outputArray.push(outputRow);");
        js.append("};");// end for row


        js.append("newServiceResponse%s=outputArray;", DEFAULT_PATH);

        String rootViewId = View.getRootViewId(getId());
        js.append("const viewTop=%s(newServiceResponse,'%s','%s',false);",
                JsGlobalModule.getQualifiedId(CreateViewFunction.class),
                rootViewId,
                getClass().getSimpleName());
        for (FieldMapping fieldMapping : fieldMappings) {
            js.append("%s(newServiceResponse,viewTop,'%s','%s','%s',%s,'%s','%s',false);",
                    JsGlobalModule.getQualifiedId(AddViewFieldFunction.class),
                    fieldMapping.getName(),
                    fieldMapping.getTo(),
                    fieldMapping.getFormatType(),
                    Format.valueOf(fieldMapping.getFormat()),
                    fieldMapping.getDataType(),
                    ViewAppearance.DEFAULT.name());
        }


        js.append("newServiceResponse.meta['%s']='%s';",
                ServiceResponse.META_SELECTED_VIEW_ID, rootViewId);

        js.append("newServiceResponse.meta['%s']=Object.assign([],newServiceResponse.meta['%s']);",
                ServiceResponse.META_TRANSFORMATIONS, ServiceResponse.META_TRANSFORMATIONS);
        js.append("newServiceResponse.meta['%s'].push(%s);",
                ServiceResponse.META_TRANSFORMATIONS,
                DtoUtil.valueOf(new TransformationMetadata(getId(), getClass().getSimpleName())));

        js.debug("console.log('ImportArrayDataAdapter',unmodifiedResponse,outputArray);");


        js.append("return newServiceResponse;");
        js.append("}");
    }
}
