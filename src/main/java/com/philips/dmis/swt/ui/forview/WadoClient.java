package com.philips.dmis.swt.ui.forview;

import com.philips.dmis.swt.ui.toolkit.data.*;
import com.philips.dmis.swt.ui.toolkit.dto.DataType;
import com.philips.dmis.swt.ui.toolkit.dto.URLAppearanceType;
import com.philips.dmis.swt.ui.toolkit.dto.URLFormat;
import com.philips.dmis.swt.ui.toolkit.dto.ViewAppearance;
import com.philips.dmis.swt.ui.toolkit.events.ActivateEventHandler;
import com.philips.dmis.swt.ui.toolkit.events.ChangeEventHandler;
import com.philips.dmis.swt.ui.toolkit.events.ClickEventHandler;
import com.philips.dmis.swt.ui.toolkit.js.pages.JsPagesModule;
import com.philips.dmis.swt.ui.toolkit.statement.mapper.C;
import com.philips.dmis.swt.ui.toolkit.statement.method.M;
import com.philips.dmis.swt.ui.toolkit.statement.predicate.P;
import com.philips.dmis.swt.ui.toolkit.statement.value.V;
import com.philips.dmis.swt.ui.toolkit.widgets.*;
import org.springframework.stereotype.Component;

@Component
public class WadoClient extends AbstractViewerPage {
    public WadoClient() throws Exception {
    }

    static class Series {
        public String patientID;
        public String patientIDAuth;

        public String studyUID;
        public String seriesUID;
        public String retrieveLocationUID;

        public String seriesDescription;
        public String seriesDate;
        public String seriesTime;
        public String modality;
        public String physicianName;
        public String bodyPartExamined;
        public String protocolName;
        public String patientPosition;
        public String seriesNumber;
        public String requestedProcedureDescription;
        public String aeTitle;
        public String description;
        public String performedProcedureStepID;
        public String performedProcedureStepDescription;
        public String requestedProcedureID;

        public String classUID;
        public String objectUID;
        public String contentType;

        public String seriesTitle;
        public String thumbnailUrl;
        public String imageUrl;
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

        ListContainer studyInfoPanel = pageHeader.add(new ListContainer(ListType.HORIZONTAL));
        HtmlLabel studyTitleHtmlLabel = studyInfoPanel.add(new HtmlLabel(""));
        studyTitleHtmlLabel.setIconsWidget(icons);
        studyTitleHtmlLabel.setIcon("image");
        HtmlLabel studyCreatedOnHtmlLabel = studyInfoPanel.add(new HtmlLabel(""));
        HtmlLabel studyAuthorHtmlLabel = studyInfoPanel.add(new HtmlLabel(""));


        // todo:
        // window level presets query: http://localhost:8080/viewer/services/display/windowLevelPresets/retrieve.json


