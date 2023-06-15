package com.philips.dmis.swt.ui.toolkit.js.widget;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.js.*;
import com.philips.dmis.swt.ui.toolkit.js.state.*;
import com.philips.dmis.swt.ui.toolkit.widgets.JsRenderException;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;

import java.util.List;

public class CloneFunction implements JsFunction {
    public static final String ID = "clone";

    @Override
    public boolean isMemberOf(Widget widget, WidgetType widgetType) {
        return true;
    }

    @Override
    public boolean isPublic() {
        return true;
    }

    @Override
    public String getPublicName(String id) {
        return id;
    }

    @Override
    public JsType getType() {
        return JsType.OBJECT;
    }

    @Override
    public void getDependencies(List<Class<? extends JsMember>> dependencies) {
    }

    @Override
    public void getParameters(List<JsParameter> parameters) {
        parameters.add(JsParameter.getInstance("templateId", JsType.STRING));
        parameters.add(JsParameter.getInstance("parentId", JsType.STRING));
        parameters.add(JsParameter.getInstance("dataKeyField", JsType.STRING));
        parameters.add(JsParameter.getInstance("dataKey", JsType.STRING));
        parameters.add(JsParameter.getInstance("isRoot", JsType.BOOLEAN));
    }

    @Override
    public void renderJs(Toolkit toolkit, JsWriter js) throws JsRenderException {
        js.append("(templateId,parentId,dataKeyField,dataKey,isRoot)=>{");
        js.trace(this);

        js.append("if(isRoot==undefined||isRoot==null){isRoot=true;};");

        js.append("window.IdGen=undefined===window.IdGen?0:window.IdGen;");
        js.append("const slaveId='g'+window.IdGen++;");

        js.append("const widget=window[templateId];");
        js.append("const clone={};");
        js.append("for(const property in widget){");

        // Event handlers
        js.append("if(property=='%s'){", EventHandlersVariable.ID);
        js.append("clone[property]={};");
        js.append("for(const handlerName in widget[property]){");
        js.append("var h={pageId:'',widgetId:'',fn:(eventContext)=>{");
        js.append("const eventHandlerFunction=%s[handlerName];", JsWidgetModule.ID);
        js.append("%s(templateId,eventHandlerFunction,eventContext.domEvent,slaveId,dataKey);",
                JsWidgetModule.getQualifiedId(RaiseEventFunction.class));
        js.append("}"); // end function
        js.append("};"); // end h

        js.append("clone[property][handlerName]=[h];");
        js.append("};");
        js.append("continue;");
        js.append("};");

        // Child widgets property
        js.append("if(property=='%s'){", ChildWidgetsVariable.ID);
        js.append("clone[property]=[];");
        js.append("continue;");
        js.append("};");

        // DataAdapters property
        js.append("if(property=='%s'){", DataAdaptersVariable.ID);
        js.append("clone[property]={};");
        js.append("continue;");
        js.append("};");

        // Other properties that must not be cloned
        js.append("if(property=='%s'){clone[property]=[];};", SlavesVariable.ID);
        js.append("if(property=='%s'){clone[property]={};};", DataKeysVariable.ID);
        js.append("if(property=='%s'){clone[property]={};};", SubstitutionsVariable.ID);

        js.debug("console.log('cloning property', property);");
        js.append("clone[property]=structuredClone(widget[property]);");
        js.append("};");

        js.append("if(isRoot){");
        js.append("clone.%s['name']=dataKey;", HtmlAttributesVariable.ID);
        js.append("};");
        js.append("clone.%s=templateId;", TemplateIdVariable.ID);
        js.append("clone.%s=slaveId;", WidgetIdVariable.ID);
        js.append("clone.%s={field:dataKeyField,value:dataKey};", DataKeyVariable.ID);
        js.append("clone.%s=parentId;", ParentWidgetIdVariable.ID);
        js.append("clone.%s={};", SyncVariable.ID);

        js.append("window[slaveId]=clone;");
        js.append("%s(templateId,slaveId);", JsWidgetModule.getId(AddSlaveFunction.class));
        js.append("%s(templateId,slaveId,dataKey);", JsWidgetModule.getId(AddDataKeyFunction.class));
        js.append("%s(dataKey,templateId,slaveId);", JsWidgetModule.getId(AddSubstitutionFunction.class));

        // Clone child widgets
        js.append("for(const c in widget.%s){", ChildWidgetsVariable.ID);
        js.append("var templateChildWidgetId=widget.%s[c];", ChildWidgetsVariable.ID);
        js.append("var childClone=%s(templateChildWidgetId,slaveId,dataKeyField,dataKey,false);",
                JsWidgetModule.getId(CloneFunction.class));
        js.append("clone.%s.push(childClone.%s);", ChildWidgetsVariable.ID, WidgetIdVariable.ID);
        js.append("};");

        js.append("if(isRoot){");
        js.append("%s(slaveId);", JsWidgetModule.getQualifiedId(InitWidgetFunction.class));
        // note: ensure that the root of the instance is displayed as templates are usually hidden.
        js.append("%s(slaveId).style.display='';", JsWidgetModule.getQualifiedId(GetElementFunction.class));
        js.append("};");

        js.append("return clone;");

        js.append("}");
    }
}
