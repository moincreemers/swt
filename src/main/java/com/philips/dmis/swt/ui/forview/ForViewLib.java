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
    public static final String IS_DOCUMENT_ACCESS_RESTRICTED = "isDocumentAccessRestricted";

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
                """
                        (xhrResponse)=>{
                            if(xhrResponse.contentType.value=='application/json'){
                                const obj=JSON.parse(xhrResponse.data);
                                return obj.message;
                            } else if(xhrResponse.contentType.value=='text/plain'){
                                const code=xhrResponse.data;
                                switch(code){
                                case 'page.login.action.badCredentials':
                                    return 'Incorrect user name or password entered';
                                    break;
                                case 'queryFailed':
                                    return 'An error occurred during query to external community';
                                    break;
                                default:
                                    return 'unknown error code';
                                    break;
                                };
                            };
                            return 'unknown response';
                        };""",
                JsParameter.getInstance("xhrResponse", JsType.OBJECT))
        );

        // end switch
        add(createFunction(PARSE_WARNING, JsType.STRING,
                """
                        (warning)=>{
                            if(warning==undefined||warning==null){
                                return 'Unknown warning';
                            };
                            if(warning.message!==undefined){
                                // note: other type of object?
                                return warning.message;
                            }
                            
                            switch(warning.warningCode){
                            case 'queryRestricted':
                                return 'Only your own patients are shown ' + warning.warningContext;
                            case 'queryFailed':
                                return 'An error occurred during query to external community ' + warning.warningContext;
                            };
                            return 'Unknown error: '+warning.warningCode+' ('+warning.warningContext+')';
                        };""",
                JsParameter.getInstance("warningCode", JsType.STRING),
                JsParameter.getInstance("warningContext", JsType.STRING),
                JsParameter.getInstance("message", JsType.STRING))
        );

        add(createFunction(IS_DOCUMENT_ACCESS_RESTRICTED, JsType.BOOLEAN,
                """
                        (warning)=>{
                            return warning.errorCodes!=undefined&&warning.errorCodes.includes('forcare.BppcOrXuaAuthorizationFailed');
                        };""",
                JsParameter.getInstance("warningCode", JsType.STRING),
                JsParameter.getInstance("warningContext", JsType.STRING),
                JsParameter.getInstance("message", JsType.STRING))
        );


        // if
        // end if
        add(createFunction(PARSE_LOGIN, JsType.STRING,
                /*
                {
                    "response": {
                        "status": "ok",
                        "jwt": "eyJhb..."
                    }
                }
                 */
                """
                        (xhrResponse)=>{
                            if(xhrResponse.contentType.value=='application/json'){
                                const obj=JSON.parse(xhrResponse.data);
                                if(obj.response.status!='ok'){
                                    throw new Error('login failed');
                                };
                                return obj.response.jwt;
                            };
                            throw new Error('invalid login response');
                        };""",

                JsParameter.getInstance("xhrResponse", JsType.OBJECT))
        );

        add(createFunction(CALCULATE_AGE, JsType.NUMBER,
                "(date)=>{"
                + ""
                + "};",
                JsParameter.getInstance("xhrResponse", JsType.STRING))
        );

        add(createFunction(CREATE_WADO_THUMB_URL, JsType.STRING,

                // IMAGE: /viewer/services/connect/proxy/wado/PACS
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

                // Structured Report
                // https://localhost/viewer/services/connect/sr/retrieve.html
                // ?studyUID=1.3.51.0.7.99.2155959091.26405.877614199.0
                // &seriesUID=1.2.528.1.1001.100.3.1853.241.52240694848.20091008125618906
                // &objectUID=1.2.528.1.1001.100.22.1853.241.52240694848.20091008130554296
                // &aeTitle=PACS
                // &patientID=7234567890
                // &patientIDAuth=1.3.6.1.4.1.21367.2005.3.7
                // &retrieveLocationUid=3.2.3
                // &homeCommunityUid=1.2.3"

                """
                        (modality, studyUID, seriesUID, objectUID, contentType, aeTitle, retrieveLocationUID, homeCommunityUid, patientID, patientIDAuth)=>{
                                                
                        if(modality=='SR'){
                            var url='http://localhost:8080/viewer/2301-4/css/common-philips/img/kosViewer/document.svg';
                            return url;
                        };
                                                
                        var url='http://localhost:8080/viewer/services/connect/proxy/wado/PACS';
                        url+='?requestType=WADO';
                        url+='&studyUID='+studyUID;
                        url+='&seriesUID='+seriesUID;
                        url+='&objectUID='+objectUID;
                        url+='&contentType='+contentType;
                        url+='&globalLocationUid='+retrieveLocationUID;
                        url+='&homeCommunityUid='+homeCommunityUid;
                        url+='&patientID='+patientID;
                        url+='&patientIDAuth='+patientIDAuth;
                        url+='&columns=128';
                        url+='&rows=128';
                        return url;
                        };""",

                JsParameter.getInstance("modality", JsType.STRING),
                JsParameter.getInstance("studyUID", JsType.STRING),
                JsParameter.getInstance("seriesUID", JsType.STRING),
                JsParameter.getInstance("objectUID", JsType.STRING),
                JsParameter.getInstance("contentType", JsType.STRING),
                JsParameter.getInstance("aeTitle", JsType.STRING),
                JsParameter.getInstance("retrieveLocationUID", JsType.STRING),
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

                """
                        (modality, studyUID, seriesUID, objectUID, contentType, aeTitle, retrieveLocationUID, homeCommunityUid, patientID, patientIDAuth)=>{
                        
                        if(modality=='SR'){
                            var url='https://localhost/viewer/services/connect/sr/retrieve.html';
                            url+='?studyUID='+studyUID;
                            url+='&seriesUID='+seriesUID;
                            url+='&objectUID='+objectUID;
                            url+='&aeTitle='+aeTitle;
                            url+='&patientID='+patientID;
                            url+='&patientIDAuth='+patientIDAuth;
                            url+='&retrieveLocationUid='+retrieveLocationUID;
                            url+='&homeCommunityUid='+homeCommunityUid;
                            return url;
                        };
                        
                        var url='http://localhost:8080/viewer/services/connect/proxy/wado/PACS';
                        url+='?requestType=WADO';
                        url+='&studyUID='+studyUID;
                        url+='&seriesUID='+seriesUID;
                        url+='&objectUID='+objectUID;
                        url+='&contentType='+contentType;
                        url+='&globalLocationUid='+retrieveLocationUID;
                        url+='&homeCommunityUid='+homeCommunityUid;
                        url+='&patientID='+patientID;
                        url+='&patientIDAuth='+patientIDAuth;
                        return url;
                        };""",

                JsParameter.getInstance("modality", JsType.STRING),
                JsParameter.getInstance("studyUID", JsType.STRING),
                JsParameter.getInstance("seriesUID", JsType.STRING),
                JsParameter.getInstance("objectUID", JsType.STRING),
                JsParameter.getInstance("contentType", JsType.STRING),
                JsParameter.getInstance("aeTitle", JsType.STRING),
                JsParameter.getInstance("retrieveLocationUID", JsType.STRING),
                JsParameter.getInstance("homeCommunityUid", JsType.STRING),
                JsParameter.getInstance("patientID", JsType.STRING),
                JsParameter.getInstance("patientIDAuth", JsType.STRING))
        );
    }
}
