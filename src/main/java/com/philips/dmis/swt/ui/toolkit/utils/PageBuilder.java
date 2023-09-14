package com.philips.dmis.swt.ui.toolkit.utils;

import com.philips.dmis.swt.ui.toolkit.widgets.*;
import org.springframework.stereotype.Component;
import org.xml.sax.Attributes;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Parameter;
import java.lang.reflect.*;
import java.util.*;

@Component
public class PageBuilder {
    private static final SAXParserFactory SAX_PARSER_FACTORY = SAXParserFactory.newInstance();

    public static PageBuilder getInstance() {
        return new PageBuilder();
    }

    public PageBuilder() {
    }

    public void loadFromXml(Page page) throws IOException, ParserConfigurationException, SAXException {
        String resource = page.getClass().getSimpleName() + ".xml";
        loadFromXml(page, resource);
    }

    public void loadFromXml(Page page, String resource) throws IOException, ParserConfigurationException, SAXException {
        Class<?> clz = page.getClass();
        try (InputStream inputStream = clz.getResourceAsStream(resource)) {
            loadFromXml(clz, inputStream, new PageBuilderHandler(page));
        }
    }

    public void loadFromXml(Page page, InputStream resource) throws IOException, ParserConfigurationException, SAXException {
        loadFromXml(page.getClass(), resource, new PageBuilderHandler(page));
    }

    void loadFromXml(Class<?> clz, InputStream inputStream, DefaultHandler handler) throws IOException, ParserConfigurationException, SAXException {
        SAXParser saxParser = SAX_PARSER_FACTORY.newSAXParser();
        saxParser.parse(inputStream, handler);
    }

    enum ReaderContext {
        INITIAL,
        WIDGET,
        COMPLEX_ATTRIBUTE
    }

    static abstract class ComplexAttribute extends DefaultHandler {
        protected final Locator locator;
        private final String qName;
        private StringBuilder textContent = new StringBuilder();
        private final List<String> elements = new ArrayList<>();
        private boolean done = false;

        ComplexAttribute(Locator locator, String qName) {
            this.locator = locator;
            this.qName = qName;
            this.elements.add(qName);
        }

        private void popElementOrThrow(String qName) throws SAXException {
            if (elements.isEmpty()) {
                throw new SAXException("complex attribute read error, encountered closing element: " + qName);
            }
            String currentElement = elements.get(elements.size() - 1);
            if (!currentElement.equals(qName)) {
                throw new SAXException("complex attribute read error, encountered closing element: " + qName + " but expected: " + currentElement);
            }
            elements.remove(elements.size() - 1);
        }

