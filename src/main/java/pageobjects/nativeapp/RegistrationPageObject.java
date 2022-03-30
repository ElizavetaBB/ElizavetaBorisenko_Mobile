package pageobjects.nativeapp;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

public class RegistrationPageObject extends NativePageObject {

    @AndroidFindBy(id = "platkovsky.alexey.epamtestapp:id/registration_email")
    private WebElement emailField;

    @AndroidFindBy(id = "platkovsky.alexey.epamtestapp:id/registration_password")
    private WebElement passwordField;

    @AndroidFindBy(id = "platkovsky.alexey.epamtestapp:id/registration_confirm_password")
    private WebElement confirmPasswordField;

    @AndroidFindBy(id = "platkovsky.alexey.epamtestapp:id/register_new_account_button")
    private WebElement regNewAccountBtn;

    public RegistrationPageObject(AppiumDriver appiumDriver) {
        super(appiumDriver);
    }

    public RegistrationPageObject register(String email, String password) {
        emailField.sendKeys(email);
        passwordField.sendKeys(password);
        confirmPasswordField.sendKeys(password);
        regNewAccountBtn.click();
        return this;
    }
}
