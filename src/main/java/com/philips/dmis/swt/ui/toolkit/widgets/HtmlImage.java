package com.philips.dmis.swt.ui.toolkit.widgets;

import com.philips.dmis.swt.ui.toolkit.data.DataAdapter;
import com.philips.dmis.swt.ui.toolkit.js.WidgetType;
import com.philips.dmis.swt.ui.toolkit.utils.PageXmlElement;

import java.util.Map;

@PageXmlElement({"imageSize", "imageType"})
public class HtmlImage extends DataBoundWidget<HtmlImage, ValueDataSourceUsage> implements HasSrc<HtmlImage> {
    private ImageSize imageSize = ImageSize.DEFAULT;
    private ImageType imageType = ImageType.DEFAULT;

    public HtmlImage(WidgetConfigurator widgetConfigurator) {
        super(widgetConfigurator, WidgetType.IMAGE);
    }

    public HtmlImage() {
        this(ImageType.DEFAULT, DEFAULT_VALUE_SRC);
    }

    public HtmlImage(ImageType imageType) {
        this(imageType, DEFAULT_VALUE_SRC);
    }

    public HtmlImage(String src) {
        this(ImageType.DEFAULT, src);
    }

    public HtmlImage(ImageType imageType, String src) {
        super(WidgetType.IMAGE);
        setImageType(imageType);
        setSrc(src);
    }

    public HtmlImage addDataSource(DataSourceSupplier dataSourceSupplier, DataAdapter... dataAdapters) throws WidgetConfigurationException {
        super.addDataSource(ValueDataSourceUsage.VALUE, dataSourceSupplier, dataAdapters);
        return this;
    }

    public ImageType getImageType() {
        return imageType;
    }

    public void setImageType(ImageType imageType) {
        if (imageType == null) {
            return;
        }
        if (this.imageType != ImageType.DEFAULT) {
            removeClassName(this.imageType.className);
        }
        this.imageType = imageType;
        addClassName(imageType.className);
    }

    public ImageSize getImageSize() {
        return imageSize;
    }

    public HtmlImage setImageSize(ImageSize imageSize) {
        if (imageSize == null) {
            return this;
        }
        if (!this.imageSize.className.isEmpty()) {
            removeClassName(this.imageSize.className);
        }
        this.imageSize = imageSize;
        addClassName(imageSize.className);
        return this;
    }

    @Override
    public void getHtmlAttributes(Map<String, String> htmlAttributes) {
        super.getHtmlAttributes(htmlAttributes);
        if (!imageSize.width.isEmpty()) {
            StyleAttribute style = new StyleAttribute(htmlAttributes, "style");
            style.add("width", imageSize.width);
        }
        if (!imageSize.height.isEmpty()) {
            StyleAttribute style = new StyleAttribute(htmlAttributes, "style");
            style.add("height", imageSize.height);
        }
    }

    // SRC

    private final SrcImpl<HtmlImage> srcImpl = new SrcImpl<>(this);

    @Override
    public HasSrc<HtmlImage> getSrcImpl() {
        return srcImpl;
    }

    @Override
    public String getSrc() {
        return srcImpl.getSrc();
    }

    @Override
    public HtmlImage setSrc(String src) {
        return srcImpl.setSrc(src);
    }
}
