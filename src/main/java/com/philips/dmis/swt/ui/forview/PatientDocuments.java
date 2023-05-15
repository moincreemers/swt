package com.philips.dmis.swt.ui.forview;

import com.philips.dmis.swt.ui.toolkit.data.*;
import com.philips.dmis.swt.ui.toolkit.dto.DataType;
import com.philips.dmis.swt.ui.toolkit.dto.Order;
import com.philips.dmis.swt.ui.toolkit.dto.ViewAppearance;
import com.philips.dmis.swt.ui.toolkit.events.*;
import com.philips.dmis.swt.ui.toolkit.js.pages.JsPagesModule;
import com.philips.dmis.swt.ui.toolkit.statement.mapper.C;
import com.philips.dmis.swt.ui.toolkit.statement.method.M;
import com.philips.dmis.swt.ui.toolkit.statement.value.V;
import com.philips.dmis.swt.ui.toolkit.widgets.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PatientDocuments extends AbstractViewerPage {
    static class DocumentRecord {
        public Object formatCode;
        //        public String mimeType;
        //        public String id;
        //        public String logicalId;
        //        public String status;
        //        public String objectType;
        //        public String home;
        public String creationTime;
        public String createdOn;
        public String hash;
        //        public String languageCode;
        public String repositoryUniqueId;
        //        public String serviceStartTime;
        //        public String serviceStopTime;
        //        public int size;
        //        public String sourcePatientId; // 356924599^^^&1.3.6.1.4.1.21367.2005.3.7&ISO
        //        public java.util.List<String> sourcePatientInfo = new ArrayList<>();
        //        public String documentAvailability;
        public String title;
        //        public String comments;
        //        public String version;
        public List<Object> authors = new ArrayList<>();
        //        public CodedValue classCode;
        //        public java.util.List<CodedValue> confidentialityCodeList = new ArrayList<>();
        //        public java.util.List<CodedValue> eventCodeList;

        //        public CodedValue healthcareFacilityTypeCode;
        //        public CodedValue practiceSettingCode;
        public Object typeCode;
        public String patientId; // 356924599^^^&1.3.6.1.4.1.21367.2005.3.7&ISO
        public String uniqueId; // 1.2.3.64537.39363.258.60738.39065.6552.64643.29376
        //        public java.util.List<Action> actions = new ArrayList<>();
        public String retrieveUrl; // /viewer/services/document/retrieve?formatCode=pdf&patientID=356924599&repositoryUniqueId=1.2.3&uniqueId=1.2.3.64537.39363.258.60738.39065.6552.64643.29376&patientIDAuth=1.3.6.1.4.1.21367.2005.3.7&home=1.2.3&hash=32b9c7e987cdb6bcc880415ff1c9932b0ab35454
        public String exportUrl; // /viewer/services/document/retrieve?mode=export&formatCode=pdf&patientID=356924599&repositoryUniqueId=1.2.3&uniqueId=1.2.3.64537.39363.258.60738.39065.6552.64643.29376&patientIDAuth=1.3.6.1.4.1.21367.2005.3.7&home=1.2.3&hash=7a19f6f67ad0c850c46b2e404043f2c640d22dc8
        public String downloadUrl; // /viewer/services/document/retrieve?formatCode=download&patientID=356924599&repositoryUniqueId=1.2.3&uniqueId=1.2.3.64537.39363.258.60738.39065.6552.64643.29376&patientIDAuth=1.3.6.1.4.1.21367.2005.3.7&home=1.2.3&title=ECG&hash=d17e1489b95d9bab81bda5bef77ba6973299594a
        //        public java.util.List<String> documentRefs = new ArrayList<>();
    }

    public PatientDocuments() throws Exception {
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




        /*
            - patientID
            - patientIDAuth
        */
        QueryService documents = add(new QueryService("http://localhost:8080/viewer/services/document/list.json",
                false, false));
        TokenUtil.setAuthorizationHeader(documents);
        documents.addDataAdapter(
                new ImportArrayDataAdapter(".result.documents")
                        .add(FieldMapping.map(".formatCode", "formatCode", DataType.OBJECT))
                        .add(FieldMapping.map(".patientId", "patientId", DataType.STRING))
                        .add(FieldMapping.map(".hash", "hash", DataType.STRING))
                        .add(FieldMapping.map(".uniqueId", "uniqueId", DataType.STRING))
                        .add(FieldMapping.map(".repositoryUniqueId", "repositoryUniqueId", DataType.STRING))
                        .add(FieldMapping.map(".creationTime", "creationTime", DataType.STRING))
                        .add(FieldMapping.map(".creationTime", "createdOn", DataType.STRING))
                        .add(FieldMapping.map(".title", "title", DataType.STRING))
                        .add(FieldMapping.map(".authors", "authors", DataType.OBJECT))
                        .add(FieldMapping.map(".typeCode", "typeCode", DataType.OBJECT))
                        .add(FieldMapping.map(".retrieveUrl", "retrieveUrl", DataType.URL))
                        .add(FieldMapping.map(".exportUrl", "exportUrl", DataType.URL))
                        .add(FieldMapping.map(".downloadUrl", "downloadUrl", DataType.URL))
        );

        ImportArrayDataAdapter importWarningsDataAdapter = new ImportArrayDataAdapter(".result.warnings", ".data.warnings")
                .add(FieldMapping.map(".warningCode", "warningCode", DataType.STRING))
                .add(FieldMapping.map(".warningContext", "warningContext", DataType.STRING))
                .add(FieldMapping.map(".message", "message", DataType.STRING));
        documents.addDataAdapter(importWarningsDataAdapter);

        warningMessagesList.addDataSource(documents,
                new OutputSelectorDataAdapter(importWarningsDataAdapter),
                new MapDataAdapter()
                        .map("warningCode", C.Call(forViewLib, ForViewLib.PARSE_WARNING), "warningCode", "warningContext", "message")
        );

        documents.addDataAdapter(new MapDataAdapter()
                .map("formatCode", C.Call(hl7, HL7Lib.FORMAT_CODED_VALUE))
                .map("createdOn", C.Call(hl7, HL7Lib.FORMAT_DATE))
                .map("typeCode", C.Call(hl7, HL7Lib.FORMAT_CODED_VALUE))
                .map("authors", C.Call(hl7, HL7Lib.FORMAT_AUTHOR))
        );

        DtoViewDataAdapter dtoViewDataAdapter = new DtoViewDataAdapter(DocumentRecord.class);
        dtoViewDataAdapter.applyToAllFields(ViewAppearance.HIDDEN);
        dtoViewDataAdapter.setAppearance("formatCode", ViewAppearance.DEFAULT);
        dtoViewDataAdapter.setAppearance("createdOn", ViewAppearance.DEFAULT);
        // note: tell this column to use values from creationTime for sorting
        dtoViewDataAdapter.setOrderSource("createdOn", "creationTime");
        dtoViewDataAdapter.setAppearance("title", ViewAppearance.OPEN);
        dtoViewDataAdapter.setAppearance("authors", ViewAppearance.DEFAULT);
        dtoViewDataAdapter.setAppearance("typeCode", ViewAppearance.DEFAULT);

        dtoViewDataAdapter.addAction("title", "download");

        documents.addDataAdapter(dtoViewDataAdapter);

        // Break-glass
        //http://localhost:8080/viewer/services/purposeofuse/overrideoption.json?patientID=356924599&patientIDAuth=1.3.6.1.4.1.21367.2005.3.7

        HtmlLink refreshHtmlLink = menu.add(new HtmlLink(icons, "refresh", "Refresh"));
        refreshHtmlLink.onClick(new ClickEventHandler(
                M.Refresh(documents)
        ));

        HtmlTable htmlTable = add(new HtmlTable());
        OrderedTableHeader orderedTableHeader = htmlTable.add(new OrderedTableHeader(documents));
        orderedTableHeader.setOrder("creationTime", Order.DESCENDING);
        HtmlTableBody htmlTableBody = htmlTable.add(new HtmlTableBody());
        htmlTableBody.setEmptyText("No documents found");
        htmlTableBody.addDataSource(documents);

        htmlTableBody.onOpen(new OpenEventHandler(
                M.OpenURL(
                        V.StringConcat(
                                V.Const("http://localhost:8080"),
                                V.ObjectMember(V.GetEvent(OpenEvent.RECORD), "retrieveUrl"))
                )
        ));

        onActivate(new ActivateEventHandler(
                M.SetText(titleLabel, V.ObjectMember(V.GetPageArgument(), "name")),
                M.SetText(patientIdHtmlLabel, V.StringConcat(V.Const("ID: "), V.ObjectMember(V.GetPageArgument(), "patientId"))),
                M.SetText(patientDateOfBirthHtmlLabel, V.StringConcat(V.ObjectMember(V.GetPageArgument(), "dateOfBirth"), V.Const(" ("), V.ObjectMember(V.GetPageArgument(), "age"), V.Const("yr)"))),
                M.SetText(patientGenderHtmlLabel, V.ObjectMember(V.GetPageArgument(), "gender")),
                M.SetText(patientAddressHtmlLabel, V.ObjectMember(V.GetPageArgument(), "address")),

                // note: PatientSearch passes the entire patient record to this page
                M.SetQueryParameter(documents, "patientID", V.ObjectMember(V.GetPageArgument(), "patientId")),
                M.SetQueryParameter(documents, "patientIDAuth", V.ObjectMember(V.GetPageArgument(), "patientIdAuth")),
                M.Refresh(documents, JsPagesModule.REASON_LOCAL),

                M.Refresh(userService)
        ));

        documents.onRefresh(new RefreshEventHandler(
                M.SetDisabled(refreshHtmlLink),
                M.SetDisplay(errorPanel, V.False)
        ));

        documents.onResponse(new ResponseEventHandler(
                M.SetEnabled(refreshHtmlLink),

                M.Iif(V.Is(V.GetEvent(ResponseEvent.HTTP_STATUS), V.HTTP_UNAUTHORIZED())).True(
                        M.OpenPage(LoginPage.class)
                ),

                M.Iif(V.Is(V.GetEvent(ResponseEvent.HTTP_STATUS), V.HTTP_BAD_REQUEST())).True(
                        M.SetText(errorMessage, V.GetEvent(ResponseEvent.HTTP_RESPONSE_TEXT)),
                        M.SetDisplay(errorPanel, V.True)
                ),

                M.Iif(V.Is(V.GetEvent(ResponseEvent.HTTP_STATUS), V.HTTP_OK())).True(
                        M.SetDisplay(errorPanel, V.False)
                )
        ));
    }
}
