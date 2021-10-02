package guru.qa.domain;

public enum AuthorizationPage {
    MAIN("https://fundayshop.com/", ".control-user .icon"),
    CLUB("https://fundayshop.com/fun-club", ".club-footer-banner .banner__backgound-img"),
    CHECKOUT("https://fundayshop.com/checkout", ".control-user .icon");

    private String url;
    private String selector;

    AuthorizationPage(String url, String selector) {
        this.url = url;
        this.selector = selector;
    }

    public String getUrl() {
        return url;
    }
    public String getSelector() {
        return selector;
    }
}
