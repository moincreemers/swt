package com.philips.dmis.swt.ui.toolkit.widgets;

import com.philips.dmis.swt.ui.toolkit.js.WidgetType;

import java.util.List;

public class HtmlForm extends ContainerWidget<HtmlForm> implements
        HasAutocomplete, HasAcceptCharSet, HasRel, HasAction, HasEncType,
        HasMethod, HasNoValidate, HasTarget {
    public HtmlForm(WidgetConfigurator widgetConfigurator) throws WidgetConfigurationException {
        super(widgetConfigurator, WidgetType.FORM);
    }

    public HtmlForm(Widget... widgets) throws WidgetConfigurationException {
        super(WidgetType.FORM);
        addAll(widgets);
    }

    @Override
    protected boolean acceptWidget(Widget widget) {
        return true;
    }

    // AUTOCOMPLETE

    private final AutocompleteImpl autocompleteImpl = new AutocompleteImpl(this);

    @Override
    public HasAutocomplete getAutocompleteImpl() {
        return autocompleteImpl;
    }

    @Override
    public AutocompleteType getAutocomplete() {
        return autocompleteImpl.getAutocomplete();
    }

    @Override
    public void setAutocomplete(AutocompleteType accept) {
        autocompleteImpl.setAutocomplete(accept);
    }

    // ACCEPT-CHARSET

    private final AcceptCharSetImpl acceptCharSetImpl = new AcceptCharSetImpl(this);

    @Override
    public HasAcceptCharSet getAcceptCharSetImpl() {
        return acceptCharSetImpl;
    }

    @Override
    public List<String> getAcceptCharSet() {
        return acceptCharSetImpl.getAcceptCharSet();
    }

    @Override
    public void setAcceptCharSet(List<String> acceptCharSet) {
        acceptCharSetImpl.setAcceptCharSet(acceptCharSet);
    }

    @Override
    public void addAcceptCharSet(String acceptCharSet) {
        acceptCharSetImpl.addAcceptCharSet(acceptCharSet);
    }

    // REL

    private final RelImpl relImpl = new RelImpl(this, false, false, true);

    @Override
    public HasRel getRelImpl() {
        return relImpl;
    }

    @Override
    public List<String> getRel() {
        return relImpl.getRel();
    }

    @Override
    public void setRel(List<String> rel) {
        relImpl.setRel(rel);
    }

    @Override
    public void addRel(String rel) {
        relImpl.addRel(rel);
    }

    @Override
    public void addRel(RelType rel) {
        relImpl.addRel(rel);
    }

    // ACTION

    private final ActionImpl actionImpl = new ActionImpl(this);

    @Override
    public HasAction getActionImpl() {
        return actionImpl;
    }

    @Override
    public String getAction() {
        return actionImpl.getAction();
    }

    @Override
    public void setAction(String action) {
        actionImpl.setAction(action);
    }

    // ENCTYPE

    private final EncTypeImpl encTypeImpl = new EncTypeImpl(this);

    @Override
    public HasEncType getEncTypeImpl() {
        return encTypeImpl;
    }

    @Override
    public String getEncType() {
        return encTypeImpl.getEncType();
    }

    @Override
    public void setEncType(String encType) {
        encTypeImpl.setEncType(encType);
    }

    @Override
    public void setEncType(MimeType mimeType) {
        encTypeImpl.setEncType(mimeType);
    }

    // METHOD

    private final MethodImpl methodImpl = new MethodImpl(this);

    @Override
    public HasMethod getMethodImpl() {
        return methodImpl;
    }

    @Override
    public String getMethod() {
        return methodImpl.getMethod();
    }

    @Override
    public void setMethod(String method) {
        methodImpl.setMethod(method);
    }

    @Override
    public void setMethod(FormMethodType formMethod) {
        methodImpl.setMethod(formMethod);
    }

    // NOVALIDATE

    private final NoValidateImpl noValidateImpl = new NoValidateImpl(this);

    @Override
    public HasNoValidate getNoValidateImpl() {
        return noValidateImpl;
    }

    @Override
    public Boolean getNoValidate() {
        return noValidateImpl.getNoValidate();
    }

    @Override
    public void setNoValidate(Boolean noValidate) {
        noValidateImpl.setNoValidate(noValidate);
    }

    // TARGET

    private final TargetImpl targetImpl = new TargetImpl(this);

    @Override
    public HasTarget getTargetImpl() {
        return targetImpl;
    }

    @Override
    public String getTarget() {
        return targetImpl.getTarget();
    }

    @Override
    public void setTarget(String target) {
        targetImpl.setTarget(target);
    }

    @Override
    public void setTarget(TargetType target) {
        targetImpl.setTarget(target);
    }
}