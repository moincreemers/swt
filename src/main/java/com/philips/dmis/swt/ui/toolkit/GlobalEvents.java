package com.philips.dmis.swt.ui.toolkit;

import com.philips.dmis.swt.ui.toolkit.events.*;
import com.philips.dmis.swt.ui.toolkit.js.HasJS;
import com.philips.dmis.swt.ui.toolkit.js.JsWriter;
import com.philips.dmis.swt.ui.toolkit.js.controller.GetCurrentPageFunction;
import com.philips.dmis.swt.ui.toolkit.js.controller.JsPageControllerModule;
import com.philips.dmis.swt.ui.toolkit.js.state.EventHandlersVariable;
import com.philips.dmis.swt.ui.toolkit.widgets.JsRenderException;

import java.util.HashSet;
import java.util.Set;

public class GlobalEvents implements HasJS {
    public static final String EVENT_PARAMETER_NAME = "e";
    private static final Set<String> eventHandlers = new HashSet<>();

    public static void onAfterPrint() {
        eventHandlers.add(AfterPrintEventHandler.NAME);
    }

    public static void onBeforePrint() {
        eventHandlers.add(BeforePrintEventHandler.NAME);
    }

    public static void onBeforeUnload() {
        eventHandlers.add(BeforeUnloadEventHandler.NAME);
    }

    public static void onBlur() {
        eventHandlers.add(BlurEventHandler.NAME);
    }

    public static void onError() {
        eventHandlers.add(ErrorEventHandler.NAME);
    }

    public static void onFocus() {
        eventHandlers.add(FocusEventHandler.NAME);
    }

    public static void onLanguageChange() {
        eventHandlers.add(LanguageChangeEventHandler.NAME);
    }

    public static void onMessage() {
        eventHandlers.add(MessageEventHandler.NAME);
    }

    public static void onOffline() {
        eventHandlers.add(OfflineEventHandler.NAME);
    }

    public static void onOnline() {
        eventHandlers.add(OnlineEventHandler.NAME);
    }

    public static void onRedo() {
        eventHandlers.add(RedoEventHandler.NAME);
    }

    public static void onUndo() {
        eventHandlers.add(UndoEventHandler.NAME);
    }

    public static void onUnload() {
        eventHandlers.add(UnloadEventHandler.NAME);
    }

    public static void onKeyPress() {
        eventHandlers.add(KeyPressEventHandler.NAME);
    }

    public static void onKeyDown() {
        eventHandlers.add(KeyDownEventHandler.NAME);
    }

    public static void onKeyUp() {
        eventHandlers.add(KeyUpEventHandler.NAME);
    }

    public static void onColorSchemeChange() {
        eventHandlers.add(ColorSchemeChangeEventHandler.NAME);
    }

