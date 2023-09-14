package com.philips.dmis.swt.ui.demo;

import com.philips.dmis.swt.ui.toolkit.Constants;
import com.philips.dmis.swt.ui.toolkit.widgets.*;
import org.springframework.stereotype.Component;

@Component
public class PagesExamplePage extends Page {
    public PagesExamplePage() throws Exception {
        super(Constants.isDemo(PagesExamplePage.class));
    }

    @Override
    protected void build() throws Exception {
        if (!isDefault()) {
            add(HtmlLink.closePage("Back to Examples"));
        }

        add(new HtmlHeading("Pages"));

        add(new HtmlParagraph("""
                Almost all applications are composed of one or more Views.
                A "View" has a specific role in the traditional
                <a href="https://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93controller">Model-View-Controller</a>
                pattern. How this pattern is implemented exactly is different between platforms and environments but
                generally speaking, a View is the component that is responsible for the interaction with users.
                                
                The toolkit also uses this pattern and the views are called "Pages".
                """));

        add(new HtmlHeading("Creating a Page programmatically", 2));

        add(new HtmlParagraph("""
                A Page can be easily created by creating a Java class and letting it extend the Page class.
                <pre>
                public class MyHomePage extends Page {
                    protected void build() {
                    ...
                    }
                }
                </pre>
                """));

        add(new HtmlHeading("Creating a Page declaratively", 2));

        add(new HtmlParagraph("""
                While you still need to create a class that extends <code>Page</code>, it is possible to use XML files
                to build your page.
                <pre>
                public class MyHomePage extends Page {
                    protected void build() {
                        PageBuilder.getInstance().loadFromXml(this);
                    }
                }
                </pre>
                In this example, the <code>PageBuilder</code> will load the widget structure from an XML file and add it
                to the provided page. In this case, the <code>loadFromXml</code> method assumes the XML file is named
                "MyHomePage.xml". However, there is an overloaded method that allows you to specify which resource file
                to load.
                <br><br>
                It is allowed to use both programmatic and declarative ways to build the page and you are
                allowed to use the PageBuilder multiple times for the same page.
                <br><br>
                When using the PageBuilder, event handlers must still be written programmatically. To do this, first
                define the widget in the page class:
                <pre>
                public class MyHomePage extends Page {
                
                    HtmlButton button1;
                
                    protected void build() {
                        PageBuilder.getInstance().loadFromXml(this);
                    }
                }
                </pre>
                Let's assume the MyHomePage.xml file contains:
                <pre>
                &lt;Page&gt;
                    &lt;HtmlButton id="button1"&gt;Click me&lt;/HtmlButton&gt;
                &lt;/Page&gt;
                </pre>
                In this case, the PageBuilder will automatically set the button1 field.
                This means that, after the PageBuilder has been used to build the page, you can use the field "button1",
                to add an event handler:
                <pre>
                public class MyHomePage extends Page {
                
                    HtmlButton button1;
                
                    protected void build() {
                        PageBuilder.getInstance().loadFromXml(this);
                        
                        button1.onClick(new ClickEventHandler(
                            M.Alert("Hello world!")
                        ));
                    }
                }
                </pre>
                
                """));

        add(new HtmlHeading("The Page Hierarchy", 2));

        add(new HtmlParagraph("""
                The Page class provides a constructor that takes the argument <code>isDefault</code>. When set to
                <code>true</code>, the toolkit will display this Page first or if the View-Controller is unable to
                locate the Page ID provided in the document hash.
                                
                Note that having no default Page results in an error when starting the application. Setting more than
                one Page as the default, results in undefined behaviour.
                """));

        add(new HtmlParagraph("""
                The default Page also serves another function which is to define the root of the Page-Hierarchy. The
                toolkit will validate all Widgets and Statements starting with the default Page. When a link to another
                Page is detected, that new Page is added to the hierarchy. This is a recursive process that ends when no
                more new Pages are found.
                
                This also means that a Page that exists in your code but is not referenced anywhere will not be
                considered part of the application. There simply is no way for the user to call that page.
                """));

        add(new HtmlHeading("Adding Widgets", 2));

        add(new HtmlParagraph("""
                Adding widgets happens in the <code>build</code> method.
                <pre>
                protected void build() {
                    HtmlButton myButton = add(new HtmlButton());
                    
                    HtmlTable table = new HtmlTable();
                    add(table);
                }
                </pre>
                                
                Note that it is recommended to use the syntax shown in the example above. This particular syntax allows
                the toolkit to recognize the names of widgets in your code (in this case: "myButton" and "table") and it
                will provide this name when the toolkit is in DEBUG mode.
                This makes locating errors much easier for example.
                """));



    }
}
