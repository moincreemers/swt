package com.philips.dmis.swt.ui.forview;

import com.philips.dmis.swt.ui.toolkit.data.*;
import com.philips.dmis.swt.ui.toolkit.dto.DataType;
import com.philips.dmis.swt.ui.toolkit.dto.URLAppearanceType;
import com.philips.dmis.swt.ui.toolkit.dto.URLFormat;
import com.philips.dmis.swt.ui.toolkit.dto.ViewAppearance;
import com.philips.dmis.swt.ui.toolkit.events.*;
import com.philips.dmis.swt.ui.toolkit.js.state.JsStateModule;
import com.philips.dmis.swt.ui.toolkit.statement.dom.D;
import com.philips.dmis.swt.ui.toolkit.statement.mapper.C;
import com.philips.dmis.swt.ui.toolkit.statement.method.M;
import com.philips.dmis.swt.ui.toolkit.statement.method.Operator;
import com.philips.dmis.swt.ui.toolkit.statement.predicate.P;
import com.philips.dmis.swt.ui.toolkit.statement.value.V;
import com.philips.dmis.swt.ui.toolkit.widgets.*;
import org.springframework.stereotype.Component;

@Component
public class WadoClient extends AbstractViewerPage {
    private static final String SELECTED_INSTANCES_VARIABLE = "selectedInstances";


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

        SingleRowPanel toolbarRight = add(new SingleRowPanel(PanelType.TOOLBAR));
        HtmlLabel seriesSelectLabel = toolbarRight.add(new HtmlLabel(icons, "photo_library"));
        HtmlSelect seriesSelect = toolbarRight.add(new HtmlSelect());
        seriesSelectLabel.setFor(seriesSelect);

        Panel navLeft = add(new Panel(PanelType.NAV_LEFT));
        navLeft.setOverflowAndSize(Overflow.FIXED_SIZE, new Size("165px", Size.AUTO));
        SingleRowPanel toolbarLeft = navLeft.add(new SingleRowPanel(PanelType.TOOLBAR));
        HtmlButton instanceThumbnailRefreshButton = toolbarLeft.add(new HtmlButton(icons, "refresh"));
        HtmlButton instanceThumbnailSelectNoneButton = toolbarLeft.add(new HtmlButton(icons, "clear_all"));
        ListContainer instanceThumbnailList = navLeft.add(new ListContainer(ListType.VERTICAL));


        // todo:
        // window level presets query: http://localhost:8080/viewer/services/display/windowLevelPresets/retrieve.json



        /* STUDY DATASOURCE */

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
        studyService.setCacheType(CacheType.LOCAL_ONLY);
        studyService.addDataAdapter(new ImportArrayDataAdapter(".wado.series")
                .add(FieldMapping.map(".studyUID", "studyUID", DataType.STRING))
                .add(FieldMapping.map(".seriesUID", "seriesUID", DataType.STRING))
                .add(FieldMapping.map(".aeTitle", "aeTitle", DataType.STRING))
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
                //.add(FieldMapping.map(".tag_00400001", "aeTitle", DataType.STRING))
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
                .map("homeCommunityUid", C.ValueOf(V.ObjectProperty(V.GetPageArgument(), "homeCommunityId")))
                .map("patientID", C.ValueOf(V.ObjectProperty(V.GetGlobalValue("selectedPatient"), "patientId")))
                .map("patientIDAuth", C.ValueOf(V.ObjectProperty(V.GetGlobalValue("selectedPatient"), "patientIdAuth")))
                .map("seriesDescription", C.ReplaceIfEmpty(V.Const("(no description available)")))

                .map("seriesTitle", C.Format(V.Const("${seriesNumber}. ${seriesDescription}")), "seriesDescription", "seriesNumber")
                .map("thumbnailUrl", C.Call(forViewLib, ForViewLib.CREATE_WADO_THUMB_URL),
                        "modality", "studyUID", "seriesUID", "objectUID", "contentType",
                        "aeTitle", "retrieveLocationUID", "homeCommunityUid", "patientID", "patientIDAuth")
                .map("imageUrl", C.Call(forViewLib, ForViewLib.CREATE_WADO_IMAGE_URL),
                        "modality", "studyUID", "seriesUID", "objectUID", "contentType",
                        "aeTitle", "retrieveLocationUID", "homeCommunityUid", "patientID", "patientIDAuth")
        );
        // create a view for the table
        DtoViewDataAdapter dtoViewDataAdapter = new DtoViewDataAdapter(Series.class);
        dtoViewDataAdapter.applyToAllFields(ViewAppearance.HIDDEN);
        dtoViewDataAdapter.setAppearance("thumbnailUrl", ViewAppearance.DEFAULT);
        dtoViewDataAdapter.setFormat("thumbnailUrl",
                new URLFormat().setAppearance(URLAppearanceType.IMAGE).setImageWidth("128px").setImageBorderRadius("0.5rem"));
        dtoViewDataAdapter.setAppearance("imageUrl", ViewAppearance.DEFAULT);
        dtoViewDataAdapter.setFormat("imageUrl",
                new URLFormat().setAppearance(URLAppearanceType.IMAGE));
        studyService.addDataAdapter(dtoViewDataAdapter);


