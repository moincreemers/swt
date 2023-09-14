package com.philips.dmis.swt.ui.toolkit.widgets;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.utils.PageXmlElement;

import java.util.Map;

@PageXmlElement("list")
public class ListImpl implements HasList {
    private final Widget widget;
    private String list;

    public ListImpl(Widget widget) {
        this.widget = widget;
        widget.registerStaticHtmlImplementation(this);
    }

    @Override
    public Widget asWidget() {
        return widget;
    }

    @Override
    public HasList getListImpl() {
        return this;
    }

    @Override
    public String getList() {
        return list;
    }

    @Override
    public void setList(String list) {
        this.list = list;
    }

    @Override
    public void setList(HtmlDataList htmlDataList) {
        setList(htmlDataList.getId());
    }

    @Override
    public void renderStaticInnerHtml(Toolkit toolkit, StringBuffer html) {

    }

    @Override
    public void getHtmlAttributes(Map<String, String> htmlAttributes) {
        if (list != null && !list.isEmpty()) {
            htmlAttributes.put("list", list);
        }
    }

    @Override
    public void validate(Toolkit toolkit) throws WidgetConfigurationException {

    }
}
