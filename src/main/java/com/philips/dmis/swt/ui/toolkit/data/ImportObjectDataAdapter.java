package com.philips.dmis.swt.ui.toolkit.data;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.dto.ServiceResponse;
import com.philips.dmis.swt.ui.toolkit.dto.TransformationMetadata;
import com.philips.dmis.swt.ui.toolkit.js.JsWriter;
import com.philips.dmis.swt.ui.toolkit.reflect.DtoUtil;
import com.philips.dmis.swt.ui.toolkit.widgets.DataSourceUsage;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Transforms any JSON document that represents a single object
 * into the ServiceResponse data structure.
 * <p>
 * The transformation starts by referencing the root of the object using [path].
 * Then, field level mappings are used to reference column values.
 * <p>
 * todo: VIEW
 *
 * <p>
 * <pre>
 * For example, given the response:
 * {
 *     "user": {
 *         "id": "robert.dolin",
 *         "canonicalUserId": "uid=robert.dolin,uid=hospital.a,ou=practices,ou=users,dc=forcare,dc=local",
 *         "displayName": "Robert Dolin",
 *         "dn": "uid=robert.dolin,uid=hospital.a,ou=practices,ou=users,dc=forcare,dc=local",
 *         "npi": "robert.dolin",
 *         "organization": {
 *             "displayName": "Hospital A",
 *             "noi": "hospital.a"
 *         },
 *         "additionalAttributes": {
 *             "urn:x-forcare:xua:departmentId": [
 *                 "hospitala.radiology"
 *             ]
 *         },
 *         "roles": [
 *             {
 *                 "code": "ReferralDispatchers",
 *                 "displayName": "ReferralDispatchers",
 *                 "codeSystemName": "urn:x-forcare:authz:role",
 *                 "codeSystem": "1.3.6.1.4.1.40371.2.11.1"
 *             },
 *             {
 *                 "code": "ReferralRecipients",
 *                 "displayName": "ReferralRecipients",
 *                 "codeSystemName": "urn:x-forcare:authz:role",
 *                 "codeSystem": "1.3.6.1.4.1.40371.2.11.1"
 *             },
 *             {
 *                 "code": "ContentCreators",
 *                 "displayName": "ContentCreators",
 *                 "codeSystemName": "urn:x-forcare:authz:role",
 *                 "codeSystem": "1.3.6.1.4.1.40371.2.11.1"
 *             },
 *             {
 *                 "code": "MedicalDoctors",
 *                 "displayName": "MedicalDoctors",
 *                 "codeSystemName": "urn:x-forcare:authz:role",
 *                 "codeSystem": "1.3.6.1.4.1.40371.2.11.1"
 *             }
 *         ],
 *         "loginMethod": "urn:oasis:names:tc:SAML:2.0:ac:classes:Password",
 *         "sessionBoundPatientId": null,
 *         "privileges": [
 *             "WORKFLOW_READ",
 *             "DOCUMENT_CREATE",
 *             "METADATA_QUERY",
 *             "DOCUMENT_READ",
 *             "PRIVACY_CONSENT_READ",
 *             "ORDER_PLACE",
 *             "PURPOSE_OF_USE_OVERRIDE",
 *             "PATIENT_QUERY",
 *             "WORKFLOW_DEPRECATE",
 *             "VIEWER_LOGIN"
 *         ]
 *     }
 * }
 * </pre>
 * The path would be: ".user".
 * </p>
 * <br/>
 * <p>
 * If we then want the output to be:
 * <pre>
 *  {
 *      "id": "robert.dolin",
 *      "displayName": "Robert Dolin",
 *      "organization": "Hospital A",
 *      "roles": [
 *          "ReferralDispatchers",
 *          "ReferralRecipients",
 *          "ContentCreators",
 *          "MedicalDoctors"
 *      ],
 *      "privileges": [
 *          "WORKFLOW_READ",
 *          "DOCUMENT_CREATE",
 *          "METADATA_QUERY",
 *          "DOCUMENT_READ",
 *          "PRIVACY_CONSENT_READ",
 *          "ORDER_PLACE",
 *          "PURPOSE_OF_USE_OVERRIDE",
 *          "PATIENT_QUERY",
 *          "WORKFLOW_DEPRECATE",
 *          "VIEWER_LOGIN"
 *      ]
 *  }
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
 *          <td>.id</td>
 *          <td>id</td>
 *      </tr>
 *      <tr>
 *          <td>Simple</td>
 *          <td>.displayName</td>
 *          <td>displayName</td>
 *      </tr>
 *      <tr>
 *          <td>Simple</td>
 *          <td>.organization.displayName</td>
 *          <td>organization</td>
 *      </tr>
 *      <tr>
 *          <td>IndexArray (with indexer: 'code')</td>
 *          <td>.roles</td>
 *          <td>roles</td>
 *      </tr>
 *      <tr>
 *          <td>Simple</td>
 *          <td>.privileges</td>
 *          <td>privileges</td>
 *      </tr>
 *  </tbody>
 * </table>
 *
 * </p>
 */
