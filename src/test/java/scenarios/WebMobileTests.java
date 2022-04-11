package scenarios;

import java.util.List;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;
import pageobjects.web.GoogleStartPageObject;
import pageobjects.web.SearchResultPageObject;
import properties.WebProperties;
import setup.BaseTest;

public class WebMobileTests extends BaseTest {

    @Test(groups = {"web"}, description = "Search EPAM on Google search page "
            + "and check that there are some relevant results.")
    public void simpleWebTest() {
        System.out.println("Android web test started");

        getDriver().get(WebProperties.URL); // open Google homepage

        GoogleStartPageObject googleStartPageObject = (GoogleStartPageObject) getPageObject().getPageObjectInstance();

        SearchResultPageObject searchResultPageObject = googleStartPageObject.search(WebProperties.QUERY);
        List<String> searchResults = searchResultPageObject.getSearchResultsTitles();

        SoftAssertions softAssertions = new SoftAssertions();
        searchResults.forEach(result -> softAssertions.assertThat(result).containsIgnoringCase(WebProperties.KEYWORD));
        softAssertions.assertAll();

        System.out.println("Android web test done");
    }

}
