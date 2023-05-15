package com.philips.dmis.swt.ui;

import com.philips.dmis.swt.ui.toolkit.statement.method.M;
import com.philips.dmis.swt.ui.toolkit.statement.value.V;
import com.philips.dmis.swt.ui.toolkit.events.ClickEventHandler;
import com.philips.dmis.swt.ui.toolkit.events.InitEventHandler;
import com.philips.dmis.swt.ui.toolkit.widgets.*;
import org.springframework.stereotype.Component;

@Component
public class FrameExample extends Page {
    public FrameExample() throws Exception {
    }

    @Override
    protected void build() throws Exception {
        add(HtmlLink.closePage("Back to Examples"));
        add(new HtmlHeading("Frames"));

        add(new HtmlParagraph("An iframe element cannot be resized automatically to fit its content, " +
                "at least not in a reliable way. If the size of the iframe is not specified, " +
                "then the browser will set it to the default size which is 300px wide and 150px high. " +
                "For this reason the default behaviour of the Frame widget is to set the iframe height " +
                "to the width and height of the container it is in (i.e., 100%)."));

        add(new HtmlParagraph("Alternatively, the Frame can be given a specific size:"));
        add(new HtmlPreformatted("Frame frame = new Frame(FrameSize.customSize(\"200px\", \"55px\");"));

        add(new HtmlParagraph("To setting the document to display, use the onInit event:"));
        add(new HtmlPreformatted("frame.onInit(new InitEventHandler(M.Set(frame, V.Const(\"example.html\"))));"));
        HtmlFrame htmlFrame = add(new HtmlFrame(FrameSize.customSize("200px", "55px")));
        htmlFrame.onInit(new InitEventHandler(
                M.SetValue(htmlFrame, V.Const("example.html"))
        ));

        add(new HtmlParagraph("Or, to display a Page, using the onInit event:"));
        add(new HtmlPreformatted(TextFormatType.JAVA_AND_JS, "frame.onInit(new InitEventHandler(M.Set(frame, V.GetPage(FormExample.class))));"));
        add(new HtmlParagraph("or using a button to load the page:"));
        add(new HtmlPreformatted(TextFormatType.JAVA_AND_JS, "button.onClick(new ClickEventHandler(" +
                "M.Set(frame, V.GetPage(FormExample.class))" +
                "));"));
        HtmlButton loadFrame = add(new HtmlButton("Load page into Frame"));
        HtmlFrame htmlFrame2 = add(new HtmlFrame(FrameSize.customSize("400px", "400px")));
        loadFrame.onClick(new ClickEventHandler(
                M.SetValue(htmlFrame2, V.GetPage(FormInsideFrameExample.class))
        ));
    }
}
