package com.philips.dmis.swt.ui.toolkit.widgets;

import com.philips.dmis.swt.ui.toolkit.events.ElapsedEventHandler;
import com.philips.dmis.swt.ui.toolkit.events.StartEventHandler;
import com.philips.dmis.swt.ui.toolkit.events.StopEventHandler;
import com.philips.dmis.swt.ui.toolkit.js.WidgetType;
import com.philips.dmis.swt.ui.toolkit.utils.PageXmlElement;

import java.util.Map;

@PageXmlElement({"name", "timerType", "intervalMilliSeconds"})
public class Timer extends Widget {
    public static final int ONE_SECOND = 1000;
    public static final int ONE_MINUTE = ONE_SECOND * 60;
    public static final int ONE_HOUR = ONE_MINUTE * 60;

    private String name;
    private TimerType timerType;
    private int intervalMilliSeconds;

    public Timer(WidgetConfigurator widgetConfigurator, String name, TimerType timerType, int intervalMilliSeconds) {
        super(widgetConfigurator, WidgetType.TIMER);
        this.name = name;
        this.timerType = timerType;
        this.intervalMilliSeconds = Math.max(1000, intervalMilliSeconds);
    }

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

    public void setName(String name) {
        this.name = name;
    }

    public TimerType getTimerType() {
        return timerType;
    }

    public void setTimerType(TimerType timerType) {
        this.timerType = timerType;
    }

    public int getIntervalMilliSeconds() {
        return intervalMilliSeconds;
    }

    public void setIntervalMilliSeconds(int intervalMilliSeconds) {
        this.intervalMilliSeconds = intervalMilliSeconds;
    }
}
