package site.nomoreparties.stellarburgers.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegisterPageBurger {

    private final WebDriver driver;
    private static final String URL = "https://stellarburgers.nomoreparties.site/register";
    private final By nameField = By.xpath(".//fieldset[1]//input");
    private final By emailField = By.xpath(".//fieldset[2]//input");
    private final By passwordField = By.xpath(".//fieldset[3]//input");
    private final By registerButton = By.xpath(".//button[text()='Зарегистрироваться']");
    private final By passwordFieldErrorMessage = By.xpath(".//fieldset[3]//p");
    private final By loginLink = By.xpath(".//a[@href='/login']");

    public RegisterPageBurger(WebDriver driver) {
        this.driver = driver;
    }

    public RegisterPageBurger open() {
        driver.get(URL);
        return this;
    }

    public RegisterPageBurger inputName(String name) {
        driver.findElement(nameField).sendKeys(name);
        return this;
    }

    public RegisterPageBurger inputEmail(String email) {
        driver.findElement(emailField).sendKeys(email);
        return this;
    }

    public RegisterPageBurger inputPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
        return this;
    }

    public RegisterPageBurger clickRegisterButton() {
        driver.findElement(registerButton).click();
        return this;
    }

    public RegisterPageBurger clickLoginLink() {
        driver.findElement(loginLink).click();
        return this;
    }

    public String passwordFieldErrorMessage() {
        return driver.findElement(passwordFieldErrorMessage).getText();
    }
}
