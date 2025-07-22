package com.qa.aims.Factory;

import com.microsoft.playwright.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.Properties;

public class PlaywrightFactory {

    Playwright playwright;
    Browser browser;
    BrowserContext browserContext;
    Page page;
    Properties prop;

    private static ThreadLocal<Browser> tlBrowser = new ThreadLocal<>();
    private static ThreadLocal<BrowserContext> tlBrowserContext = new ThreadLocal<>();
    private static ThreadLocal<Page> tlPage = new ThreadLocal<>();
    private static ThreadLocal<Playwright> tlPlaywright = new ThreadLocal<>();

    public static Playwright getPlaywright() {
        return tlPlaywright.get();
    }

    public static Browser getBrowser() {
        return tlBrowser.get();
    }

    public static BrowserContext getBrowserContext() {
        return tlBrowserContext.get();
    }

    public static Page getPage() {
        return tlPage.get();
    }

    public Page initBrowser(Properties prop) {
        String browserName = prop.getProperty("browser").trim();
        System.out.println("Browser name is: " + browserName);

        tlPlaywright.set(Playwright.create());

        switch (browserName.toLowerCase()) {
            case "chromium":
                tlBrowser.set(getPlaywright().chromium()
                        .launch(new BrowserType.LaunchOptions().setHeadless(false)));
                break;

            case "firefox":
                tlBrowser.set(getPlaywright().firefox()
                        .launch(new BrowserType.LaunchOptions().setHeadless(false)));
                break;

            case "safari":
                tlBrowser.set(getPlaywright().webkit()
                        .launch(new BrowserType.LaunchOptions().setHeadless(false)));
                break;

            case "chrome":
                tlBrowser.set(getPlaywright().chromium()
                        .launch(new BrowserType.LaunchOptions().setChannel("chrome").setHeadless(false)));
                break;

            case "edge":
                tlBrowser.set(getPlaywright().chromium()
                        .launch(new BrowserType.LaunchOptions().setChannel("msedge").setHeadless(false)));
                break;

            default:
                System.out.println("Please pass a valid browser name.");
                break;
        }

        tlBrowserContext.set(getBrowser().newContext());
        tlPage.set(getBrowserContext().newPage());
        getPage().navigate(prop.getProperty("url").trim());

        return getPage();
    }

    /**
     * Load configuration from config.properties file
     */
    public Properties init_prop() {
        Properties prop = new Properties();
        try (FileInputStream ip = new FileInputStream(".\\src\\test\\java\\config\\config.properties")) {
            prop.load(ip);
            System.out.println("Configuration loaded successfully.");
        } catch (IOException e) {
            System.err.println("Failed to load config.properties file: " + e.getMessage());
            e.printStackTrace();
        }
        return prop;
    }

    /**
     * Take screenshot and return Base64 string
     */
    public static String takeScreenshot() {
        String path = System.getProperty("user.dir") + "/screenshot/" + System.currentTimeMillis() + ".png";
        byte[] buffer = getPage().screenshot(
                new Page.ScreenshotOptions().setPath(Paths.get(path)).setFullPage(true));
        return Base64.getEncoder().encodeToString(buffer);
    }
}