    @Override
    public void renderJs(Toolkit toolkit, JsWriter js) throws JsRenderException {
        // add event handlers
        js.append("const initGlobalEvents=()=>{");
        if (eventHandlers.contains(AfterPrintEventHandler.NAME)) {
            js.append("window.addEventListener('afterprint',(e)=>{%s(e);});", AfterPrintEventHandler.NAME);
        }
        if (eventHandlers.contains(BeforePrintEventHandler.NAME)) {
            js.append("window.addEventListener('beforeprint',(e)=>{%s(e);});", BeforePrintEventHandler.NAME);
        }
        if (eventHandlers.contains(BeforeUnloadEventHandler.NAME)) {
            js.append("window.addEventListener('beforeunload',(e)=>{%s(e);});", BeforeUnloadEventHandler.NAME);
        }
        if (eventHandlers.contains(BlurEventHandler.NAME)) {
            js.append("window.addEventListener('blur',(e)=>{%s(e);});", BlurEventHandler.NAME);
        }
        if (eventHandlers.contains(ErrorEventHandler.NAME)) {
            js.append("window.addEventListener('error',(e)=>{%s(e);});", ErrorEventHandler.NAME);
        }
        if (eventHandlers.contains(FocusEventHandler.NAME)) {
            js.append("window.addEventListener('focus',(e)=>{%s(e);});", FocusEventHandler.NAME);
        }
        if (eventHandlers.contains(LanguageChangeEventHandler.NAME)) {
            js.append("window.addEventListener('languagechange',(e)=>{%s(e);});", LanguageChangeEventHandler.NAME);
        }
        if (eventHandlers.contains(MessageEventHandler.NAME)) {
            js.append("window.addEventListener('message',(e)=>{%s(e);});", MessageEventHandler.NAME);
        }
        if (eventHandlers.contains(OfflineEventHandler.NAME)) {
            js.append("window.addEventListener('offline',(e)=>{%s(e);});", OfflineEventHandler.NAME);
        }
        if (eventHandlers.contains(OnlineEventHandler.NAME)) {
            js.append("window.addEventListener('online',(e)=>{%s(e);});", OnlineEventHandler.NAME);
        }
        if (eventHandlers.contains(RedoEventHandler.NAME)) {
            js.append("window.addEventListener('redo',(e)=>{%s(e);});", RedoEventHandler.NAME);
        }
        if (eventHandlers.contains(UndoEventHandler.NAME)) {
            js.append("window.addEventListener('undo',(e)=>{%s(e);});", UndoEventHandler.NAME);
        }
        if (eventHandlers.contains(UnloadEventHandler.NAME)) {
            js.append("window.addEventListener('unload',(e)=>{%s(e);});", UnloadEventHandler.NAME);
        }
        if (eventHandlers.contains(KeyPressEventHandler.NAME)) {
            js.append("window.addEventListener('keypress',(e)=>{%s(e);});", KeyPressEventHandler.NAME);
        }
        if (eventHandlers.contains(KeyDownEventHandler.NAME)) {
            js.append("window.addEventListener('keydown',(e)=>{%s(e);});", KeyDownEventHandler.NAME);
        }
        if (eventHandlers.contains(KeyUpEventHandler.NAME)) {
            js.append("window.addEventListener('keyup',(e)=>{%s(e);});", KeyUpEventHandler.NAME);
        }
        if (eventHandlers.contains(ColorSchemeChangeEventHandler.NAME)) {
            js.append("if(window.matchMedia('(prefers-color-scheme:light)').matches){%s(%s);};",
                    ColorSchemeChangeEventHandler.NAME,
                    CustomEvent.valueOf(new ColorSchemeChangeCustomEvent(ColorSchemeChangeCustomEvent.SCHEME_LIGHT)));
            js.append("if(window.matchMedia('(prefers-color-scheme:dark)').matches){%s(%s);};",
                    ColorSchemeChangeEventHandler.NAME,
                    CustomEvent.valueOf(new ColorSchemeChangeCustomEvent(ColorSchemeChangeCustomEvent.SCHEME_DARK)));
            js.append("window.matchMedia('(prefers-color-scheme:dark)').addEventListener('change',e=>{%s(e.matches?%s:%s);});",
                    ColorSchemeChangeEventHandler.NAME,
                    CustomEvent.valueOf(new ColorSchemeChangeCustomEvent(ColorSchemeChangeCustomEvent.SCHEME_DARK)),
                    CustomEvent.valueOf(new ColorSchemeChangeCustomEvent(ColorSchemeChangeCustomEvent.SCHEME_LIGHT)));
        }
        js.append("};");

        for (String eventHandlerName : eventHandlers) {
            js.append("const %s=(event)=>{", eventHandlerName);

            js.append("const currentPageId=%s();", JsPageControllerModule.getQualifiedId(GetCurrentPageFunction.class));
            js.append("const eventSourceId=(event.srcElement==undefined||event.srcElement==null)?'':event.srcElement.id;");
            js.append("const widgetIds=[];");
            js.append("if(currentPageId!=null&&currentPageId!=''){widgetIds.push(currentPageId);};");
            js.append("if(eventSourceId!=null&&eventSourceId!=''){widgetIds.push(eventSourceId);};");
            js.append("for(const i in widgetIds){"); // for
            js.append("const widget=window[widgetIds[i]];");
            js.append("const c=widget.%s['%s'];", EventHandlersVariable.ID, eventHandlerName.toLowerCase());
            js.append("for(const i in c){"); // for
            js.append("var h=c[i];");
            js.append("if((h.pageId==''||h.pageId==currentPageId)" +
                    "&&(eventSourceId==''||h.widgetId==''||h.widgetId==eventSourceId)){");
            js.append("h.fn(event);");
            js.append("};"); // end if
            js.append("};"); // end for
            js.append("};"); // end for



            js.append("};");
        }
    }
}
