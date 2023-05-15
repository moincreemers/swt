package com.philips.dmis.swt.ui.toolkit;

import com.philips.dmis.swt.ui.toolkit.js.controller.JsPageControllerModule;
import com.philips.dmis.swt.ui.toolkit.js.global.JsGlobalModule;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import java.util.Arrays;

public class ToolkitControllerTest {
    MockHttpServletRequest request = new MockHttpServletRequest();
    MockHttpServletResponse response = new MockHttpServletResponse();

    @Test
    public void testRenderJsGlobalModule() throws Exception {
        ToolkitController toolkitController = new ToolkitController(
                Arrays.asList(new JsGlobalModule()),
                Arrays.asList(new EmptyPage()));
        toolkitController.render(request, response);
        System.out.println(response.getContentAsString());
    }

    @Test
    public void testRenderJsPageControllerModule() throws Exception {
        ToolkitController toolkitController = new ToolkitController(
                Arrays.asList(new JsPageControllerModule()),
                Arrays.asList(new EmptyPage()));
        toolkitController.render(request, response);
        System.out.println(response.getContentAsString());
    }

    @Test
    public void testRenderSingleChildPage() throws Exception {
        ToolkitController toolkitController = new ToolkitController(
                Arrays.asList(new JsPageControllerModule()),
                Arrays.asList(new SingleChildPage()));
        toolkitController.render(request, response);
        System.out.println(response.getContentAsString());
    }
}
