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

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class PatientSearch extends AbstractViewerPage {
    static class PatientRecord {
        public String rawPid;
        public String patientId;
        public String patientIdAuth;
        public String authority;
        public String name;
        public String gender;
        public Date dateOfBirth;
        public int age;
        public String address;
//        public String phone;
//        public String email;
    }

    public PatientSearch() throws Exception {
        super(false);
    }

    @Override
    protected void build() throws Exception {
        super.build();

        titleLabel.setIcon("search");
        titleLabel.setText("Find Patient");

        StaticData whatDomainReturnedOptions = add(new StaticData(
                DataBuilder.keyValue()
                        .add("", "Anywhere")
                        .getData()
        ));

        QueryService patientDomains = add(new QueryService(
                "http://localhost:8080/viewer/services/domain/list.json", false, false));
        // note: this endpoint seems to be cookie-only. Sending a Bearer token always results in 401.
        TokenUtil.setAuthorizationHeader(patientDomains);
        patientDomains.addDataAdapter(
                new ImportArrayDataAdapter(".items")
                        .add(FieldMapping.map(".id", KeyValueListDataAdapter.DEFAULT_KEY_FIELD, DataType.STRING))
                        .add(FieldMapping.map(".name", KeyValueListDataAdapter.DEFAULT_VALUE_FIELD, DataType.STRING))
        );
        patientDomains.addDataAdapter(
                new MetaDataAdapter("", "domains")
                        .map("allowPatientIdQueryWithoutDomain",
                                FieldMapping.map(".allowPatientIdQueryWithoutDomain", "allowPatientIdQueryWithoutDomain", DataType.BOOLEAN)
                        )
                        .map("targetDomain",
                                FieldMapping.map(".targetDomain", "targetDomain", DataType.STRING)
                        )
        );

        QueryService patients = add(new QueryService("http://localhost:8080/viewer/services/patient/list.json",
                false, false));
        // note: endpoint does not use access token but a cookie
        TokenUtil.setAuthorizationHeader(patients);
        patients.addDataAdapter(
                new ImportArrayDataAdapter(".result.patients")
                        .add(FieldMapping.map(".id.extension", "patientId", DataType.STRING))
                        .add(FieldMapping.map(".id.root", "patientIdAuth", DataType.STRING))
                        .add(FieldMapping.map(".id.root", "authority", DataType.STRING))
                        .add(FieldMapping.map(".identifiedPerson.name.display", "name", DataType.STRING))
                        .add(FieldMapping.map(".identifiedPerson.administrativeGenderCode.code", "gender", DataType.STRING))
                        .add(FieldMapping.map(".identifiedPerson.birthTime.value", "dateOfBirth", DataType.STRING))
                        .add(FieldMapping.map(".address.display", "address", DataType.STRING))
                        .add(FieldMapping.indexArray(".additionalAttributes", "additionalAttributes", "name"))
                        .add(FieldMapping.map(".additionalAttributes.PID14.values", "XTN", DataType.ARRAY))
        );

        ImportArrayDataAdapter importWarningsDataAdapter = new ImportArrayDataAdapter(".result.warnings", ".data.warnings")
                .add(FieldMapping.map(".warningCode", "warningCode", DataType.STRING))
                .add(FieldMapping.map(".warningContext", "warningContext", DataType.STRING))
                .add(FieldMapping.map(".message", "message", DataType.STRING));
        patients.addDataAdapter(importWarningsDataAdapter);

        warningMessagesList.addDataSource(patients,
                new OutputSelectorDataAdapter(importWarningsDataAdapter),
                new MapDataAdapter()
                        .map("warningCode", C.Call(forViewLib, ForViewLib.PARSE_WARNING), "warningCode", "warningContext", "message")
        );

        patientDomains.onResponse(new ResponseEventHandler(
                //M.Log(V.Const("patient-domains response"), V.GetEvent()),

                M.Iif(V.Is(V.GetEvent(ResponseEvent.HTTP_STATUS), V.HTTP_UNAUTHORIZED())).True(
                        M.OpenPage(LoginPage.class)
                ),
                M.Iif(V.Is(V.GetEvent(ResponseEvent.HTTP_STATUS), V.HTTP_OK())).True(
                        // the default is USER, so we change to LOCAL which enables cache
                        M.Refresh(patients, JsPagesModule.REASON_LOCAL)
                )
        ));

        // todo: how do we do a join?
        Map<Object, Object> domains = new HashMap<>();
        domains.put("2.16.840.1.113883.2.1.4.1", "BSN");
        domains.put("1.3.6.1.4.1.21367.2005.3.7", "ISX");

        Map<Object, Object> genders = new HashMap<>();
        genders.put("*", "Unknown");
        genders.put("M", "Male");
        genders.put("F", "Female");

        patients.addDataAdapter(new MapDataAdapter()
                        .map("authority", C.Lookup(domains))
                        .map("gender", C.Lookup(genders))
                        .map("age", C.Call(hl7, HL7Lib.FORMAT_AGE), "dateOfBirth")
                        .map("dateOfBirth", C.Call(hl7, HL7Lib.FORMAT_DATE))
                //.map("email", "pid14", C.Call(hl7, HL7Lib.PARSE_XTN))
        );

        // create the default view, this is generated from a DTO
        DtoViewDataAdapter dtoViewDataAdapter = new DtoViewDataAdapter(PatientRecord.class);
        // set the patientId as the cell to click to open the document list
        dtoViewDataAdapter.setAppearance("rawPid", ViewAppearance.HIDDEN);
        dtoViewDataAdapter.setAppearance("patientId", ViewAppearance.OPEN);
        dtoViewDataAdapter.setAppearance("patientIdAuth", ViewAppearance.HIDDEN);
        patients.addDataAdapter(dtoViewDataAdapter);

        // Parameters:
        // - givenName
        // - surName
        // - patientID
        // - patientIDAuth (oid)
        // - administrativeSex (M/F)
        // - whatDomainReturned (oid)
        // - xdsChangesDaysAgo (int)

        Panel navLeft = add(new Panel(PanelType.NAV_LEFT));
        HtmlForm filterHtmlForm = navLeft.add(new HtmlForm());
        ListContainer nameFilterPanel = filterHtmlForm.add(new ListContainer(ListType.VERTICAL));
        nameFilterPanel.setAppearance(WidgetAppearance.BLOCK);
        HtmlTextInput surNameFilter = nameFilterPanel.add(new HtmlTextInput());
        surNameFilter.setPlaceholder("Last name");
        HtmlTextInput givenNameFilter = nameFilterPanel.add(new HtmlTextInput());
        givenNameFilter.setPlaceholder("Given name");

        ListContainer idFilterPanel = filterHtmlForm.add(new ListContainer(ListType.VERTICAL));
        idFilterPanel.setAppearance(WidgetAppearance.BLOCK);
        HtmlTextInput idFilter = idFilterPanel.add(new HtmlTextInput());
        idFilter.setPlaceholder("ID");
        HtmlSelect patientIdAuthFilter = idFilterPanel.add(new HtmlSelect());
        patientIdAuthFilter.setDisabled(true);
        patientIdAuthFilter.addDataSource(DataSourceUsage.OPTIONS, patientDomains,
                new KeyValueListDataAdapter(OrderBy.BY_VALUE));
        idFilter.onInput(new InputEventHandler(
                M.SetDisabled(patientIdAuthFilter, V.Is(V.GetValue(idFilter), V.Const("")))
        ));

        ListContainer otherFilterPanel = filterHtmlForm.add(new ListContainer(ListType.VERTICAL));
        otherFilterPanel.setAppearance(WidgetAppearance.BLOCK);
        StaticData administrativeSexOptions = add(new StaticData(
                DataBuilder.keyValue()
                        .add("", "All")
                        .add("M", "Male")
                        .add("F", "Female")
                        .getData()
        ));
        HtmlSelect administrativeSexFilter = otherFilterPanel.add(
                new HtmlSelect().addDataSource(DataSourceUsage.OPTIONS, administrativeSexOptions,
                        new KeyValueListDataAdapter()));
        HtmlSelect whatDomainReturnedFilter = otherFilterPanel.add(new HtmlSelect());
        whatDomainReturnedFilter.addDataSource(DataSourceUsage.OPTIONS, whatDomainReturnedOptions);
        whatDomainReturnedFilter.addDataSource(DataSourceUsage.OPTIONS, patientDomains,
                new KeyValueListDataAdapter(OrderBy.BY_VALUE));
        StaticData xdsChangesDaysAgoOptions = add(new StaticData(
                DataBuilder.keyValue()
                        .add("", "with or without documents")
                        .add("0", "with documents")
                        .add("1", "new documents since yesterday")
                        .add("7", "new documents in the past week")
                        .add("31", "new documents in the past month")
                        .add("366", "new documents in the past year")
                        .getData()
        ));
        HtmlSelect xdsChangesDaysAgoFilter = otherFilterPanel.add(new HtmlSelect().addDataSource(
                DataSourceUsage.OPTIONS, xdsChangesDaysAgoOptions, new KeyValueListDataAdapter()));

        Panel buttonPanel = filterHtmlForm.add(new Panel(PanelType.DEFAULT));
        buttonPanel.setAppearance(WidgetAppearance.BLOCK);
        HtmlButton resetHtmlButton = buttonPanel.add(new HtmlButton("Reset"));
        resetHtmlButton.onClick(new ClickEventHandler(
                M.Reset(filterHtmlForm)
        ));
        HtmlButton searchHtmlButton = buttonPanel.add(new HtmlButton(ButtonType.PRIMARY, "Search"));
        searchHtmlButton.onClick(new ClickEventHandler(
                M.Refresh(patients)
        ));

        patients.onRefresh(new RefreshEventHandler(
                M.SetDisabled(searchHtmlButton),
                M.SetDisplay(errorPanel, V.False)
        ));

        patients.onResponse(new ResponseEventHandler(
                M.SetEnabled(searchHtmlButton),

                M.Iif(V.Is(V.GetEvent(ResponseEvent.HTTP_STATUS), V.HTTP_UNAUTHORIZED())).True(
                        M.OpenPage(LoginPage.class)
                ),
                M.Iif(V.Is(V.GetEvent(ResponseEvent.HTTP_STATUS), V.HTTP_SERVER_ERROR())).True(
                        M.SetText(errorMessage, V.Call(forViewLib, ForViewLib.PARSE_ERROR, V.GetEvent())),
                        M.SetDisplay(errorPanel, V.True)
                ),
                M.Iif(V.Is(V.GetEvent(ResponseEvent.HTTP_STATUS), V.HTTP_BAD_REQUEST())).True(
                        M.SetText(errorMessage, V.Call(forViewLib, ForViewLib.PARSE_ERROR, V.GetEvent())),
                        M.SetDisplay(errorPanel, V.True)
                )
        ));

        filterHtmlForm.onInput(new InputEventHandler(
                M.RemoveAllQueryParameters(patients),

                M.SetQueryParameter(patients, "surName", V.GetValue(surNameFilter)),
                M.SetQueryParameter(patients, "givenName", V.GetValue(givenNameFilter)),
                M.SetQueryParameter(patients, "patientID", V.GetValue(idFilter)),
                M.If(idFilter).NotEmpty(
                        M.SetQueryParameter(patients, "patientIDAuth", V.GetValue(patientIdAuthFilter))
                ),
                M.SetQueryParameter(patients, "administrativeSex", V.GetValue(administrativeSexFilter)),
                M.If(whatDomainReturnedFilter).NotEmpty(
                        M.SetQueryParameter(patients, "whatDomainReturned", V.GetValue(whatDomainReturnedFilter))
                ),
                M.If(xdsChangesDaysAgoFilter).NotEmpty(
                        M.SetQueryParameter(patients, "xdsChangesDaysAgo", V.GetValue(xdsChangesDaysAgoFilter))
                )
        ));

        HtmlTable htmlTable = add(new HtmlTable());
        OrderedTableHeader orderedTableHeader = htmlTable.add(new OrderedTableHeader(patients));
        orderedTableHeader.setOrder("name", Order.ASCENDING); // default ordering
        HtmlTableBody htmlTableBody = htmlTable.add(new HtmlTableBody());
        htmlTableBody.setEmptyText("No patients found");
        htmlTableBody.addDataSource(patients);
        htmlTableBody.onOpen(new OpenEventHandler(
                M.Log(V.Const("Open"), V.GetEvent(OpenEvent.RECORD)),
                M.OpenPage(PatientDocuments.class, V.GetEvent(OpenEvent.RECORD))
        ));

        onActivate(new ActivateEventHandler(
                M.SetDisabled(medicalDocumentsHtmlLink),
                M.SetDisabled(patientDetailsHtmlLink),
                M.Refresh(patientDomains),
                M.Refresh(userService)
        ));

        onKeyDown(new KeyDownEventHandler(
                M.Iif(KeyDownEvent.IsKeyCodeAndFocus(KeyDownEvent.VK_ENTER, filterHtmlForm)).True(
                        M.Click(searchHtmlButton)
                ),
                M.Iif(V.IsMac()).True(
                        M.Iif(KeyDownEvent.IsMetaAndKeyCode(KeyDownEvent.VK_F)).True(
                                M.PreventDefault(),
                                M.Focus(surNameFilter))
                ).Else(
                        M.Iif(KeyDownEvent.IsControlAndKeyCode(KeyDownEvent.VK_F)).True(
                                M.PreventDefault(),
                                M.Focus(surNameFilter))
                )
        ));
    }
}
