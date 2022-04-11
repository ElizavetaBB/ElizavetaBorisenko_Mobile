package pageobjects.nativeapp;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;

public class NativePageObject  {

    AppiumDriver driver;

    public NativePageObject(AppiumDriver appiumDriver) {
        driver = appiumDriver;
        PageFactory.initElements(
                new AppiumFieldDecorator(appiumDriver), this);
    }
}
