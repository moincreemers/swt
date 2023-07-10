package com.philips.dmis.swt.ui;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;

@Controller
public class MyApplicationController {
    static final String APP_VERSION = "1.0";
    final Toolkit toolkit;

    public MyApplicationController(Toolkit toolkit) throws IOException {
        this.toolkit = toolkit;
        toolkit.setAppVersion(APP_VERSION);
        toolkit.addLanguageResourceFile(
                MyApplicationController.class.getResourceAsStream("/ui-languages.properties"));
    }

    @GetMapping("/")
    public void serveHtml(HttpServletRequest request, HttpServletResponse response) throws IOException {
        toolkit.render(request, response);
    }
}
