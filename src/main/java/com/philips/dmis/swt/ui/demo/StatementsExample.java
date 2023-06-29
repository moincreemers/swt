package com.philips.dmis.swt.ui.demo;

import com.philips.dmis.swt.ui.toolkit.Constants;
import com.philips.dmis.swt.ui.toolkit.events.ClickEventHandler;
import com.philips.dmis.swt.ui.toolkit.statement.method.M;
import com.philips.dmis.swt.ui.toolkit.statement.predicate.P;
import com.philips.dmis.swt.ui.toolkit.statement.value.*;
import com.philips.dmis.swt.ui.toolkit.widgets.*;
import org.springframework.stereotype.Component;

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

        add(new HtmlParagraph("There are several types of statement: "));
        add(new ListContainer(
                new SingleRowPanel(
                        new HtmlParagraph("Methods are any JS statement such as: "),
                        new HtmlPreformatted("alert(message)")
                ),
                new SingleRowPanel(
                        new HtmlParagraph("Values are arguments to a function such as: "),
                        new HtmlPreformatted("\"a String value\"")
                ),
                new SingleRowPanel(
                        new HtmlParagraph("Predicates are boolean functions: "),
                        new HtmlPreformatted("boolean function(argument)")
                ),
                new SingleRowPanel(
                        new HtmlParagraph("Aggregates are functions that receive an array and return a single value: "),
                        new HtmlPreformatted("int sum(array)")
                ),
                new SingleRowPanel(
                        new HtmlParagraph("Reducers are functions that receive two arguments and return a single value: "),
                        new HtmlPreformatted("int sum(a, b)")
                )
        ));

        add(new HtmlParagraph("You can instantiate statements directly but its more convenient to use the 'shorthand' " +
                "classes. Use \"M\" for Methods, \"V\" for Values, \"P\" for Predicates, \"A\" for Aggregates and \"R\" " +
                "for reducers. "));


        // ARRAY

        add(new HtmlHeading("Array", 2));
        Grid gridArray = add(new Grid(4));
        gridArray.setAppearance(WidgetAppearance.BORDERED);

        // ArrayConcat
        HtmlPreformatted arrayConcatText = new HtmlPreformatted();
        gridArray.addAll(new HtmlParagraph(ArrayConcatValue.class.getSimpleName()), new HtmlButton("Run").onClick(
                        new ClickEventHandler(
                                M.SetText(arrayConcatText,
                                        V.GetJSON(
                                                V.ArrayConcat(
                                                        V.Const(new Integer[]{1, 2, 3}),
                                                        V.Const(new Integer[]{4, 5, 6})
                                                )
                                        )
                                )
                        )
                ),
                new HtmlPreformatted(
                        "V.ArrayConcat(\n" +
                                "  V.Const(new Integer[]{1, 2, 3}),\n" +
                                "  V.Const(new Integer[]{4, 5, 6})\n" +
                                ")"), arrayConcatText);

        // ArrayOf
        HtmlPreformatted arrayOfText = new HtmlPreformatted();
        gridArray.addAll(new HtmlParagraph(ArrayOfValue.class.getSimpleName()), new HtmlButton("Run").onClick(
                new ClickEventHandler(
                        M.SetText(arrayOfText, V.GetJSON(
                                V.ArrayOf(V.Const("Apple"), V.Const(123), V.Const(true))))
                )
        ), new HtmlPreformatted(
                "V.ArrayOf(\n" +
                        "  V.Const(\"Apple\"),\n" +
                        "  V.Const(123),\n" +
                        "  V.Const(true),\n" +
                        ")"), arrayOfText);

        // ArrayCopyWithin
        HtmlPreformatted arrayCopyWithinText = new HtmlPreformatted();
        gridArray.addAll(new HtmlParagraph(ArrayCopyWithinValue.class.getSimpleName()), new HtmlButton("Run").onClick(
                new ClickEventHandler(
                        M.SetText(arrayCopyWithinText,
                                V.GetJSON(
                                        V.ArrayCopyWithin(
                                                V.Const(new String[]{"a", "b", "c", "d", "e"}),
                                                V.Const(1),
                                                V.Const(3))
                                )
                        )
                )
        ), new HtmlPreformatted(
                "V.ArrayCopyWithin(\n" +
                        "  V.Const(new String[]{\"a\", \"b\", \"c\", \"d\", \"e\"}),\n" +
                        "  V.Const(1),\n" +
                        "  V.Const(3)\n" +
                        ")"), arrayCopyWithinText);

        // V.ArrayEvery
        HtmlPreformatted arrayEveryText = new HtmlPreformatted();
        gridArray.addAll(new HtmlParagraph(ArrayEveryValue.class.getSimpleName()), new HtmlButton("Run").onClick(
                new ClickEventHandler(
                        M.SetText(arrayEveryText,
                                V.GetJSON(
                                        V.ArrayEvery(
                                                V.Const(new Integer[]{345, 213, 3, 12, 23, 667}),
                                                P.IsGreaterThan(V.Const(100))
                                        )
                                )
                        )
                )
        ), new HtmlPreformatted(
                "V.ArrayEvery(\n" +
                        "  V.Const(new Integer[]{345, 213, 3, 12, 23, 667}),\n" +
                        "  P.IsGreaterThan(V.Const(100))\n" +
                        ")"), arrayEveryText);

        // V.ArrayFill
        HtmlPreformatted arrayFillText = new HtmlPreformatted();
        gridArray.addAll(new HtmlParagraph(ArrayFillValue.class.getSimpleName()), new HtmlButton("Run").onClick(
                new ClickEventHandler(
                        M.SetText(arrayFillText, V.GetJSON(V.ArrayFill(V.Array(V.Const(5)), V.Const(true)))))
        ), new HtmlPreformatted(
                "V.ArrayFill(\n" +
                        "  V.Array(\n" +
                        "    V.Const(5)\n" +
                        "  ),\n" +
                        "  V.Const(true)\n" +
                        ")"), arrayFillText);


        // V.ArraySort
        HtmlPreformatted arraySortText = new HtmlPreformatted();
        gridArray.addAll(new HtmlParagraph(ArraySortValue.class.getSimpleName()), new HtmlButton("Run").onClick(
                new ClickEventHandler(
                        M.SetText(arraySortText, V.ArraySort(V.Const(new String[]{"Zucchini", "Banana", "Apple"}))))
        ), new HtmlPreformatted(
                "V.ArraySort(\n" +
                        "  V.Const(new String[]{\"Zuchini\", \"Banana\",\" Apple\"})\n" +
                        ")"), arraySortText);

        // V.Array
        HtmlPreformatted arrayText = new HtmlPreformatted();
        gridArray.addAll(new HtmlParagraph(ArrayValue.class.getSimpleName()), new HtmlButton("Run").onClick(
                new ClickEventHandler(
                        M.SetText(arrayText, V.GetJSON(V.Array(V.Const(5)))))
        ), new HtmlPreformatted(
                "V.Array(\n" +
                        "  V.Const(5)\n" +
                        ")\n"), arrayText);

        // MISCELLANEOUS

        add(new HtmlHeading("Miscellaneous", 2));
        Grid gridOther = add(new Grid(4));
        gridOther.setAppearance(WidgetAppearance.BORDERED);

        // M.Focus
        HtmlTextInput focusText = add(new HtmlTextInput());
        gridOther.addAll(new HtmlParagraph("M.Focus"), new HtmlButton("Focus").onClick(
                new ClickEventHandler(
                        M.Focus(focusText)
                )), new HtmlPreformatted("M.Focus(focusText)"), focusText);

        //  M.Iif and M.Enable
        HtmlTextInput enableText = add(new HtmlTextInput());
        HtmlButton enableHtmlButton = add(new HtmlButton("Disable"));
        enableHtmlButton.onClick(
                new ClickEventHandler(
                        M.Iif(
                                        V.GetEnabled(enableText))
                                .True(
                                        M.SetDisabled(enableText),
                                        M.SetText(enableHtmlButton, V.Const("Enable")))
                                .False(
                                        M.SetEnabled(enableText),
                                        M.SetText(enableHtmlButton, V.Const("Disable")))
                )
        );
        gridOther.addAll(new HtmlParagraph("M.Iif, M.Enable, M.Disable"), enableHtmlButton, new HtmlPreformatted(
                """
                        M.Iif(V.GetEnabled(enableText))
                          .True(
                            M.Disable(enableText),
                            M.Set(enableButton, V.Const("Enable"))  )
                          .False(
                            M.Enable(enableText),
                            M.Set(enableButton, V.Const("Disable")))"""
        ), enableText);

//        M.ForEach(V.Array(V.Const(10))).Apply(
////            V.Key(),
////                V.Value()
//        );
    }
}