        // retrieve study query: https://localhost/viewer/services/document/retrieve
        //  ?formatCode=1.2.840.10008.5.1.4.1.1.88.59
        //  &patientID=570288723
        //  &patientIDAuth=1.3.6.1.4.1.21367.2005.3.7
        //  &repositoryUniqueId=1.2.3
        //  &uniqueId=1.3.6.1.4.1.40371.1.2.30653.21523.34932.41296.48043.35790.48041
        //  &home=1.2.3
        //  &hash=ddfdf7c8863f74a90d65afff21fe8c12073dd39a
        QueryService studyService = add(new QueryService(
                "http://localhost:8080/viewer/services/document/retrieve",
                false, false));
        studyService.addDataAdapter(new ImportArrayDataAdapter(".wado.series")
                        .add(FieldMapping.map(".studyUID", "studyUID", DataType.STRING))
                        .add(FieldMapping.map(".seriesUID", "seriesUID", DataType.STRING))
//                .add(FieldMapping.map(".aeTitle", "aeTitle", DataType.STRING))
                        .add(FieldMapping.map(".retrieveLocationUID", "retrieveLocationUID", DataType.STRING))

                        //        tag_0008103E: "Series Description"
                        //        tag_00080021: "Series Date"
                        //        tag_00080031: "Series Time"
                        //        tag_00080060: "Modality"
                        //        tag_00081050: "Performing Physician's Name"
                        //        tag_00180015: "Body Part Examined"
                        //        tag_00181030: "Protocol Name"
                        //        tag_00185100: "Patient Position"
                        //        tag_00200011: "Series Number"
                        //        tag_00321060: "Requested Procedure Description"
                        //        tag_00400001: "AE Title"
                        //        tag_00400007: "Description"
                        //        tag_00400253: "Performed Procedure Step ID"
                        //        tag_00400254: "Performed Procedure Step Description"
                        //        tag_00401001: "Requested Procedure ID"
                        .add(FieldMapping.map(".tag_0008103E", "seriesDescription", DataType.STRING))
                        .add(FieldMapping.map(".tag_00080021", "seriesDate", DataType.STRING))
                        .add(FieldMapping.map(".tag_00080031", "seriesTime", DataType.STRING))
                        .add(FieldMapping.map(".tag_00080060", "modality", DataType.STRING))
                        .add(FieldMapping.map(".tag_00081050", "physicianName", DataType.STRING))
                        .add(FieldMapping.map(".tag_00180015", "bodyPartExamined", DataType.STRING))
                        .add(FieldMapping.map(".tag_00181030", "protocolName", DataType.STRING))
                        .add(FieldMapping.map(".tag_00185100", "patientPosition", DataType.STRING))
                        .add(FieldMapping.map(".tag_00200011", "seriesNumber", DataType.STRING))
                        .add(FieldMapping.map(".tag_00321060", "requestedProcedureDescription", DataType.STRING))
                        .add(FieldMapping.map(".tag_00400001", "aeTitle", DataType.STRING))
                        .add(FieldMapping.map(".tag_00400007", "description", DataType.STRING))
                        .add(FieldMapping.map(".tag_00400253", "performedProcedureStepID", DataType.STRING))
                        .add(FieldMapping.map(".tag_00400254", "performedProcedureStepDescription", DataType.STRING))
                        .add(FieldMapping.map(".tag_00401001", "requestedProcedureID", DataType.STRING))

                        .add(FieldMapping.map(".instances", "instances", DataType.ARRAY))
        );
        // flatten "instances"
        studyService.addDataAdapter(new FlattenArrayDataAdapter("instances"));

        // modify the data set
        studyService.addDataAdapter(new MapDataAdapter()
                .map("homeCommunityUid", C.ValueOf(V.ObjectMember(V.GetPageArgument(), "homeCommunityId")))
                .map("patientID", C.ValueOf(V.ObjectMember(V.GetGlobalValue("selectedPatient"), "patientId")))
                .map("patientIDAuth", C.ValueOf(V.ObjectMember(V.GetGlobalValue("selectedPatient"), "patientIdAuth")))
                .map("seriesDescription", C.ReplaceIfEmpty(V.Const("(no description available)")))

                .map("seriesTitle", C.Format(V.Const("${seriesNumber}. ${seriesDescription}")), "seriesDescription", "seriesNumber")
                .map("thumbnailUrl", C.Call(forViewLib, ForViewLib.CREATE_WADO_THUMB_URL),
                        "studyUID", "seriesUID", "objectUID", "contentType",
                        "retrieveLocationUID", "homeCommunityUid", "patientID", "patientIDAuth")
                .map("imageUrl", C.Call(forViewLib, ForViewLib.CREATE_WADO_IMAGE_URL),
                        "studyUID", "seriesUID", "objectUID", "contentType",
                        "retrieveLocationUID", "homeCommunityUid", "patientID", "patientIDAuth")
        );
        // create a view for the table
        DtoViewDataAdapter dtoViewDataAdapter = new DtoViewDataAdapter(Series.class);
        dtoViewDataAdapter.applyToAllFields(ViewAppearance.HIDDEN);

        dtoViewDataAdapter.setAppearance("thumbnailUrl", ViewAppearance.DEFAULT);
        dtoViewDataAdapter.setFormat("thumbnailUrl",
                new URLFormat().setAppearance(URLAppearanceType.IMAGE).setImageWidth("128px").setImageHeight("128px"));

        dtoViewDataAdapter.setAppearance("imageUrl", ViewAppearance.DEFAULT);
        dtoViewDataAdapter.setFormat("imageUrl",
                new URLFormat().setAppearance(URLAppearanceType.IMAGE));

        studyService.addDataAdapter(dtoViewDataAdapter);

        Panel toolbarRight = add(new Panel(PanelType.TOOLBAR));
        HtmlLabel seriesSelectLabel = toolbarRight.add(new HtmlLabel("Series:"));
        HtmlSelect seriesSelect = toolbarRight.add(new HtmlSelect());
        seriesSelectLabel.setFor(seriesSelect);
        seriesSelect.addDataSource(studyService,
                new KeyValueListDataAdapter("seriesUID", "seriesTitle"));
        HtmlButton layoutOneButton = toolbarRight.add(new HtmlButton(icons, "crop_square", "One"));
        HtmlButton layoutGridButton = toolbarRight.add(new HtmlButton(icons, "grid_view", "Grid"));

