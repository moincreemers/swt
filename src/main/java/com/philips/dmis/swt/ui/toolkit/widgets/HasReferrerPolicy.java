package com.philips.dmis.swt.ui.toolkit.widgets;

public interface HasReferrerPolicy extends HasStaticHTML {
    HasReferrerPolicy getReferrerPolicyImpl();

    ReferrerPolicyType getReferrerPolicy();

    void setReferrerPolicy(ReferrerPolicyType referrerPolicy);
}
