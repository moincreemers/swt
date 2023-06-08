package com.philips.dmis.swt.ui.toolkit.js.state;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.js.*;
import com.philips.dmis.swt.ui.toolkit.utils.ProtectedHashMap;
import com.philips.dmis.swt.ui.toolkit.widgets.JsRenderException;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;

import java.util.*;

/**
 * Composite function.
 */
public class JsStateModule implements JsModule {
    public static final String REASON_INIT = "init";
    public static final String REASON_SHOW = "show";
    public static final String REASON_USER = "user";
    public static final String REASON_LOCAL = "local";
    public static final String REASON_DATA_SOURCE = "datasource";

    private final Widget widget;
    private final WidgetType widgetType;

    private static final Map<String, Map<Class<? extends JsMember>, Pair<String, JsMember>>> INDEX_PER_WIDGET = new HashMap<>();
    private final List<Pair<String, JsMember>> members = new ArrayList<>();

    public static JsMember getMemberInstance(Widget widget, Class<? extends JsMember> memberClass) {
        if (!INDEX_PER_WIDGET.containsKey(widget.getId())) {
            throw new IllegalArgumentException("no index for: " + widget.getId());
        }
        return JsModule.getMemberInstance(memberClass, INDEX_PER_WIDGET.get(widget.getId()));
    }

    public static String getId(Widget widget, Class<? extends JsMember> memberClass) throws JsRenderException {
        return getId(widget.getId(), memberClass);
    }

    public static String getId(String id, Class<? extends JsMember> memberClass) throws JsRenderException {
        if (!INDEX_PER_WIDGET.containsKey(id)) {
            throw new IllegalArgumentException("no index for: " + id);
        }
        return JsModule.getId(memberClass, INDEX_PER_WIDGET.get(id));
    }

    public static String getQualifiedId(Widget widget, Class<? extends JsMember> memberClass) throws JsRenderException {
        return getQualifiedId(widget.getId(), memberClass);
    }

    public static String getQualifiedId(String id, Class<? extends JsMember> memberClass) throws JsRenderException {
        if (!INDEX_PER_WIDGET.containsKey(id)) {
            throw new IllegalArgumentException("no index for: " + id);
        }
        return JsModule.getQualifiedId(id, memberClass, INDEX_PER_WIDGET.get(id));
    }

