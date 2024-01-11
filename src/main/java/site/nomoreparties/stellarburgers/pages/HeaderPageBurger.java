package site.nomoreparties.stellarburgers.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HeaderPageBurger {

    private final WebDriver driver;
    private final By profileButton = By.xpath(".//a[@href='/account']");
    private final By constructorButton = By.xpath(".//a[@class='AppHeader_header__link__3D_hX' and @href='/']");
    private final By logoButton = By.xpath(".//div[@class='AppHeader_header__logo__2D0X2']/a");

    public HeaderPageBurger(WebDriver driver) {
        this.driver = driver;
    }

    public HeaderPageBurger clickProfileButton() {
        driver.findElement(profileButton).click();
        return this;
    }

    public HeaderPageBurger clickConstructorButton() {
        driver.findElement(constructorButton).click();
        return this;
    }

    public HeaderPageBurger clickLogoButton() {
        driver.findElement(logoButton).click();
        return this;
    }
}
