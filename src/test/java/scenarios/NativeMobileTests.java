package scenarios;

import static org.assertj.core.api.Assertions.assertThat;

import org.testng.annotations.Test;
import pageobjects.nativeapp.BudgetActivityPageObject;
import pageobjects.nativeapp.LoginPageObject;
import pageobjects.nativeapp.RegistrationPageObject;
import properties.NativeProperties;
import setup.BaseTest;

public class NativeMobileTests extends BaseTest {

    @Test(groups = {"native"}, description = "Test to register, login and check BudgetActivityPage")
    public void simpleNativeTest() {
        System.out.println("Android native test started");

        LoginPageObject loginPageObject = (LoginPageObject) getPageObject().getPageObjectInstance();

        RegistrationPageObject registrationPageObject = loginPageObject.openRegistrationPage();
        registrationPageObject.register(NativeProperties.EMAIL, NativeProperties.PASSWORD);

        BudgetActivityPageObject budgetActivityPageObject = loginPageObject
                .login(NativeProperties.EMAIL, NativeProperties.PASSWORD)
                .openBudgetActivityPage();

        String pageTitle = budgetActivityPageObject.getTitle();
        assertThat(pageTitle).isEqualTo(NativeProperties.BUDGET_PAGE_TITLE);

        System.out.println("Android native test done");
    }

}