    public JsStateModule(Widget widget) {
        this.widget = widget;
        this.widgetType = widget.getWidgetType();

        members.add(new Pair<>(AutoRefreshVariable.ID, new AutoRefreshVariable(widget)));
        members.add(new Pair<>(ButtonTypeVariable.ID, new ButtonTypeVariable(widget)));
        members.add(new Pair<>(CacheTypeVariable.ID, new CacheTypeVariable(widget)));
        members.add(new Pair<>(CalculatedValueVariable.ID, new CalculatedValueVariable(widget)));
        members.add(new Pair<>(ChildWidgetsVariable.ID, new ChildWidgetsVariable(widget)));
        members.add(new Pair<>(ColumnsVariable.ID, new ColumnsVariable(widget)));
        members.add(new Pair<>(ContentTypeEncodingVariable.ID, new ContentTypeEncodingVariable(widget)));
        members.add(new Pair<>(ContentTypeVariable.ID, new ContentTypeVariable(widget)));
        members.add(new Pair<>(DataAdaptersVariable.ID, new DataAdaptersVariable(widget)));
        members.add(new Pair<>(DataKeysVariable.ID, new DataKeysVariable(widget)));
        members.add(new Pair<>(DataKeyVariable.ID, new DataKeyVariable(widget)));
        members.add(new Pair<>(DataVariable.ID, new DataVariable(widget)));
        members.add(new Pair<>(DisabledDataAdaptersVariable.ID, new DisabledDataAdaptersVariable(widget)));
        members.add(new Pair<>(EmptyTextVariable.ID, new EmptyTextVariable(widget)));
        members.add(new Pair<>(EventHandlersVariable.ID, new EventHandlersVariable(widget)));
        members.add(new Pair<>(ExpectServiceResponseVariable.ID, new ExpectServiceResponseVariable(widget)));
        members.add(new Pair<>(HtmlAttributesVariable.ID, new HtmlAttributesVariable(widget)));
        members.add(new Pair<>(HtmlTagVariable.ID, new HtmlTagVariable(widget)));
        members.add(new Pair<>(HttpHeadersVariable.ID, new HttpHeadersVariable(widget)));
        members.add(new Pair<>(HttpMethodVariable.ID, new HttpMethodVariable(widget)));
        members.add(new Pair<>(ImplementsVariable.ID, new ImplementsVariable(widget)));
        members.add(new Pair<>(IsMultiLevelVariable.ID, new IsMultiLevelVariable(widget)));
        members.add(new Pair<>(IsNumberedVariable.ID, new IsNumberedVariable(widget)));
        members.add(new Pair<>(NotifySubscribersVariable.ID, new NotifySubscribersVariable(widget)));
        members.add(new Pair<>(NumberingLevelVariable.ID, new NumberingLevelVariable(widget)));
        members.add(new Pair<>(OrderingVariable.ID, new OrderingVariable(widget)));
        members.add(new Pair<>(PageClassNameVariable.ID, new PageClassNameVariable(widget)));
        members.add(new Pair<>(PageIdVariable.ID, new PageIdVariable(widget)));
        members.add(new Pair<>(PageRefreshVariable.ID, new PageRefreshVariable(widget)));
        members.add(new Pair<>(PanelTypeVariable.ID, new PanelTypeVariable(widget)));
        members.add(new Pair<>(ParametersVariable.ID, new ParametersVariable(widget)));
        members.add(new Pair<>(ParentWidgetIdVariable.ID, new ParentWidgetIdVariable(widget)));
        members.add(new Pair<>(ResponseTypeVariable.ID, new ResponseTypeVariable(widget)));
        members.add(new Pair<>(SlavesVariable.ID, new SlavesVariable(widget)));
        members.add(new Pair<>(StaticDataVariable.ID, new StaticDataVariable(widget)));
        members.add(new Pair<>(StaticInnerHtmlVariable.ID, new StaticInnerHtmlVariable(widget)));
        members.add(new Pair<>(SubscribersVariable.ID, new SubscribersVariable(widget)));
        members.add(new Pair<>(SyncVariable.ID, new SyncVariable(widget)));
        members.add(new Pair<>(TableOrientationTypeVariable.ID, new TableOrientationTypeVariable(widget)));
        members.add(new Pair<>(TemplateIdVariable.ID, new TemplateIdVariable(widget)));
        members.add(new Pair<>(TextFormatVariable.ID, new TextFormatVariable(widget)));
        members.add(new Pair<>(TimerHandleVariable.ID, new TimerHandleVariable(widget)));
        members.add(new Pair<>(TimerIntervalVariable.ID, new TimerIntervalVariable(widget)));
        members.add(new Pair<>(TimerTypeVariable.ID, new TimerTypeVariable(widget)));
        members.add(new Pair<>(URLVariable.ID, new URLVariable(widget)));
        members.add(new Pair<>(ViewPositionVariable.ID, new ViewPositionVariable(widget)));
        members.add(new Pair<>(ViewTypeVariable.ID, new ViewTypeVariable(widget)));
        members.add(new Pair<>(WidgetIdVariable.ID, new WidgetIdVariable(widget)));
        members.add(new Pair<>(WidgetTypeVariable.ID, new WidgetTypeVariable(widget)));

        // we cannot use JsModule.updateIndex because we use our own function id's
        // JsModule.updateIndex(members, INDEX);

        INDEX_PER_WIDGET.remove(widget.getId());
        Map<Class<? extends JsMember>, Pair<String, JsMember>> m = new ProtectedHashMap<>();
        for (Pair<String, JsMember> pair : members) {
            m.put(pair.getValue().getClass(), pair);
        }
        INDEX_PER_WIDGET.put(widget.getId(), m);
    }

    @Override
    public void renderJs(Toolkit toolkit, JsWriter js) {
        // NOTE: assignment goes here
        js.append("var %s=(function(){", widget.getId());

        List<Pair<String, JsMember>> membersInScope = members.stream()
                .filter(m -> m.getValue().isMemberOf(widget, widgetType)).toList();

        // functions
        List<Pair<String, JsMember>> sorted = membersInScope.stream()
                .sorted(Comparator.comparingInt(m0 -> JsModule.getOrder(m0.getValue())))
                .toList();

        for (Pair<String, JsMember> pair : sorted) {
            JsMember jsMember = pair.getValue();
            if (jsMember instanceof JsComposite) {
                js.comment("begin composite: " + ((JsComposite) jsMember).getName());
                jsMember.renderJs(toolkit, js);
                js.comment("end composite: " + ((JsComposite) jsMember).getName());
            } else if (jsMember instanceof JsVariable) {
                js.append("var %s=", pair.getKey());
                jsMember.renderJs(toolkit, js);
                js.append(";");
            } else {
                js.append("const %s=", pair.getKey());
                jsMember.renderJs(toolkit, js);
                js.append(";");
            }
        }

        // export:

        // state
        js.append("return {");

        int i = 0;
        for (Pair<String, JsMember> pair : membersInScope) {
            if (!pair.getValue().isPublic()) {
                continue;
            }
            if (i > 0) {
                js.append(",");
            }
            js.cr();
            if (pair.getValue() instanceof JsSymbol) {
                js.append("%s:", pair.getKey());
                pair.getValue().renderJs(toolkit, js);
            } else {
                js.append("%s:%s",
                        pair.getValue().getPublicName(pair.getKey()),
                        pair.getKey());
            }
            i++;
        }

        js.append("};"); // end export

        js.append("})();"); // end module
    }

    @Override
    public String getInitFunctionId() {
        return null;
    }
}
