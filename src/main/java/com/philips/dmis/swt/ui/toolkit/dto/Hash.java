package com.philips.dmis.swt.ui.toolkit.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Hash implements Serializable {
    private List<String> p = new ArrayList<>();
    private List<String> o = new ArrayList<>();
    private List<String> d = new ArrayList<>();

    public Hash() {
    }

    public Hash(String pageId, String ownerPageId, String data) {
        this.p.add(pageId);
        this.o.add(ownerPageId);
        if (data != null) {
            this.d.add(data);
        }
    }

    public List<String> getP() {
        return p;
    }

    public void setP(List<String> p) {
        this.p = p;
    }

    public List<String> getO() {
        return o;
    }

    public void setO(List<String> o) {
        this.o = o;
    }

    public List<String> getD() {
        return d;
    }

    public void setD(List<String> d) {
        this.d = d;
    }

    @Override
    public String toString() {
        StringBuffer s = new StringBuffer();
        s.append("#");
        s.append(p.isEmpty() ? "" : "p=" + String.join("&p=", p));
        s.append(o.isEmpty() ? "" : "o=" + String.join("&o=", o));
        s.append(d.isEmpty() ? "" : "d=" + String.join("&d=", d));
        return s.toString();
    }
}
