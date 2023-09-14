package com.philips.dmis.swt.ui.toolkit.utils;

import com.philips.dmis.swt.ui.toolkit.widgets.*;
import jakarta.el.MethodNotFoundException;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.util.*;
import java.util.logging.Logger;

public class PageXmlWriter {
    public static PageXmlWriter getInstance() {
        return new PageXmlWriter();
    }

    private static final Logger LOG = Logger.getLogger(PageXmlWriter.class.getName());
    private static final XMLOutputFactory XML_OUTPUT_FACTORY = XMLOutputFactory.newInstance();
    private static final TransformerFactory TRANSFORMER_FACTORY = TransformerFactory.newInstance();
    private static final List<Class<?>> COMPLEX_TYPES = List.of(Map.class);

    public void exportXml(Page page) throws IOException, InterruptedException, URISyntaxException, XMLStreamException,
            InvocationTargetException, TransformerException {
        FileOutputStream fileOutputStream = null;
        try {
            File outputFile = getOutputFile(page.getClass());
            LOG.info("output file: " + outputFile.getAbsolutePath());

            // ensure path exists
            Files.createDirectories(outputFile.getParentFile().toPath());

            // ensure file does not exist or else exit
            if (outputFile.exists() && outputFile.length() != 0) {
                LOG.info("file exists");
                int x = 0;
                while (x < 3 && !outputFile.delete()) {
                    Thread.sleep(500);
                    x++;
                }
                //return;
            }

            // create new file
            Files.createFile(outputFile.toPath());

            fileOutputStream = new FileOutputStream(outputFile);

            exportXml(page, fileOutputStream);
        } finally {
            if (fileOutputStream != null) {
                fileOutputStream.close();
            }
        }
    }

    public void exportXml(Page page, OutputStream outputStream) throws XMLStreamException, InvocationTargetException,
            TransformerException {
        XMLStreamWriter writer = null;
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

            writer = XML_OUTPUT_FACTORY.createXMLStreamWriter(byteArrayOutputStream);

            writer.writeStartDocument();
            writeWidget(writer, page);
            writer.writeEndDocument();

            writer.flush();

            Transformer t = TRANSFORMER_FACTORY.newTransformer();
            t.setOutputProperty(OutputKeys.INDENT, "yes");
            t.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
            t.transform(new StreamSource(new ByteArrayInputStream(byteArrayOutputStream.toByteArray())),
                    new StreamResult(outputStream));
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }

    void writeWidget(XMLStreamWriter writer, Widget widget) throws XMLStreamException, InvocationTargetException {
        String name = widget instanceof Page ? "Page" : widget.getClass().getSimpleName();
        writer.writeStartElement(name);

        writer.writeAttribute("id", widget.getId());

        // process attributes
        Set<String> fields = new HashSet<>();
        getSerializedFields(widget.getClass(), fields);
        for (HasStaticHTML hasStaticHTML : widget.getStaticHtmlImplementations()) {
            getSerializedFields(hasStaticHTML.getClass(), fields);
        }
        LOG.info("fields in " + widget.getClass().getSimpleName() + ": " + fields);
        for (String field : fields) {
            writeAttribute(writer, widget, field);
        }
        for (String field : fields) {
            writeComplexAttribute(writer, widget, field);
        }

        // special cases: either container or ...
        if (widget instanceof ContainerWidget<?> containerWidget) {
            for (Widget child : containerWidget) {
                writeWidget(writer, child);
            }
        } else {
            if (widget instanceof HasText) {
                String text = ((HasText) widget).getText();
                if (text != null && !text.isEmpty()) {
                    writer.writeCharacters(text);
                }
            }
            if (widget instanceof HasJson) {
                String json = ((HasJson) widget).getJson();
                if (json != null && !json.isEmpty()) {
                    writer.writeCharacters(json);
                }
            }
        }

        writer.writeEndElement();
    }

