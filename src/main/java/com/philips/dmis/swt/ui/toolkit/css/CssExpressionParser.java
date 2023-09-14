package com.philips.dmis.swt.ui.toolkit.css;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class CssExpressionParser {
    private static final String OPERATORS = "*/+-";
    private static final String DELIMITERS = " ,;)" + OPERATORS;

    public static class Node {
        Node parent;
        final List<Node> children = new ArrayList<>();
        CssValue cssValue;

        Node(CssValue cssValue) {
            this.cssValue = cssValue;
        }

        boolean isRoot() {
            return cssValue instanceof Root;
        }

        boolean isFunction() {
            return cssValue instanceof CssFunction;
        }
    }

    static class Root extends CssValue {
    }

    public static class CssExpressionParseException extends RuntimeException {
        public CssExpressionParseException(String message) {
            super(message);
        }
    }

    private Node root;
    private Node current;

    public static CssExpressionParser getInstance() {
        return new CssExpressionParser();
    }

    public CssValue parse(String expr) {
        Node node = parseTree(expr);

        if (node.children.size() == 1) {
            return node.children.get(0).cssValue;
        }

        return new CssFunction("",
                node.children.stream().map(n -> n.cssValue).toList().toArray(new CssValue[0]));
    }

    Node parseTree(String expr) {
        StringTokenizer stringTokenizer = new StringTokenizer(expr, DELIMITERS, true);
        while (stringTokenizer.hasMoreTokens()) {
            String token = stringTokenizer.nextToken();
            //System.out.println(token);

            if (token.equals(" ")) {
                // ignore
                continue;
            }

            if (token.equals(")")) {
                pop();
                continue;
            }

            if (OPERATORS.contains(token)) {
                if (!(current.isFunction())) {
                    throw new CssExpressionParseException("encountered operator \"" + token
                            + "\" but current node is not a function");
                }
                push(new Node(new CssLiteral(token)));
                continue;
            }

            if (tryParseFunction(stringTokenizer, token)) {
                continue;
            }
            if (tryParseLength(stringTokenizer, token)) {
                continue;
            }

            push(new Node(new CssLiteral(normalizeLiteral(token))));
        }

        //print(System.out, root);

        return root;
    }

    void push(Node node) {
        if (root == null) {
            root = new Node(new Root());
            current = root;
        }
        node.parent = current;
        current.children.add(node);
        if (current.isFunction()) {
            ((CssFunction) current.cssValue).addTerm(node.cssValue);
        }
        if (node.isFunction()) {
            current = node;
        }
    }

    void pop() {
        if (!current.isFunction()) {
            throw new CssExpressionParseException("current node is not a function");
        }
        current = current.parent;
    }

    @SuppressWarnings("unused")
    boolean tryParseFunction(StringTokenizer stringTokenizer, String token) {
        if (token.endsWith("(")) {
            String name = token.substring(0, token.length() - 1);
            push(new Node(new CssFunction(name)));
            return true;
        }
        return false;
    }

    @SuppressWarnings("unused")
    boolean tryParseLength(StringTokenizer stringTokenizer, String token) {
        for (CssUnit unit : CssUnit.values()) {
            if (token.endsWith(unit.getValue())) {
                push(new Node(new CssLength(Double.parseDouble(CssUnit.removeUnit(token, unit)), unit)));
                return true;
            }
        }
        return false;
    }

    //

    @SuppressWarnings("unused")
    public static void print(PrintStream out, Node node) {
        print(out, 0, node);
    }

    private static void print(PrintStream out, int indent, Node node) {
        for (int i = 0; i < indent; i++) {
            out.print("  ");
        }
        if (!node.isRoot()) {
            out.println(node.cssValue.getClass().getSimpleName() + ": " + node.cssValue);
            indent++;
        }
        for (Node child : node.children) {
            print(out, indent, child);
        }
    }

    private static String normalizeLiteral(String token) {
        return token.replace("\"", "'");
    }
}