        /* THUMBNAIL DATASOURCE */
        DataProxy instanceThumbnailDataProxy = add(new DataProxy());
        instanceThumbnailDataProxy.addDataSource(studyService);

        /* SET UP SERIES DROP DOWN */
        seriesSelect.addDataSource(ValueAndItemsDataSourceUsage.ITEMS, studyService,
                new KeyValueListDataAdapter("seriesUID", "seriesTitle"));
        // when the series changes, we want to reset the selected instances variable
        // and then refresh the data source for the instances
        seriesSelect.onChange(new ChangeEventHandler(
                M.SetPageVariable(V.Const(SELECTED_INSTANCES_VARIABLE), null, Operator.CLEAR),
                M.Refresh(instanceThumbnailDataProxy)
        ));


        /* CREATE THE THUMBNAIL TEMPLATES */

        Panel templateThumbnail = navLeft.add(new Panel());
        templateThumbnail.setAppearance(WidgetAppearance.ROUNDED_CORNERS);
        // we use this class name so we are able to 'select' these elements later
        templateThumbnail.addClassName("wadoclient_thumbnail");
        // we need to add this class name to make the panel 'selectable'
        templateThumbnail.addClassName("tk-selectable");
        HtmlLabel templateThumbnailLabel = templateThumbnail.add(new HtmlLabel(icons, "image"));
        templateThumbnailLabel.setAppearance(WidgetAppearance.BLOCK);
        HtmlImageButton templateThumbnailImage = templateThumbnail.add(new HtmlImageButton());
        templateThumbnailImage.setAppearance(WidgetAppearance.ROUNDED_CORNERS);
        templateThumbnail.setVisible(false);
        templateThumbnailImage.onClick(new ClickEventHandler(
                M.SetPageVariable(V.Const(SELECTED_INSTANCES_VARIABLE), V.GetDataKey(), Operator.XOR)
        ));

        Panel templateReport = navLeft.add(new Panel());
        // we use this class name so we are able to 'select' these elements later
        templateReport.addClassName("wadoclient_thumbnail");
        // we need to add this class name to make the panel 'selectable'
        templateReport.addClassName("tk-selectable");
        HtmlLink templateReportLink = templateReport.add(new HtmlLink(icons, "unknown_document", "Report"));
        templateReport.setVisible(false);
        templateReportLink.onClick(new ClickEventHandler(
                M.SetPageVariable(V.Const(SELECTED_INSTANCES_VARIABLE), V.GetDataKey(), Operator.XOR)
        ));


        /* CREATE A DATA CONSUMER WIDGET */
        // this data consumer will receive the instances data when the series has been selected
        // we use a data consumer because from this point on we use procedural code to generate
        // the thumbnails as opposed to just using data binding.

        DataConsumerWidget instanceThumbnailDataConsumer = navLeft.add(new DataConsumerWidget());
        instanceThumbnailDataConsumer.addDataSource(instanceThumbnailDataProxy,
                new FilterDataAdapter().addPredicate("seriesUID", P.IsEqual(V.GetValue(seriesSelect))));
        instanceThumbnailDataConsumer.onUpdate(new UpdateEventHandler(
                M.RemoveAllClones(templateReport),
                M.RemoveAllClones(templateThumbnail),
                M.ForEach(UpdateEvent.Items()).Apply(
                        M.Iif(V.Is(V.ObjectProperty(V.Value(), V.Const("modality")), V.Const("SR")))
                                .True(
                                        M.CloneWidget(templateReport, instanceThumbnailList,
                                                V.ObjectProperty(V.Value(), V.Const("objectUID"))),
                                        M.WithDataKey(V.ObjectProperty(V.Value(), V.Const("objectUID")),
                                                M.SetText(templateReportLink, V.StringConcat(V.Sum(V.Key(), V.Const(1)), V.Const("/"), V.ObjectProperty(UpdateEvent.Items(), "length")))
                                        )
                                )
                                .False(
                                        M.CloneWidget(templateThumbnail, instanceThumbnailList,
                                                V.ObjectProperty(V.Value(), V.Const("objectUID"))),
                                        M.WithDataKey(V.ObjectProperty(V.Value(), V.Const("objectUID")),
                                                // todo:
                                                //C.Download()
                                                M.SetText(templateThumbnailLabel, V.StringConcat(V.Sum(V.Key(), V.Const(1)), V.Const("/"), V.ObjectProperty(UpdateEvent.Items(), "length"))),
                                                M.SetValue(templateThumbnailImage,
                                                        V.ObjectProperty(V.Value(), V.Const("thumbnailUrl")))
                                        )
                                )
                )
        ));

