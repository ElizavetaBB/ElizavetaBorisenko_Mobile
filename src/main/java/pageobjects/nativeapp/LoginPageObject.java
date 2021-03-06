package pageobjects.nativeapp;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;

public class LoginPageObject extends NativePageObject {

    @AndroidFindBy(id = "platkovsky.alexey.epamtestapp:id/login_email")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeTextField")
    private WebElement emailField;

    @AndroidFindBy(id = "platkovsky.alexey.epamtestapp:id/login_pwd")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeSecureTextField")
    private WebElement passwordField;

    @AndroidFindBy(id = "platkovsky.alexey.epamtestapp:id/email_sign_in_button")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@label='Sign In']")
    private WebElement signInBtn;

    @AndroidFindBy(id = "platkovsky.alexey.epamtestapp:id/register_button")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@label='Register new account']")
    private WebElement registerBtn;

    public LoginPageObject(AppiumDriver appiumDriver) {
        super(appiumDriver);
    }

    public RegistrationPageObject openRegistrationPage() {
        registerBtn.click();
        return new RegistrationPageObject(driver);
    }

    public LoginPageObject login(String email, String password) {
        emailField.sendKeys(email);
        passwordField.sendKeys(password);
        signInBtn.click();
        return this;
    }

    public BudgetActivityPageObject openBudgetActivityPage() {
        return new BudgetActivityPageObject(driver);
    }

    public LoginPageObject clickLoginButton() {
        signInBtn.click();
        return this;
    }
}
