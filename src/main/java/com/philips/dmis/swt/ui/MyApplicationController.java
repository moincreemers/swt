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

    public MyApplicationController(Toolkit toolkit) {
        this.toolkit = toolkit;

        // The application version is how the toolkit can tell if the application has changed
        // it will be embedded into the JS Client as well.
        // when the application changes, the application version should be changed accordingly, this will allow clients
        // to know that the application version has changed and they should reload.
        toolkit.setAppVersion(APP_VERSION);
    }

    @GetMapping("/")
    public void serveHtml(HttpServletRequest request, HttpServletResponse response) throws IOException {
        toolkit.render(request, response);
    }
}
