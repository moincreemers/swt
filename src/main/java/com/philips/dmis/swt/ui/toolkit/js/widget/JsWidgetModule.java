package com.philips.dmis.swt.ui.toolkit.js.widget;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.js.JsMember;
import com.philips.dmis.swt.ui.toolkit.js.JsModule;
import com.philips.dmis.swt.ui.toolkit.js.JsWriter;
import com.philips.dmis.swt.ui.toolkit.js.Pair;
import com.philips.dmis.swt.ui.toolkit.widgets.JsRenderException;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Component
public class JsWidgetModule implements JsModule {
    public static final String ID = "W";

    private static final List<JsMember> MEMBERS = Arrays.asList(
            new AppendElementFunction(),
            new AppendOptionFunction(),
            new BeforeEventFunction(),
            new BeforeUpdateFunction(),
            new BeforeUpdateListItemsFunction(),
            new BeforeUpdateOptionsFunction(),
            new BeforeUpdateSubscribersFunction(),
            new BeforeUpdateTableBodyFunction(),
            new BeforeUpdateTableFooterFunction(),
            new BeforeUpdateTableHeaderFunction(),
            new BeforeUpdateTextFunction(),
            new BeforeUpdateValueFunction(),
            new BlobToElementFunction(),
            new CloneFunction(),
            new EventHandlerFunction.OnActivateEventHandlerFunction(),
            new EventHandlerFunction.OnAppendEventHandlerFunction(),
            new EventHandlerFunction.OnChangeEventHandlerFunction(),
            new EventHandlerFunction.OnClickEventHandlerFunction(),
            new EventHandlerFunction.OnDeactivateEventHandlerFunction(),
            new EventHandlerFunction.OnElapsedEventHandlerFunction(),
            new EventHandlerFunction.OnErrorEventHandlerFunction(),
            new EventHandlerFunction.OnHideEventHandlerFunction(),
            new EventHandlerFunction.OnInitEventHandlerFunction(),
            new EventHandlerFunction.OnInputEventHandlerFunction(),
            new EventHandlerFunction.OnRemoveEventHandlerFunction(),
            new EventHandlerFunction.OnResetEventHandlerFunction(),
            new EventHandlerFunction.OnResponseEventHandlerFunction(),
            new EventHandlerFunction.OnRefreshEventHandlerFunction(),
            new EventHandlerFunction.OnShowEventHandlerFunction(),
            new EventHandlerFunction.OnStartEventHandlerFunction(),
            new EventHandlerFunction.OnStopEventHandlerFunction(),
            new EventHandlerFunction.OnUpdateEventHandlerFunction(),
            new EventHandlerFunction.OnOrderChangeEventHandlerFunction(),
            new EventHandlerFunction.OnClickOutsideDialogEventHandlerFunction(),
            new EventHandlerFunction.OnOpenEventHandlerFunction(),
            new EventHandlerFunction.OnSelectionChangeEventHandlerFunction(),
            new GetElementFunction(),
            new GetFunction(),
            new GetInnerElementFunction(),
            new GetMultiLevelFunction(),
            new GetOrderingFunction(),
            new GetParameterFunction(),
            new GetParametersFunction(),
            new GetWidgetTypeFunction(),
            new InitFunction(),
            new InitWidgetFunction(),
            new IsContainerFunction(),
            new PostFunction(),
            new ProcessResourceResponseFunction(),
            new ProcessResponseFunction(),
            new RaiseEventFunction(),
            new RefreshFunction(),
            new RefreshPageFunction(),
            new RemoveAllParametersFunction(),
            new RemoveClassNameFunction(),
            new RemoveElementFunction(),
            new RemoveOptionsFunction(),
            new RemoveParameterFunction(),
            new RenderElementFunction(),
            new RenderInnerHTMLFunction(),
            new ResetFunction(),
            new SetAutocompleteFunction(),
            new SetDataAdapterEnabledFunction(),
            new SetDisabledFunction(),
            new SetIconFunction(),
            new SetLengthFunction(),
            new SetListFunction(),
            new SetMultipleFunction(),
            new SetNumberFunction(),
            new SetOrderFunction(),
            new SetParameterFunction(),
            new SetParametersFunction(),
            new SetPatternFunction(),
            new SetPlaceholderFunction(),
            new SetRangeFunction(),
            new SetReadonlyFunction(),
            new SetRequiredFunction(),
            new SetStepFunction(),
            new SetTextFunction(),
            new SetValueFunction(),
            new StartFunction(),
            new StopFunction(),
            new UpdateFunction(),
            new UpdateListItemsFunction(),
            new UpdateNumberingFunction(),
            new UpdateOptionsFunction(),
            new UpdateSubscribersFunction(),
            new UpdateTableBodyFunction(),
            new UpdateTableFooterFunction(),
            new UpdateTableHeaderFunction(),
            new UpdateTextFunction(),
            new UpdateValueFunction(),
            new WidgetTypesVariable()
    );

    private static final Map<Class<? extends JsMember>, Pair<String, JsMember>> INDEX =
            JsModule.createIndex(MEMBERS);

    public static JsMember getMemberInstance(Class<? extends JsMember> memberClass) {
        return JsModule.getMemberInstance(memberClass, INDEX);
    }

    public static String getId(Class<? extends JsMember> memberClass) throws JsRenderException {
        return JsModule.getId(memberClass, INDEX);
    }

    public static String getQualifiedId(Class<? extends JsMember> memberClass) {
        return JsModule.getQualifiedId(ID, memberClass, INDEX);
    }

    @Override
    public void renderJs(Toolkit toolkit, JsWriter js) {
        JsModule.renderModule(toolkit, js, ID, INDEX.values());
    }

    @Override
    public String getInitFunctionId() {
        return getQualifiedId(InitFunction.class);
    }
}
