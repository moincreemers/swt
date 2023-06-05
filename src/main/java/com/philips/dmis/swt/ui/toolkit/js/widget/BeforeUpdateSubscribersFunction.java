package com.philips.dmis.swt.ui.toolkit.js.widget;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.js.*;
import com.philips.dmis.swt.ui.toolkit.js.state.CacheTypeVariable;
import com.philips.dmis.swt.ui.toolkit.js.state.ImplementsVariable;
import com.philips.dmis.swt.ui.toolkit.js.state.SubscribersVariable;
import com.philips.dmis.swt.ui.toolkit.js.state.WidgetTypeVariable;
import com.philips.dmis.swt.ui.toolkit.widgets.DataSourceSupplier;
import com.philips.dmis.swt.ui.toolkit.widgets.JsRenderException;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;

import java.util.List;

public class BeforeUpdateSubscribersFunction implements JsFunction {
    public static final String ID = "beforeUpdateSubscribers";

    @Override
    public boolean isMemberOf(Widget widget, WidgetType widgetType) {
        return widget instanceof DataSourceSupplier;
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
        return JsType.VOID;
    }

    @Override
    public void getDependencies(List<Class<? extends JsMember>> dependencies) {
        dependencies.add(UpdateFunction.class);
    }

    @Override
    public void getParameters(List<JsParameter> parameters) {
        parameters.add(JsParameter.getInstance("id", JsType.STRING));
        parameters.add(JsParameter.getInstance("reason", JsType.STRING));
        parameters.add(JsParameter.getInstance("cacheType", JsType.STRING));
    }

    @Override
    public void renderJs(Toolkit toolkit, JsWriter js) throws JsRenderException {
        js.append("(id,reason,cacheType)=>{");
        js.trace(this);

        js.append("const widget=window[id];");
        js.append("const widgetType=widget.%s;", WidgetTypeVariable.ID);
        js.append("const implements=widget.%s;", ImplementsVariable.ID);
        js.append("if(implements.includes('%s')){", DataSourceSupplier.class.getSimpleName()); // if
        // note: we do not use the provided cache type
        js.append("const cacheType1=widget.%s;", CacheTypeVariable.ID);
        js.append("for(const i in widget.%s){", SubscribersVariable.ID); // for
        js.append("var subscriber=widget.%s[i];", SubscribersVariable.ID);
        js.append("%s(subscriber.subscriberId,reason,cacheType1,subscriber.dataSourceUsage,subscriber.dataSourceId);",
                JsWidgetModule.getId(BeforeUpdateFunction.class));
        js.append("};"); // end for
        js.append("};"); // end if

        js.append("}");
    }
}
