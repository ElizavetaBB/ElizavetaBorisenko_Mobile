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
        "bundleId", "platformVersion"})
    @BeforeSuite(alwaysRun = true)
    public void setUp(String cloudName, String platformName, String appType,
                      @Optional("") String deviceName,
                      @Optional("") String udid,
                      @Optional("browser") String browserName,
                      @Optional("app") String app,
                      @Optional("") String appPackage,
                      @Optional("") String appActivity,
                      @Optional("") String bundleId,
                      @Optional("") String platformVersion) throws Exception {
        System.out.println("Before: cloud name - " + cloudName);
        System.out.println("Before: app type - " + appType);
        setAppiumDriver(platformName, deviceName, udid, platformVersion,
                browserName, app, appPackage, appActivity, bundleId);
        setPageObject(appType, appiumDriver);
        System.setProperty("cloud", cloudName);
        System.setProperty("platformName", platformName);
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() {
        System.out.println("After");
        appiumDriver.closeApp();
    }

    private void setAppiumDriver(String platformName, String deviceName, String udid,
                                 String platformVersion, String browserName, String app,
                                 String appPackage, String appActivity, String bundleId) {
        String cloudName = System.getProperty("ts.appium");
        String cloudParameter = cloudName.contains("epam") ? "" : "appium:";
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("platformName", platformName);
        capabilities.setCapability(cloudParameter + "deviceName", deviceName);

        if (!browserName.equals("browser")) {
            capabilities.setCapability("browserName", browserName);
        }

        if (cloudName.contains("epam")) {
            capabilities.setCapability("udid", udid);

            if (app.endsWith(".apk")) {
                capabilities.setCapability("app", (new File(app)).getAbsolutePath());
            }

            // web capabilities
            capabilities.setCapability("chromedriverDisableBuildCheck", "true");

            // android app capabilities
            capabilities.setCapability("appPackage", appPackage);
            capabilities.setCapability("appActivity", appActivity);

            // ios app capabilities
            capabilities.setCapability("bundleId", bundleId);
        } else {
            capabilities.setCapability("appium:platformVersion", platformVersion);

            if (app.endsWith(".ipa") || app.endsWith(".apk")) {
                capabilities.setCapability("appium:app", "storage:filename=" + app);
            }

            MutableCapabilities sauceOptions = new MutableCapabilities();
            sauceOptions.setCapability("appiumVersion", "1.22.0");
            capabilities.setCapability("sauce:options", sauceOptions);
        }

        try {
            String key = cloudName.contains("epam")
                    ? URLEncoder.encode(System.getProperty("token"), StandardCharsets.UTF_8.name())
                    : System.getProperty("token");
            appiumDriver = new AppiumDriver(new URL(
                    String.format(System.getProperty("ts.appium"), System.getProperty("username"), key)
            ), capabilities);
        } catch (MalformedURLException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        // Timeouts tuning
        appiumDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    private void setPageObject(String appType, AppiumDriver appiumDriver) throws Exception {
        po = new PageObject(appType, appiumDriver);
    }
}
