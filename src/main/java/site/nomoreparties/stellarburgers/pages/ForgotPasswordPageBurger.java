package site.nomoreparties.stellarburgers.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ForgotPasswordPageBurger {
    private final WebDriver driver;
    private static final String URL = "https://stellarburgers.nomoreparties.site/forgot-password";
    private final By loginLink = By.xpath(".//a[@href='/login']");

    public ForgotPasswordPageBurger(WebDriver driver) {
        this.driver = driver;
    }

    public ForgotPasswordPageBurger open() {
        driver.get(URL);
        return this;
    }

    public ForgotPasswordPageBurger clickLoginLink() {
        driver.findElement(loginLink).click();
        return this;
    }
}
