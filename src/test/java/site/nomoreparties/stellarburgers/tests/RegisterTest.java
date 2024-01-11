package site.nomoreparties.stellarburgers.tests;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import site.nomoreparties.stellarburgers.pages.LoginPageBurger;
import site.nomoreparties.stellarburgers.pages.RegisterPageBurger;
import site.nomoreparties.stellarburgers.rules.BrowserRule;

import static site.nomoreparties.stellarburgers.datafaker.UserDataFaker.*;

public class RegisterTest {

    @Rule
    public BrowserRule browserRule = new BrowserRule();

    @Test
    public void registerUser() {
        WebDriver driver = browserRule.getWebDriver();
        RegisterPageBurger registerPageBurger = new RegisterPageBurger(driver);
        registerPageBurger
                .open()
                .inputName(randomValidName())
                .inputEmail(randomValidEmail())
                .inputPassword(randomValidPassword())
                .clickRegisterButton();
        browserRule.getWait().until(ExpectedConditions.urlToBe(LoginPageBurger.getUrl()));
        Assert.assertEquals(LoginPageBurger.getUrl(), driver.getCurrentUrl());
    }

    @Test
    public void registerUserWithInvalidPassword() {
        WebDriver driver = browserRule.getWebDriver();
        RegisterPageBurger registerPageBurger = new RegisterPageBurger(driver);
        registerPageBurger
                .open()
                .inputName(randomValidName())
                .inputEmail(randomValidEmail())
                .inputPassword(randomInvalidPassword())
                .clickRegisterButton();
        Assert.assertEquals("Некорректный пароль", registerPageBurger.passwordFieldErrorMessage());
    }
}