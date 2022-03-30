package scenarios;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Locale;
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

        assertThat(searchResults.stream()
                .filter(x -> x.toLowerCase(Locale.ROOT).contains(WebProperties.KEYWORD))
                .count()).isGreaterThan(WebProperties.LINKS_NUMBER);

        System.out.println("Android web test done");
    }

}
