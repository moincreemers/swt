package com.philips.dmis.swt.ui.toolkit.widgets;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.js.WidgetType;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * Adds an icons-font.
 */
public class IconsWidget extends Widget {
    private String className = "icons";
    private final String fontFamily = "tk_icons_font";
    private String fontFile;
    private String fontCodepointsFile;
    private String fontFormat = "woff2";
    private String fontSize = "larger";
    private final Map<String, String> codepoints = new HashMap<>();
    private int occurrence = 0;

    public IconsWidget(String fontFile) {
        super(WidgetType.STYLE);
        setFontFile(fontFile);
    }

    public IconsWidget(String fontFile, String fontCodepointsFile) {
        super(WidgetType.STYLE);
        setFontFile(fontFile);
        setFontCodepointsFile(fontCodepointsFile);
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getFontFile() {
        return fontFile;
    }

    public void setFontFile(String fontFile) {
        if (fontFile == null || fontFile.isEmpty()) {
            throw new IllegalArgumentException("missing fontFile");
        }
        this.fontFile = fontFile;
    }

    public String getFontCodepointsFile() {
        return fontCodepointsFile;
    }

    public void setFontCodepointsFile(String fontCodepointsFile) {
        this.fontCodepointsFile = fontCodepointsFile;
    }

    public String getFontFormat() {
        return fontFormat;
    }

    public void setFontFormat(String fontFormat) {
        this.fontFormat = fontFormat;
    }

    public String getFontSize() {
        return fontSize;
    }

    public void setFontSize(String fontSize) {
        this.fontSize = fontSize;
    }

    @Override
    public void getHtmlAttributes(Map<String, String> htmlAttributes) {
        super.getHtmlAttributes(htmlAttributes);
        // Note: class is important because we use it to select nodes by class to
        //  determine if the font is already on the page.
        htmlAttributes.put("class", fontFamily);
        htmlAttributes.put("tk_occurrence", Integer.valueOf(occurrence).toString());
    }

    @Override
    public void renderStaticInnerHtml(Toolkit toolkit, StringBuffer html) {
        super.renderStaticInnerHtml(toolkit, html);
        html.append(String.format("@font-face{font-family:\"%s\";font-style:normal;", fontFamily));
        html.append(String.format("src:url(%s) format(\"%s\");}", fontFile, fontFormat));
        html.append(String.format(".%s{font-family:\"tk_icons_font\";font-size:%s;font-weight:inherit;font-style:normal;", className, fontSize));
        html.append("display:inline-block;line-height:1em;text-transform:none;letter-spacing:normal;word-wrap:normal;white-space:nowrap;direction:ltr;vertical-align:text-bottom;margin:0;vertical-align:text-top;}");
    }

    void ensureCodepoints() throws IOException {
        if (codepoints.isEmpty()) {
            InputStream inputStream = getParent().getClass().getClassLoader().getResourceAsStream(fontCodepointsFile);
            if (inputStream == null) {
                throw new IOException(String.format("resource not found \"%s\"", fontCodepointsFile));
            }
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            try (BufferedReader bufferedReader = new BufferedReader(inputStreamReader)) {
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    String[] values = line.split(" ");
                    if (values.length == 2 && values[0].length() != 0 && values[1].length() != 0) {
                        codepoints.put(values[0].toLowerCase(), values[1]);
                    }
                }
            }
        }
    }

    public void validateCodepointExists(String key) throws WidgetConfigurationException {
        if (fontCodepointsFile == null || fontCodepointsFile.isEmpty() || key == null || key.isEmpty()) {
            return;
        }
        try {
            ensureCodepoints();
        } catch (IOException e) {
            throw new WidgetConfigurationException("failed to load icons codepoints file", e);
        }
        if (!codepoints.containsKey(key.toLowerCase())) {
            throw new WidgetConfigurationException(String.format("icon \"%s\" does not exist", key));
        }
    }
}
