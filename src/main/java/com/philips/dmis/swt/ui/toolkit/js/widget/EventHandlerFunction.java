package com.philips.dmis.swt.ui.toolkit.js.widget;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.events.*;
import com.philips.dmis.swt.ui.toolkit.js.*;
import com.philips.dmis.swt.ui.toolkit.js.state.EventHandlersVariable;
import com.philips.dmis.swt.ui.toolkit.js.state.ParentWidgetIdVariable;
import com.philips.dmis.swt.ui.toolkit.widgets.JsRenderException;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;

import java.util.List;

public abstract class EventHandlerFunction implements JsFunction {
    public static final String EVENTCONTEXT_ARGUMENT = "eventContext";

    // GLOBAL EVENTS
    public static class OnAfterPrintEventHandlerFunction extends EventHandlerFunction {
        public static final String ID = AfterPrintEventHandler.NAME;

        public OnAfterPrintEventHandlerFunction() {
            super(ID, false);
        }
    }

    public static class OnBeforePrintEventHandlerFunction extends EventHandlerFunction {
        public static final String ID = BeforePrintEventHandler.NAME;

        public OnBeforePrintEventHandlerFunction() {
            super(ID, false);
        }
    }

    public static class OnBeforeUnloadEventHandlerFunction extends EventHandlerFunction {
        public static final String ID = BeforeUnloadEventHandler.NAME;

        public OnBeforeUnloadEventHandlerFunction() {
            super(ID, false);
        }
    }

    public static class OnBlurEventHandlerFunction extends EventHandlerFunction {
        public static final String ID = BlurEventHandler.NAME;

        public OnBlurEventHandlerFunction() {
            super(ID, false);
        }
    }

    public static class OnErrorEventHandlerFunction extends EventHandlerFunction {
        public static final String ID = ErrorEventHandler.NAME;

        public OnErrorEventHandlerFunction() {
            super(ID, false);
        }
    }

    public static class OnFocusEventHandlerFunction extends EventHandlerFunction {
        public static final String ID = FocusEventHandler.NAME;

        public OnFocusEventHandlerFunction() {
            super(ID, false);
        }
    }

    public static class OnLanguageChangeEventHandlerFunction extends EventHandlerFunction {
        public static final String ID = LanguageChangeEventHandler.NAME;

        public OnLanguageChangeEventHandlerFunction() {
            super(ID, false);
        }
    }

    public static class OnMessageEventHandlerFunction extends EventHandlerFunction {
        public static final String ID = MessageEventHandler.NAME;

        public OnMessageEventHandlerFunction() {
            super(ID, false);
        }
    }

    public static class OnOfflineEventHandlerFunction extends EventHandlerFunction {
        public static final String ID = OfflineEventHandler.NAME;

        public OnOfflineEventHandlerFunction() {
            super(ID, false);
        }
    }

    public static class OnOnlineEventHandlerFunction extends EventHandlerFunction {
        public static final String ID = OnlineEventHandler.NAME;

        public OnOnlineEventHandlerFunction() {
            super(ID, false);
        }
    }

    public static class OnRedoEventHandlerFunction extends EventHandlerFunction {
        public static final String ID = RedoEventHandler.NAME;

        public OnRedoEventHandlerFunction() {
            super(ID, false);
        }
    }

    public static class OnUndoEventHandlerFunction extends EventHandlerFunction {
        public static final String ID = UndoEventHandler.NAME;

        public OnUndoEventHandlerFunction() {
            super(ID, false);
        }
    }

    public static class OnUnloadEventHandlerFunction extends EventHandlerFunction {
        public static final String ID = UnloadEventHandler.NAME;

        public OnUnloadEventHandlerFunction() {
            super(ID, false);
        }
    }

    public static class OnKeyPressEventHandlerFunction extends EventHandlerFunction {
        public static final String ID = KeyPressEventHandler.NAME;

        public OnKeyPressEventHandlerFunction() {
            super(ID, false);
        }
    }

    public static class OnKeyDownEventHandlerFunction extends EventHandlerFunction {
        public static final String ID = KeyDownEventHandler.NAME;

        public OnKeyDownEventHandlerFunction() {
            super(ID, false);
        }
    }

    public static class OnKeyUpEventHandlerFunction extends EventHandlerFunction {
        public static final String ID = KeyUpEventHandler.NAME;

        public OnKeyUpEventHandlerFunction() {
            super(ID, false);
        }
    }

    public static class OnColorSchemeChangeEventHandlerFunction extends EventHandlerFunction {
        public static final String ID = ColorSchemeChangeEventHandler.NAME;

        public OnColorSchemeChangeEventHandlerFunction() {
            super(ID, false);
        }
    }

    // LOCAL EVENTS

    public static class OnActivateEventHandlerFunction extends EventHandlerFunction {
        public static final String ID = ActivateEventHandler.NAME;

        public OnActivateEventHandlerFunction() {
            super(ID, false);
        }
    }

    public static class OnAppendEventHandlerFunction extends EventHandlerFunction {
        public static final String ID = AppendEventHandler.NAME;

        public OnAppendEventHandlerFunction() {
            super(ID, false);
        }
    }

    public static class OnChangeEventHandlerFunction extends EventHandlerFunction {
        public static final String ID = ChangeEventHandler.NAME;

        public OnChangeEventHandlerFunction() {
            super(ID, true);
        }
    }

    public static class OnClickEventHandlerFunction extends EventHandlerFunction {
        public static final String ID = ClickEventHandler.NAME;

        public OnClickEventHandlerFunction() {
            super(ID, false);
        }
    }

    public static class OnDeactivateEventHandlerFunction extends EventHandlerFunction {
        public static final String ID = DeactivateEventHandler.NAME;

