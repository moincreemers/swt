package com.philips.dmis.swt.ui.toolkit;

import com.philips.dmis.swt.ui.toolkit.events.EventHandler;
import com.philips.dmis.swt.ui.toolkit.statement.Statement;
import com.philips.dmis.swt.ui.toolkit.statement.method.OpenPageStatement;
import com.philips.dmis.swt.ui.toolkit.statement.value.GetPageValueStatement;
import com.philips.dmis.swt.ui.toolkit.widgets.ContainerWidget;
import com.philips.dmis.swt.ui.toolkit.widgets.Page;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;
import com.philips.dmis.swt.ui.toolkit.widgets.WidgetConfigurationException;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class DependencyFinder {
    private static final Logger LOG = Logger.getLogger(DependencyFinder.class.getName());
    final Class<? extends Page> rootPage;
    final List<Page> pages;

    public DependencyFinder(Class<? extends Page> rootPage, List<Page> pages) {
        this.rootPage = rootPage;
        this.pages = pages;
        LOG.info("Initialized with pages: " + pages.stream()
                .map(page -> page.getClass().getSimpleName() + " (" + page.getId() + ")")
                .collect(Collectors.joining(", ")));
    }

    public List<Page> find() throws WidgetConfigurationException {
        List<Page> dependencies = new ArrayList<>();
        scanWidget(getPageInstance(rootPage), dependencies);
        return dependencies;
    }

    void scanWidget(Widget widget, List<Page> dependencies) throws WidgetConfigurationException {
        if (dependencies.contains(widget)) {
            return;
        }
        if (widget instanceof Page) {
            //System.out.println("dependency: " + widget.getClass().getName());
            dependencies.add((Page) widget);
        }
        scanEventHandlers(widget, dependencies);
        if (widget instanceof ContainerWidget<?>) {
            scanContainer((ContainerWidget<?>) widget, dependencies);
        }
    }

    void scanContainer(ContainerWidget<?> containerWidget, List<Page> dependencies) throws WidgetConfigurationException {
        for (Widget child : containerWidget) {
            scanWidget(child, dependencies);
        }
    }

    void scanEventHandlers(Widget widget, List<Page> dependencies) throws WidgetConfigurationException {
        for (EventHandler eventHandler : widget.getEventHandlers()) {
            scanStatements(eventHandler.getStatements(), dependencies);
        }
    }

    void scanStatements(List<Statement> statements, List<Page> dependencies) throws WidgetConfigurationException {
        for (Statement statement : statements) {
            if (statement == null) {
                continue;
            }
            checkStatement(statement, dependencies);
            List<Statement> references = new ArrayList<>();
            statement.getReferences(references);
            scanStatements(references, dependencies);
        }
    }

    void checkStatement(Statement statement, List<Page> dependencies) throws WidgetConfigurationException {
        if (statement instanceof OpenPageStatement openPageStatement) {
            scanWidget(getPageInstance(openPageStatement.getPageClass()), dependencies);
        }
        if (statement instanceof GetPageValueStatement getPageValueStatement) {
            scanWidget(getPageInstance(getPageValueStatement.getPageClass()), dependencies);
        }
    }

    Page getPageInstance(Class<? extends Page> pageClass) throws WidgetConfigurationException {
        for (Page page : pages) {
            if (page.getClass() == pageClass) {
                return page;
            }
        }
        throw new WidgetConfigurationException("missing page: " + pageClass.getSimpleName() + " in: "
                                               + pages.stream()
                                                       .map(page -> page.getClass().getSimpleName() + " (" + page.getId() + ")")
                                                       .collect(Collectors.joining(", ")));
    }
}
