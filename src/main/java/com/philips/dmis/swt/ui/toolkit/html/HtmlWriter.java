package com.philips.dmis.swt.ui.toolkit.html;

public class HtmlWriter {
    private final StringBuffer html = new StringBuffer();

    //html.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
    //html.append("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.1//EN\" \"http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd\">");
    //html.append("<html version=\"-//W3C//DTD XHTML 1.1//EN\" xmlns=\"http://www.w3.org/1999/xhtml\" xml:lang=\"en\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://www.w3.org/1999/xhtml http://www.w3.org/MarkUp/SCHEMA/xhtml11.xsd\">");


    public void append(String format, Object... args) {
        if (format == null) {
            return;
        }
        if (args == null || args.length == 0) {
            html.append(format);
        } else {
            html.append(String.format(format, args));
        }
    }

    public void link(String href) {
        append("<link rel='stylesheet' type='text/css' href='%s'></link>", href);
    }

    @Override
    public String toString() {
        return html.toString();
    }
}
