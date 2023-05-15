package com.philips.dmis.swt.ui.toolkit;

public class LoadingScreen {
    public void renderHtml(StringBuffer html) {
        html.append("<div id='splash' class='splash spinner-container'><div class='spinner'></div></div>");
    }
}
