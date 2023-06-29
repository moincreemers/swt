package com.philips.dmis.swt.ui.toolkit.data;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.dto.Order;
import com.philips.dmis.swt.ui.toolkit.dto.ServiceResponse;
import com.philips.dmis.swt.ui.toolkit.dto.TransformationMetadata;
import com.philips.dmis.swt.ui.toolkit.js.JsWriter;
import com.philips.dmis.swt.ui.toolkit.js.widget.GetOrderingFunction;
import com.philips.dmis.swt.ui.toolkit.js.widget.JsWidgetModule;
import com.philips.dmis.swt.ui.toolkit.reflect.DtoUtil;
import com.philips.dmis.swt.ui.toolkit.widgets.HasOrderingControls;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;

/**
 * Adds ordering.
 */
public class InteractiveOrderingDataAdapter extends DataAdapter {
    private HasOrderingControls<?> widget;

    public InteractiveOrderingDataAdapter(HasOrderingControls<?> widget) {
        super(DEFAULT_PATH);
        this.widget = widget;
    }

    @Override
    public void renderJs(Toolkit toolkit, Widget widget, JsWriter js) {
        js.append("(serviceResponse,unmodifiedResponse)=>{");
        js.trace(this);

        js.append("const ordering=%s('%s');",
                JsWidgetModule.getQualifiedId(GetOrderingFunction.class),
                this.widget.asWidget().getId());
        js.append("const items=serviceResponse%s;", getPath());

        js.append("const collator=new Intl.Collator();");
        js.append("const fn=(item0,item1)=>{"); // function
        js.append("var c=0;");
        js.append("for(const col in ordering){"); // for
        js.append("var o=ordering[col];");
        js.append("var v0=item0[o.source];");
        js.append("var v1=item1[o.source];");
        js.append("if(v0==undefined||v0==null||v1==undefined||v1==null){break;};");

        js.append("if(typeof v0=='number'){"); // if
        js.append("c=v0-v1;");
        js.append("}else{"); // else
        js.append("c=collator.compare(v0,v1);");
        js.append("};"); // end if

        js.append("if(c!=0){"); // if
        js.append("if(o.order=='%s'){", Order.DESCENDING.name()); // if
        js.append("c*=-1;");
        js.append("};"); // end if
        js.append("break;"); // break for
        js.append("};"); // end if

        js.append("};"); // end for
        js.append("return c;");
        js.append("};"); // end function

        js.append("items.sort(fn);");

        js.append("serviceResponse.meta['%s']=true;",
                ServiceResponse.META_ORDERING_ENABLED);

        js.append("serviceResponse.meta['%s']=Object.assign([],serviceResponse.meta['%s']);",
                ServiceResponse.META_TRANSFORMATIONS, ServiceResponse.META_TRANSFORMATIONS);
        js.append("serviceResponse.meta['%s'].push(%s);",
                ServiceResponse.META_TRANSFORMATIONS,
                DtoUtil.valueOf(new TransformationMetadata(getId(), getClass().getSimpleName())));

        js.debug("console.log('SortingDataAdapter after',serviceResponse);");
        js.append("return serviceResponse;");

        js.append("}"); // end function
    }
}
