package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;



public class Login {

    private static final String baseURL = "https://www.saucedemo.com/";
    private static final By usernameLocator = By.id("user-name");
    private static final By passwordLocator = By.id("password");
    private static final By loginButtonLocator = By.id("login-button");
    private static final By loggedInUserText = By.xpath("//span[@class='title']");
    private static final String getLoggedInUserTextSample = "PRODUCTS";

    @Test
    public static void logInTest() {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        driver.get(baseURL);
        driver.manage().window().maximize();

        wait.until(ExpectedConditions.visibilityOf(driver.findElement(usernameLocator)));
        driver.findElement(usernameLocator).sendKeys("standard_user");

        wait.until(ExpectedConditions.visibilityOf(driver.findElement(passwordLocator)));
        driver.findElement(passwordLocator).sendKeys("secret_sauce");

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(loginButtonLocator)));
        driver.findElement(loginButtonLocator).click();

        wait.until(ExpectedConditions.visibilityOf(driver.findElement(loggedInUserText)));
        String loggedInUserTextValue = driver.findElement(loggedInUserText).getText();
        Assert.assertTrue(loggedInUserTextValue.contains(getLoggedInUserTextSample));

        driver.quit();
    }
}
