package com.philips.dmis.swt.ui.toolkit.widgets;

public enum RelType {
    /**
     * Alternate representations of the current document.
     */
    ALTERNATE("alternate",true,true,false),

    /**
     * Author of the current document or article.
     */
    AUTHOR("author",true,true,false),

    /**
     * Permalink for the nearest ancestor section.
     */
    BOOKMARK("bookmark",false,true,false),

    /**
     * Preferred URL for the current document.
     */
    CANONICAL("canonical",true,false,false),

    /**
     * Tells the browser to preemptively perform DNS resolution for the target resource's origin.
     */
    DNS_PREFETCH("dns-prefetch",true,false,false),

    /**
     * The referenced document is not part of the same site as the current document.
     */
    EXTERNAL("external",false,true,true),

    /**
     * Link to context-sensitive help.
     */
    HELP("help",true,true,true),

    /**
     * An icon representing the current document.
     */
    ICON("icon",true,false,false),

    /**
     * Indicates that the main content of the current document is covered by the copyright license. described by the referenced document.
     */
    LICENSE("license",true,true,true),

    /**
     * Web app manifest.
     */
    MANIFEST("manifest",true,false,false),

    /**
     * Indicates that the current document represents the person who owns the linked content.
     */
    ME("me",true,true,false),

    /**
     * Tells the browser to preemptively fetch the script and store it in the document's module map for later evaluation. Optionally, the module's dependencies can be fetched as well.
     */
    MODULE_PRELOAD("modulepreload",true,false,false),

    /**
     * Indicates that the current document is a part of a series and that the next document in the series is the referenced document.
     */
    NEXT("next",true,true,true),

    /**
     * Indicates that the current document's original author or publisher does not endorse the referenced document.
     */
    NO_FOLLOW("nofollow",false,true,true),

    /**
     * Creates a top-level browsing context that is not an auxiliary browsing context if the hyperlink would create either of those, to begin with (i.e., has an appropriate target attribute value).
     */
    NO_OPENER("noopener",false,true,true),

    /**
     * No Referer header will be included. Additionally, has the same effect as noopener.
     */
    NO_REFERRER("noreferrer",false,true,true),

    /**
     * Creates an auxiliary browsing context if the hyperlink would otherwise create a top-level browsing context that is not an auxiliary browsing context (i.e., has "_blank" as target attribute value).
     */
    OPENER("opener",false,true,true),

    /**
     * Gives the address of the pingback server that handles pingbacks to the current document.
     */
    PING_BACK("pingback",true,false,false),

    /**
     * Specifies that the user agent should preemptively connect to the target resource's origin.
     */
    PRE_CONNECT("preconnect",true,false,false),

    /**
     * Specifies that the user agent should preemptively fetch and cache the target resource as it is likely to be required for a followup navigation.
     */
    PREFETCH("prefetch",true,false,false),

    /**
     * Specifies that the user agent must preemptively fetch and cache the target resource for current navigation according to the potential destination given by the as attribute (and the priority associated with the corresponding destination).
     */
    PRELOAD("preload",true,false,false),

    /**
     * Specifies that the user agent should preemptively fetch the target resource and process it in a way that helps deliver a faster response in the future.
     */
    PRERENDER("prerender",true,false,false),

    /**
     * Indicates that the current document is a part of a series and that the previous document in the series is the referenced document.
     */
    PREV("prev",true,true,true),

    /**
     * Gives a link to a resource that can be used to search through the current document and its related pages.
     */
    SEARCH("search",true,true,true),

    /**
     * Imports a style sheet.
     */
    STYLESHEET("stylesheet",true,false,false),

    /**
     * Gives a tag (identified by the given address) that applies to the current document.
     */
    TAG("tag",false,true,false),

    ;

    final String value;
    final boolean externalResource;
    final boolean anchor;
    final boolean form;

    RelType(String value, boolean externalResource, boolean anchor, boolean form) {
        this.value = value;
        this.externalResource = externalResource;
        this.anchor = anchor;
        this.form = form;
    }

    public String getValue() {
        return value;
    }

    public boolean isExternalResource() {
        return externalResource;
    }

    public boolean isAnchor() {
        return anchor;
    }

    public boolean isForm() {
        return form;
    }


}
