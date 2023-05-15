package com.philips.dmis.swt.ui;

import com.philips.dmis.swt.ui.toolkit.data.FieldMapping;
import com.philips.dmis.swt.ui.toolkit.data.ImportObjectDataAdapter;
import com.philips.dmis.swt.ui.toolkit.dto.DataType;
import com.philips.dmis.swt.ui.toolkit.events.*;
import com.philips.dmis.swt.ui.toolkit.statement.method.M;
import com.philips.dmis.swt.ui.toolkit.statement.value.V;
import com.philips.dmis.swt.ui.toolkit.widgets.*;
import org.springframework.http.HttpMethod;

public abstract class AbstractViewerPage extends Page {
    IconsWidget icons;
    HL7Lib hl7;
    ForViewLib forViewLib;
    Panel pageHeader;
    ListContainer userMenu;
    ListContainer menu;
    HtmlLink signOutLink;
    HtmlLink patientSearchHtmlLink;
    HtmlLink medicalDocumentsHtmlLink;
    HtmlLink patientDetailsHtmlLink;
    QueryService userService;
    QueryService logoutService;
    Panel errorPanel;
    HtmlLabel errorMessage;
    HtmlList warningMessagesList;
    ListContainer titlePanel;
    HtmlLabel titleLabel;


    public AbstractViewerPage() throws Exception {
        this(false);
    }

    public AbstractViewerPage(boolean isDefault) throws Exception {
        super(isDefault);
    }

    @Override
    protected void build() throws Exception {
        icons = add(new IconsWidget("MaterialSymbolsSharp.woff2"));
        hl7 = add(new HL7Lib());
        forViewLib = add(new ForViewLib());
        pageHeader = add(new Panel(PanelType.PAGE_HEADER));
        userMenu = pageHeader.add(new ListContainer(ListType.HORIZONTAL));
        userMenu.setAppearance(WidgetAppearance.FLOAT_RIGHT);
        signOutLink = userMenu.add(new HtmlLink("Sign out"));
        menu = pageHeader.add(new ListContainer(ListType.HORIZONTAL));
        patientSearchHtmlLink = menu.add(new HtmlLink("Patient Search"));
        medicalDocumentsHtmlLink = menu.add(new HtmlLink("Medical Documents"));
        patientDetailsHtmlLink = menu.add(new HtmlLink("Patient Details"));
        titlePanel = pageHeader.add(new ListContainer(ListType.HORIZONTAL));
        titlePanel.setAppearance(WidgetAppearance.BLOCK);
        titleLabel = titlePanel.add(new HtmlLabel(""));
        titleLabel.setAppearance(WidgetAppearance.LARGE_FONT);
        errorPanel = add(new Panel(PanelType.ERROR));
        errorPanel.setVisible(false);
        errorMessage = errorPanel.add(new HtmlLabel(icons, "error", ""));
        warningMessagesList = add(new HtmlList(ListType.PILLS_WARNING));

        titleLabel.setIconsWidget(icons);
        patientSearchHtmlLink.onClick(new ClickEventHandler(
                M.OpenPage(PatientSearch.class)
        ));
        medicalDocumentsHtmlLink.onClick(new ClickEventHandler(
                M.OpenPage(PatientDocuments.class, V.GetPageArgument())
        ));
        patientDetailsHtmlLink.onClick(new ClickEventHandler(
                M.OpenPage(PatientDetails.class, V.GetPageArgument())
        ));

        userService = add(new QueryService(
                "http://localhost:8080/viewer/services/user/details/retrieve.json",
                false, false));
        userService.addDataAdapter(new ImportObjectDataAdapter(".user")
                .add(FieldMapping.map(".id", "id", DataType.STRING))
                .add(FieldMapping.map(".displayName", "displayName", DataType.STRING))
                .add(FieldMapping.map(".organization.displayName", "organization", DataType.STRING))
        );
        userService.onResponse(new ResponseEventHandler(
//                M.Log(V.Const("User service response"),
//                        V.ObjectMember(V.ParseJSON(V.GetValue(userService)), ".data.items.displayName"))

                // todo: As it turns out, it takes a second for the logout to work properly

                M.SetText(signOutLink,
                        V.StringConcat(
                                V.Const("Sign out "),
                                V.ObjectMember(V.ParseJSON(V.GetValue(userService)), ".data.items.displayName")))
        ));

        logoutService = add(new QueryService(
                "http://localhost:8080/viewer/services/user/logout",
                false, false));
        logoutService.setHttpMethod(HttpMethod.POST);
        signOutLink.onClick(new ClickEventHandler(
                M.Refresh(logoutService)
        ));
        logoutService.onRefresh(new RefreshEventHandler(
                M.RemoveAccessToken()
        ));
        logoutService.onResponse(new ResponseEventHandler(
                M.Log(V.Const("Logout"), V.GetEvent(ResponseEvent.HTTP_STATUS)),
                M.SetText(signOutLink, V.Const("Sign out")),
                M.OpenPage(LoginPage.class)
        ));
    }
}