        @Override
        public void characters(char[] ch, int start, int length) throws SAXException {
            assertNotDone();
            textContent.append(ch, start, length);
        }

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            assertNotDone();
            elements.add(qName);
            handleElement(qName, attributes);
        }

        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
            assertNotDone();
            if (!textContent.isEmpty()) {
                setTextContent(PageBuilderHandler.normalizeTextContent(textContent.toString()));
            }
            textContent = new StringBuilder();
            popElementOrThrow(qName);
            done = elements.isEmpty();
        }

        public String getQName() {
            return qName;
        }

        public boolean isDone() {
            return done;
        }

        void assertNotDone() throws SAXException {
            if (done) {
                throw new SAXException("complex type is done");
            }
        }

        abstract void handleElement(String qName, Attributes attributes) throws SAXException;

        abstract void setTextContent(String text) throws SAXException;

        abstract void updateWidget(Widget widget);
    }

    static class MapAttribute extends ComplexAttribute {
        private final Widget widget;
        private String key = null;

        MapAttribute(Locator locator, Widget widget, String qName) {
            super(locator, qName);
            this.widget = widget;
        }

        @Override
        void handleElement(String qName, Attributes attributes) throws SAXException {
            if (qName.equals("add")) {
                key = attributes.getValue("key");
            }
        }

        @Override
        void setTextContent(String text) throws SAXException {
            if (key != null && !key.isEmpty()) {
                Method setter = PageBuilderHandler.findSetterWithTwoParameters(locator, widget.getClass(), getQName());
                Object k = null, v = null;
                try {
                    setter.setAccessible(true);
                    k = TypeConverter.getInstance(PageBuilderHandler.getPosition(locator)).convert(
                            setter.getParameters()[0].getType(), key);
                    v = TypeConverter.getInstance(PageBuilderHandler.getPosition(locator)).convert(
                            setter.getParameters()[1].getType(), text);
                    setter.invoke(widget, k, v);
                } catch (IllegalArgumentException e) {
                    throw new SAXException("method \"" + setter.getName() + "\" with values \"" + k + "\" and \"" + v
                            + "\" failed on widget \"" + widget.getClass().getSimpleName() + "\" "
                            + PageBuilderHandler.getPosition(locator), e);
                } catch (IllegalAccessException | InvocationTargetException e) {
                    throw new SAXException(e);
                } finally {
                    setter.setAccessible(false);
                }

                key = "";
            }
        }

        @Override
        void updateWidget(Widget widget) {
            // this ComplexAttribute implementation does not use this method to update the widget
        }
    }

    static class PageBuilderHandler extends DefaultHandler {
        private final Page page;
        private Widget currentWidget;
        private final List<Widget> widgets = new ArrayList<>();
        private StringBuilder textContent = new StringBuilder();
        private ReaderContext readerContext = ReaderContext.INITIAL;
        private Locator locator;
        private ComplexAttribute complexAttribute;

        PageBuilderHandler(Page page) {
            this.page = page;
        }

        @Override
        public void setDocumentLocator(Locator locator) {
            this.locator = locator;
        }

        public Widget pop() {
            if (widgets.size() > 1) {
                Widget w = widgets.get(widgets.size() - 1);
                widgets.remove(w);
                return w;
            }
            return null;
        }

        public void push(Widget widget) throws WidgetConfigurationException {
            while (!(currentWidget instanceof ContainerWidget<?>)) {
                currentWidget = pop();
            }
            this.widgets.add(widget);
            ((ContainerWidget<?>) currentWidget).add(widget);
        }

        public Widget peek() {
            return widgets.get(widgets.size() - 1);
        }

        @Override
        public void characters(char[] ch, int start, int length) throws SAXException {
            switch (readerContext) {
                case COMPLEX_ATTRIBUTE:
                    complexAttribute.characters(ch, start, length);
                    break;
                default:
                    textContent.append(ch, start, length);
                    break;
            }
        }

        @Override
        public void startElement(String uri, String lName, String qName, Attributes attr) throws SAXException {
            switch (readerContext) {
                case INITIAL:
                    readerContext = handleWidgetElement(uri, lName, qName, attr);
                    break;
                case WIDGET:
                    readerContext = handleStartComplexAttribute(uri, lName, qName, attr);
                    break;
                case COMPLEX_ATTRIBUTE:
                    complexAttribute.startElement(uri, lName, qName, attr);
                    break;
            }
        }

        @SuppressWarnings("unused")
        ReaderContext handleWidgetElement(String uri, String lName, String qName, Attributes attr) throws SAXException {
            List<String> attributesUsedInConstructor = new ArrayList<>();
            // note: never set id
            attributesUsedInConstructor.add("id");

            // instantiate widget
            String className = Widget.class.getPackageName() + "." + qName;
            Class<?> widgetClass = getWidgetClass(className);

            String id = null;
            if (widgetClass == Page.class) {
                currentWidget = page;
                this.widgets.add(currentWidget);

            } else {
                id = attr.getValue("id");
                currentWidget = createWidget(widgetClass, id, attr, attributesUsedInConstructor);

                // add to container widget
                try {
                    push(currentWidget);
                } catch (WidgetConfigurationException e) {
                    throw new SAXException(e);
                }
            }

            // process attributes
            for (int i = 0; i < attr.getLength(); i++) {
                String name = attr.getQName(i);
                if (attributesUsedInConstructor.contains(name)) {
                    continue;
                }
                String value = attr.getValue(i);
                Method setter = findSetterWithOneParameter(locator, widgetClass, name);
                try {
                    setter.setAccessible(true);
                    Class<?> paramType = setter.getParameters()[0].getType();
                    Object paramValue = TypeConverter.getInstance(getPosition(locator)).convert(paramType, value);
                    setter.invoke(currentWidget, paramValue);
                } catch (IllegalArgumentException e) {
                    throw new SAXException("method \"" + setter.getName() + "\" with value \"" + value + "\" failed on widget \""
                            + widgetClass.getSimpleName() + "\" " + getPosition(locator), e);
                } catch (IllegalAccessException | InvocationTargetException e) {
                    throw new SAXException("failed to access or invoke method: " + setter.getName(), e);
                } finally {
                    setter.setAccessible(false);
                }
            }

            // set widget-field of page
            if (id != null && !id.isEmpty()) {
                Field field = findField(page.getClass(), id, widgetClass);
                if (field != null) {
                    try {
                        field.setAccessible(true);
                        field.set(page, currentWidget);
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    } finally {
                        field.setAccessible(false);
                    }
                }
            }

            return ReaderContext.WIDGET;
        }

        @SuppressWarnings("unused")
        ReaderContext handleStartComplexAttribute(String uri, String lName, String qName, Attributes attr) throws SAXException {
            String type = attr.getValue("type");
            if (type == null || type.isEmpty()) {
                return ReaderContext.WIDGET;
            }
            switch (type) {
                case "map":
                    complexAttribute = new MapAttribute(locator, currentWidget, qName);
                    break;
            }
            return ReaderContext.COMPLEX_ATTRIBUTE;
        }

        void updateComplexAttribute() throws SAXException {
            complexAttribute.updateWidget(currentWidget);
        }

        Class<?> getWidgetClass(String className) throws SAXException {
            try {
                return Class.forName(className);
            } catch (ClassNotFoundException e) {
                throw new SAXException("widget type not found \"" + className + "\" " + getPosition(locator));
            }
        }

        Widget createWidget(Class<?> widgetClass, String id, Attributes attr, List<String> attributesUsedInConstructor) throws SAXException {
            Constructor<?> c = findConstructor(widgetClass);
            try {
                List<Object> paramValues = new ArrayList<>();
                for (Parameter p : c.getParameters()) {
                    if (p.getType() == WidgetConfigurator.class) {
                        paramValues.add(new DefaultWidgetConfigurator(id));
                    } else {
                        paramValues.add(findParameterValueInAttributes(widgetClass, p, attr));
                        attributesUsedInConstructor.add(p.getName());
                    }
                }
                c.setAccessible(true);
                Object o = c.newInstance(paramValues.toArray());
                if (o instanceof Widget) {
                    return (Widget) o;
                }
            } catch (InvocationTargetException | InstantiationException | IllegalAccessException e) {
                throw new SAXException(e);
            } finally {
                c.setAccessible(false);
            }
            throw new SAXException("unexpected error, unable to create widget \"" + widgetClass.getSimpleName() + "\" "
                    + getPosition(locator));
        }

        Constructor<?> findConstructor(Class<?> widgetClass) throws SAXException {
            Constructor<?> c = null;
            int parameterCount = Integer.MAX_VALUE;
            for (Constructor<?> constructor : widgetClass.getDeclaredConstructors()) {
                if (Arrays.stream(constructor.getParameters()).anyMatch(p -> p.getType() == WidgetConfigurator.class)) {
                    if (constructor.getParameterCount() < parameterCount) {
                        c = constructor;
                        parameterCount = constructor.getParameterCount();
                    }
                }
            }
            if (c != null) {
                return c;
            }
            throw new SAXException("widget type does not declare a suitable constructor \"" + widgetClass.getSimpleName() + "\" "
                    + getPosition(locator));
        }

        Object findParameterValueInAttributes(Class<?> widgetClass, Parameter p, Attributes attr) throws SAXException {
            String value = attr.getValue(p.getName());
            if (value == null) {
                throw new SAXException("missing required attribute \"" + p.getName() + "\" in widget \"" + widgetClass.getSimpleName() + "\" "
                        + getPosition(locator));
            }
            return TypeConverter.getInstance(getPosition(locator)).convert(p.getType(), value);
        }

        static Method findSetterWithOneParameter(Locator locator, Class<?> widgetClass, String name) throws SAXException {
            String properName = name.substring(0, 1).toUpperCase() + name.substring(1);
            String setterName = "set" + properName;
            Method[] methods = widgetClass.getMethods();
            for (Method method : methods) {
                if (method.getParameterCount() != 1 || !method.getName().equals(setterName)) {
                    continue;
                }
                return method;
            }
            throw new SAXException("method not found \"" + setterName + "\" on class \"" + widgetClass.getSimpleName() + "\" "
                    + getPosition(locator));
        }

        static Method findSetterWithTwoParameters(Locator locator, Class<?> widgetClass, String name) throws SAXException {
            String properName = name.substring(0, 1).toUpperCase() + name.substring(1);
            String setterName = "set" + properName;
            String adderName = "add" + properName;
            Method[] methods = widgetClass.getMethods();
            for (Method method : methods) {
                if (method.getParameterCount() != 2 ||
                        !(method.getName().equals(setterName) || method.getName().equals(adderName))) {
                    continue;
                }
                return method;
            }
            throw new SAXException("method not found \"" + setterName + "\" or \"" + adderName + "\" on class \""
                    + widgetClass.getSimpleName() + "\" " + getPosition(locator));
        }


        Field findField(Class<?> widgetClass, String id, Class<?> childWidgetClass) {
            Field[] fields = widgetClass.getDeclaredFields();
            for (Field field : fields) {
                if (field.getName().equals(id) && field.getType().isAssignableFrom(childWidgetClass)) {
                    return field;
                }
            }
            return null;
        }

        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
            switch (readerContext) {
                case INITIAL:
                    // not a valid case
                    break;
                case WIDGET:
                    Widget widget = pop();
                    if (widget instanceof HasText) {
                        ((HasText) widget).setText(normalizeTextContent(textContent.toString()));
                    }
                    textContent = new StringBuilder();
                    readerContext = ReaderContext.INITIAL;
                    currentWidget = widgets.isEmpty() ? page : peek();
                    break;
                case COMPLEX_ATTRIBUTE:
                    complexAttribute.endElement(uri, localName, qName);
                    if (complexAttribute.isDone()) {
                        updateComplexAttribute();
                        complexAttribute = null;
                        readerContext = ReaderContext.WIDGET;
                    }
                    break;
            }
        }

        static String normalizeTextContent(String textContent) {
            return textContent.trim();
        }

        static String getPosition(Locator locator) {
            return "[" + locator.getLineNumber() + ", " + locator.getColumnNumber() + "]";
        }
    }
}
