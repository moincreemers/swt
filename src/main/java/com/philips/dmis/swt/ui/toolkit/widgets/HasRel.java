package com.philips.dmis.swt.ui.toolkit.widgets;

public interface HasRel extends HasStaticHTML {
    HasRel getRelImpl();

    java.util.List<String> getRel();

    void setRel(java.util.List<String> rel);

    void addRel(String rel);

    void addRel(RelType rel);
}
