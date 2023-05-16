package com.philips.dmis.swt.ui.toolkit.js.pages;

import com.philips.dmis.swt.ui.toolkit.GlobalEvents;
import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.events.*;
import com.philips.dmis.swt.ui.toolkit.js.*;
import com.philips.dmis.swt.ui.toolkit.widgets.JsRenderException;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;

import java.util.List;

// todo: limit eventhandler rendering. Not all widgets support all eventhandlers

public abstract class EventHandlerFunction implements JsFunction, IsPageModuleMember {
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

    public static class OnErrorEventHandlerFunction extends EventHandlerFunction {
        public static final String ID = ErrorEventHandler.NAME;

        public OnErrorEventHandlerFunction() {
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
        return id;
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
        parameters.add(JsParameter.getInstance("event", JsType.OBJECT));
    }

    @Override
    public void renderJs(Toolkit toolkit, JsWriter js) throws JsRenderException {
        js.append("(%s)=>{", GlobalEvents.EVENT_PARAMETER_NAME);
        js.debug("console.log('event handler','%s',%s);", name, GlobalEvents.EVENT_PARAMETER_NAME);
        js.append("var c=%s['%s'];", EventHandlersVariable.ID, name.toLowerCase());
        js.append("if(c!=undefined&&c!=null&&c.length!=0){");
        js.debug("console.log('event handlers',c);");
        js.append("for(const i in c){");
        js.append("c[i](%s);", GlobalEvents.EVENT_PARAMETER_NAME);
        js.append("};");//end for
        js.append("};");//end if
        if (propagateToParent) {
            js.append("if(%s!=null&&%s.length!=0){",
                    ParentWidgetIdVariable.ID, ParentWidgetIdVariable.ID);
            js.append("window[%s].%s(%s);",
                    ParentWidgetIdVariable.ID, name, GlobalEvents.EVENT_PARAMETER_NAME);
            js.append("};");
        }
        js.append("}");
    }
}
