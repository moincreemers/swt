package com.philips.dmis.swt.ui.toolkit;

import com.philips.dmis.swt.ui.toolkit.events.ColorSchemeChangeCustomEvent;
import com.philips.dmis.swt.ui.toolkit.events.ColorSchemeChangeEventHandler;
import com.philips.dmis.swt.ui.toolkit.html.HasConstantStorage;
import com.philips.dmis.swt.ui.toolkit.html.StaticValueStorage;
import com.philips.dmis.swt.ui.toolkit.js.JsModule;
import com.philips.dmis.swt.ui.toolkit.js.JsWriter;
import com.philips.dmis.swt.ui.toolkit.js.pages.InitFunction;
import com.philips.dmis.swt.ui.toolkit.js.pages.JsPagesModule;
import com.philips.dmis.swt.ui.toolkit.statement.method.M;
import com.philips.dmis.swt.ui.toolkit.statement.value.V;
import com.philips.dmis.swt.ui.toolkit.widgets.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.logging.Logger;

@Component
public class ToolkitController implements Toolkit, HasConstantStorage {
    private static final Logger LOG = Logger.getLogger(ToolkitController.class.getName());
    private final List<? extends JsModule> jsModules;
    private final Map<Class<? extends Page>, Page> pages = new HashMap<>();
    private final Page defaultPage;
    private final HasConstantStorage constantStorageImpl = new StaticValueStorage();
    private final Map<String, HasCode> codeModules = new LinkedHashMap<>();

    public ToolkitController(List<? extends JsModule> jsModules, List<Page> pages) throws WebControllerException, WidgetConfigurationException {
        if (jsModules == null || jsModules.isEmpty()) {
            throw new WebControllerException("missing modules");
        }
        if (pages == null || pages.isEmpty()) {
            throw new WebControllerException("no pages provided");
        }
        this.jsModules = jsModules;
        this.defaultPage = findRootPage(pages);
        DependencyFinder dependencyFinder = new DependencyFinder(defaultPage.getClass(), pages);
        List<Page> logicalPages = dependencyFinder.find();
        for(Page page: logicalPages){
            LOG.info("PAGE FOUND: " + page.getId() + ", " + page.getClass().getName());
            this.pages.put(page.getClass(), page);
        }
        LOG.info("DEFAULT PAGE: " + defaultPage.getId() + ", " + defaultPage.getClass().getName());
        for (Page page : logicalPages) {
            page.validate(this);
        }
    }

    private Page findRootPage(List<Page> pages) throws WebControllerException {
        Page defaultPage = null;
        for (Page page : pages) {
            if (page.isDefault()) {
                defaultPage = page;
                break;
            }
        }
        if (defaultPage == null) {
            throw new WebControllerException("no default page defined");
        }
        return defaultPage;
    }

    private CacheStrategy getCacheStrategy(HttpServletRequest request) {
        return new DefaultCacheStrategyImpl(request);
    }

    @Override
    public void setAppVersion(String appVersion) {
        // todo: not implemented
    }

    @Override
    public Page getDefaultPage() {
        return defaultPage;
    }

    @Override
    public List<Page> getPages() {
        return new ArrayList<>(pages.values());
    }

    @Override
    public Page getPage(Class<? extends Page> pageClass) {
        return pages.get(pageClass);
    }


    // CONSTANT STORAGE

    @Override
    public HasConstantStorage getConstantStorageImpl() {
        return constantStorageImpl.getConstantStorageImpl();
    }

    @Override
    public String registerConstant(String string) {
        return constantStorageImpl.registerConstant(string);
    }

    @Override
    public void renderConstantHtml(StringBuffer html) {
        constantStorageImpl.renderConstantHtml(html);
    }

    @Override
    public void renderConstantJs(JsWriter js) {
        constantStorageImpl.renderConstantJs(js);
    }

    // HasCode

    public void registerCode(HasCode hasCode) {
        if (codeModules.containsKey(hasCode.getModuleName())) {
            LOG.warning("code module " + hasCode.getClass().getName() + " overwrites a code module with the same module name.");
        }
        codeModules.put(hasCode.getModuleName(), hasCode);
        LOG.info("Registered code module: " + hasCode.getClass().getName());
    }

