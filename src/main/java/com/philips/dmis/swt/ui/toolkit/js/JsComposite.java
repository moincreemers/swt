package com.philips.dmis.swt.ui.toolkit.js;

/**
 * NOT USED AT THIS TIME
 *
 * Defines a JS composite which simply outputs JS
 * but is not actually declared as a member of the module.
 * <p>
 * This is useful when generating static content such as pages.
 */
public interface JsComposite extends JsMember {
    /**
     * A name used to distinguish between code blocks.
     *
     * @return
     */
    String getName();
}
