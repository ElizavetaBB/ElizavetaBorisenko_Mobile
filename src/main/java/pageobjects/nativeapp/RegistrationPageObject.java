package pageobjects.nativeapp;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;

public class RegistrationPageObject extends NativePageObject {

    @AndroidFindBy(id = "platkovsky.alexey.epamtestapp:id/registration_email")
    @iOSXCUITFindBy(xpath =
            "//XCUIElementTypeStaticText[@value='Email']/following-sibling::XCUIElementTypeTextField")
    private WebElement emailField;

    @AndroidFindBy(id = "platkovsky.alexey.epamtestapp:id/registration_password")
    @iOSXCUITFindBy(xpath =
            "//XCUIElementTypeStaticText[@value='Password']/following-sibling::XCUIElementTypeSecureTextField")
    private WebElement passwordField;

    @AndroidFindBy(id = "platkovsky.alexey.epamtestapp:id/registration_confirm_password")
    @iOSXCUITFindBy(xpath =
            "//XCUIElementTypeStaticText[@value='Confirm password']/following-sibling::XCUIElementTypeSecureTextField")
    private WebElement confirmPasswordField;

    @AndroidFindBy(xpath = "//android.widget.CheckedTextView")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@value='I read agreaments and agree wit it']")
    private WebElement agreementText;

    @AndroidFindBy(id = "platkovsky.alexey.epamtestapp:id/register_new_account_button")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@value='Register new account']")
    private WebElement regNewAccountBtn;

    public RegistrationPageObject(AppiumDriver appiumDriver) {
        super(appiumDriver);
    }

    public RegistrationPageObject register(String email, String password) {
        emailField.sendKeys(email);
        passwordField.sendKeys(password);
        confirmPasswordField.sendKeys(password);
        // hide keyboard for android app on Sauce Labs
        if (System.getProperty("cloud").contains("Sauce Labs")
                && System.getProperty("platformName").contains("Android")) {
            driver.hideKeyboard();
        }
        agreementText.click();
        regNewAccountBtn.click();
        return this;
    }
}
