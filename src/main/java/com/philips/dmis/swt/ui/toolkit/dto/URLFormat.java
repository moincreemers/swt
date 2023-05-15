package com.philips.dmis.swt.ui.toolkit.dto;

public class URLFormat extends TextFormat {
    private URLAppearanceType appearance = URLAppearanceType.ANCHOR;
    private String text = "";
    private String target = "_blank";
    private String imageMaxWidth;
    private String imageMaxHeight;
    private String imageWidth;
    private String imageHeight;
    private String imageBorderRadius;

    public URLAppearanceType getAppearance() {
        return appearance;
    }

    public URLFormat setAppearance(URLAppearanceType appearance) {
        this.appearance = appearance;
        return this;
    }

    public String getText() {
        return text;
    }

    public URLFormat setText(String text) {
        this.text = text;
        return this;
    }

    public String getTarget() {
        return target;
    }

    public URLFormat setTarget(String target) {
        this.target = target;
        return this;
    }

    public String getImageMaxWidth() {
        return imageMaxWidth;
    }

    public URLFormat setImageMaxWidth(String imageMaxWidth) {
        this.imageMaxWidth = imageMaxWidth;
        return this;
    }

    public String getImageMaxHeight() {
        return imageMaxHeight;
    }

    public URLFormat setImageMaxHeight(String imageMaxHeight) {
        this.imageMaxHeight = imageMaxHeight;
        return this;
    }

    public String getImageWidth() {
        return imageWidth;
    }

    public URLFormat setImageWidth(String imageWidth) {
        this.imageWidth = imageWidth;
        return this;
    }

    public String getImageHeight() {
        return imageHeight;
    }

    public URLFormat setImageHeight(String imageHeight) {
        this.imageHeight = imageHeight;
        return this;
    }

    public String getImageBorderRadius() {
        return imageBorderRadius;
    }

    public URLFormat setImageBorderRadius(String imageBorderRadius) {
        this.imageBorderRadius = imageBorderRadius;
        return this;
    }

    @Override
    public DataType getFormatType() {
        return DataType.URL;
    }

    @Override
    public boolean isValidDataType(DataType dataType) {
        return dataType == DataType.URL
                || dataType == DataType.STRING;
    }

    @Override
    public void getProperties(CompactMap properties) {
        super.getProperties(properties);
        properties.put("appearance", appearance);
        properties.put("text", text);
        properties.put("target", target);
        properties.put("imageMaxWidth", imageMaxWidth);
        properties.put("imageMaxHeight", imageMaxHeight);
        properties.put("imageWidth", imageWidth);
        properties.put("imageHeight", imageHeight);
        properties.put("imageBorderRadius", imageBorderRadius);
    }
}