        DataProxy instanceThumbnailDataProxy = add(new DataProxy());
        instanceThumbnailDataProxy.addDataSource(studyService,
                new FilterDataAdapter().addPredicate("seriesUID", P.IsEqual(V.GetValue(seriesSelect)))
        );
        seriesSelect.onChange(new ChangeEventHandler(
                M.Refresh(instanceThumbnailDataProxy)
        ));


        Panel navLeft = add(new Panel(PanelType.NAV_LEFT));
        navLeft.setOverflowAndSize(Overflow.FIXED_SIZE, new Size("160px", Size.AUTO));
        Panel toolbarLeft = navLeft.add(new Panel(PanelType.TOOLBAR));
        HtmlButton instancesButton = toolbarLeft.add(new HtmlButton(icons, "photo_library", "Refresh"));
        MultipleChoice instanceThumbnailList = navLeft.add(new MultipleChoice());
        instanceThumbnailList.addDataSource(instanceThumbnailDataProxy,
                new MapDataAdapter().map("thumbnailUrl",
                        HttpHeaderUtil.setNoCache(HttpHeaderUtil.setAuthorizationHeader(C.Download()))),
                new KeyValueListDataAdapter("objectUID", "thumbnailUrl")
        );

        instancesButton.onClick(new ClickEventHandler(
                M.Refresh(instanceThumbnailDataProxy)
        ));

        DataProxy instancesDataProxy = add(new DataProxy());
        instancesDataProxy.addDataSource(studyService,
                new FilterDataAdapter().addPredicate("objectUID", P.InArray(V.GetValue(instanceThumbnailList)))
        );

        instanceThumbnailList.onChange(new ChangeEventHandler(
                M.Log(V.GetValue(instanceThumbnailList)),
                M.Refresh(instancesDataProxy)
        ));

        HtmlList instanceList = add(new HtmlList(ListType.HORIZONTAL));
        instanceList.addDataSource(instancesDataProxy,
                new MapDataAdapter().map("imageUrl",
                        HttpHeaderUtil.setNoCache(HttpHeaderUtil.setAuthorizationHeader(C.Download()))),
                new KeyValueListDataAdapter("objectUID", "imageUrl")
        );

        onActivate(new ActivateEventHandler(
                M.SetText(titleLabel, V.ObjectMember(V.GetGlobalValue("selectedPatient"), "name")),
                M.SetText(patientIdHtmlLabel, V.StringConcat(V.Const("ID: "), V.ObjectMember(V.GetGlobalValue("selectedPatient"), "patientId"))),
                M.SetText(patientDateOfBirthHtmlLabel, V.StringConcat(V.ObjectMember(V.GetGlobalValue("selectedPatient"), "dateOfBirth"), V.Const(" ("), V.ObjectMember(V.GetGlobalValue("selectedPatient"), "age"), V.Const("yr)"))),
                M.SetText(patientGenderHtmlLabel, V.ObjectMember(V.GetGlobalValue("selectedPatient"), "gender")),
                M.SetText(patientAddressHtmlLabel, V.ObjectMember(V.GetGlobalValue("selectedPatient"), "address")),

                M.SetText(studyTitleHtmlLabel, V.ObjectMember(V.GetPageArgument(), "title")),
                M.SetText(studyCreatedOnHtmlLabel, V.ObjectMember(V.GetPageArgument(), "createdOn")),
                M.SetText(studyAuthorHtmlLabel, V.ObjectMember(V.GetPageArgument(), "authors")),

                // retrieveUrl: /viewer/services/document/retrieve?formatCode=1.2.840.10008.5.1.4.1.1.88.59&patientID=570288723&repositoryUniqueId=1.2.3&uniqueId=1.3.6.1.4.1.40371.1.2.15373.27245.6828.41491.49139.34116.57958&patientIDAuth=1.3.6.1.4.1.21367.2005.3.7&home=1.2.3&hash=5e2988b925093f4c9e119c837ae1a7905be3b4dd
                M.SetQueryParameters(studyService,
                        V.GetQueryStringAsObject(V.ObjectMember(V.GetPageArgument(), "retrieveUrl"))),

                M.Refresh(studyService, JsPagesModule.REASON_LOCAL),

                M.Refresh(userService)
        ));
    }
}
