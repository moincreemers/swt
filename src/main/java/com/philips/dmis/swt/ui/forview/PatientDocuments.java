package com.philips.dmis.swt.ui.forview;

import com.philips.dmis.swt.ui.toolkit.data.DtoViewDataAdapter;
import com.philips.dmis.swt.ui.toolkit.data.FieldMapping;
import com.philips.dmis.swt.ui.toolkit.data.ImportArrayDataAdapter;
import com.philips.dmis.swt.ui.toolkit.data.MapDataAdapter;
import com.philips.dmis.swt.ui.toolkit.dto.DataType;
import com.philips.dmis.swt.ui.toolkit.dto.Order;
import com.philips.dmis.swt.ui.toolkit.dto.ViewAppearance;
import com.philips.dmis.swt.ui.toolkit.events.*;
import com.philips.dmis.swt.ui.toolkit.js.state.JsStateModule;
import com.philips.dmis.swt.ui.toolkit.statement.mapper.C;
import com.philips.dmis.swt.ui.toolkit.statement.method.M;
import com.philips.dmis.swt.ui.toolkit.statement.method.Operator;
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
        documents.setCacheType(CacheType.LOCAL_ONLY);
        documents.setAuthenticationType(AuthenticationType.BEARER_JWT);
        documents.addDataAdapter(
                new ImportArrayDataAdapter(".result.documents")
                        .add(FieldMapping.map(".formatCode", "formatCode", DataType.OBJECT))
                        .add(FieldMapping.map(".patientId", "patientId", DataType.STRING))
                        .add(FieldMapping.map(".hash", "hash", DataType.STRING))
                        .add(FieldMapping.map(".home", "homeCommunityId", DataType.STRING))
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

        //dtoViewDataAdapter.addAction("title", "download");

        documents.addDataAdapter(dtoViewDataAdapter);

        // Break-glass
        //http://localhost:8080/viewer/services/purposeofuse/overrideoption.json?patientID=356924599&patientIDAuth=1.3.6.1.4.1.21367.2005.3.7

        HtmlLink refreshHtmlLink = menu.add(new HtmlLink(icons, "refresh", "Refresh"));
        refreshHtmlLink.onClick(new ClickEventHandler(
                M.Refresh(documents)
        ));

        SingleRowPanel toolbar = add(new SingleRowPanel(PanelType.TOOLBAR));

        HtmlButton changePurposeOfUseButton = toolbar.add(new HtmlButton(icons, "lock", "Change purpose of use"));
        HtmlButton revertPurposeOfUseButton = toolbar.add(new HtmlButton(icons, "lock_open", "Revert to regular purpose of use"));
        changePurposeOfUseButton.setVisible(false);
        revertPurposeOfUseButton.setVisible(false);

        HtmlTable htmlTable = add(new HtmlTable());
        OrderedTableHeader orderedTableHeader = htmlTable.add(new OrderedTableHeader(documents));
        orderedTableHeader.setOrder("creationTime", Order.DESCENDING);
        HtmlTableBody htmlTableBody = htmlTable.add(new HtmlTableBody());
        htmlTableBody.setEmptyText("No documents found");
        htmlTableBody.addDataSource(documents);

        htmlTableBody.onOpen(new OpenEventHandler(
                M.Log(V.Const("Open"), V.GetEvent(OpenEvent.RECORD)),
                M.Iif(V.Is(V.ObjectProperty(V.GetEvent(OpenEvent.RECORD), "formatCode"), V.Const("DICOM Manifest"))).True(
                        M.OpenPage(WadoClient.class, V.GetEvent(OpenEvent.RECORD))
                ).Else(
                        M.OpenURL(
                                V.StringConcat(
                                        V.Const("http://localhost:8080"),
                                        V.ObjectProperty(V.GetEvent(OpenEvent.RECORD), "retrieveUrl"))
                        ))
        ));

        onActivate(new ActivateEventHandler(
                M.DeclarePageVariable(V.Const("purposeOfUsePatients")),
                M.DeclarePageVariable(V.Const("purposeOfUseReason")),

                //M.SetVisible(purposeOfUseToolbar, V.False),
                M.SetVisible(changePurposeOfUseButton, V.False),
                M.Iif(V.ArrayIncludes(V.GetPageVariable(V.Const("purposeOfUsePatients")),
                        V.StringConcat(
                                V.ObjectProperty(V.GetGlobalValue("selectedPatient"), "patientId"),
                                V.Const("-"),
                                V.ObjectProperty(V.GetGlobalValue("selectedPatient"), "patientIdAuth")
                        ))
                ).True(
                        //M.SetVisible(purposeOfUseToolbar, V.True),
                        M.SetVisible(revertPurposeOfUseButton, V.True)
                ),

                M.SetText(titleLabel, V.ObjectProperty(V.GetGlobalValue("selectedPatient"), "name")),
                M.SetText(patientIdHtmlLabel, V.StringConcat(V.Const("ID: "), V.ObjectProperty(V.GetGlobalValue("selectedPatient"), "patientId"))),
                M.SetText(patientDateOfBirthHtmlLabel, V.StringConcat(V.ObjectProperty(V.GetGlobalValue("selectedPatient"), "dateOfBirth"), V.Const(" ("), V.ObjectProperty(V.GetGlobalValue("selectedPatient"), "age"), V.Const("yr)"))),
                M.SetText(patientGenderHtmlLabel, V.ObjectProperty(V.GetGlobalValue("selectedPatient"), "gender")),
                M.SetText(patientAddressHtmlLabel, V.ObjectProperty(V.GetGlobalValue("selectedPatient"), "address")),

                // note: PatientSearch passes the entire patient record to this page
                M.SetQueryParameter(documents, "patientID", V.ObjectProperty(V.GetGlobalValue("selectedPatient"), "patientId")),
                M.SetQueryParameter(documents, "patientIDAuth", V.ObjectProperty(V.GetGlobalValue("selectedPatient"), "patientIdAuth")),
                M.Refresh(documents, JsStateModule.REASON_LOCAL),

                M.Refresh(userService)
        ));

        documents.onRefresh(new RefreshEventHandler(
                M.SetDisabled(refreshHtmlLink),
                M.SetVisible(errorPanel, V.False),
                //M.RemoveAllItems(errorPanel),
                M.SetVisible(warningMessagesList, V.False),
                M.RemoveAllItems(warningMessagesList)
        ));

        documents.onResponse(new ResponseEventHandler(
                M.SetEnabled(refreshHtmlLink),
                M.SetVisible(warningMessagesList, V.False),
                M.RemoveAllItems(warningMessagesList),

                M.Iif(ResponseEvent.isUnauthorized()).True(
                        M.OpenPage(LoginPage.class)
                ),
                M.Iif(ResponseEvent.isServerError()).True(
                        M.SetText(errorMessage, V.Call(forViewLib, ForViewLib.PARSE_ERROR, V.GetEvent())),
                        M.SetVisible(errorPanel, V.True)
                ),
                M.Iif(ResponseEvent.isBadRequest()).True(
                        M.SetText(errorMessage, ResponseEvent.getResponseText()),
                        M.SetVisible(errorPanel, V.True)
                ),
                M.Iif(V.Is(V.GetEvent(ResponseEvent.HTTP_STATUS), V.HTTP_OK())).True(
                        M.SetVisible(errorPanel, V.False)
                ),
                M.Iif(V.Is(V.GetEvent(ResponseEvent.HTTP_STATUS), V.HTTP_OK())).True(
                        M.SetVisible(errorPanel, V.False),
                        M.ForEach(V.ObjectProperty(ResponseEvent.getResponseData(), "result.warnings"))
                                .Apply(
                                        M.AppendItems(warningMessagesList, V.Call(forViewLib, ForViewLib.PARSE_WARNING, V.Value())),
                                        M.SetVisible(warningMessagesList, V.True),

                                        // purpose of use
                                        M.Iif(V.ArrayIncludes(V.GetPageVariable(V.Const("purposeOfUsePatients")),
                                                V.StringConcat(
                                                        V.ObjectProperty(V.GetGlobalValue("selectedPatient"), "patientId"),
                                                        V.Const("-"),
                                                        V.ObjectProperty(V.GetGlobalValue("selectedPatient"), "patientIdAuth")
                                                ))
                                        ).False(
                                                M.Iif(V.Call(forViewLib, ForViewLib.IS_DOCUMENT_ACCESS_RESTRICTED, V.Value())).True(
                                                        //M.SetVisible(purposeOfUseToolbar, V.True),
                                                        M.SetVisible(changePurposeOfUseButton, V.True),
                                                        M.SetVisible(revertPurposeOfUseButton, V.False)
                                                ))
                                )
                )
        ));

        UpdateService changePurposeOfUseService = add(new UpdateService(
                "http://localhost:8080/viewer/services/purposeofuse/override"));
        changePurposeOfUseService.setContentType(ContentType.FORM_URLENCODED);
        changePurposeOfUseService.setAuthenticationType(AuthenticationType.BEARER_JWT);

        UpdateService revertPurposeOfUseService = add(new UpdateService(
                "http://localhost:8080/viewer/services/purposeofuse/revoke"));
        revertPurposeOfUseService.setContentType(ContentType.FORM_URLENCODED);
        revertPurposeOfUseService.setAuthenticationType(AuthenticationType.BEARER_JWT);

        HtmlDialog changePurposeOfUseDialog = add(new HtmlDialog());
        changePurposeOfUseDialog.add(new HtmlHeading("Change purpose of use", 3));
        changePurposeOfUseDialog.add(new HtmlParagraph("""
                You may change your purpose of use for this patient's information.<br/>
                This might affect what information is visible to you.<br/>
                All access to information will be audited and logged by the application.<br/>
                The patient may be informed.
                """));
        HtmlLabel purposeOfUseLabel = changePurposeOfUseDialog.add(new HtmlLabel("Please specify the reason for access:"));
        HtmlTextAreaInput purposeOfUseReasonText = changePurposeOfUseDialog.add(new HtmlTextAreaInput());
        purposeOfUseLabel.setFor(purposeOfUseReasonText);
        Panel changePurposeOfUseDialogButtonPanel = changePurposeOfUseDialog.add(new Panel());
        changePurposeOfUseDialogButtonPanel.setAppearance(WidgetAppearance.ALIGN_RIGHT);
        HtmlButton changePurposeOfUseCancelButton = changePurposeOfUseDialogButtonPanel.add(new HtmlButton("Cancel"));
        HtmlButton changePurposeOfUseOkButton = changePurposeOfUseDialogButtonPanel.add(new HtmlButton(ButtonType.PRIMARY, "OK"));
        changePurposeOfUseOkButton.setEnabled(false);
        changePurposeOfUseCancelButton.onClick(new ClickEventHandler(
                M.CloseDialog(changePurposeOfUseDialog, V.Cancel)
        ));
        changePurposeOfUseOkButton.onClick(new ClickEventHandler(
                M.CloseDialog(changePurposeOfUseDialog, V.Ok)
        ));
        changePurposeOfUseButton.onClick(new ClickEventHandler(
                M.OpenDialog(changePurposeOfUseDialog)
        ));
        purposeOfUseReasonText.onInput(new InputEventHandler(
                M.SetDisabled(changePurposeOfUseOkButton, V.IsEmpty(V.GetValue(purposeOfUseReasonText)))
        ));

        changePurposeOfUseDialog.onClose(new CloseDialogEventHandler(
                M.Iif(
                        V.And(
                                V.Is(V.GetReturnValue(changePurposeOfUseDialog), V.Ok),
                                V.IsNotEmpty(V.GetValue(purposeOfUseReasonText))
                        )
                ).True(
                        // POST: http://localhost:8080/viewer/services/purposeofuse/override
                        // patientid=7482736282
                        // patientIDAuth=1.3.6.1.4.1.21367.2005.3.7
                        // reason=test
                        // code=2
                        // codeSystem=1.0.14265.1
                        M.SetValue(changePurposeOfUseService,
                                V.Object()
                                        .add(V.Const("patientID"), V.ObjectProperty(V.GetGlobalValue("selectedPatient"), "patientId"))
                                        .add(V.Const("patientIDAuth"), V.ObjectProperty(V.GetGlobalValue("selectedPatient"), "patientIdAuth"))
                                        .add(V.Const("reason"), V.GetValue(purposeOfUseReasonText))
                                        .add(V.Const("code"), V.Const("2"))
                                        .add(V.Const("codeSystem"), V.Const("1.0.14265.1"))
                        )
                )
        ));

        changePurposeOfUseService.onResponse(new ResponseEventHandler(
                M.Iif(ResponseEvent.isOk()).True(
                        M.SetPageVariable(V.Const("purposeOfUsePatients"),
                                V.StringConcat(
                                        V.ObjectProperty(V.GetGlobalValue("selectedPatient"), "patientId"),
                                        V.Const("-"),
                                        V.ObjectProperty(V.GetGlobalValue("selectedPatient"), "patientIdAuth")
                                ), Operator.ADD),
                        //M.SetVisible(purposeOfUseToolbar, V.True),
                        M.SetVisible(changePurposeOfUseButton, V.False),
                        M.SetVisible(revertPurposeOfUseButton, V.True),
                        M.Refresh(documents)
                )
        ));

        revertPurposeOfUseButton.onClick(new ClickEventHandler(
                // POST: https://localhost/viewer/services/purposeofuse/revoke
                // patientid=7482736282
                // patientIDAuth=1.3.6.1.4.1.21367.2005.3.7
                M.SetValue(revertPurposeOfUseService,
                        V.Object()
                                .add(V.Const("patientID"), V.ObjectProperty(V.GetGlobalValue("selectedPatient"), "patientId"))
                                .add(V.Const("patientIDAuth"), V.ObjectProperty(V.GetGlobalValue("selectedPatient"), "patientIdAuth"))
                )
        ));

        revertPurposeOfUseService.onResponse(new ResponseEventHandler(
                M.Iif(ResponseEvent.isOk()).True(
                        M.SetPageVariable(V.Const("purposeOfUsePatients"),
                                V.StringConcat(
                                        V.ObjectProperty(V.GetGlobalValue("selectedPatient"), "patientId"),
                                        V.Const("-"),
                                        V.ObjectProperty(V.GetGlobalValue("selectedPatient"), "patientIdAuth")
                                ), Operator.REMOVE),
                        //M.SetVisible(purposeOfUseToolbar, V.True),
                        M.SetVisible(changePurposeOfUseButton, V.True),
                        M.SetVisible(revertPurposeOfUseButton, V.False),
                        M.Refresh(documents)
                )
        ));
    }

}
