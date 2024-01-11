package site.nomoreparties.stellarburgers.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPageBurger {

    private final WebDriver driver;
    private static final String URL = "https://stellarburgers.nomoreparties.site/login";

    private final By emailField = By.xpath(".//fieldset[1]//input");
    private final By passwordField = By.xpath(".//fieldset[2]//input");
    private final By loginButton = By.xpath(".//button[text()='Войти']");

    public LoginPageBurger(WebDriver driver) {
        this.driver = driver;
    }

    public static String getUrl() {
        return URL;
    }

    public LoginPageBurger inputEmail(String email) {
        driver.findElement(emailField).sendKeys(email);
        return this;
    }

    public LoginPageBurger inputPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
        return this;
    }

    public LoginPageBurger clickLoginButton() {
        driver.findElement(loginButton).click();
        return this;
    }
}
