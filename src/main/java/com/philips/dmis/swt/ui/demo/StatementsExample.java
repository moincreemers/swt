package com.philips.dmis.swt.ui.demo;

import com.philips.dmis.swt.ui.toolkit.Constants;
import com.philips.dmis.swt.ui.toolkit.css.CssLength;
import com.philips.dmis.swt.ui.toolkit.events.ClickEventHandler;
import com.philips.dmis.swt.ui.toolkit.statement.Description;
import com.philips.dmis.swt.ui.toolkit.statement.method.FocusStatement;
import com.philips.dmis.swt.ui.toolkit.statement.method.M;
import com.philips.dmis.swt.ui.toolkit.statement.predicate.P;
import com.philips.dmis.swt.ui.toolkit.statement.value.*;
import com.philips.dmis.swt.ui.toolkit.widgets.*;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.Comparator;

@Component
public class StatementsExample extends Page {
    public StatementsExample() throws Exception {
        super(Constants.isDemo(StatementsExample.class));
    }

    @Override
    protected void build() throws Exception {
        if (!isDefault()) {
            add(HtmlLink.closePage("Back to Examples"));
        }
        add(new HtmlHeading("Using Statements"));

        add(new HtmlParagraph("Statements serve as the logic part of Singular applications. " +
                "All statements are translated to its Javascript equivalent."));

        add(new HtmlParagraph("Statements are always invoked by an Event and Events are always " +
                "raised by Widgets. There are several types of Events and not all Widgets support " +
                "the same Events."));

        add(new HtmlParagraph("""
                There are several types of statement:
                <ul>
                    <li>Method Statements invoke a certain action. Methods usually require one or more values but not always. All Method Statements are available through the provided <code>M</code> class.</li>
                    <li>Value Statements always return a value. All Value Statements are available through the provided <code>V</code> class.</li>
                    <li>There are several specialized statements such as predicates, these are used in other places such as Data Adapters.</li>
                </ul>
                """));

        Grid grid = add(new Grid(4));
        grid.setColumnWidth(0, CssLength.px(250));

        grid.addAll(new HtmlHeading("Method Statements", 3), new HtmlText(), new HtmlText(), new HtmlText());
        renderStaticFieldsAndMethods(M.class, grid);

        grid.addAll(new HtmlHeading("Value Statements", 3), new HtmlText(), new HtmlText(), new HtmlText());
        renderStaticFieldsAndMethods(V.class, grid);
    }


    void renderStaticFieldsAndMethods(Class<?> cls, Grid container) throws WidgetConfigurationException {
        Field[] fields = cls.getFields();
        for (Field field : Arrays.stream(fields).sorted(Comparator.comparing(Field::getName)).toList()) {
            if (!((field.getModifiers() & Modifier.PUBLIC) == Modifier.PUBLIC &&
                    (field.getModifiers() & Modifier.STATIC) == Modifier.STATIC)) {
                continue;
            }
            Description description = field.getAnnotation(Description.class);
            if (description == null) {
                description = field.getType().getAnnotation(Description.class);
                if (description == null) {
                    continue;
                }
            }
            container.addAll(
                    new HtmlParagraph("<code>" + cls.getSimpleName() + "." + field.getName() + "</code>"),
                    new HtmlParagraph(""),
                    new HtmlParagraph(description.value()),
                    new HtmlText()
            );
        }
        Method[] methods = cls.getMethods();
        for (Method method : Arrays.stream(methods).sorted(Comparator.comparing(Method::getName)).toList()) {
            if (!((method.getModifiers() & Modifier.PUBLIC) == Modifier.PUBLIC &&
                    (method.getModifiers() & Modifier.STATIC) == Modifier.STATIC)) {
                continue;
            }
            Description description = method.getAnnotation(Description.class);
            if (description == null) {
                description = method.getReturnType().getAnnotation(Description.class);
                if (description == null) {
                    continue;
                }
            }
            Parameter[] parameters = method.getParameters();
            StringBuilder parametersText = new StringBuilder();
            int i = 0;
            for (Parameter parameter : parameters) {
                if (i > 0) {
                    parametersText.append(", ");
                }
                parametersText.append("<code>");
                parametersText.append(parameter.getType().getSimpleName());
                parametersText.append(" ");
                parametersText.append(parameter.getName());
                parametersText.append("</code>");
                i++;
            }
            Panel examplePanel = new Panel();
            createExample(method.getReturnType(), examplePanel);
            container.addAll(
                    new HtmlParagraph("<code>" + cls.getSimpleName() + "." + method.getName() + "</code>"),
                    new HtmlParagraph(parametersText.toString()),
                    new HtmlParagraph(description.value()),
                    examplePanel
            );
        }
    }

