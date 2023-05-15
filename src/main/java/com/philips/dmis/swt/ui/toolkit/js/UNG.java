package com.philips.dmis.swt.ui.toolkit.js;

import com.philips.dmis.swt.ui.toolkit.Constants;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UNG {
    private static final List<String> KEYWORDS = Arrays.asList(
            "abstract", "arguments", "await", "boolean",
            "break", "byte", "case", "catch",
            "char", "class", "const", "continue",
            "debugger", "default", "delete", "do",
            "double", "else", "enum", "eval",
            "export", "extends", "false", "final",
            "finally", "float", "for", "function",
            "goto", "if", "implements", "import",
            "in", "instanceof", "int", "interface",
            "let", "long", "native", "new",
            "null", "package", "private", "protected",
            "public", "return", "short", "static",
            "super", "switch", "synchronized", "this",
            "throw", "throws", "transient", "true",
            "try", "typeof", "var", "void",
            "volatile", "while", "with", "yield",
//
            "alert", "all", "anchor", "anchors",
            "area", "assign", "blur", "button",
            "checkbox", "clearInterval", "clearTimeout", "clientInformation",
            "close", "closed", "confirm", "constructor",
            "crypto", "decodeURI", "decodeURIComponent", "defaultStatus",
            "document", "element", "elements", "embed",
            "embeds", "encodeURI", "encodeURIComponent", "escape",
            "event", "fileUpload", "focus", "form",
            "forms", "frame", "innerHeight", "innerWidth",
            "layer", "layers", "link", "location",
            "mimeTypes", "navigate", "navigator", "frames",
            "frameRate", "hidden", "history", "image",
            "images", "offscreenBuffering", "open", "opener",
            "option", "outerHeight", "outerWidth", "packages",
            "pageXOffset", "pageYOffset", "parent", "parseFloat",
            "parseInt", "password", "pkcs11", "plugin",
            "prompt", "propertyIsEnum", "radio", "reset",
            "screenX", "screenY", "scroll", "secure",
            "select", "self", "setInterval", "setTimeout",
            "status", "submit", "taint", "text",
            "textarea", "top", "unescape", "untaint",
            "window"


    );
    private static final Set<String> NAMES = new HashSet<>();

    public static String register(String name) {
        NAMES.add(name);
        return name;
    }

    public static String generate(Class<?> c) {
        String abbr = abbreviate(c.getSimpleName(), 3);
        String fn = abbr;
        int i = 0;
        while (NAMES.contains(fn) || KEYWORDS.contains(fn)) {
            fn = abbr + i++;
        }
        return register(fn);
    }

    static String abbreviate(String name, int len) {
        if (Constants.DEBUG) {
            return name;
        }
        String s = "";
        if (!Character.isUpperCase(name.charAt(0))) {
            s = name.substring(0, 1);
        }
        for (char ch : name.toCharArray()) {
            if (Character.isUpperCase(ch)) {
                s += ch;
            }
            if (s.length() >= len) {
                break;
            }
        }
        return s.toLowerCase();
    }
}
