package site.nomoreparties.stellarburgers.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProfilePageBurger {
    private final WebDriver driver;
    private static final String URL = "https://stellarburgers.nomoreparties.site/account/profile";
    private final By logoutButton = By.xpath(".//button[text()='Выход']");

    public ProfilePageBurger(WebDriver driver) {
        this.driver = driver;
    }

    public static String getUrl() {
        return URL;
    }

    public ProfilePageBurger clickLogoutButton() {
        driver.findElement(logoutButton).click();
        return this;
    }
}