        public OnDeactivateEventHandlerFunction() {
            super(ID, false);
        }
    }

    public static class OnElapsedEventHandlerFunction extends EventHandlerFunction {
        public static final String ID = ElapsedEventHandler.NAME;

        public OnElapsedEventHandlerFunction() {
            super(ID, false);
        }
    }

    public static class OnHideEventHandlerFunction extends EventHandlerFunction {
        public static final String ID = HideEventHandler.NAME;

        public OnHideEventHandlerFunction() {
            super(ID, false);
        }
    }

    public static class OnInitEventHandlerFunction extends EventHandlerFunction {
        public static final String ID = InitEventHandler.NAME;

        public OnInitEventHandlerFunction() {
            super(ID, false);
        }
    }

    public static class OnInputEventHandlerFunction extends EventHandlerFunction {
        public static final String ID = InputEventHandler.NAME;

        public OnInputEventHandlerFunction() {
            super(ID, true);
        }
    }

    public static class OnRemoveEventHandlerFunction extends EventHandlerFunction {
        public static final String ID = RemoveEventHandler.NAME;

        public OnRemoveEventHandlerFunction() {
            super(ID, false);
        }
    }

    public static class OnResetEventHandlerFunction extends EventHandlerFunction {
        public static final String ID = ResetEventHandler.NAME;

        public OnResetEventHandlerFunction() {
            super(ID, false);
        }
    }

    public static class OnRefreshEventHandlerFunction extends EventHandlerFunction {
        public static final String ID = RefreshEventHandler.NAME;

        public OnRefreshEventHandlerFunction() {
            super(ID, false);
        }
    }

    public static class OnResponseEventHandlerFunction extends EventHandlerFunction {
        public static final String ID = ResponseEventHandler.NAME;

        public OnResponseEventHandlerFunction() {
            super(ID, true);
        }
    }

    public static class OnShowEventHandlerFunction extends EventHandlerFunction {
        public static final String ID = ShowEventHandler.NAME;

        public OnShowEventHandlerFunction() {
            super(ID, false);
        }
    }

    public static class OnStartEventHandlerFunction extends EventHandlerFunction {
        public static final String ID = StartEventHandler.NAME;

        public OnStartEventHandlerFunction() {
            super(ID, false);
        }
    }

    public static class OnStopEventHandlerFunction extends EventHandlerFunction {
        public static final String ID = StopEventHandler.NAME;

        public OnStopEventHandlerFunction() {
            super(ID, false);
        }
    }

    public static class OnBeforeUpdateEventHandlerFunction extends EventHandlerFunction {
        public static final String ID = BeforeUpdateEventHandler.NAME;

        public OnBeforeUpdateEventHandlerFunction() {
            super(ID, false);
        }
    }

    public static class OnUpdateEventHandlerFunction extends EventHandlerFunction {
        public static final String ID = UpdateEventHandler.NAME;

        public OnUpdateEventHandlerFunction() {
            super(ID, false);
        }
    }

    public static class OnOrderChangeEventHandlerFunction extends EventHandlerFunction {
        public static final String ID = OrderChangeEventHandler.NAME;

        public OnOrderChangeEventHandlerFunction() {
            super(ID, false);
        }
    }

    public static class OnClickOutsideDialogEventHandlerFunction extends EventHandlerFunction {
        public static final String ID = ClickOutsideDialogEventHandler.NAME;

        public OnClickOutsideDialogEventHandlerFunction() {
            super(ID, false);
        }
    }

    public static class OnOpenEventHandlerFunction extends EventHandlerFunction {
        public static final String ID = OpenEventHandler.NAME;

        public OnOpenEventHandlerFunction() {
            super(ID, false);
        }
    }

    public static class OnSelectionChangeEventHandlerFunction extends EventHandlerFunction {
        public static final String ID = SelectionChangeEventHandler.NAME;

        public OnSelectionChangeEventHandlerFunction() {
            super(ID, false);
        }
    }

    private final String name;
    private final boolean propagateToParent;

    protected EventHandlerFunction(String name, boolean propagateToParent) {
        this.name = name;
        this.propagateToParent = propagateToParent;
    }

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
        return name;
    }

    @Override
    public JsType getType() {
        return JsType.VOID;
    }

    @Override
    public void getDependencies(List<Class<? extends JsMember>> dependencies) {
    }

    @Override
    public void getParameters(List<JsParameter> parameters) {
        parameters.add(JsParameter.getInstance("eventContext", JsType.OBJECT));
    }

    @Override
    public void renderJs(Toolkit toolkit, JsWriter js) throws JsRenderException {
        js.append("(eventContext)=>{");
        js.trace(this);

        js.append("const widget=window[eventContext.widgetId];");
        js.append("const c=widget.%s['%s'];", EventHandlersVariable.ID, name);
        js.append("if(c!=undefined&&c!=null&&c.length!=0){");
        js.info("console.log('event handlers',c);");
        js.append("for(const i in c){");
        js.append("var h=c[i];");
        js.append("h.fn(eventContext);");
        js.append("};");//end for
        js.append("};");//end if
        if (propagateToParent) {
            js.append("if(widget.%s!=null&&widget.%s.length!=0){",
                    ParentWidgetIdVariable.ID, ParentWidgetIdVariable.ID);
            js.append("%s(widget.%s,%s['%s'],eventContext.domEvent,eventContext.slaveId,eventContext.dataKey);",
                    JsWidgetModule.getId(RaiseEventFunction.class),
                    ParentWidgetIdVariable.ID,
                    JsWidgetModule.ID,
                    name);
            js.append("};");
        }
        js.append("}");
    }
}
