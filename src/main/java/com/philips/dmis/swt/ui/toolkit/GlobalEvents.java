package com.philips.dmis.swt.ui.toolkit;

import com.philips.dmis.swt.ui.toolkit.events.*;
import com.philips.dmis.swt.ui.toolkit.js.HasJS;
import com.philips.dmis.swt.ui.toolkit.js.JsWriter;
import com.philips.dmis.swt.ui.toolkit.widgets.JsRenderException;

import java.util.ArrayList;
import java.util.List;

public class GlobalEvents implements HasJS {
    public static final String EVENT_PARAMETER_NAME = "e";
    private static final List<EventHandler> eventHandlers = new ArrayList<>();

    public static void onAfterPrint(AfterPrintEventHandler eventHandler) {
        eventHandlers.add(eventHandler);
    }

    public static void onBeforePrint(BeforePrintEventHandler eventHandler) {
        eventHandlers.add(eventHandler);
    }

    public static void onBeforeUnload(BeforeUnloadEventHandler eventHandler) {
        eventHandlers.add(eventHandler);
    }

    public static void onBlur(BlurEventHandler eventHandler) {
        eventHandlers.add(eventHandler);
    }

    public static void onError(ErrorEventHandler eventHandler) {
        eventHandlers.add(eventHandler);
    }

    public static void onFocus(FocusEventHandler eventHandler) {
        eventHandlers.add(eventHandler);
    }

    public static void onLanguageChange(LanguageChangeEventHandler eventHandler) {
        eventHandlers.add(eventHandler);
    }

    public static void onMessage(MessageEventHandler eventHandler) {
        eventHandlers.add(eventHandler);
    }

    public static void onOffline(OfflineEventHandler eventHandler) {
        eventHandlers.add(eventHandler);
    }

    public static void onOnline(OnlineEventHandler eventHandler) {
        eventHandlers.add(eventHandler);
    }

    public static void onRedo(RedoEventHandler eventHandler) {
        eventHandlers.add(eventHandler);
    }

    public static void onUndo(UndoEventHandler eventHandler) {
        eventHandlers.add(eventHandler);
    }

    public static void onUnload(UnloadEventHandler eventHandler) {
        eventHandlers.add(eventHandler);
    }

    public static void onKeyPress(KeyPressEventHandler eventHandler) {
        eventHandlers.add(eventHandler);
    }

    public static void onKeyDown(KeyDownEventHandler eventHandler) {
        eventHandlers.add(eventHandler);
    }

    public static void onKeyUp(KeyUpEventHandler eventHandler) {
        eventHandlers.add(eventHandler);
    }

    public static void onColorSchemeChange(ColorSchemeChangeEventHandler eventHandler) {
        eventHandlers.add(eventHandler);
    }

    @Override
    public void renderJs(Toolkit toolkit, JsWriter js) throws JsRenderException {
        List<String> eventHandlerNames = eventHandlers.stream().map(EventHandler::getName).sorted().distinct().toList();

        // add event handlers
        js.append("const initGlobalEvents=()=>{");
        if (eventHandlerNames.contains(AfterPrintEventHandler.NAME)) {
            js.append("window.addEventListener('afterprint',(e)=>{%s(e);});", AfterPrintEventHandler.NAME);
        }
        if (eventHandlerNames.contains(BeforePrintEventHandler.NAME)) {
            js.append("window.addEventListener('beforeprint',(e)=>{%s(e);});", BeforePrintEventHandler.NAME);
        }
        if (eventHandlerNames.contains(BeforeUnloadEventHandler.NAME)) {
            js.append("window.addEventListener('beforeunload',(e)=>{%s(e);});", BeforeUnloadEventHandler.NAME);
        }
        if (eventHandlerNames.contains(BlurEventHandler.NAME)) {
            js.append("window.addEventListener('blur',(e)=>{%s(e);});", BlurEventHandler.NAME);
        }
        if (eventHandlerNames.contains(ErrorEventHandler.NAME)) {
            js.append("window.addEventListener('error',(e)=>{%s(e);});", ErrorEventHandler.NAME);
        }
        if (eventHandlerNames.contains(FocusEventHandler.NAME)) {
            js.append("window.addEventListener('focus',(e)=>{%s(e);});", FocusEventHandler.NAME);
        }
        if (eventHandlerNames.contains(LanguageChangeEventHandler.NAME)) {
            js.append("window.addEventListener('languagechange',(e)=>{%s(e);});", LanguageChangeEventHandler.NAME);
        }
        if (eventHandlerNames.contains(MessageEventHandler.NAME)) {
            js.append("window.addEventListener('message',(e)=>{%s(e);});", MessageEventHandler.NAME);
        }
        if (eventHandlerNames.contains(OfflineEventHandler.NAME)) {
            js.append("window.addEventListener('offline',(e)=>{%s(e);});", OfflineEventHandler.NAME);
        }
        if (eventHandlerNames.contains(OnlineEventHandler.NAME)) {
            js.append("window.addEventListener('online',(e)=>{%s(e);});", OnlineEventHandler.NAME);
        }
        if (eventHandlerNames.contains(RedoEventHandler.NAME)) {
            js.append("window.addEventListener('redo',(e)=>{%s(e);});", RedoEventHandler.NAME);
        }
        if (eventHandlerNames.contains(UndoEventHandler.NAME)) {
            js.append("window.addEventListener('undo',(e)=>{%s(e);});", UndoEventHandler.NAME);
        }
        if (eventHandlerNames.contains(UnloadEventHandler.NAME)) {
            js.append("window.addEventListener('unload',(e)=>{%s(e);});", UnloadEventHandler.NAME);
        }
        if (eventHandlerNames.contains(KeyPressEventHandler.NAME)) {
            js.append("window.addEventListener('keypress',(e)=>{%s(e);});", KeyPressEventHandler.NAME);
        }
        if (eventHandlerNames.contains(KeyDownEventHandler.NAME)) {
            js.append("window.addEventListener('keydown',(e)=>{%s(e);});", KeyDownEventHandler.NAME);
        }
        if (eventHandlerNames.contains(KeyUpEventHandler.NAME)) {
            js.append("window.addEventListener('keyup',(e)=>{%s(e);});", KeyUpEventHandler.NAME);
        }
        if (eventHandlerNames.contains(ColorSchemeChangeEventHandler.NAME)) {
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

        for (String eventHandlerName : eventHandlerNames) {
            js.append("const %s=(%s)=>{", eventHandlerName, EVENT_PARAMETER_NAME);
            js.debug("console.log('global event','%s',%s);", eventHandlerName, EVENT_PARAMETER_NAME);
            EventHandlerRenderer.renderHandlers(eventHandlers, toolkit, null, js, eventHandlerName, EVENT_PARAMETER_NAME);
            js.append("};");
        }
    }
}
