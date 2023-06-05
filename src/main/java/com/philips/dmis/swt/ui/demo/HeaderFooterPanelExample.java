package com.philips.dmis.swt.ui.demo;

import com.philips.dmis.swt.ui.toolkit.Constants;
import com.philips.dmis.swt.ui.toolkit.widgets.*;
import org.springframework.stereotype.Component;

@Component
public class HeaderFooterPanelExample extends Page {
    public HeaderFooterPanelExample() throws Exception {
        super(Constants.isDemo(HeaderFooterPanelExample.class));
    }

    @Override
    protected void build() throws Exception {
        if (!isDefault()) {
            add(HtmlLink.closePage("Back to Examples"));
        }
        add(new HtmlHeading("Header & Footer Panels"));

        Panel headerPanel = add(new Panel(PanelType.PAGE_HEADER));
        headerPanel.add(new HtmlLabel("This is a fixed page header"));

        Panel footerPanel = add(new Panel(PanelType.PAGE_FOOTER));
        footerPanel.add(new HtmlLabel("This is a fixed page footer"));

        Panel navLeft = add(new Panel(PanelType.NAV_LEFT));
        navLeft.add(new Panel(PanelType.INFO, new HtmlLabel("This is a fixed navigation panel")));

        Panel navRight = add(new Panel(PanelType.NAV_RIGHT));
        navRight.add(new Panel(PanelType.SUCCESS, new HtmlLabel("This is a fixed navigation panel")));

        add(new HtmlHeading("Page fill", 3));
        add(new HtmlParagraph()).setText("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.");
        add(new HtmlParagraph()).setText("Mauris ultricies feugiat commodo. Nullam non metus nunc. Nam id neque justo. In hac habitasse platea dictumst. Nunc tortor justo, efficitur vitae facilisis eu, molestie et lorem. Mauris sodales, dui in vulputate suscipit, sapien arcu aliquam sem, a sagittis est lorem quis orci. Cras accumsan metus id dui malesuada, a efficitur arcu lacinia. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Proin sit amet condimentum orci. Proin pulvinar tortor nec scelerisque ullamcorper.");
        add(new HtmlParagraph()).setText("Proin facilisis neque auctor turpis interdum, vitae sodales nisi placerat. Vestibulum vitae augue vehicula, tempor ex a, dignissim est. Duis dui nunc, sollicitudin sed euismod cursus, fermentum suscipit nisl. Proin feugiat tellus vitae hendrerit malesuada. Integer risus est, fringilla quis velit a, convallis lacinia tortor. Donec ex ipsum, accumsan nec cursus ut, maximus vel lectus. Morbi non rhoncus magna. Quisque hendrerit faucibus dignissim. Vivamus lectus felis, tristique condimentum ante et, posuere gravida neque. Pellentesque sit amet nibh a neque cursus venenatis in eu dui.");
        add(new HtmlParagraph()).setText("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.");
        add(new HtmlParagraph()).setText("Mauris ultricies feugiat commodo. Nullam non metus nunc. Nam id neque justo. In hac habitasse platea dictumst. Nunc tortor justo, efficitur vitae facilisis eu, molestie et lorem. Mauris sodales, dui in vulputate suscipit, sapien arcu aliquam sem, a sagittis est lorem quis orci. Cras accumsan metus id dui malesuada, a efficitur arcu lacinia. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Proin sit amet condimentum orci. Proin pulvinar tortor nec scelerisque ullamcorper.");
        add(new HtmlParagraph()).setText("Proin facilisis neque auctor turpis interdum, vitae sodales nisi placerat. Vestibulum vitae augue vehicula, tempor ex a, dignissim est. Duis dui nunc, sollicitudin sed euismod cursus, fermentum suscipit nisl. Proin feugiat tellus vitae hendrerit malesuada. Integer risus est, fringilla quis velit a, convallis lacinia tortor. Donec ex ipsum, accumsan nec cursus ut, maximus vel lectus. Morbi non rhoncus magna. Quisque hendrerit faucibus dignissim. Vivamus lectus felis, tristique condimentum ante et, posuere gravida neque. Pellentesque sit amet nibh a neque cursus venenatis in eu dui.");
        add(new HtmlParagraph()).setText("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.");
        add(new HtmlParagraph()).setText("Mauris ultricies feugiat commodo. Nullam non metus nunc. Nam id neque justo. In hac habitasse platea dictumst. Nunc tortor justo, efficitur vitae facilisis eu, molestie et lorem. Mauris sodales, dui in vulputate suscipit, sapien arcu aliquam sem, a sagittis est lorem quis orci. Cras accumsan metus id dui malesuada, a efficitur arcu lacinia. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Proin sit amet condimentum orci. Proin pulvinar tortor nec scelerisque ullamcorper.");
        add(new HtmlParagraph()).setText("Proin facilisis neque auctor turpis interdum, vitae sodales nisi placerat. Vestibulum vitae augue vehicula, tempor ex a, dignissim est. Duis dui nunc, sollicitudin sed euismod cursus, fermentum suscipit nisl. Proin feugiat tellus vitae hendrerit malesuada. Integer risus est, fringilla quis velit a, convallis lacinia tortor. Donec ex ipsum, accumsan nec cursus ut, maximus vel lectus. Morbi non rhoncus magna. Quisque hendrerit faucibus dignissim. Vivamus lectus felis, tristique condimentum ante et, posuere gravida neque. Pellentesque sit amet nibh a neque cursus venenatis in eu dui.");
    }
}
