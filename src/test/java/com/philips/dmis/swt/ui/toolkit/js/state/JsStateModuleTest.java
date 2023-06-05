package com.philips.dmis.swt.ui.toolkit.js.state;

import com.philips.dmis.swt.ui.toolkit.EmptyPage;
import com.philips.dmis.swt.ui.toolkit.SingleChildPage;
import com.philips.dmis.swt.ui.toolkit.TestToolkit;
import com.philips.dmis.swt.ui.toolkit.js.JsWriter;
import org.junit.jupiter.api.Test;

public class JsStateModuleTest {
    @Test
    public void testEmptyPage() throws Exception {
        EmptyPage emptyPage = new EmptyPage();
        JsStateModule jsStateModule = new JsStateModule(emptyPage);
        JsWriter js = new JsWriter();
        jsStateModule.renderJs(new TestToolkit(), js);

        System.out.println(js);
    }

    @Test
    public void testPageWithOneChild() throws Exception {
        SingleChildPage singleChildPage = new SingleChildPage();
        JsStateModule jsStateModule = new JsStateModule(singleChildPage);
        JsWriter js = new JsWriter();
        jsStateModule.renderJs(new TestToolkit(), js);

        System.out.println(js);
    }
}
