package com.philips.dmis.swt.ui.toolkit.widgets;

import com.philips.dmis.swt.ui.toolkit.events.InputEventHandler;
import com.philips.dmis.swt.ui.toolkit.events.KeyDownEventHandler;
import com.philips.dmis.swt.ui.toolkit.events.KeyPressEventHandler;
import com.philips.dmis.swt.ui.toolkit.events.KeyUpEventHandler;

public interface HasKeyInput<T extends Widget> extends HasStaticHTML {
    HasKeyInput<T> getKeyInputImpl();

    T onInput(InputEventHandler eventHandler);

    T onKeyDown(KeyDownEventHandler eventHandler);

    T onKeyPress(KeyPressEventHandler eventHandler);

    T onKeyUp(KeyUpEventHandler eventHandler);
}
