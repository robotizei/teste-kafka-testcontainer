package br.com.utils;

import io.cucumber.core.gherkin.Feature;
import io.cucumber.java.Scenario;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collections;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import static br.com.utils.FileUtils.createFolder;
import static br.com.utils.PropertiesHelper.*;
import static java.lang.Thread.sleep;

public class WebDriverHelper {

    private static final int TIME_WAIT = 50;
    public static ThreadLocal<Scenario> testScenario = new ThreadLocal<>();
    public static ThreadLocal<Feature> testFeature = new ThreadLocal<>();
    public static WebDriver driver;

    private static WebDriver initChromeDriver() {

        if (urlSeleniumGrid != null && urlSeleniumGrid.length() != 0) {
            try {
                driver = new RemoteWebDriver(new URL(urlSeleniumGrid),
                        chromeCapabilities());
            } catch (MalformedURLException e) {
                System.out.println(e.getMessage());
            }
        } else {
            WebDriverManager.chromedriver().setup();
            HashMap<String, Object> chromePrefs = new HashMap<>();
            ChromeOptions options = new ChromeOptions();

            if (pathDownload != null && pathDownload.length() == 0) {
                String pathForDownload = System
                        .getProperty("user.dir")
                        .concat(File.separator)
                        .concat("src")
                        .concat(File.separator)
                        .concat("test")
                        .concat(File.separator)
                        .concat("resources")
                        .concat(File.separator)
                        .concat("downloads");

                createFolder(pathForDownload);
                chromePrefs.put("download.default_directory", pathForDownload);
            } else {
                createFolder(pathDownload);
                chromePrefs.put("download.default_directory", System
                        .getProperty("user.dir").concat(File.separator).concat(pathDownload));
            }

            options.addArguments("--ignore-ssl-errors", "–-no-sandbox", "--ignore-certificate-errors");
            options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
            options.setExperimentalOption("useAutomationExtension", false);
            options.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
            chromePrefs.put("profile.default_content_settings.popups", 0);
            chromePrefs.put("profile.password_manager_enabled", false);
            chromePrefs.put("download.prompt_for_download", false);
            chromePrefs.put("credentials_enable_service", false);
            options.setExperimentalOption("prefs", chromePrefs);
//            options.addArguments("headless");
            driver = new ChromeDriver(options);
            driver.manage().timeouts().implicitlyWait(TIME_WAIT, TimeUnit.SECONDS);
            driver.manage().window().setSize(new Dimension(1200, 800));
        }
//
//        if (urlFrontendCirculacao != null) {
//            driver.get(urlFrontendCirculacao);
//        }

        return driver;
    }

    public static WebDriver getDriverWeb() {
        if (driver == null) {
            initChromeDriver();
        }
        return driver;
    }

    public static void closeDriverWeb() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }

    private static Capabilities chromeCapabilities() {
        DesiredCapabilities desiredCapabilities = DesiredCapabilities.chrome();
        HashMap<String, Object> chromePrefs = new HashMap<>();
        chromePrefs.put("profile.default_content_settings.popups", 0);
        chromePrefs.put("download.prompt_for_download", "false");
        chromePrefs.put("plugins.plugins_disabled", new String[]{
                "Adobe Flash Player", "Chrome PDF Viewer"});
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", chromePrefs);
        options.addArguments("start-maximized", "--ignore-ssl-errors", "–-no-sandbox", "--disable-infobars", "--ignore-certificate-errors", "--disable-web-security");
        desiredCapabilities.setCapability(CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION, true);
        desiredCapabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
        desiredCapabilities.setCapability(ChromeOptions.CAPABILITY, options);
        desiredCapabilities.setCapability("name", testScenario.get().getName());
        return desiredCapabilities;
    }

    public static void resetDriver(String url) {
        if (driver != null) {
            driver.get(urlTokenAuthorization.concat("logout?post_logout_redirect_uri=").concat(url));
            try {
                sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            driver.navigate().refresh();
            try {
                sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            driver.navigate().refresh();
        }
    }

    public static void resetDriverOtherURL(String url) {
        driver.get(url);
    }

}
