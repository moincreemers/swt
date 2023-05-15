package com.philips.dmis.swt.ui.toolkit.html;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SanitizeHTMLTest {
    static final String[][] GOOD_HTML = { //
            {"text", "some text"},
            {"text with bold", "some <b>text</b> test"},
            {"text with italic", "some <i>text</i> test"},
            {"text with underline", "some <u>text</u> test"},
            {"text with break", "some <u>text</u> test"},
            {"text with break", "some <u>text</u> test"},
            {"text with link", "some <a href='http://example.com'>text</a> test"},
            {"text with link", "some <a href='https://example.com'>text</a> test"},

    };

    static final String[][] BAD_HTML = { //
            {"match tag", "<script>"},
            {"match tag + whitespace after", "<script >"},
            {"match tag + whitespace before", "< script>"},
            {"match tag + whitespace around", "< script >"},
            {"match tag in text", "test <script> test"},
            {"match tag in text + whitespace after", "test <script > test"},
            {"match tag in text + whitespace before", "test < script> test"},
            {"match tag in text + whitespace around", "test < script > test"},
            {"match link with incomplete javascript href", "test <a href='javascript'>link</a> test"},
            {"match link with javascript href", "test <a href='javascript:'>link</a> test"},
            {"match link with javascript href", "test <a href='javascript:doSomething();'>link</a> test"},
            {"match link without closing tag", "test <a href='http://example.com'>link test"},
            {"text with link + id", "some <a id='abc'>text</a> test"},
            {"text with link malformed", "some <a>text</a > test"},
            {"text with non-http/non-https link", "some <a href='ftp://example.com'>text</a> test"},
            {"text with bold", "some <b onclick=\"ok\">text</b> test"},
    };

    @Test
    public void shouldDisallow() {
        for (String[] test : BAD_HTML) {
            System.out.println("testing bad html: " + test[1]);
            String s = SanitizeHTML.secureHTML(test[1]);
            Assertions.assertEquals("unsafe content", s, test[0]);
        }
    }

    @Test
    public void shouldAllow() {
        for (String[] test : GOOD_HTML) {
            System.out.println("testing good html: " + test[1]);
            String s = SanitizeHTML.secureHTML(test[1]);
            Assertions.assertEquals(test[1], s, test[0]);
        }
    }
}
