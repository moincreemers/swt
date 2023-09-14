package com.philips.dmis.swt.ui.toolkit.widgets;

import com.philips.dmis.swt.ui.toolkit.js.WidgetType;
import com.philips.dmis.swt.ui.toolkit.utils.PageXmlElement;

@PageXmlElement("listType")
public class ListContainer extends ContainerWidget<ListContainer> {
    private ListType listType = ListType.UNORDERED;

    public ListContainer(WidgetConfigurator widgetConfigurator, ListType listType) throws WidgetConfigurationException {
        super(widgetConfigurator, WidgetType.LIST_CONTAINER);
        setListType(listType);
    }

    public ListContainer() throws WidgetConfigurationException {
        this(ListType.UNORDERED);
    }

    public ListContainer(Widget... widgets) throws WidgetConfigurationException {
        this(ListType.UNORDERED, widgets);
    }

    public ListContainer(ListType listType, Widget... widgets) throws WidgetConfigurationException {
        super(WidgetType.LIST_CONTAINER);
        setListType(listType);
        addAll(widgets);
    }

    public ListType getListType() {
        return listType;
    }

    public void setListType(ListType listType) {
        if (this.listType != null) {
            removeClassName(this.listType.getClassName());
        }
        this.listType = listType;
        addClassName(listType.getClassName());
    }

    @Override
    protected boolean acceptWidget(Widget widget) {
        return true;
    }

    @Override
    public String getHtmlTag(String defaultHtmlTag) {
        return (listType == ListType.ORDERED) ? "ol" : super.getHtmlTag(defaultHtmlTag);
    }
}
