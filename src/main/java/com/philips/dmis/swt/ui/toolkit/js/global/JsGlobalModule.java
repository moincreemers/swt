package com.philips.dmis.swt.ui.toolkit.js.global;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.js.JsMember;
import com.philips.dmis.swt.ui.toolkit.js.JsModule;
import com.philips.dmis.swt.ui.toolkit.js.JsWriter;
import com.philips.dmis.swt.ui.toolkit.js.Pair;
import com.philips.dmis.swt.ui.toolkit.widgets.JsRenderException;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Component
public class JsGlobalModule implements JsModule {
    public static final String ID = "Util";

    public static final boolean TRUE = true;
    public static final boolean FALSE = false;
    public static final int ZERO = 0;
    public static final int ONE = 1;
    public static final int OK = 1;
    public static final int CANCEL = 2;
    public static final int YES = 3;
    public static final int NO = 4;
    public static final String EMPTY = "''";
    public static final String NONE = "none";

    private static final List<JsMember> MEMBERS = Arrays.asList(
            // Constants
            new HyperlinksRegEx(),
            // Functions
            new AddClassNameFunction(),
            new AddViewActionFunction(),
            new AddViewFieldFunction(),
            new AppendOptionFunction(),
            new ClearDocumentHashFunction(),
            new ContainsFunction(),
            new ConvertHyperlinksFunction(),
            new CreateViewFunction(),
            new EqualsFunction(),
            new FormatArrayFunction(),
            new FormatBooleanFunction(),
            new FormatCodeFunction(),
            new FormatDateFunction(),
            new FormatNumberFunction(),
            new FormatStringFunction(),
            new FormatTextFunction(),
            new FormatURLFunction(),
            new GetConstantFunction(),
            new GetXhrResponseFunction(),
            new GetDocumentHashFunction(),
            new GetNavigatorName(),
            new GetOsName(),
            new GetScrollPositionFunction(),
            new GetSearchString(),
            new GetSelectedViewFunction(),
            new GetSelectionFunction(),
            new GetSessionValueFunction(),
            new GreaterThanFunction(),
            new GreaterThanOrEqualsFunction(),
            new InitFunction(),
            new IsFocusedFunction(),
            new IsObjectFunction(),
            new IsSelectionFunction(),
            new IsValidHashFunction(),
            new JoinArrayFunction(),
            new LessThanFunction(),
            new LessThanOrEqualsFunction(),
            new MatchFunction(),
            new NotEqualsFunction(),
            new ObjectToSearchFunction(),
            new ParseHttpHeaderFunction(),
            new ProperCaseFunction(),
            new ResolveObjectMemberOrPath(),
            new RemoveClassNameFunction(),
            new RemoveSessionValueFunction(),
            new SafeTextFunction(),
            new SanitizeHtmlFunction(),
            new SearchToObjectFunction(),
            new SendHttpRequestFunction(),
            new SetDocumentHashFunction(),
            new SetSessionValueFunction(),
            new SetXhrRequestHeadersFunction(),
            new ToNumberFunction(),
            new ToQueryStringFunction(),
            new ToStringSafeFunction(),
            new ToXmlFunction(),
            new WeekOfYearFunction(),
            new WeekOfYearStringFunction(),
            new WeekOfYearToDateFunction()

    );

    private static final Map<Class<? extends JsMember>, Pair<String, JsMember>> INDEX =
            JsModule.createIndex(MEMBERS);

    public static JsMember getMemberInstance(Class<? extends JsMember> memberClass) {
        return JsModule.getMemberInstance(memberClass, INDEX);
    }

    public static String getId(Class<? extends JsMember> memberClass) throws JsRenderException {
        return JsModule.getId(memberClass, INDEX);
    }

    public static String getQualifiedId(Class<? extends JsMember> memberClass) {
        return JsModule.getQualifiedId(ID, memberClass, INDEX);
    }

    @Override
    public void renderJs(Toolkit toolkit, JsWriter js) {
        JsModule.renderModule(toolkit, js, ID, INDEX.values());
    }

    @Override
    public String getInitFunctionId() {
        return getQualifiedId(InitFunction.class);
    }
}
