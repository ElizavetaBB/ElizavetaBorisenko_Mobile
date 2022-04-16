package setup;

import io.appium.java_client.AppiumDriver;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import pageobjects.PageObject;
import properties.AuthorizationProperties;

public class BaseTest implements IDriver {

    private static AppiumDriver appiumDriver; // singleton
    private static IPageObject po;

    @Override
    public AppiumDriver getDriver() {
        return appiumDriver;
    }

    public IPageObject getPageObject() {
        return po;
    }

    @Parameters({"cloudName", "platformName", "appType", "deviceName",
        "udid", "browserName", "app", "appPackage", "appActivity",
        "bundleId", "platformVersion", "appName"})
    @BeforeSuite(alwaysRun = true)
    public void setUp(String cloudName, String platformName, String appType,
                      @Optional("") String deviceName,
                      @Optional("") String udid,
                      @Optional("") String browserName,
                      @Optional("app") String app,
                      @Optional("") String appPackage,
                      @Optional("") String appActivity,
                      @Optional("") String bundleId,
                      @Optional("") String platformVersion,
                      @Optional("app") String appName) throws Exception {
        System.out.println("Before: app type - " + appType);
        if (cloudName.contains("EPAM")) {
            setAppiumDriver(platformName, deviceName, udid, browserName, app, appPackage, appActivity, bundleId);
        } else {
            setAppiumDriver(platformName, deviceName, platformVersion, browserName, appName);
        }
        setPageObject(appType, appiumDriver);
        System.setProperty("cloud", cloudName);
        System.setProperty("platformName", platformName);
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() {
        System.out.println("After");
        appiumDriver.closeApp();
    }

    private void setAppiumDriver(String platformName, String deviceName, String udid, String browserName,
                                 String app, String appPackage, String appActivity, String bundleId) {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        // mandatory Android capabilities
        capabilities.setCapability("platformName", platformName);
        capabilities.setCapability("deviceName", deviceName);
        capabilities.setCapability("udid", udid);

        if (app.endsWith(".apk")) {
            capabilities.setCapability("app", (new File(app)).getAbsolutePath());
        }

        // web capabilities
        capabilities.setCapability("browserName", browserName);
        capabilities.setCapability("chromedriverDisableBuildCheck", "true");

        // android app capabilities
        capabilities.setCapability("appPackage", appPackage);
        capabilities.setCapability("appActivity", appActivity);

        // ios app capabilities
        capabilities.setCapability("bundleId", bundleId);

        // EPAM driver
        try {
            String key = URLEncoder.encode(AuthorizationProperties.TOKEN_EPAM, StandardCharsets.UTF_8.name());
            appiumDriver = new AppiumDriver(new URL(
                    String.format(System.getProperty("ts.appium.epam"), AuthorizationProperties.PROJECT_NAME_EPAM, key)
                ), capabilities);
        } catch (MalformedURLException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        // Timeouts tuning
        appiumDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    private void setAppiumDriver(String platformName, String deviceName, String platformVersion,
                                 String browserName, String appName) {
        MutableCapabilities caps = new MutableCapabilities();
        caps.setCapability("platformName", platformName);
        caps.setCapability("appium:deviceName", deviceName);
        caps.setCapability("appium:platformVersion", platformVersion);

        if (appName.endsWith(".ipa") || appName.endsWith(".apk")) {
            caps.setCapability("appium:app", "storage:filename=" + appName);
        } else {
            caps.setCapability("browserName", browserName);
        }

        MutableCapabilities sauceOptions = new MutableCapabilities();
        sauceOptions.setCapability("appiumVersion", "1.22.0");
        caps.setCapability("sauce:options", sauceOptions);

        // Sauce Labs driver
        try {
            appiumDriver = new AppiumDriver(new URL(
                    String.format(System.getProperty("ts.appium.sauceLabs"),
                            AuthorizationProperties.USERNAME_SAUCE_LABS, AuthorizationProperties.TOKEN_SAUCE_LABS)
            ), caps);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        // Timeouts tuning
        appiumDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    private void setPageObject(String appType, AppiumDriver appiumDriver) throws Exception {
        po = new PageObject(appType, appiumDriver);
    }
}
