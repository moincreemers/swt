package com.philips.dmis.swt.ui.toolkit.js.controller;

import com.philips.dmis.swt.ui.toolkit.ExtModuleInvoke;
import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.dto.ExtModuleEvent;
import com.philips.dmis.swt.ui.toolkit.js.*;
import com.philips.dmis.swt.ui.toolkit.js.global.*;
import com.philips.dmis.swt.ui.toolkit.js.state.JsStateModule;
import com.philips.dmis.swt.ui.toolkit.js.state.ViewTypeVariable;
import com.philips.dmis.swt.ui.toolkit.js.widget.*;
import com.philips.dmis.swt.ui.toolkit.widgets.ViewType;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;

import java.util.Arrays;
import java.util.List;

public class DisplayFunction implements JsFunction {
    @Override
    public boolean isMemberOf(Widget widget, WidgetType widgetType) {
        return true;
    }

    @Override
    public boolean isPublic() {
        return false;
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
        dependencies.add(GetDocumentHashFunction.class);
        dependencies.add(IsValidHashFunction.class);
        dependencies.add(DefaultPageIdConst.class);
        dependencies.add(ClearDocumentHashFunction.class);
        dependencies.add(GetScrollPositionFunction.class);
        dependencies.add(RefreshPageFunction.class);
        dependencies.add(PagesConst.class);
        dependencies.add(GetElementFunction.class);
        dependencies.add(EventHandlerFunction.class);
    }

    @Override
    public void getParameters(List<JsParameter> parameters) {
    }

    @Override
    public void renderJs(Toolkit toolkit, JsWriter js) {
        js.append("()=>{");
        js.trace(this);

        js.append("var defaultPageId=%s;", JsPageControllerModule.getId(DefaultPageIdConst.class));
        js.append("var selectedPageId=null;");
        js.append("var pagesToDisplay=[];");
        js.append("var hash=%s();", JsGlobalModule.getQualifiedId(GetDocumentHashFunction.class));
        js.append("if(%s(hash)){", JsGlobalModule.getQualifiedId(IsValidHashFunction.class)); // if

        js.append("selectedPageId=(hash.p.length==0)?defaultPageId:hash.p[0];");
        js.append("var pageHistory=(hash.o.length==0)?[defaultPageId]:Object.assign([],hash.o);");
        js.append("pageHistory.reverse();");
        js.append("pagesToDisplay.push(selectedPageId);");
        js.append("var isDialog=(window[selectedPageId].%s!='%s');",
                ViewTypeVariable.ID, ViewType.DEFAULT.name());

        js.append("for(const j in pageHistory){"); // for
        js.append("if(window[pageHistory[j]].%s=='%s'){",
                ViewTypeVariable.ID, ViewType.DIALOG.name()); // if
        js.append("pagesToDisplay.push(pageHistory[j]);");
        js.append("};"); // end if
        js.append("if(isDialog&&window[pageHistory[j]].%s=='%s'){",
                ViewTypeVariable.ID, ViewType.DEFAULT.name()); // if
        js.append("pagesToDisplay.push(pageHistory[j]);");
        js.append("break;");
        js.append("};"); // end if
        js.append("};"); // end for

        js.append("}else{"); // if/else

        js.debug("console.log('invalid hash detected, showing default page');");
        js.append("%s();", JsGlobalModule.getQualifiedId(ClearDocumentHashFunction.class));
        js.append("return;");
        js.append("};");

        //js.info("console.log('pagesToDisplay',pagesToDisplay,'isDialog',isDialog,'pageHistory',pageHistory);");

        // note: the rest of the func is for displaying/hiding/scroll etc.
        js.append("const zIndex=1000;");
        js.append("const pagesInOrder=Object.assign([],pagesToDisplay);");
        js.append("pagesInOrder.reverse();");
        js.append("for(const k in %s){", JsPageControllerModule.getId(PagesConst.class)); // for
        js.append("var pageId=%s[k];", JsPageControllerModule.getId(PagesConst.class));
        // js.debug("console.log(pageId);");

        js.append("var pageElement=%s(pageId);", JsWidgetModule.getQualifiedId(GetElementFunction.class));
        js.append("var displayPage=pagesToDisplay.includes(pageId);");
        js.append("var activePage=(selectedPageId==pageId);");
        js.append("var currentValue=pageElement.style.display;");
        js.append("if(displayPage){"); // if
        js.append("var position=pagesInOrder.lastIndexOf(pageId);");
        //js.info("console.log(pageId,'position',position);");
        js.append("pageElement.style.zIndex=(zIndex+position);");
        js.append("if(currentValue!='block'){");
        js.append("%s(pageId,'%s');", JsWidgetModule.getQualifiedId(RefreshPageFunction.class), JsStateModule.REASON_SHOW);
        js.append("pageElement.style.display='block';");
        js.append("%s(pageId);", JsWidgetModule.getQualifiedId(EventHandlerFunction.OnShowEventHandlerFunction.class));
        js.append("};");
        js.append("}else{"); // else
        js.append("pageElement.style.zIndex='';");
        js.append("if(currentValue=='block'){");
        js.append("pageElement.style.display='none';");
        js.append("%s(pageId);", JsWidgetModule.getQualifiedId(EventHandlerFunction.OnHideEventHandlerFunction.class));
        js.append("};");
        js.append("};"); // end if
        js.append("var isActive=pageElement.classList.contains('tk-page-active');");
        js.append("if(activePage){"); // if
        js.append("pageElement.classList.add('tk-page-active');");
        //js.append("window[pageId].%s().scrollIntoViewIfNeeded();", GetElementFunction.ID);
        js.append("if(!isActive){"); // if
        js.append("%s(pageId);", JsWidgetModule.getQualifiedId(EventHandlerFunction.OnActivateEventHandlerFunction.class));
        js.append("};"); // end if
        js.append("}else{"); // else
        js.append("pageElement.classList.remove('tk-page-active');");
        js.append("if(isActive){"); // if
        js.append("%s(pageId);", JsWidgetModule.getQualifiedId(EventHandlerFunction.OnDeactivateEventHandlerFunction.class));
        js.append("};"); // end if
        js.append("};"); // end if
        js.append("};"); // end for

        js.append("var hideOverflow=['%s'];", String.join("','", Arrays.stream(ViewType.values())
                .filter(ViewType::isHideScroll).map(Enum::name).toList()));
        js.append("var hideScroll=hideOverflow.includes(window[selectedPageId].%s);", ViewTypeVariable.ID);
        js.append("document.body.style.overflow='auto';");
        js.append("var scrollPos=%s();", JsGlobalModule.getQualifiedId(GetScrollPositionFunction.class));
        //js.debug("console.log('scrollPos',scrollPos);");
        js.append("document.body.style.overflow=hideScroll?'hidden':'';");
        js.append("if(isDialog){");
        js.append("%s(selectedPageId).style.top=scrollPos.y+'px';", JsWidgetModule.getQualifiedId(GetElementFunction.class));
        // note: if active page is a dialog, then set focus to it (dialog pages have a tab index for this purpose).
        js.append("%s(selectedPageId).focus();", JsWidgetModule.getQualifiedId(GetInnerElementFunction.class));
        js.append("};");

        ExtModuleInvoke.renderIndirectCall(ExtModuleEvent.DISPLAY, "selectedPageId", null, js);

        // ensure the splash screen is hidden
        js.append("var splash=document.getElementById('splash');");
        js.append("if(splash!=null&&splash.style.display!='none'){");
        js.append("splash.style.display='none';");
        js.append("};");

        js.append("}"); // end function
    }
}
