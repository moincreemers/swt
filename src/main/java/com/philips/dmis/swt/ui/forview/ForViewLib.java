package com.philips.dmis.swt.ui.forview;

import com.philips.dmis.swt.ui.toolkit.js.JsParameter;
import com.philips.dmis.swt.ui.toolkit.js.JsType;
import com.philips.dmis.swt.ui.toolkit.widgets.Code;

public class ForViewLib extends Code {
    public static final String PARSE_ERROR = "parseError";
    public static final String PARSE_WARNING = "parseWarning";
    public static final String PARSE_LOGIN = "parseLogin";
    public static final String CALCULATE_AGE = "calcAge";
    public static final String CREATE_WADO_THUMB_URL = "createWadoThumbnailUrl";
    public static final String CREATE_WADO_IMAGE_URL = "createWadoImageUrl";

    public ForViewLib() {
        super("FV");
    }

    @Override
    protected void build() {
        add(createFunction(PARSE_ERROR, JsType.STRING,
                /*
                {
                    "message":"authentication required",
                    "logContextId":"79a8f001e495/viewer-4763",
                    "origin":"/viewer/services/domain/list.json",
                    "causes":[
                        "nl.forcare.common.servlet.HttpErrorException: authentication required"
                    ],
                    "stacktrace":{
                        "__text__":"nl.forcare.common.servlet.HttpErrorException: authentication required"
                    }
                }
                 */
                "(xhrResponse)=>{"
                        + "if(xhrResponse.contentType.value=='application/json'){" // if
                        + "const obj=JSON.parse(xhrResponse.responseText);return obj.message;"
                        + "} else if(xhrResponse.contentType.value=='text/plain'){" // else if
                        + "const code=xhrResponse.responseText;"
                        + "switch(code){"
                        + "case 'page.login.action.badCredentials':"
                        + "return 'Incorrect user name or password entered';"
                        + "break;"
                        + "case 'queryFailed':"
                        + "return 'An error occurred during query to external community';"
                        + "break;"
                        + "default:"
                        + "return 'unknown error code';"
                        + "break;"
                        + "};" // end switch
                        + "};" // end if
                        + "return 'unknown response';"
                        + "};",
                JsParameter.getInstance("xhrResponse", JsType.OBJECT))
        );

        add(createFunction(PARSE_WARNING, JsType.STRING,
                "(warningCode,warningContext,message)=>{"
                        + "if(message!==undefined&&message!=''){"
                        + "return message;"
                        + "};"
                        + "switch(warningCode){"
                        + "case 'queryFailed':"
                        + "return 'An error occurred during query to external community ' + warningContext;"
                        + "break;"
                        + "};" // end switch
                        + "return 'Unknown error: '+warningCode+' ('+warningContext+')';"
                        + "};",
                JsParameter.getInstance("warningCode", JsType.STRING),
                JsParameter.getInstance("warningContext", JsType.STRING),
                JsParameter.getInstance("message", JsType.STRING))
        );

        add(createFunction(PARSE_LOGIN, JsType.STRING,
                /*
                {
                    "response": {
                        "status": "ok",
                        "jwt": "eyJhb..."
                    }
                }
                 */
                "(xhrResponse)=>{"
                        + "if(xhrResponse.contentType.value=='application/json'){" // if
                        + "const obj=JSON.parse(xhrResponse.responseText);"
                        + "if(obj.response.status!='ok'){"
                        + "throw new Error('login failed');"
                        + "};"
                        + "return obj.response.jwt;"
                        + "}" // end if
                        + "throw new Error('invalid login response');"
                        + "};", // end function
                JsParameter.getInstance("xhrResponse", JsType.OBJECT))
        );

        add(createFunction(CALCULATE_AGE, JsType.NUMBER,
                "(date)=>{"
                        + ""
                        + "};",
                JsParameter.getInstance("xhrResponse", JsType.STRING))
        );

        add(createFunction(CREATE_WADO_THUMB_URL, JsType.STRING,

                // thumbnail image url: /viewer/services/connect/proxy/wado/PACS
                //  ?requestType=WADO
                //  &studyUID=1.3.6.1.4.1.33437.11.2.5604041.12963165250.145.2
                //  &seriesUID=1.3.6.1.4.1.33437.11.3.5604041.12963165452.145.3.0
                //  &objectUID=1.3.6.1.4.1.33437.11.4.5604041.12963165452.145.4.0.0.0
                //  &contentType=image%2Fjpeg
                //  &globalLocationUid=3.2.3
                //  &homeCommunityUid=1.2.3
                //  &patientID=7832654321
                //  &patientIDAuth=1.3.6.1.4.1.21367.2005.3.7
                //  &columns=128
                //  &rows=128

                "(studyUID, seriesUID, objectUID, contentType, globalLocationUid, homeCommunityUid, patientID, patientIDAuth)=>{"
                        + "var url='http://localhost:8080/viewer/services/connect/proxy/wado/PACS';"
                        + "url+='?requestType=WADO';"
                        + "url+='&studyUID='+studyUID;"
                        + "url+='&seriesUID='+seriesUID;"
                        + "url+='&objectUID='+objectUID;"
                        + "url+='&contentType='+contentType;"
                        + "url+='&globalLocationUid='+globalLocationUid;"
                        + "url+='&homeCommunityUid='+homeCommunityUid;"
                        + "url+='&patientID='+patientID;"
                        + "url+='&patientIDAuth='+patientIDAuth;"
                        + "url+='&columns=128';"
                        + "url+='&rows=128';"
                        + "return url;"
                        + "};",
                JsParameter.getInstance("studyUID", JsType.STRING),
                JsParameter.getInstance("seriesUID", JsType.STRING),
                JsParameter.getInstance("objectUID", JsType.STRING),
                JsParameter.getInstance("contentType", JsType.STRING),
                JsParameter.getInstance("globalLocationUid", JsType.STRING),
                JsParameter.getInstance("homeCommunityUid", JsType.STRING),
                JsParameter.getInstance("patientID", JsType.STRING),
                JsParameter.getInstance("patientIDAuth", JsType.STRING))
        );

        add(createFunction(CREATE_WADO_IMAGE_URL, JsType.STRING,

                // thumbnail image url: /viewer/services/connect/proxy/wado/PACS
                //  ?requestType=WADO
                //  &studyUID=1.3.6.1.4.1.33437.11.2.5604041.12963165250.145.2
                //  &seriesUID=1.3.6.1.4.1.33437.11.3.5604041.12963165452.145.3.0
                //  &objectUID=1.3.6.1.4.1.33437.11.4.5604041.12963165452.145.4.0.0.0
                //  &contentType=image%2Fjpeg
                //  &globalLocationUid=3.2.3
                //  &homeCommunityUid=1.2.3
                //  &patientID=7832654321
                //  &patientIDAuth=1.3.6.1.4.1.21367.2005.3.7
                //  &columns=128
                //  &rows=128

                "(studyUID, seriesUID, objectUID, contentType, globalLocationUid, homeCommunityUid, patientID, patientIDAuth)=>{"
                        + "var url='http://localhost:8080/viewer/services/connect/proxy/wado/PACS';"
                        + "url+='?requestType=WADO';"
                        + "url+='&studyUID='+studyUID;"
                        + "url+='&seriesUID='+seriesUID;"
                        + "url+='&objectUID='+objectUID;"
                        + "url+='&contentType='+contentType;"
                        + "url+='&globalLocationUid='+globalLocationUid;"
                        + "url+='&homeCommunityUid='+homeCommunityUid;"
                        + "url+='&patientID='+patientID;"
                        + "url+='&patientIDAuth='+patientIDAuth;"
                        + "return url;"
                        + "};",
                JsParameter.getInstance("studyUID", JsType.STRING),
                JsParameter.getInstance("seriesUID", JsType.STRING),
                JsParameter.getInstance("objectUID", JsType.STRING),
                JsParameter.getInstance("contentType", JsType.STRING),
                JsParameter.getInstance("globalLocationUid", JsType.STRING),
                JsParameter.getInstance("homeCommunityUid", JsType.STRING),
                JsParameter.getInstance("patientID", JsType.STRING),
                JsParameter.getInstance("patientIDAuth", JsType.STRING))
        );
    }
}
