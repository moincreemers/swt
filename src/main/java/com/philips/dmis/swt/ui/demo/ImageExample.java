package com.philips.dmis.swt.ui.demo;

import com.philips.dmis.swt.ui.toolkit.Constants;
import com.philips.dmis.swt.ui.toolkit.events.ChangeEventHandler;
import com.philips.dmis.swt.ui.toolkit.events.ClickEventHandler;
import com.philips.dmis.swt.ui.toolkit.statement.method.M;
import com.philips.dmis.swt.ui.toolkit.statement.value.V;
import com.philips.dmis.swt.ui.toolkit.widgets.*;
import org.springframework.stereotype.Component;

@Component
public class ImageExample extends Page {
    public ImageExample() throws Exception {
        super(Constants.isDemo(ImageExample.class));
    }

    @Override
    protected void build() throws Exception {
        if (!isDefault()) {
            add(HtmlLink.closePage("Back to Examples"));
        }

        add(new HtmlHeading("Image manipulation using SetStyle &amp; Overlay-panels"));

        IconsWidget icons = add(new IconsWidget("MaterialSymbolsSharp.woff2"));

        SingleRowPanel toolbar = add(new SingleRowPanel(PanelType.TOOLBAR));
        HtmlButton zoomOut = toolbar.add(new HtmlButton(icons, "zoom_out", "1:1"));
        HtmlButton zoomIn = toolbar.add(new HtmlButton(icons, "zoom_in", "2x"));
        HtmlButton fitContent = toolbar.add(new HtmlButton(icons, "photo_size_select_large", "Fit Content"));
        HtmlButton fixedSize = toolbar.add(new HtmlButton(icons, "photo_size_select_small", "Fixed Size"));
        HtmlCheckInput showLayer1 = toolbar.add(new HtmlCheckInput("Overlay 1"));
        HtmlCheckInput showLayer2 = toolbar.add(new HtmlCheckInput("Overlay 2"));
        HtmlCheckInput showLayer3 = toolbar.add(new HtmlCheckInput("Overlay 3"));

        Panel panel = add(new Panel(PanelType.BORDERED));
        panel.setOverflowAndSize(Overflow.FIXED_SIZE, new Size("fit-content", "fit-content"));

        OverlayPanel layer1 = panel.add(new OverlayPanel());
        layer1.setAppearance(WidgetAppearance.POSITION_TOP_RIGHT);
        layer1.add(new HtmlParagraph("Layer 1 is positioned to the top-right corner of the parent"));
        layer1.setVisible(false);
        OverlayPanel layer2 = panel.add(new OverlayPanel());
        layer2.add(new HtmlParagraph("""
                Layer 2 dims the background color when the mouse hovers over it.
                This is accomplished using <code>OverlayPanel.setAppearance(WidgetAppearance.DIM_ON_HOVER)</code>"""));
        layer2.setAppearance(WidgetAppearance.DIM_ON_HOVER);
        layer2.setVisible(false);
        OverlayPanel layer3 = panel.add(new OverlayPanel());
        layer3.add(new HtmlParagraph("""
                Layer 3 shows its children when the mouse hovers over it.
                This is accomplished using <code>OverlayPanel.setAppearance(WidgetAppearance.SHOW_ON_HOVER)</code>"""));
        layer3.setAppearance(WidgetAppearance.SHOW_ON_HOVER);
        layer3.setVisible(false);

        HtmlImage image = panel.add(new HtmlImage(ImageType.DEFAULT, "never-settle.webp"));

        zoomOut.onClick(new ClickEventHandler(
                M.SetStyle(image, V.Const("transform"), V.Const("scale(1)"))
        ));
        zoomIn.onClick(new ClickEventHandler(
                M.SetStyle(image, V.Const("transform"), V.Const("scale(2)"))
        ));
        fitContent.onClick(new ClickEventHandler(
                M.SetStyle(image, V.Const("width"), V.Const("auto")),
                M.SetStyle(panel, V.Const("width"), V.Const("fit-content")),
                M.SetStyle(panel, V.Const("height"), V.Const("fit-content"))
        ));
        fixedSize.onClick(new ClickEventHandler(
                M.SetStyle(image, V.Const("width"), V.Const("100%")),
                M.SetStyle(panel, V.Const("width"), V.Const("500px")),
                M.SetStyle(panel, V.Const("height"), V.Const("auto"))
        ));
        showLayer1.onChange(new ChangeEventHandler(
                M.SetVisible(layer1, V.GetValue(showLayer1))
        ));
        showLayer2.onChange(new ChangeEventHandler(
                M.SetVisible(layer2, V.GetValue(showLayer2))
        ));
        showLayer3.onChange(new ChangeEventHandler(
                M.SetVisible(layer3, V.GetValue(showLayer3))
        ));
    }
}
