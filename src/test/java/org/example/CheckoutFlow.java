package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class CheckoutFlow {

    private static final String baseURL = "https://www.saucedemo.com/";
    private static final By usernameLocator = By.id("user-name");
    private static final By passwordLocator = By.id("password");
    private static final By loginButtonLocator = By.id("login-button");
    private static final By addToCartBackpackButtonLocator = By.id("add-to-cart-sauce-labs-backpack");
    private static final By shoppingCartIconLocator = By.id("shopping_cart_container");
    private static final By checkoutButtonLocator = By.id("checkout");
    private static final By firstNameLocator = By.id("first-name");
    private static final By lastNameLocator = By.id("last-name");
    private static final By postalCodeLocator = By.id("postal-code");
    private static final By continueButtonLocator = By.id("continue");
    private static final By finishButtonLocator = By.id("finish");
    private static final By checkoutSuccessTextLocator = By.xpath("//h2[@class='complete-header']");
    private static final String checkoutSuccessTextSample = "THANK YOU FOR YOUR ORDER";

    @Test
    public static void checkoutFlowTest() {
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

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(addToCartBackpackButtonLocator)));
        driver.findElement(addToCartBackpackButtonLocator).click();

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(shoppingCartIconLocator)));
        driver.findElement(shoppingCartIconLocator).click();

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(checkoutButtonLocator)));
        driver.findElement(checkoutButtonLocator).click();

        wait.until(ExpectedConditions.visibilityOf(driver.findElement(firstNameLocator)));
        driver.findElement(firstNameLocator).sendKeys("Test");

        wait.until(ExpectedConditions.visibilityOf(driver.findElement(lastNameLocator)));
        driver.findElement(lastNameLocator).sendKeys("User");

        wait.until(ExpectedConditions.visibilityOf(driver.findElement(postalCodeLocator)));
        driver.findElement(postalCodeLocator).sendKeys("2233");

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(continueButtonLocator)));
        driver.findElement(continueButtonLocator).click();

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(finishButtonLocator)));
        driver.findElement(finishButtonLocator).click();

        wait.until(ExpectedConditions.visibilityOf(driver.findElement(checkoutSuccessTextLocator)));
        String checkoutSuccessTextValue = driver.findElement(checkoutSuccessTextLocator).getText();
        Assert.assertTrue(checkoutSuccessTextValue.contains(checkoutSuccessTextSample));

        driver.quit();

    }
}
