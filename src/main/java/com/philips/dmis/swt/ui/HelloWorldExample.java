package com.philips.dmis.swt.ui;

import com.philips.dmis.swt.ui.toolkit.widgets.*;
import org.springframework.stereotype.Component;

@Component
public class HelloWorldExample extends Page {
    public HelloWorldExample() throws Exception {
    }

    @Override
    protected void build() throws Exception {
        add(HtmlLink.closePage("Back to Examples"));
        add(new HtmlHeading("The Singular Web Toolkit"));

        add(new HtmlParagraph("The Singular Web Toolkit (SWT) is a Web UI library created for Java " +
                "Spring-Boot developers."));

        add(new HtmlParagraph("The goal of SWT is to provide software engineers that have already " +
                "decided to use a Java Spring-Boot stack with a tool to quickly and easily create " +
                "Web UI applications for their services."));

        add(new HtmlParagraph("While choosing a tool to create the UI may sound straightforward, we " +
                "believe that for business applications, there are not that many options available and " +
                "the options that exist do a poor job of addressing important concerns and only focus " +
                "on the technical aspects."));

        add(new HtmlParagraph("For that reason, SWT is designed specifically for " +
                "the creation of business applications."));

        add(new HtmlParagraph("SWT addresses total cost of ownership related aspects such as: " +
                "competence management, maintenance, dependency management, information security and more, " +
                "in a way that makes sense to developers and business leaders."));

        add(new HtmlHeading("SWT Design Requirements", 2));
        ListContainer reqListContainer = add(new ListContainer());

        reqListContainer.add(new HtmlParagraph("Designed primarily for creation and maintenance of Business Applications"));
        reqListContainer.add(new HtmlParagraph("For Java Software Development teams"));
        reqListContainer.add(new HtmlParagraph("Designed for Spring-Boot and must work together with Spring-Boot features"));
        reqListContainer.add(new HtmlParagraph("Low-dependencies, no web framework needed"));
        reqListContainer.add(new HtmlParagraph("Java only tech-stack, must not require any JS related development, test or build " +
                "tools"));
        reqListContainer.add(new HtmlParagraph("Zero-configuration, must work out of the box without any setup"));
        reqListContainer.add(new HtmlParagraph("Support creation of Single Page Applications (SPA)"));
        reqListContainer.add(new HtmlParagraph("No lead time for technology adoption"));
        reqListContainer.add(new HtmlParagraph("Light weight"));
        reqListContainer.add(new HtmlParagraph("Zero HTML, Javascript or CSS coding"));
        reqListContainer.add(new HtmlParagraph("Speed of development over choice"));
        reqListContainer.add(new HtmlParagraph("Speed of development over looks"));
        reqListContainer.add(new HtmlParagraph("Avoid elaborate rules or conventions"));
        reqListContainer.add(new HtmlParagraph("No domain specific languages (DSL)"));
        reqListContainer.add(new HtmlParagraph("Basic, functional but still sophisticated styling (CSS) out of the box"));
        reqListContainer.add(new HtmlParagraph("Support the standard HTML widgets and controls as much as possible"));
        reqListContainer.add(new HtmlParagraph("Allow development of features across teams"));
        reqListContainer.add(new HtmlParagraph("Built-in view controller logic"));
        reqListContainer.add(new HtmlParagraph("Support querying any REST service"));
        reqListContainer.add(new HtmlParagraph("Support updating REST services"));
        reqListContainer.add(new HtmlParagraph("Support various encoding methods"));
        reqListContainer.add(new HtmlParagraph("Supports static data for things like options or menu's etc."));
        reqListContainer.add(new HtmlParagraph("Validates views automatically so composition errors are quickly identified"));
        reqListContainer.add(new HtmlParagraph("Support a highly flexible data binding strategy"));
        reqListContainer.add(new HtmlParagraph("Easy to extend by creating new widgets if needed"));
    }
}
