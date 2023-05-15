package com.philips.dmis.swt.ui.toolkit.js;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.widgets.JsRenderException;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;

public interface JsMember extends HasJS {
    /**
     * Returns true if the widget type has this member.
     * @param widgetType
     * @return
     */
    boolean isMemberOf(Widget widget, WidgetType widgetType);

    /**
     * Export the member.
     *
     * @return
     */
    boolean isPublic();

    /**
     * The name by which the member is exported. The method should return the specified id
     * unless it is a symbol.
     *
     * @param id
     * @return
     */
    String getPublicName(String id);

    /**
     * The return type of the member.
     *
     * @return
     */
    JsType getType();

    /**
     * Render the member javascript code.
     * <ul>
     * <li>If the member is a function, the expected syntax is an anonymous function, i.e.: <code>()=>{}</code>.
     * Parameters may be added to the parentheses. In that case, make sure to report each parameter in the
     * <code>getParameters</code> method.</li>
     *
     * <li>If the member is a constant, the expected syntax is: <code>value</code>.</li>
     *
     * <li>If the member is a variable, the expected syntax is: <code>value</code> or <code>null</code></li>
     * </ul>
     *
     * @param toolkit
     * @param js
     * @throws JsRenderException
     */
    void renderJs(Toolkit toolkit, JsWriter js) throws JsRenderException;
}
