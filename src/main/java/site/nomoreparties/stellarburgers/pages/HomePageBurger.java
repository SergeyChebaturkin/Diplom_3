package site.nomoreparties.stellarburgers.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class HomePageBurger {

    private final WebDriver driver;
    private static final String URL = "https://stellarburgers.nomoreparties.site/";
    private final By logInAccountButton = By.xpath(".//button[text()='Войти в аккаунт']");
    private final By ingredientsTabs = By.xpath(".//div[contains(@class,'tab_tab__1SPyG')]");
    private final By ingredientHeadersList = By.xpath(".//h2[contains(@class,'mb-6')]");


    public HomePageBurger(WebDriver driver) {
        this.driver = driver;
    }

    public static String getUrl() {
        return URL;
    }

    public HomePageBurger open() {
        driver.get(URL);
        return this;
    }

    public HomePageBurger clickOnLogInAccountButton() {
        driver.findElement(logInAccountButton).click();
        return this;
    }

    public List<WebElement> getIngredientsTabs() {
        return driver.findElements(ingredientsTabs);
    }

    public List<WebElement> getIngredientHeaders() {
        return driver.findElements(ingredientHeadersList);
    }

}
