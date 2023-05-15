package com.philips.dmis.swt.ui.forview;

import com.philips.dmis.swt.ui.toolkit.data.DtoViewDataAdapter;
import com.philips.dmis.swt.ui.toolkit.events.ActivateEventHandler;
import com.philips.dmis.swt.ui.toolkit.js.pages.JsPagesModule;
import com.philips.dmis.swt.ui.toolkit.statement.method.M;
import com.philips.dmis.swt.ui.toolkit.statement.value.V;
import com.philips.dmis.swt.ui.toolkit.widgets.*;
import org.springframework.stereotype.Component;

@Component
public class PatientDetails extends AbstractViewerPage {
    static class PatientRecord {
        public String patientId;
        public String patientIdAuth;
        public String authority;
        public String name;
        public String dateOfBirth;
        public int age;
        public String gender;
        public String organization;
        public String address;
        public Object XTN;
    }

    public PatientDetails() throws Exception {
    }

    @Override
    protected void build() throws Exception {
        super.build();

        titleLabel.setIcon("account_circle");
        HtmlLabel patientIdHtmlLabel = titlePanel.add(new HtmlLabel(""));
        HtmlLabel patientDateOfBirthHtmlLabel = titlePanel.add(new HtmlLabel(""));
        HtmlLabel patientGenderHtmlLabel = titlePanel.add(new HtmlLabel(""));
        HtmlLabel patientAddressHtmlLabel = titlePanel.add(new HtmlLabel(""));
        HtmlLabel patientPhoneHtmlLabel = titlePanel.add(new HtmlLabel(""));
        HtmlLabel patientEmailHtmlLabel = titlePanel.add(new HtmlLabel(""));

        Data patientRecord = add(new Data("patientRecord"));
        patientRecord.addDataAdapter(new DtoViewDataAdapter(PatientRecord.class));

        HtmlTable htmlTable = add(new HtmlTable());
        htmlTable.setAppearance(WidgetAppearance.FIT_CONTENT);
        HtmlTableBody htmlTableBody = htmlTable.add(new HtmlTableBody(TableOrientationType.ROW_HEADERS));
        htmlTableBody.setEmptyText("No patient details available"); // not possible
        htmlTableBody.addDataSource(patientRecord);

        onActivate(new ActivateEventHandler(
                M.SetValue(patientRecord, V.GetPageArgument()),

                M.SetText(titleLabel, V.ObjectMember(V.GetPageArgument(), "name")),
                M.SetText(patientIdHtmlLabel, V.StringConcat(V.Const("ID: "), V.ObjectMember(V.GetPageArgument(), "patientId"))),
                M.SetText(patientDateOfBirthHtmlLabel, V.StringConcat(V.ObjectMember(V.GetPageArgument(), "dateOfBirth"), V.Const(" ("), V.ObjectMember(V.GetPageArgument(), "age"), V.Const("yr)"))),
                M.SetText(patientGenderHtmlLabel, V.ObjectMember(V.GetPageArgument(), "gender")),
                M.SetText(patientAddressHtmlLabel, V.ObjectMember(V.GetPageArgument(), "address")),

                M.SetQueryParameter(patientRecord, "patientID", V.ObjectMember(V.GetPageArgument(), "patientId")),
                M.SetQueryParameter(patientRecord, "patientIDAuth", V.ObjectMember(V.GetPageArgument(), "patientIdAuth")),
                M.Refresh(patientRecord, JsPagesModule.REASON_LOCAL),

                M.Refresh(userService)
        ));
    }
}
