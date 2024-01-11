package site.nomoreparties.stellarburgers.tests;

import io.qameta.allure.Step;
import io.restassured.RestAssured;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import site.nomoreparties.stellarburgers.models.User;
import site.nomoreparties.stellarburgers.models.UserCreds;
import site.nomoreparties.stellarburgers.pages.HeaderPageBurger;
import site.nomoreparties.stellarburgers.pages.HomePageBurger;
import site.nomoreparties.stellarburgers.pages.LoginPageBurger;
import site.nomoreparties.stellarburgers.pages.ProfilePageBurger;
import site.nomoreparties.stellarburgers.rules.BrowserRule;
import site.nomoreparties.stellarburgers.user.UserClient;

import static site.nomoreparties.stellarburgers.user.UserGenerator.randomValidUser;

public class ProfileTest {

    @Rule
    public BrowserRule browserRule = new BrowserRule();

    @Test
    public void goToProfileFromHeaderButton() {
        WebDriver driver = browserRule.getWebDriver();
        HomePageBurger homePageBurger = new HomePageBurger(driver);
        homePageBurger.open();
        HeaderPageBurger headerPageBurger = new HeaderPageBurger(driver);
        headerPageBurger.clickProfileButton();
        Assert.assertEquals(LoginPageBurger.getUrl(), driver.getCurrentUrl());
        LoginPageBurger loginPageBurger = new LoginPageBurger(driver);
        String token = loginUser(loginPageBurger, driver);
        headerPageBurger.clickProfileButton();
        browserRule.getWait().until(ExpectedConditions.urlToBe(ProfilePageBurger.getUrl()));
        Assert.assertEquals(ProfilePageBurger.getUrl(), driver.getCurrentUrl());
        deleteUser(token);
    }

    @Test
    public void goToMainPageFromProfileByClickOnConstructorButton() {
        WebDriver driver = browserRule.getWebDriver();
        HomePageBurger homePageBurger = new HomePageBurger(driver);
        homePageBurger.open();
        HeaderPageBurger headerPageBurger = new HeaderPageBurger(driver);
        headerPageBurger.clickProfileButton();
        Assert.assertEquals(LoginPageBurger.getUrl(), driver.getCurrentUrl());
        LoginPageBurger loginPageBurger = new LoginPageBurger(driver);
        String token = loginUser(loginPageBurger, driver);
        headerPageBurger.clickProfileButton();
        browserRule.getWait().until(ExpectedConditions.urlToBe(ProfilePageBurger.getUrl()));
        Assert.assertEquals(ProfilePageBurger.getUrl(), driver.getCurrentUrl());
        headerPageBurger.clickConstructorButton();
        Assert.assertEquals(HomePageBurger.getUrl(), driver.getCurrentUrl());
        deleteUser(token);
    }

    @Test
    public void goToMainPageFromProfileByClickOnLogotype() {
        WebDriver driver = browserRule.getWebDriver();
        HomePageBurger homePageBurger = new HomePageBurger(driver);
        homePageBurger.open();
        HeaderPageBurger headerPageBurger = new HeaderPageBurger(driver);
        headerPageBurger.clickProfileButton();
        Assert.assertEquals(LoginPageBurger.getUrl(), driver.getCurrentUrl());
        LoginPageBurger loginPageBurger = new LoginPageBurger(driver);
        String token = loginUser(loginPageBurger, driver);
        headerPageBurger.clickProfileButton();
        browserRule.getWait().until(ExpectedConditions.urlToBe(ProfilePageBurger.getUrl()));
        Assert.assertEquals(ProfilePageBurger.getUrl(), driver.getCurrentUrl());
        headerPageBurger.clickLogoButton();
        Assert.assertEquals(HomePageBurger.getUrl(), driver.getCurrentUrl());
        deleteUser(token);
    }

    @Test
    public void logoutFromProfile() {
        WebDriver driver = browserRule.getWebDriver();
        HomePageBurger homePageBurger = new HomePageBurger(driver);
        homePageBurger.open();
        HeaderPageBurger headerPageBurger = new HeaderPageBurger(driver);
        headerPageBurger.clickProfileButton();
        Assert.assertEquals(LoginPageBurger.getUrl(), driver.getCurrentUrl());
        LoginPageBurger loginPageBurger = new LoginPageBurger(driver);
        String token = loginUser(loginPageBurger, driver);
        headerPageBurger.clickProfileButton();
        browserRule.getWait().until(ExpectedConditions.urlToBe(ProfilePageBurger.getUrl()));
        Assert.assertEquals(ProfilePageBurger.getUrl(), driver.getCurrentUrl());
        ProfilePageBurger profilePageBurger = new ProfilePageBurger(driver);
        profilePageBurger.clickLogoutButton();
        browserRule.getWait().until(ExpectedConditions.urlToBe(LoginPageBurger.getUrl()));
        Assert.assertEquals(LoginPageBurger.getUrl(), driver.getCurrentUrl());
        deleteUser(token);
    }

    @Step("login user and return token from local storage")
    public String loginUser(LoginPageBurger loginPageBurger, WebDriver driver) {
        UserCreds userCreds = registerUser();
        loginPageBurger
                .inputEmail(userCreds.getEmail())
                .inputPassword(userCreds.getPassword())
                .clickLoginButton();
        browserRule.getWait().until(ExpectedConditions.urlToBe(HomePageBurger.getUrl()));
        Assert.assertEquals(HomePageBurger.getUrl(), driver.getCurrentUrl());
        String token = receiveTokenFromLocalStorage(driver);
        Assert.assertNotNull(token);
        return token;
    }

    @Step("register user by API and return its creds")
    public UserCreds registerUser() {
        RestAssured.baseURI = HomePageBurger.getUrl();
        User user = randomValidUser();
        UserClient userClient = new UserClient();
        userClient.register(user);
        return UserCreds.fromUser(user);
    }

    @Step("delete user by API")
    public void deleteUser(String token) {
        RestAssured.baseURI = HomePageBurger.getUrl();
        UserClient userClient = new UserClient();
        userClient.delete(token);
    }

    @Step("receive token from local storage")
    public String receiveTokenFromLocalStorage(WebDriver driver) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        return (String) js.executeScript(String.format(
                "return window.localStorage.getItem('%s');", "accessToken"));
    }
}
