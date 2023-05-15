package com.philips.dmis.swt.ui.toolkit.js.controller;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.js.*;
import com.philips.dmis.swt.ui.toolkit.widgets.JsRenderException;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class JsPageControllerModule implements JsModule {
    public static final String ID = "Controller";

    private static final List<JsMember> MEMBERS = Arrays.asList(
            // Constants
            new DefaultPageIdConst(),
            new PagesConst(),
            // Functions
            new InitFunction(),
            new DisplayFunction(),
            new GetCurrentPageFunction(),
            new UpdateFunction(),
            new RegisterPage(),
            new PageExistsFunction(),
            new GetDefaultPageFunction()
    );

    private static final Map<Class<? extends JsMember>, Pair<String, JsMember>> INDEX =
            JsModule.createIndex(MEMBERS);

    public static JsMember getMemberInstance(Class<? extends JsMember> memberClass) {
        return JsModule.getMemberInstance(memberClass, INDEX);
    }

    public static String getId(Class<? extends JsMember> memberClass) throws JsRenderException {
        return JsModule.getId(memberClass, INDEX);
    }

    public static String getQualifiedId(Class<? extends JsMember> memberClass) {
        return JsModule.getQualifiedId(ID, memberClass, INDEX);
    }

    @Override
    public void renderJs(Toolkit toolkit, JsWriter js) {
        JsModule.renderModule(toolkit, js, ID, INDEX.values());
    }

    @Override
    public String getInitFunctionId() {
        return getQualifiedId(InitFunction.class);
    }
}
