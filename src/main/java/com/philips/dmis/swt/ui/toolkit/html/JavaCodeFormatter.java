package com.philips.dmis.swt.ui.toolkit.html;

public class JavaCodeFormatter {
    private static final String INDENT = "  ";
    private static final String NEWLINE = "\n";

    // todo: replace with proper tokenizer
    public static String format(String code) {
        if (code == null || code.isEmpty()) {
            return "";
        }
        StringBuffer a = new StringBuffer();
        // todo:
        char[] chars = code.toCharArray();
        int indentLevel = 0;
        int pc = -1;
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            switch (c) {
                case 10: // lf
                case 13: // cr
                    lineFeed(a, indentLevel);
                    break;
                case 32: // space
                    if (pc != -1 && pc != 32) {
                        a.append(" ");
                    }
                    break;

                case 40: // (
                    if (a.length() > 0 && pc != 32) {
                        a.append(" ");
                    }
                    a.append(c);
                    break;

                case 44: // comma
                    a.append(c);
                    a.append(" ");
                    break;

                case 59: // semicolon
                    a.append(c);
                    lineFeed(a, indentLevel);
                    break;

                case 123: // {
                    a.append(" ");
                    a.append(c);
                    indentLevel++;
                    lineFeed(a, indentLevel);
                    break;
                case 125: // }
                    indentLevel--;
                    lineFeed(a, indentLevel);
                    a.append(c);
                    lineFeed(a, indentLevel);
                    break;


                case 41: // )
                default:
                    a.append(c);
                    break;
            }
            pc = c;
        }
        return a.toString();
    }

    private static void lineFeed(StringBuffer a, int indentLevel) {
        // remove indent
        if (a.toString().endsWith(NEWLINE + INDENT)) {
            a.delete(a.length() - (NEWLINE.length() + INDENT.length()), a.length());
        }
        a.append(NEWLINE);
        for (int j = 0; j < indentLevel; j++) {
            a.append(INDENT);
        }
    }
}
