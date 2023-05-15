package com.philips.dmis.swt.ui.toolkit.widgets;

public interface HasList extends HasStaticHTML {
    HasList getListImpl();

    String getList();

    void setList(String list);

    void setList(HtmlDataList htmlDataList);
}
