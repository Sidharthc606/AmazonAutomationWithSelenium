package com.amazon.automation;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class PreviousOrdersTest extends BaseTest {

    @Test
    public void verifyYourOrdersPage() {
        driver.get("https://www.amazon.com/");

        // Login
        login();

        // Navigate to Your Account page
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement accountLink = wait.until(ExpectedConditions.elementToBeClickable(By.id("nav-link-accountList-nav-line-1")));
        accountLink.click();

        // Wait for the account page to load
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[contains(text(),'Your Account')]")));

        // Click on Your Orders link using the provided full XPath
        WebElement yourOrdersLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/div[1]/div/div[2]/div[1]/a/div/div/div/div[2]/div/span")));
        yourOrdersLink.click();

        // Wait for the Your Orders page to load
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[contains(text(),'Your Orders')]")));

        // Verify that the Your Orders page has loaded
        WebElement ordersHeader = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[contains(text(),'Your Orders')]")));
        Assert.assertTrue(ordersHeader.isDisplayed(), "Your Orders page did not load correctly!");

        // Verify that at least one order is present
        boolean orderPresent = isElementPresent(By.xpath("/html/body/div[1]/section/div[1]/div[3]/ul/li[1]"));
        Assert.assertTrue(orderPresent, "No orders found in Your Orders page!");

        System.out.println("Your Orders page verified successfully, and orders are present.");
    }

    private boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    private void login() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.findElement(By.id("nav-link-accountList-nav-line-1")).click();
        WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ap_email")));
        emailField.sendKeys("sc558236@gmail.com"); // Replace with your email
        driver.findElement(By.id("continue")).click();
        WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ap_password")));
        passwordField.sendKeys("Welcome.7"); // Replace with your password
        driver.findElement(By.id("signInSubmit")).click();
    }
}
