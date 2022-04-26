package pageobjects.web;

import io.appium.java_client.AppiumDriver;
import java.util.List;
import java.util.stream.Collectors;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;

public class SearchResultPageObject extends WebPageObject {

    @FindAll({
        @FindBy(css = "div.BkwXh div"),
        @FindBy(css = "#rso div.xpd div.oewGkc div")
    })
    private List<WebElement> searchResultsTitles;

    public SearchResultPageObject(AppiumDriver appiumDriver) {
        super(appiumDriver);
    }

    public List<String> getSearchResultsTitles() {
        return searchResultsTitles.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }
}
