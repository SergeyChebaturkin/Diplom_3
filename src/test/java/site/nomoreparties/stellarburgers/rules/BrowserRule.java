package site.nomoreparties.stellarburgers.rules;

import org.junit.rules.ExternalResource;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.io.File;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.UUID;

public class BrowserRule extends ExternalResource {

    private WebDriver webDriver;
    private Wait<WebDriver> wait;

    public WebDriver getWebDriver() {
        return webDriver;
    }

    public Wait<WebDriver> getWait() {
        return wait;
    }

    @Override
    protected void before() {

        ChromeOptions options= new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--auto-open-devtools-for-tabs");
        webDriver = new ChromeDriver(options);
        webDriver.manage().window().maximize();
        wait = new FluentWait<>(webDriver)
                .withTimeout(Duration.ofSeconds(3))
                .pollingEvery(Duration.ofMillis(100))
                .ignoring(NoSuchElementException.class);
    }

    @Override
    protected void after() {
        File sceFile = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.FILE);
        DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy__hh_mm_ss__");
        String fileName = UUID.randomUUID().toString();
        File targetFile = new File("target/screenshots/" + dateFormat.format(new Date()) + fileName + ".jpg");
        try {
            FileUtils.copyFile(sceFile, targetFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        webDriver.quit();
    }

}
