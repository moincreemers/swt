package com.philips.dmis.swt.ui.toolkit.js.pages;

import com.philips.dmis.swt.ui.toolkit.EmptyPage;
import com.philips.dmis.swt.ui.toolkit.SingleChildPage;
import com.philips.dmis.swt.ui.toolkit.TestToolkit;
import com.philips.dmis.swt.ui.toolkit.js.JsWriter;
import org.junit.jupiter.api.Test;

public class JsPagesModuleTest {
    @Test
    public void testEmptyPage() throws Exception {
        EmptyPage emptyPage = new EmptyPage();
        JsPagesModule jsPagesModule = new JsPagesModule(emptyPage);
        JsWriter js = new JsWriter();
        jsPagesModule.renderJs(new TestToolkit(), js);

        System.out.println(js);
    }

    @Test
    public void testPageWithOneChild() throws Exception {
        SingleChildPage singleChildPage = new SingleChildPage();
        JsPagesModule jsPagesModule = new JsPagesModule(singleChildPage);
        JsWriter js = new JsWriter();
        jsPagesModule.renderJs(new TestToolkit(), js);

        System.out.println(js);
    }
}
