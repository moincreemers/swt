package com.philips.dmis.swt.ui.demo;

import com.philips.dmis.swt.ui.toolkit.Constants;
import com.philips.dmis.swt.ui.toolkit.widgets.*;
import org.springframework.stereotype.Component;

@Component
public class HelloWorldExample extends Page {
    public HelloWorldExample() throws Exception {
        super(Constants.isDemo(HelloWorldExample.class));
    }

    @Override
    protected void build() throws Exception {
        if (!isDefault()) {
            add(HtmlLink.closePage("Back to Examples"));
        }
        add(new HtmlHeading("The Singular Web Toolkit"));

        Grid grid = add(new Grid(4, 0));

        grid.add(new Panel(PanelType.INFO, new HtmlParagraph("""
                <h3>For</h3>The Singular Web Toolkit is a Web UI library created for Java Spring-Boot developers
                """)));

        grid.add(new Panel(PanelType.INFO, new HtmlParagraph("""
                <h3>Why</h3>Quickly and easily create Web UI applications without writing any HTML, CSS or Javascript code
                """)));

        grid.add(new Panel(PanelType.INFO, new HtmlParagraph("""
                <h3>What</h3>Specifically aimed at creation and maintenance of business applications
                """)));

        grid.add(new Panel(PanelType.INFO, new HtmlParagraph("""
                <h3>Unique</h3>SWT addresses total cost of ownership related aspects such as:
                competence management, maintenance, dependency management, information security and more
                """)));

        add(new HtmlHeading("SWT Design Requirements", 2));

        Grid gridReq = add(new Grid(4, 0));

        gridReq.add(new Panel(PanelType.BANNER, new HtmlParagraph("""
                Produces Single Page Applications (SPA) that do not require interaction with a backend""")));
        gridReq.add(new Panel(PanelType.BANNER, new HtmlParagraph("""
                Low-dependency, no web framework is needed""")));
        gridReq.add(new Panel(PanelType.BANNER, new HtmlParagraph("""
                Integrates like a component and does not impact any other technology such as data storage""")));
        gridReq.add(new Panel(PanelType.BANNER, new HtmlParagraph("""
                Supports a Java-only tech-stack, does not require any JS related development, test or build tools""")));
        gridReq.add(new Panel(PanelType.BANNER, new HtmlParagraph("""
                Zero HTML or CSS coding""")));
        gridReq.add(new Panel(PanelType.BANNER, new HtmlParagraph("""
                Zero-configuration""")));
        gridReq.add(new Panel(PanelType.BANNER, new HtmlParagraph("""
                Prioritize speed of development over choice, appearance and cutting edge""")));
        gridReq.add(new Panel(PanelType.BANNER, new HtmlParagraph("""
                Avoid elaborate rules or conventions as much as possible""")));
        gridReq.add(new Panel(PanelType.BANNER, new HtmlParagraph("""
                No domain specific language (DSL)""")));
        gridReq.add(new Panel(PanelType.BANNER, new HtmlParagraph("""
                Basic, functional and sophisticated look and feel out of the box""")));
        gridReq.add(new Panel(PanelType.BANNER, new HtmlParagraph("""
                Support the standard HTML widgets and controls as much as possible""")));
        gridReq.add(new Panel(PanelType.BANNER, new HtmlParagraph("""
                Allow development of features across teams""")));
        gridReq.add(new Panel(PanelType.BANNER, new HtmlParagraph("""
                Built-in view controller logic""")));
        gridReq.add(new Panel(PanelType.BANNER, new HtmlParagraph("""
                Support querying any REST service""")));
        gridReq.add(new Panel(PanelType.BANNER, new HtmlParagraph("""
                Support updating REST services""")));
        gridReq.add(new Panel(PanelType.BANNER, new HtmlParagraph("""
                Support various encoding methods""")));
        gridReq.add(new Panel(PanelType.BANNER, new HtmlParagraph("""
                Support static data for things like options or a menu""")));
        gridReq.add(new Panel(PanelType.BANNER, new HtmlParagraph("""
                Validates views automatically so composition errors are quickly identified""")));
        gridReq.add(new Panel(PanelType.BANNER, new HtmlParagraph("""
                Support a flexible and powerful data binding strategy""")));
        gridReq.add(new Panel(PanelType.BANNER, new HtmlParagraph("""
                Allow for extension""")));
    }
}
