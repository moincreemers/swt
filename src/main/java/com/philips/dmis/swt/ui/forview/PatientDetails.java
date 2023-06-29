package com.philips.dmis.swt.ui.forview;

import com.philips.dmis.swt.ui.toolkit.data.DtoViewDataAdapter;
import com.philips.dmis.swt.ui.toolkit.events.ActivateEventHandler;
import com.philips.dmis.swt.ui.toolkit.js.state.JsStateModule;
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

                M.SetText(titleLabel, V.ObjectProperty(V.GetPageArgument(), "name")),
                M.SetText(patientIdHtmlLabel, V.StringConcat(V.Const("ID: "), V.ObjectProperty(V.GetPageArgument(), "patientId"))),
                M.SetText(patientDateOfBirthHtmlLabel, V.StringConcat(V.ObjectProperty(V.GetPageArgument(), "dateOfBirth"), V.Const(" ("), V.ObjectProperty(V.GetPageArgument(), "age"), V.Const("yr)"))),
                M.SetText(patientGenderHtmlLabel, V.ObjectProperty(V.GetPageArgument(), "gender")),
                M.SetText(patientAddressHtmlLabel, V.ObjectProperty(V.GetPageArgument(), "address")),

// note: this was used when patientRecord was a QueryService
//                M.SetQueryParameter(patientRecord, "patientID", V.ObjectMember(V.GetPageArgument(), "patientId")),
//                M.SetQueryParameter(patientRecord, "patientIDAuth", V.ObjectMember(V.GetPageArgument(), "patientIdAuth")),
                M.Refresh(patientRecord, JsStateModule.REASON_LOCAL),

                M.Refresh(userService)
        ));
    }
}
