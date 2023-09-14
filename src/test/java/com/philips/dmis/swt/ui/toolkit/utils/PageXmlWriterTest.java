package com.philips.dmis.swt.ui.toolkit.utils;

import com.philips.dmis.swt.ui.toolkit.css.CssLength;
import com.philips.dmis.swt.ui.toolkit.css.CssUnit;
import com.philips.dmis.swt.ui.toolkit.js.state.PageRefreshType;
import com.philips.dmis.swt.ui.toolkit.widgets.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;

public class PageXmlWriterTest {
    final PageXmlWriter pageXmlWriter = PageXmlWriter.getInstance();

    static class TestPage extends Page {
        public TestPage() throws Exception {
            // set to non-default values to force serialization
            setDefault(true);
            setPanelType(PanelType.BANNER);
            setName("testPage");
            setAppearance(WidgetAppearance.ROUNDED_CORNERS, WidgetAppearance.BLOCK);
            setPageRefresh(PageRefreshType.MANUAL);
            setViewType(ViewType.DIALOG);
            setViewPosition(ViewPosition.DIALOG_CENTER);
            setWidth(new CssLength(100d, CssUnit.PIXEL));
            setHeight(new CssLength(200d, CssUnit.PIXEL));
        }

        @Override
        protected void build() throws Exception {
        }
    }

    static class TestPage2 extends Page {
        public TestPage2() throws Exception {
        }

        @Override
        protected void build() throws Exception {
        }
    }

    @Test
    public void shouldWriteFile() throws Exception {
        Page page1 = new TestPage();
        File file = pageXmlWriter.getOutputFile(page1.getClass());
        if (file.exists()) {
            file.delete();
        }
        pageXmlWriter.exportXml(page1);
        Assertions.assertTrue(file.exists());
    }

    @Test
    public void shouldDeserialize() throws Exception {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        Page page1 = new TestPage();
        pageXmlWriter.exportXml(page1, byteArrayOutputStream);

        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());

        Page page2 = new TestPage2();
        PageBuilder.getInstance().loadFromXml(page2, byteArrayInputStream);

        Assertions.assertEquals(page1.isDefault(), page2.isDefault());
        Assertions.assertEquals(page1.getPanelType(), page2.getPanelType());
        Assertions.assertEquals(page1.getName(), page2.getName());
        Assertions.assertArrayEquals(page1.getAppearance().toArray(), page2.getAppearance().toArray());
        Assertions.assertEquals(page1.getPageRefresh(), page2.getPageRefresh());
        Assertions.assertEquals(page1.getViewType(), page2.getViewType());
        Assertions.assertEquals(page1.getViewPosition(), page2.getViewPosition());
        Assertions.assertEquals(page1.getWidth(), page2.getWidth());
        Assertions.assertEquals(page1.getHeight(), page2.getHeight());
    }
}
