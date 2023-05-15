package com.philips.dmis.swt.ui.toolkit.widgets;

import com.philips.dmis.swt.ui.toolkit.events.ElapsedEventHandler;
import com.philips.dmis.swt.ui.toolkit.events.StartEventHandler;
import com.philips.dmis.swt.ui.toolkit.events.StopEventHandler;
import com.philips.dmis.swt.ui.toolkit.js.WidgetType;

import java.util.Map;

public class Timer extends Widget {
    private final String name;
    private final TimerType timerType;
    private final int intervalMilliSeconds;

    public Timer(String name, TimerType timerType, int intervalMilliSeconds) {
        super(WidgetType.TIMER);
        this.name = name;
        this.timerType = timerType;
        this.intervalMilliSeconds = Math.max(1000, intervalMilliSeconds);
    }

    public Timer onStart(StartEventHandler eventHandler) {
        eventHandlers.add(eventHandler);
        return this;
    }

    public Timer onStop(StopEventHandler eventHandler) {
        eventHandlers.add(eventHandler);
        return this;
    }

    public Timer onElapsed(ElapsedEventHandler eventHandler) {
        eventHandlers.add(eventHandler);
        return this;
    }

    @Override
    public void getHtmlAttributes(Map<String, String> htmlAttributes) {
        super.getHtmlAttributes(htmlAttributes);
        StyleAttribute style = new StyleAttribute(htmlAttributes, "style");
        style.add("display", "none");
    }

    public String getName() {
        return name;
    }

    public TimerType getTimerType() {
        return timerType;
    }

    public int getIntervalMilliSeconds() {
        return intervalMilliSeconds;
    }
}