    /**
     * Generates the JS Client.
     */
    @Override
    public void render(HttpServletRequest request, HttpServletResponse response) throws IOException, JsRenderException {
        LOG.info("rendering HTML");

        CacheStrategy cacheStrategy = getCacheStrategy(request);
        if (!Constants.DEBUG && cacheStrategy.exists(request)) {
            response.setStatus(304);
            response.flushBuffer();
            return;
        }

        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        response.setContentType("text/html");

        // RENDER JS
        JsWriter js = new JsWriter(this, false);
        renderJs(js);

        // RENDER HTML
        StringBuffer html = new StringBuffer();
        html.append("<!DOCTYPE html>");
        html.append("<html>");
        html.append("<head>");
        html.append("<meta charset=\"utf-8\">");
        html.append("<meta name=\"viewport\" content=\"width=device-width,initial-scale=1\">");
        html.append("<link rel=\"shortcut icon\" href=\"favicon.ico\"/>");
        html.append("<link rel='stylesheet' type='text/css' href='default.css'></link>");
        html.append("<link rel='stylesheet' type='text/css' href='dark.css'></link>");
        html.append("<script language='javascript' src='pageheaders.js'></script>");
        html.append("</head><body>");

        // RENDER ALL HTML
        LoadingScreen loadingScreen = new LoadingScreen();
        loadingScreen.renderHtml(html);

        // render constants
        constantStorageImpl.renderConstantHtml(html);

        // note: add JS to page
        html.append("<script language='javascript'>");
        html.append(js);
        html.append("</script>");

        html.append("</body></html>");

        PrintWriter w = response.getWriter();
        try {
            long renderTime = cacheStrategy.getNowMillis();
            int maxAge = cacheStrategy.getMaxAgeSeconds();

            String eTag = "\"" + getHashCode(html) + "\"";

            // public, private, no-store, no-cache (-> requires ETag, check server before browser cache)
            response.setDateHeader("Date", renderTime);
            response.setHeader("Cache-Control", "no-cache, max-age=" + maxAge);
            response.setDateHeader("Expires", renderTime + maxAge * 1000L);
            response.setHeader("ETag", eTag);

            cacheStrategy.setETag(eTag);

            w.println(html);
            w.flush();
        } catch (NoSuchAlgorithmException e) {
            //
            throw new IOException(e);
        } finally {
            w.close();
        }
    }

    void renderJs(JsWriter js) throws IOException, JsRenderException {
        LOG.info("rendering JS");

        // bootstrapper
        js.comment("bootstrapper");
        Bootstrapper bootstrapper = new Bootstrapper(Constants.MAIN_MODULE_NAME, Constants.ENTRY_POINT);
        bootstrapper.renderJs(this, js);

        // render modules
        js.comment("modules");
        for (JsModule jsModule : jsModules) {
            jsModule.renderJs(this, js);
        }

        // render pages
        for (Page page : pages.values()) {
            page.renderJs(this, js);
        }

        // render isolated code modules
        for (HasCode hasCode : codeModules.values()) {
            hasCode.isolatedRenderJs(this, js);
        }

        // main module
        js.append("var %s=(function(){", Constants.MAIN_MODULE_NAME);

        GlobalEvents.onColorSchemeChange(new ColorSchemeChangeEventHandler(
                M.SetStylesheetDisabled(V.Const(Constants.CSS_DARK),
                        V.Is(
                                V.GetEvent(ColorSchemeChangeCustomEvent.COLOR_SCHEME),
                                V.Const(ColorSchemeChangeCustomEvent.SCHEME_LIGHT)))
        ));

        GlobalEvents globalEvents = new GlobalEvents();
        globalEvents.renderJs(this, js);

        js.append("const %s=()=>{", Constants.ENTRY_POINT); // MAIN
        js.append("initGlobalEvents();");

        for (Page page : pages.values()) {
            js.append("%s();", JsPagesModule.getQualifiedId(page, InitFunction.class));
        }
        for (JsModule jsModule : jsModules) {
            js.append("%s();", jsModule.getInitFunctionId());
        }
        js.append("try{PageHeaders.resize();}catch(e){}");
        js.append("console.log('hello world');");
        js.append("};"); // END MAIN

        constantStorageImpl.renderConstantJs(js);

        // export main and gc
        js.append("return {");
        js.append("%s:%s,", Constants.ENTRY_POINT, Constants.ENTRY_POINT);
        js.append("%s:%s,", HasConstantStorage.JS_GET_FUNCTION, HasConstantStorage.JS_GET_FUNCTION);
        js.append("%s:%s", HasConstantStorage.JS_INIT_FUNCTION, HasConstantStorage.JS_INIT_FUNCTION);
        js.append("};");

        js.append("})();"); // end module
    }

    private String getHashCode(StringBuffer stringBuffer) throws NoSuchAlgorithmException {
        final MessageDigest alg = MessageDigest.getInstance("md5");// not thread-safe
        return String.format("%032x",
                new BigInteger(1,
                        alg.digest(stringBuffer.toString().getBytes(StandardCharsets.UTF_8))));
    }
}