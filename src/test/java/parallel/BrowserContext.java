package parallel;

public class BrowserContext {
    private static ThreadLocal<String> browserThreadLocal = new ThreadLocal<>();

    public static void setBrowser(String browser) {
        browserThreadLocal.set(browser);
    }

    public static String getBrowser() {
        return browserThreadLocal.get();
    }

    public static void removeBrowser() {
        browserThreadLocal.remove();
    }
}
