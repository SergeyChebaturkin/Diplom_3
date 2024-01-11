package site.nomoreparties.stellarburgers.tests;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import site.nomoreparties.stellarburgers.pages.HomePageBurger;
import site.nomoreparties.stellarburgers.rules.BrowserRule;

import java.util.List;

public class ConstructorTest {

    @Rule
    public BrowserRule browserRule = new BrowserRule();

    @Test
    public void scrollIngredientsListByClickingTabs() throws InterruptedException {
        WebDriver driver = browserRule.getWebDriver();
        HomePageBurger homePageBurger = new HomePageBurger(driver);
        homePageBurger.open();
        List<WebElement> ingredientHeaders = homePageBurger.getIngredientHeaders();
        int coordinateY = 0;
        List<WebElement> tabs = homePageBurger.getIngredientsTabs();
        for (int i = tabs.size() - 1; i >= 0; i--) {
            WebElement currentTab = tabs.get(i);
            WebElement currentHeader = ingredientHeaders.get(i);
            currentTab.click();
            Assert.assertTrue(currentTab.getAttribute("class").contains("noselect"));
            if (coordinateY == 0) {
                Thread.sleep(1000);
                coordinateY = currentHeader.getLocation().getY();
                continue;
            }
            int finalCoordinateY = coordinateY;
            browserRule.getWait().until(d -> currentHeader.getLocation().getY() == finalCoordinateY);
            Assert.assertEquals(coordinateY, currentHeader.getLocation().getY());
        }
    }
}
