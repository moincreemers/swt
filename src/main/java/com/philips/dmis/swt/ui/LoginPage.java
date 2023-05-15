package com.philips.dmis.swt.ui;

import com.philips.dmis.swt.ui.toolkit.events.*;
import com.philips.dmis.swt.ui.toolkit.statement.method.M;
import com.philips.dmis.swt.ui.toolkit.statement.value.V;
import com.philips.dmis.swt.ui.toolkit.widgets.*;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

@Component
public class LoginPage extends AbstractViewerPage {
    public LoginPage() throws Exception {
    }

    @Override
    protected void build() throws Exception {
        super.build();

        // set to repeat every 2.5 minutes (we do not know why but that is what ForView does)
        Timer refreshSessionTokenTimer = add(new Timer("refreshSessionTokenTimer", TimerType.REPEAT, 150000));

        QueryService loginService = add(new QueryService("http://localhost:8080/viewer/services/user/login.json",
                false, false));
        loginService.setHttpMethod(HttpMethod.POST);
        loginService.setContentType(ContentType.FORM_URLENCODED);

        // note: do we really need this?
        //?lastUpdateTime=1683471605126&request.preventCache=1683480286176
        // ... apparently we do not

        QueryService refreshSessionService = add(new QueryService("http://localhost:8080/viewer/services/user/session/refresh.json",
                false, true));
        TokenUtil.setAuthorizationHeader(refreshSessionService);

        // this is one way of doing it (see onResponse)
//        loginService.addDataAdapter(new ExtractDataAdapter(".response")
//                .map("jwt", M.SetAccessToken(V.GetValue("jwt"))));

        titleLabel.setIcon("login");
        titleLabel.setText("Sign In");

        HtmlForm htmlForm = add(new HtmlForm());
        htmlForm.setAppearance(WidgetAppearance.CENTER);
        ListContainer loginFormList = htmlForm.add(new ListContainer(ListType.VERTICAL));
        HtmlTextInput userName = loginFormList.add(new HtmlTextInput("j_username"));
        userName.setPlaceholder("User name");
        HtmlPasswordInput password = loginFormList.add(new HtmlPasswordInput("j_password"));
        password.setPlaceholder("Password");
        HtmlButton loginHtmlButton = loginFormList.add(new HtmlButton(ButtonType.PRIMARY, "Login"));

        onActivate(new ActivateEventHandler(
                // note: suspend the session refresh because user is signed out
                M.Stop(refreshSessionTokenTimer),

                M.SetDisplay(errorPanel, V.False),
                M.SetDisabled(patientSearchHtmlLink),
                M.SetDisabled(medicalDocumentsHtmlLink),
                M.SetDisabled(patientDetailsHtmlLink),
                M.SetDisabled(signOutLink),

                M.Reset(htmlForm),
                M.Focus(userName),
                M.SetEnabled(loginHtmlButton)
        ));

        onKeyDown(new KeyDownEventHandler(
                M.Iif(KeyDownEvent.IsKeyCode(KeyDownEvent.VK_ENTER)).True(
                        M.Click(loginHtmlButton)
                )
        ));

        loginHtmlButton.onClick(new ClickEventHandler(
                M.SetQueryParameter(loginService, "j_username", V.GetValue(userName)),
                M.SetQueryParameter(loginService, "j_password", V.GetValue(password)),
                M.Refresh(loginService)
        ));

        refreshSessionTokenTimer.onElapsed(new ElapsedEventHandler(
                M.Log("Refresh session"),
                M.Refresh(refreshSessionService)
        ));

        refreshSessionService.onResponse(new ResponseEventHandler(
                M.Iif(V.Is(V.GetEvent(ResponseEvent.HTTP_STATUS), V.HTTP_OK())).True(
                        M.Log("refresh session ok"),
                        M.SetAccessToken(
                                V.ObjectMember(V.ParseJSON(V.GetEvent(ResponseEvent.HTTP_RESPONSE_TEXT)), "jwt")),
                        M.SetGlobalValue("sessionTimeout",
                                V.ObjectMember(V.ParseJSON(V.GetEvent(ResponseEvent.HTTP_RESPONSE_TEXT)), "sessionTimeout"))
                ).Else(
                        M.Log(V.Call(forViewLib, ForViewLib.PARSE_ERROR, V.GetEvent())),
                        M.OpenPage(LoginPage.class)
                )
        ));

        loginService.onRefresh(new RefreshEventHandler(
                M.SetDisabled(loginHtmlButton)
        ));
        loginService.onResponse(new ResponseEventHandler(
                M.SetEnabled(loginHtmlButton),
                M.RemoveAccessToken(),
                M.Iif(V.Is(V.GetEvent(ResponseEvent.HTTP_STATUS), V.HTTP_OK())).True(

                        // store the access token and start the timer
                        M.SetAccessToken(V.Call(forViewLib, ForViewLib.PARSE_LOGIN, V.GetEvent())),
                        M.Start(refreshSessionTokenTimer),

                        M.OpenPage(PatientSearch.class)
                ).Else(
                        M.SetText(errorMessage, V.Call(forViewLib, ForViewLib.PARSE_ERROR, V.GetEvent())),
                        M.SetDisplay(errorPanel, V.True)
                )
                // todo: sessionTimeout
        ));
    }
}
