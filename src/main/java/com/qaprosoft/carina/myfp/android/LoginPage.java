package com.qaprosoft.carina.myfp.android;

import com.qaprosoft.carina.core.foundation.commons.SpecialKeywords;
import com.qaprosoft.carina.core.foundation.crypto.CryptoTool;
import com.qaprosoft.carina.core.foundation.utils.R;
import com.qaprosoft.carina.core.foundation.utils.factory.DeviceType;
import com.qaprosoft.carina.core.foundation.utils.mobile.IMobileUtils;
import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.myfp.common.LoginPageBase;
import com.qaprosoft.carina.myfp.common.StartPageBase;
import com.qaprosoft.carina.myfp.common.UserPageBase;
import com.qaprosoft.carina.myfp.utils.constants.TimeConstants;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.regex.Pattern;


@DeviceType(pageType = DeviceType.Type.ANDROID_PHONE, parentClass = LoginPageBase.class)
public class LoginPage extends LoginPageBase implements IMobileUtils, TimeConstants {

    private static final CryptoTool cryptoTool = new CryptoTool("./.settings/crypto.key");
    private static final Pattern CRYPTO_PATTERN = Pattern.compile(SpecialKeywords.CRYPT);
    private static final String EMAIL = cryptoTool.decryptByPattern(R.EMAIL.get("user1.email"), CRYPTO_PATTERN);
    private static final String PASSWORD = cryptoTool.decryptByPattern(R.EMAIL.get("user1.password"), CRYPTO_PATTERN);

    @FindBy(id = "com.myfitnesspal.android:id/email_address_edit")
    private ExtendedWebElement emailInputField;

    @FindBy(id = "com.myfitnesspal.android:id/password_edit")
    private ExtendedWebElement passwordInputField;

    @FindBy(id = "com.myfitnesspal.android:id/login_button")
    private ExtendedWebElement loginBtn;

    @FindBy(id = "com.myfitnesspal.android:id/forgot_password_button")
    private ExtendedWebElement forgotPasswordButton;

    @FindBy(id = "com.myfitnesspal.android:id/facebook_button")
    private ExtendedWebElement facebookButton;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isEmailFieldPresent() {
        return emailInputField.isElementPresent(THREE_SECONDS);
    }

    @Override
    public boolean isPasswordFieldPresent() {
        return passwordInputField.isElementPresent(THREE_SECONDS);
    }

    @Override
    public boolean isForgotPasswordButtonPresent() {
        return forgotPasswordButton.isElementPresent(THREE_SECONDS);
    }

    @Override
    public boolean isFacebookButtonPresent() {
        return facebookButton.isElementPresent(THREE_SECONDS);
    }

    @Override
    public boolean isLoginButtonPresent() {
        return loginBtn.isElementPresent(THREE_SECONDS);
    }

    @Override
    public void typeEmailAddress(String name) {
        emailInputField.type(name);
        hideKeyboard();
    }

    @Override
    public void typePassword(String password) {
        passwordInputField.type(password);
    }

    @Override
    public boolean isLoginButtonActive() {
        return false;
    }

    @Override
    public boolean isPageOpened() {
        return loginBtn.isElementPresent(THREE_SECONDS);
    }

    @Override
    public StartPageBase clickLogInButton() {
        loginBtn.click(THREE_SECONDS);
        return initPage(getDriver(), StartPageBase.class);
    }

    @Override
    public boolean isEmailAddressFieldBelowPasswordField() {
        return emailInputField.getLocation().getY() < passwordInputField.getLocation().getY();
    }

    @Override
    public boolean isPasswordFieldBelowLoginBtn() {
        return passwordInputField.getLocation().getY() < loginBtn.getLocation().getY();
    }

    @Override
    public String getEmailAddressFieldText() {
        return emailInputField.getText();
    }

    @Override
    public String getPasswordFieldText() {
        return passwordInputField.getText();
    }

    @Override
    public String getLogInText() {
        return loginBtn.getText();
    }

    @Override
    public String getForgotPasswordBtnText() {
        return forgotPasswordButton.getText();
    }

    @Override
    public String getFacebookBtnText() {
        return facebookButton.getText();
    }

    @Override
    public UserPageBase logIn() {
        emailInputField.type(EMAIL);
        passwordInputField.type(PASSWORD);
        loginBtn.click(SIXTY_SECONDS);
        waitUntil(ExpectedConditions.visibilityOf(loginBtn.getElement()), TEN_TIMEOUT);
        return initPage(getDriver(), UserPageBase.class);
    }

}






