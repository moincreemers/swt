package com.philips.dmis.swt.ui.toolkit;

import com.philips.dmis.swt.ui.toolkit.widgets.HasCode;
import com.philips.dmis.swt.ui.toolkit.widgets.Page;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public interface Toolkit {
    /**
     * The application version is how the toolkit can tell if the application has changed
     * it will be embedded into the JS Client as well. When the application changes,
     * the application version should be changed accordingly, this will allow clients
     * to know that the application version has changed and they should reload.
     *
     * @param appVersion
     */
    void setAppVersion(String appVersion);

    Page getDefaultPage();

    List<Page> getPages();

    Page getPage(Class<? extends Page> pageClass);

    String registerConstant(String string);

    void addLanguageResourceFile(InputStream inputStream) throws IOException;

    void registerCode(HasCode code);

    void render(HttpServletRequest request, HttpServletResponse response) throws IOException;
}