    void createExample(Class<?> statementClass, Panel panel) throws WidgetConfigurationException {
        HtmlPreformatted outputText = new HtmlPreformatted();
        HtmlButton runButton = new HtmlButton("Run");
        HtmlTextInput someTextInput = new HtmlTextInput();

        if (FocusStatement.class == statementClass) {
            panel.addAll(runButton.onClick(
                    new ClickEventHandler(
                            M.Focus(someTextInput)
                    )), new HtmlPreformatted("M.Focus(focusText)"), someTextInput);
        }


        if (ArrayConcatValueStatement.class == statementClass) {
            panel.addAll(runButton.onClick(
                            new ClickEventHandler(
                                    M.SetText(outputText, V.GetJSON(V.ArrayConcat(V.Const(new Integer[]{1, 2, 3}), V.Const(new Integer[]{4, 5, 6})))))),
                    new HtmlPreformatted(
                            """
                                    V.ArrayConcat(V.Const(new Integer[]{1, 2, 3}), V.Const(new Integer[]{4, 5, 6}))
                                    """
                    ), outputText);
        }
        if (ArrayOfValueStatement.class == statementClass) {
            panel.addAll(runButton.onClick(
                    new ClickEventHandler(
                            M.SetText(outputText, V.GetJSON(V.ArrayOf(V.Const("Apple"), V.Const(123), V.Const(true))))
                    )
            ), new HtmlPreformatted(
                    """
                            V.ArrayOf(V.Const("Apple"), V.Const(123), V.Const(true))
                            """
            ), outputText);
        }
        if (ArrayCopyWithinValueStatement.class == statementClass) {
            panel.addAll(runButton.onClick(
                    new ClickEventHandler(
                            M.SetText(outputText, V.GetJSON(V.ArrayCopyWithin(V.Const(new String[]{"a", "b", "c", "d", "e"}), V.Const(1), V.Const(3)))))
            ), new HtmlPreformatted(
                    """
                            V.ArrayCopyWithin(V.Const(new String[]{"a", "b", "c", "d", "e"}), V.Const(1), V.Const(3))
                            """), outputText);
        }
        if (ArrayOfValueStatement.class == statementClass) {
            panel.addAll(runButton.onClick(
                    new ClickEventHandler(
                            M.SetText(outputText, V.GetJSON(V.ArrayOf(V.Const("Apple"), V.Const(123), V.Const(true))))
                    )
            ), new HtmlPreformatted(
                    """
                            V.ArrayOf(V.Const("Apple"), V.Const(123), V.Const(true))
                            """), outputText);
        }
        if (ArrayEveryValueStatement.class == statementClass) {
            panel.addAll(runButton.onClick(
                    new ClickEventHandler(
                            M.SetText(outputText, V.GetJSON(V.ArrayEvery(V.Const(new Integer[]{345, 213, 3, 12, 23, 667}), P.IsGreaterThan(V.Const(100))))))
            ), new HtmlPreformatted(
                    """
                            V.ArrayEvery(V.Const(new Integer[]{345, 213, 3, 12, 23, 667}), P.IsGreaterThan(V.Const(100)))
                            """), outputText);
        }
        if (ArrayFillValueStatement.class == statementClass) {
            panel.addAll(runButton.onClick(
                    new ClickEventHandler(
                            M.SetText(outputText, V.GetJSON(V.ArrayFill(V.ArrayWithLength(V.Const(5)), V.Const(true)))))
            ), new HtmlPreformatted(
                    """
                            V.ArrayFill(V.Array(V.Const(5)), V.Const(true))
                            """), outputText);
        }
        if (ArraySortValueStatement.class == statementClass) {
            panel.addAll(runButton.onClick(
                    new ClickEventHandler(
                            M.SetText(outputText, V.ArraySort(V.Const(new String[]{"Zucchini", "Banana", "Apple"}))))
            ), new HtmlPreformatted(
                    """
                            V.ArraySort(V.Const(new String[]{"Zuchini", "Banana", "Apple"}))
                            """), outputText);
        }
        if (ArrayWithLengthValueStatement.class == statementClass) {
            panel.addAll(runButton.onClick(
                    new ClickEventHandler(
                            M.SetText(outputText, V.GetJSON(V.ArrayWithLength(V.Const(5)))))
            ), new HtmlPreformatted(
                    """
                            V.Array(V.Const(5))
                            """), outputText);
        }
    }
}