public class ImportObjectDataAdapter extends DataAdapter {
    private final String outputPath;
    private final List<FieldMapping> fieldMappings = new ArrayList<>();

    public ImportObjectDataAdapter(String path) {
        this(path, DEFAULT_PATH);
    }

    /**
     * @param path          A path to a field in the JSON document that contains the object.
     * @param outputPath    A property name that will store the new object. By default this is the default path ".data.items".
     * @param fieldMappings Field mappings are used to extract information from the JSON document into a tabular format and to generate the view.
     */
    public ImportObjectDataAdapter(String path, String outputPath, FieldMapping... fieldMappings) {
        super(path);
        // note: in-/output path is unlikely the same but it is possible.
        //if (DEFAULT_PATH.equals(path)) {
        //throw new IllegalArgumentException("expected a different path than: " + DEFAULT_PATH);
        //}
        validatePath(outputPath);
        this.outputPath = outputPath;
        this.fieldMappings.addAll(Arrays.asList(fieldMappings));
    }

    public ImportObjectDataAdapter add(FieldMapping fieldMapping) {
        this.fieldMappings.add(fieldMapping);
        return this;
    }

    @Override
    public boolean isDataSourceUsage(DataSourceUsage dataSourceUsage) {
        return dataSourceUsage == DataSourceUsage.IMPORT;
    }

    public String getOutputPath() {
        return outputPath;
    }

    @Override
    public void renderJs(Toolkit toolkit, Widget widget, JsWriter js) {
        js.append("(serviceResponse,unmodifiedResponse)=>{");

        // create new ServiceResponse or use existing service response (this is only true if this data adapter is doing
        // a secondary import on the same data source)
        js.append("serviceResponse=(serviceResponse!==undefined&&serviceResponse!==null)?serviceResponse:%s;",
                DtoUtil.getDefault(ServiceResponse.class, false));

        // note: we always import from the unmodified response provided by the data source
        js.append("let object=unmodifiedResponse%s;", getPath());

        js.append("if(typeof object!='object'){");
        js.append("const path='%s';", getPath());
        js.throwError("failed to transform object because path does not map to a valid object", "object", "path");
        js.append("};");

        js.append("const outputObject={};");
        for (FieldMapping fieldMapping : fieldMappings) {
            js.append("var s=object;");
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
                    js.append("outputObject['%s']=targetObj;", fieldMapping.getTo());
                    js.append("};"); // end if
                    break;

                default:
                case SIMPLE:
                    js.append("outputObject['%s']=s;", fieldMapping.getTo());
                    break;
            }
        }


        js.append("serviceResponse%s=outputObject;", outputPath);

//        js.append("serviceResponse.meta['%s']='%s';",
//                ServiceResponse.META_SELECTED_VIEW_ID, rootViewId);

        js.append("serviceResponse.meta['%s']=Object.assign([],serviceResponse.meta['%s']);",
                ServiceResponse.META_TRANSFORMATIONS, ServiceResponse.META_TRANSFORMATIONS);
        js.append("serviceResponse.meta['%s'].push(%s);",
                ServiceResponse.META_TRANSFORMATIONS,
                DtoUtil.valueOf(new TransformationMetadata(getId(), getClass().getSimpleName())));

        js.info("console.log('ImportObjectDataAdapter',unmodifiedResponse,outputObject);");


        js.append("return serviceResponse;");
        js.append("}");
    }
}
