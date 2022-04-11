package pageobjects.web;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.support.PageFactory;

public class WebPageObject {

    AppiumDriver driver;

    public WebPageObject(AppiumDriver appiumDriver) {
        driver = appiumDriver;
        PageFactory.initElements(appiumDriver, this);
    }
}
