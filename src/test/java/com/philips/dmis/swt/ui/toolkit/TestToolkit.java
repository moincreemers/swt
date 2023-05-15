package com.philips.dmis.swt.ui.toolkit;

import com.philips.dmis.swt.ui.toolkit.widgets.HasCode;
import com.philips.dmis.swt.ui.toolkit.widgets.Page;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

public class TestToolkit implements Toolkit {
    @Override
    public void setAppVersion(String appVersion) {
    }

    @Override
    public Page getDefaultPage() {
        return null;
    }

    @Override
    public List<Page> getPages() {
        return null;
    }

    @Override
    public Page getPage(Class<? extends Page> pageClass) {
        return null;
    }

    @Override
    public String registerConstant(String string) {
        return null;
    }

    @Override
    public void registerCode(HasCode code) {
    }

    @Override
    public void render(HttpServletRequest request, HttpServletResponse response) throws IOException {
    }
}
