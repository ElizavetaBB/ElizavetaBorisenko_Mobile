package pageobjects.web;

import io.appium.java_client.AppiumDriver;
import java.util.List;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class GoogleStartPageObject extends WebPageObject {

    @FindBy(css = "style ~ input")
    private WebElement searchField;

    @FindBy(css = "ul[role='listbox'] li span")
    private List<WebElement> searchOptions;

    @FindBy(xpath = "//div[@class='VDity']//button[2]")
    private WebElement cookieButton;

    public GoogleStartPageObject(AppiumDriver appiumDriver) {
        super(appiumDriver);
    }

    public SearchResultPageObject search(String query) {
        // accept cookies for Sauce Labs
        if (System.getProperty("cloud").contains("Sauce Labs")) {
            JavascriptExecutor executor = driver;
            executor.executeScript("arguments[0].click();", cookieButton);
        }
        searchField.click();
        searchField.sendKeys(query);
        searchOptions.get(0).click();
        return new SearchResultPageObject(driver);
    }
}
