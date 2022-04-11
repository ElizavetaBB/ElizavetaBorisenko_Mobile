package pageobjects;

import io.appium.java_client.AppiumDriver;
import pageobjects.nativeapp.LoginPageObject;
import pageobjects.web.GoogleStartPageObject;
import setup.IPageObject;

public class PageObject implements IPageObject {

    AppiumDriver driver;
    Object somePageObject; // it should be set of web page or EPAM Test App WebElements

    public PageObject(String appType, AppiumDriver appiumDriver) throws Exception {
        driver = appiumDriver;
        System.out.println("Current app type: " + appType);
        switch (appType) {
            case "web":
                somePageObject = new GoogleStartPageObject(appiumDriver);
                break;
            case "native":
                somePageObject = new LoginPageObject(appiumDriver);
                break;
            default: throw new Exception("Can't create a page object for " + appType);
        }
    }

    @Override
    public Object getPageObjectInstance() {
        return somePageObject;
    }
}
