package com.philips.dmis.swt.ui.demo;

import com.philips.dmis.swt.ui.toolkit.Constants;
import com.philips.dmis.swt.ui.toolkit.data.KeyValueListDataAdapter;
import com.philips.dmis.swt.ui.toolkit.events.ClickEventHandler;
import com.philips.dmis.swt.ui.toolkit.statement.method.M;
import com.philips.dmis.swt.ui.toolkit.widgets.*;
import org.springframework.stereotype.Component;

@Component
public class DialogsExamplePage extends Page {
    public DialogsExamplePage() throws Exception {
        super(Constants.isDemo(DialogsExamplePage.class));
    }

    @Override
    protected void build() throws Exception {
        if (!isDefault()) {
            add(HtmlLink.closePage("Back to Examples"));
        }

        add(new HtmlHeading("Dialogs"));

        add(new HtmlParagraph("All dialogs and sidebars behave like 'modal' dialogs. " +
                "This means that, while the page that opened the dialog is still part of the context, " +
                "it is not accessible while the dialog is visible."));
        add(new HtmlParagraph("To emphasise this the page that opened the dialog will be blurred."));

        Panel popupDialogs = add(new Panel(PanelType.BANNER));
        popupDialogs.add(new HtmlHeading("Popup", 3));
        StaticData dialogPositionStaticData = popupDialogs.add(new StaticData(
                DataBuilder.values()
                        .add(ViewPosition.DIALOG_CENTER.name())
                        .add(ViewPosition.DIALOG_TOP_LEFT.name())
                        .add(ViewPosition.DIALOG_TOP_RIGHT.name())
                        .add(ViewPosition.DIALOG_BOTTOM_RIGHT.name())
                        .add(ViewPosition.DIALOG_BOTTOM_LEFT.name())
                        .getData()
        ));
        SingleChoice dialogPosition = popupDialogs.add(new SingleChoice()
                .addDataSource(ValueAndOptionsDataSourceUsage.OPTIONS, dialogPositionStaticData, new KeyValueListDataAdapter()));
        dialogPosition.setValue(ViewPosition.DIALOG_CENTER.name());
        HtmlButton showDialog = popupDialogs.add(new HtmlButton("Show popup dialog"));
        showDialog.onClick(new ClickEventHandler(
                M.If(dialogPosition)
                        .Equals(ViewPosition.DIALOG_CENTER.name(), M.OpenPage(DialogCenterExample.class))
                        .Equals(ViewPosition.DIALOG_TOP_LEFT.name(), M.OpenPage(DialogTopLeftExample.class))
                        .Equals(ViewPosition.DIALOG_TOP_RIGHT.name(), M.OpenPage(DialogTopRightExample.class))
                        .Equals(ViewPosition.DIALOG_BOTTOM_RIGHT.name(), M.OpenPage(DialogBottomRightExample.class))
                        .Equals(ViewPosition.DIALOG_BOTTOM_LEFT.name(), M.OpenPage(DialogBottomLeftExample.class))
        ));

        Panel sidebarDialogs = add(new Panel(PanelType.BANNER));
        sidebarDialogs.add(new HtmlHeading("Sidebars", 3));
        StaticData sidebarPositionStaticData = popupDialogs.add(new StaticData(
                DataBuilder.values()
                        .add(ViewPosition.SIDEBAR_TOP.name())
                        .add(ViewPosition.SIDEBAR_RIGHT.name())
                        .add(ViewPosition.SIDEBAR_BOTTOM.name()).add(ViewPosition.SIDEBAR_LEFT.name())
                        .getData()
        ));
        SingleChoice sidebarPosition = sidebarDialogs.add(new SingleChoice()
                .addDataSource(ValueAndOptionsDataSourceUsage.OPTIONS, sidebarPositionStaticData, new KeyValueListDataAdapter()));
        sidebarPosition.setValue(ViewPosition.SIDEBAR_TOP.name());
        HtmlButton showSidebar = sidebarDialogs.add(new HtmlButton("Show sidebar dialog"));
        showSidebar.onClick(new ClickEventHandler(
                M.If(sidebarPosition)
                        .Equals(ViewPosition.SIDEBAR_TOP.name(), M.OpenPage(SidebarTopDialogExample.class))
                        .Equals(ViewPosition.SIDEBAR_RIGHT.name(), M.OpenPage(SidebarRightDialogExample.class))
                        .Equals(ViewPosition.SIDEBAR_BOTTOM.name(), M.OpenPage(SidebarBottomDialogExample.class))
                        .Equals(ViewPosition.SIDEBAR_LEFT.name(), M.OpenPage(SidebarLeftDialogExample.class))
        ));

        add(new HtmlHeading("Page fill", 3));
        add(new HtmlParagraph()).setText("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.");
        add(new HtmlParagraph()).setText("Mauris ultricies feugiat commodo. Nullam non metus nunc. Nam id neque justo. In hac habitasse platea dictumst. Nunc tortor justo, efficitur vitae facilisis eu, molestie et lorem. Mauris sodales, dui in vulputate suscipit, sapien arcu aliquam sem, a sagittis est lorem quis orci. Cras accumsan metus id dui malesuada, a efficitur arcu lacinia. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Proin sit amet condimentum orci. Proin pulvinar tortor nec scelerisque ullamcorper.");
        add(new HtmlParagraph()).setText("Proin facilisis neque auctor turpis interdum, vitae sodales nisi placerat. Vestibulum vitae augue vehicula, tempor ex a, dignissim est. Duis dui nunc, sollicitudin sed euismod cursus, fermentum suscipit nisl. Proin feugiat tellus vitae hendrerit malesuada. Integer risus est, fringilla quis velit a, convallis lacinia tortor. Donec ex ipsum, accumsan nec cursus ut, maximus vel lectus. Morbi non rhoncus magna. Quisque hendrerit faucibus dignissim. Vivamus lectus felis, tristique condimentum ante et, posuere gravida neque. Pellentesque sit amet nibh a neque cursus venenatis in eu dui.");
    }
}
