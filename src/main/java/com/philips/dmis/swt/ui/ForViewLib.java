package com.philips.dmis.swt.ui;

import com.philips.dmis.swt.ui.toolkit.js.JsParameter;
import com.philips.dmis.swt.ui.toolkit.js.JsType;
import com.philips.dmis.swt.ui.toolkit.widgets.Code;

public class ForViewLib extends Code {
    public static final String PARSE_ERROR = "parseError";
    public static final String PARSE_WARNING = "parseWarning";
    public static final String PARSE_LOGIN = "parseLogin";
    public static final String CALCULATE_AGE = "calcAge";

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
    }
}