        instanceThumbnailRefreshButton.onClick(new ClickEventHandler(
                M.Refresh(instanceThumbnailDataProxy)
        ));
        instanceThumbnailSelectNoneButton.onClick(new ClickEventHandler(
                M.SetPageVariable(V.Const(SELECTED_INSTANCES_VARIABLE), null, Operator.CLEAR)
        ));


        /* INSTANCES DATA PROXY */


        DataProxy instancesDataProxy = add(new DataProxy());
        instancesDataProxy.addDataSource(studyService);

        HtmlList instanceList = add(new HtmlList(ListType.HORIZONTAL));
        instanceList.addDataSource(ItemsDataSourceUsage.ITEMS, instancesDataProxy,
                new FilterDataAdapter().addPredicate("objectUID", P.InArray(V.GetPageVariable(SELECTED_INSTANCES_VARIABLE))),
                new MapDataAdapter().map("imageUrl",
                        RequestUtil.setNoCache(
                                RequestUtil.setAuthenticationType(C.Download(), AuthenticationType.BEARER_JWT))),
                new KeyValueListDataAdapter("objectUID", "imageUrl")
        );




        /* PAGE VARIABLE CHANGE EVENT HANDLER */
        // when we select or deselect a thumbnail (instance), we set update a page variable
        // this event handler lets us respond to that.
        // 1. We handle highlighting the selected thumbnails
        // 2. We refresh the INSTANCES DATA PROXY
        onPageVariableChange(new PageVariableChangeEventHandler(
                M.Iif(V.Is(PageVariableChangeEvent.Name(), V.Const(SELECTED_INSTANCES_VARIABLE))).True(
                        D.RemoveClassName(D.GetElementsByClassName("wadoclient_thumbnail"), "tk-selected"),
                        M.ForEach(PageVariableChangeEvent.Value()).Apply(
                                D.AddClassName(D.GetElementsByName(V.Value()), "tk-selected")
                        ),
                        M.Refresh(instancesDataProxy)
                )
        ));

        onActivate(new ActivateEventHandler(
                M.DeclarePageVariable(V.Const(SELECTED_INSTANCES_VARIABLE)),
                M.Refresh(userService),

                M.SetText(titleLabel, V.ObjectProperty(V.GetGlobalValue("selectedPatient"), "name")),
                M.SetText(patientIdHtmlLabel, V.StringConcat(V.Const("ID: "), V.ObjectProperty(V.GetGlobalValue("selectedPatient"), "patientId"))),
                M.SetText(patientDateOfBirthHtmlLabel, V.StringConcat(V.ObjectProperty(V.GetGlobalValue("selectedPatient"), "dateOfBirth"), V.Const(" ("), V.ObjectProperty(V.GetGlobalValue("selectedPatient"), "age"), V.Const("yr)"))),
                M.SetText(patientGenderHtmlLabel, V.ObjectProperty(V.GetGlobalValue("selectedPatient"), "gender")),
                M.SetText(patientAddressHtmlLabel, V.ObjectProperty(V.GetGlobalValue("selectedPatient"), "address")),

                M.SetText(studyTitleHtmlLabel, V.ObjectProperty(V.GetPageArgument(), "title")),
                M.SetText(studyCreatedOnHtmlLabel, V.ObjectProperty(V.GetPageArgument(), "createdOn")),
                M.SetText(studyAuthorHtmlLabel, V.ObjectProperty(V.GetPageArgument(), "authors")),

                // retrieveUrl: /viewer/services/document/retrieve?formatCode=1.2.840.10008.5.1.4.1.1.88.59&patientID=570288723&repositoryUniqueId=1.2.3&uniqueId=1.3.6.1.4.1.40371.1.2.15373.27245.6828.41491.49139.34116.57958&patientIDAuth=1.3.6.1.4.1.21367.2005.3.7&home=1.2.3&hash=5e2988b925093f4c9e119c837ae1a7905be3b4dd
                M.SetQueryParameters(studyService,
                        V.GetQueryStringAsObject(V.ObjectProperty(V.GetPageArgument(), "retrieveUrl"))),

                M.Refresh(studyService, JsStateModule.REASON_LOCAL)
        ));
    }
}
