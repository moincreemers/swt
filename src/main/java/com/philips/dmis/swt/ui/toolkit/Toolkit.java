package com.philips.dmis.swt.ui.toolkit;

import com.philips.dmis.swt.ui.toolkit.widgets.HasCode;
import com.philips.dmis.swt.ui.toolkit.widgets.Page;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

public interface Toolkit {
    void setAppVersion(String appVersion);

    Page getDefaultPage();

    List<Page> getPages();

    Page getPage(Class<? extends Page> pageClass);

    String registerConstant(String string);

    void registerCode(HasCode code);

    void render(HttpServletRequest request, HttpServletResponse response) throws IOException;
}