    void writeAttribute(XMLStreamWriter writer, Widget widget, String field) throws XMLStreamException, InvocationTargetException {
        Method[] methods = widget.getClass().getMethods();
        String getterName = "get" + field;
        String isName = "is" + field;
        Method method = null;
        for (Method m : methods) {
            if (m.getName().equalsIgnoreCase(getterName)
                    || m.getName().equalsIgnoreCase(isName)
                    || m.getName().equalsIgnoreCase(field)) {
                method = m;
                break;
            }
        }
        if (method == null) {
            throw new MethodNotFoundException("missing method to retrieve value of: " + field);
        }
        // complex types are not serialized as attributes but elements
        if (COMPLEX_TYPES.contains(method.getReturnType())) {
            return;
        }
        try {
            method.setAccessible(true);
            String value = convertValue(method.getReturnType(), method.invoke(widget));
            if (value.isEmpty()) {
                return;
            }
            writer.writeAttribute(field, value);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } finally {
            method.setAccessible(false);
        }
    }

    void writeComplexAttribute(XMLStreamWriter writer, Widget widget, String field) throws XMLStreamException, InvocationTargetException {
        Method[] methods = widget.getClass().getMethods();
        String getterName = "get" + field;
        String isName = "is" + field;
        Method method = null;
        for (Method m : methods) {
            if (m.getName().equalsIgnoreCase(getterName)
                    || m.getName().equalsIgnoreCase(isName)
                    || m.getName().equalsIgnoreCase(field)) {
                method = m;
                break;
            }
        }
        if (method == null) {
            throw new MethodNotFoundException("missing method to retrieve value of: " + field);
        }
        if (!COMPLEX_TYPES.contains(method.getReturnType())) {
            return;
        }
        try {
            method.setAccessible(true);
            Object value = method.invoke(widget);
            if (value == null) {
                return;
            }

            // Map types
            if (value instanceof Map<?, ?> map) {
                boolean hasEntry = false;
                for (Map.Entry<?, ?> entry : map.entrySet()) {
                    String k = convertValue(entry.getKey().getClass(), entry.getKey());
                    if (k.isEmpty()) {
                        continue;
                    }
                    if (!hasEntry) {
                        writer.writeStartElement(field);
                        hasEntry = true;
                    }
                    writer.writeStartElement("add");
                    writer.writeAttribute("key", k);
                    String v = convertValue(entry.getValue().getClass(), entry.getValue());
                    writer.writeCharacters(v);
                    writer.writeEndElement();
                }
                if (hasEntry) {
                    writer.writeEndElement();
                }
            }


            //String value = convertValue(method.getReturnType(), method.invoke(widget));

        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } finally {
            method.setAccessible(false);
        }
    }

    String convertValue(Class<?> type, Object value) {
        if (value == null) {
            return "";
        }
        if (type.isArray()) {
            return joinValues((Object[]) value);
        }
        if (Collection.class.isAssignableFrom(type)) {
            return joinValues(((Collection<?>) value).toArray());
        }
        if (value instanceof Widget) {
            return ((Widget) value).getId();
        }
        if (type.isEnum()) {
            if (value instanceof HasDefault) {
                if (((HasDefault<?>) value).getDefault() == value) {
                    return "";
                }
            }
            return ((Enum<?>) value).name();
        }
        if (type == Boolean.class || type == boolean.class) {
            if (!((boolean) value)) {
                return "";
            }
        }

        // default relies on object.toString()
        return value.toString();
    }

    String joinValues(Object[] array) {
        StringBuilder s = new StringBuilder();
        int i = 0;
        for (Object arrayElement : array) {
            if (i > 0) {
                s.append(TypeConverter.STRING_ARRAY_SEPARATOR);
            }
            String value = convertValue(arrayElement.getClass(), arrayElement);
            if (value.isEmpty()) {
                continue;
            }
            s.append(value);
            i++;
        }
        return s.toString();
    }

    void getSerializedFields(Class<?> cls, Set<String> fields) {
        if (cls == Object.class) {
            return;
        }
        PageXmlElement pageXmlElement = cls.getAnnotation(PageXmlElement.class);
        if (pageXmlElement != null) {
            fields.addAll(List.of(pageXmlElement.value()));
        }
        getSerializedFields(cls.getSuperclass(), fields);
    }

    public File getOutputFile(Class<?> clz) throws URISyntaxException {
        URL url = clz.getClassLoader().getResource("");
        return new File(new File(url.toURI()),
                clz.getPackageName().replace(".", "/")
                        + "/" + clz.getSimpleName() + ".xml");
    }
}
