package com.philips.dmis.swt.ui.toolkit.html;

import java.net.URI;
import java.util.*;
import java.util.function.Function;

public class SanitizeHTML {
    private static final Map<String, Map<String, Function<String, Boolean>>> WHITELIST = new HashMap<>();


    static {
        Map<String, Function<String, Boolean>> aConfig = new HashMap<>();
        aConfig.put("href", SanitizeHTML::validateHref);

        WHITELIST.put("a", aConfig);
        WHITELIST.put("b", new HashMap<>());
        WHITELIST.put("i", new HashMap<>());
        WHITELIST.put("u", new HashMap<>());
    }

    public static boolean validateHref(String href) {
        if (href == null || href.isEmpty()) {
            return true;
        }
        try {
            URI uri = URI.create(href);
            if (uri.getScheme() == null || uri.getScheme().isEmpty()) {
                return false;
            }
            if (uri.getScheme().equals("http") || uri.getScheme().equals("https")) {
                return true;
            }
        } catch (Exception e) {
            return false;
        }
        return false;
    }

    // allow HTML but disallow certain elements
    // todo: replace with proper tokenizer
    public static String secureHTML(String text) {
        if (text == null) {
            return "";
        }

        int x = 1000;

        String textLower = text.toLowerCase();
        int i = textLower.indexOf("<");
        while (x > 0 && i > -1 && i < textLower.length()) {
            x--;

            int j = textLower.indexOf(">", i);
            if (j == -1) {
                j = textLower.indexOf(" ", i);
                if (j != -1) {
                    j = textLower.length();
                }
            }
            j = Math.min(textLower.length(), j + 1);
            String tag = textLower.substring(i, j);
            //System.out.println(tag);

            boolean allowed = false;
            String allowedElement = "";
            for (String e : WHITELIST.keySet()) {
                if (tag.contains("<" + e)) {
                    allowed = true;
                    allowedElement = e;
                    break;
                }
            }

            if (allowed) {
                String[] parts = tag.substring(1, tag.length() - 1).split(" ");
                if (parts.length > 1) {
                    Map<String, Function<String, Boolean>> allowedAttr = WHITELIST.get(allowedElement);
                    for (int a = 1; a < parts.length; a++) {
                        String[] kv = parts[a].split("=");
                        Function<String, Boolean> validator = allowedAttr.get(kv[0]);
                        if (validator == null) {
                            allowed = false;
                            break;
                        }
                        if (kv.length > 1) {
                            String v = kv[1];
                            if ((v.startsWith("'") && v.endsWith("'"))
                                    || (v.startsWith("\"") && v.endsWith("\""))
                            ) {
                                v = v.substring(1, v.length() - 1);
                                if (!validator.apply(v)) {
                                    allowed = false;
                                    break;
                                }
                            } else {
                                allowed = false;
                                break;
                            }
                        } else {
                            allowed = false;
                            break;
                        }
                    }
                }
                if (allowed) {
                    String closing = "</" + allowedElement + ">";
                    j = textLower.indexOf(closing);
                    if (j == -1) {
                        allowed = false;
                    } else {
                        j += closing.length();
                    }
                }
            }

            if (!allowed) {
                return "unsafe content";
            }

            i = textLower.indexOf("<", j);
        }

        if (x == 0) {
            return "unsafe content";
        }

        // todo: check URLs

        return text;
    }
}
