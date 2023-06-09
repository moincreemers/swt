package com.philips.dmis.swt.ui.demo;

import com.philips.dmis.swt.ui.toolkit.Constants;
import com.philips.dmis.swt.ui.toolkit.statement.method.M;
import com.philips.dmis.swt.ui.toolkit.statement.value.V;
import com.philips.dmis.swt.ui.toolkit.events.ClickEventHandler;
import com.philips.dmis.swt.ui.toolkit.events.ElapsedEventHandler;
import com.philips.dmis.swt.ui.toolkit.widgets.*;
import org.springframework.stereotype.Component;

@Component
public class TimerExample extends Page {
    public TimerExample() throws Exception {
        super(Constants.isDemo(TimerExample.class));
    }

    @Override
    protected void build() throws Exception {
        if(!isDefault()) {
            add(HtmlLink.closePage("Back to Examples"));
        }
        add(new HtmlHeading("Timers"));

        add(new HtmlParagraph("Timers can be used to let your application execute an action " +
                "after a specified time which is called an alarm. To create an alarm that elapses after 5 seconds:"));

        add(new HtmlPreformatted("TimerWidget timer = new TimerWidget(\"alarm\", TimerType.ALARM, 5000)"));

        HtmlButton setAlarm = add(new HtmlButton("Set Alarm"));
        Panel alarmPanel = add(new Panel(PanelType.ERROR, new HtmlParagraph("Wake up!")));
        alarmPanel.setVisible(false);
        Timer alarm = add(new Timer("wake-up", TimerType.ALARM, 5000));
        alarm.onElapsed(new ElapsedEventHandler(
                M.SetVisible(alarmPanel, V.True)
        ));
        setAlarm.onClick(new ClickEventHandler(
                M.Start(alarm),
                M.SetVisible(alarmPanel, V.False)
        ));

        add(new HtmlParagraph("Timers can also be used to let your application execute an action " +
                "repeatedly which is called a repeater. To create a repeat event every second:"));

        add(new HtmlPreformatted("TimerWidget timer = new TimerWidget(\"repeater\", TimerType.REPEAT, 1000)"));

        HtmlButton startRepeater = add(new HtmlButton("Start Repeater"));
        HtmlButton stopRepeater = add(new HtmlButton("Stop Repeater"));
        Timer repeater = add(new Timer("drink-coffee", TimerType.REPEAT, 1000));
        Panel repeaterPanel = add(new Panel(PanelType.INFO, new HtmlParagraph("Drink coffee now!")));
        repeaterPanel.setVisible(false);
        startRepeater.onClick(new ClickEventHandler(
                M.Start(repeater)
        ));
        stopRepeater.onClick(new ClickEventHandler(
                M.Stop(repeater)
        ));
        repeater.onElapsed(new ElapsedEventHandler(
                M.Iif(V.GetDisplay(repeaterPanel))
                .True(M.SetVisible(repeaterPanel, V.False))
                .False(M.SetVisible(repeaterPanel, V.True))
        ));
    }
}
