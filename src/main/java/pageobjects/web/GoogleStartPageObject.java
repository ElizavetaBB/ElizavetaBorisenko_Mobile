package pageobjects.web;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class GoogleStartPageObject extends WebPageObject {

    @FindBy(css = "style ~ input")
    private WebElement searchField;

    public GoogleStartPageObject(AppiumDriver appiumDriver) {
        super(appiumDriver);
    }

    public SearchResultPageObject search(String query) {
        searchField.sendKeys(query, Keys.ENTER);
        return new SearchResultPageObject(driver);
    }
}
