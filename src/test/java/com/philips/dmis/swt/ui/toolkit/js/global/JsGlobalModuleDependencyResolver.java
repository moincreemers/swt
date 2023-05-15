package com.philips.dmis.swt.ui.toolkit.js.global;

import com.philips.dmis.swt.ui.toolkit.TestJsFunction;
import com.philips.dmis.swt.ui.toolkit.js.JsMember;

public class JsGlobalModuleDependencyResolver implements TestJsFunction.DependencyResolver {
    @Override
    public JsMember resolve(Class<? extends JsMember> memberClass) {
        return JsGlobalModule.getMemberInstance(memberClass);
    }

    @Override
    public String getId(Class<? extends JsMember> memberClass) {
        return JsGlobalModule.getQualifiedId(memberClass);
    }
}
