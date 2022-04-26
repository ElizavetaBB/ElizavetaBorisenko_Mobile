package scenarios;

import java.util.List;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import pageobjects.web.GoogleStartPageObject;
import pageobjects.web.SearchResultPageObject;
import properties.WebProperties;
import setup.BaseTest;

public class WebMobileTests extends BaseTest {

    @Test(groups = {"web"}, description = "Search EPAM on Google search page "
            + "and check that there are some relevant results.")
    public void simpleWebTest() {
        System.out.println("Web test started");

        getDriver().get(WebProperties.URL); // open Google homepage

        GoogleStartPageObject googleStartPageObject = (GoogleStartPageObject) getPageObject().getPageObjectInstance();

        SearchResultPageObject searchResultPageObject = googleStartPageObject.search(WebProperties.QUERY);

        // wait for the page with results to be ready
        new WebDriverWait(getDriver(), 5).until(
                driver -> ((JavascriptExecutor) driver)
                        .executeScript("return document.readyState")
                        .equals("complete"));

        List<String> searchResults = searchResultPageObject.getSearchResultsTitles();

        Assertions.assertThat(searchResults).isNotNull();

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(searchResults.size()).isNotZero();
        searchResults.forEach(result -> softAssertions.assertThat(result).containsIgnoringCase(WebProperties.KEYWORD));
        softAssertions.assertAll();

        System.out.println("Web test done");
    }

}
