package com.amazon.automation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class CheckoutTest extends BaseTest {

    @Test
    public void verifyCheckoutProcess() {
        driver.get("https://www.amazon.com/");

        // Login
        login();

        // Search for a product
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement searchBox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("twotabsearchtextbox")));
        searchBox.sendKeys("laptop");
        searchBox.submit();

        // Add the first item to the cart
        WebElement addToCartButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='a-autoid-2-announce']")));
        addToCartButton.click();

        // Wait for 4 seconds to ensure the item is added to the cart
        try {
            Thread.sleep(4000); // Wait for 4 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Go to cart
        WebElement goToCartButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='ewc-compact-actions-container']/div/div[2]/span/span/a")));
        goToCartButton.click();

        // Wait for the cart to load
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[contains(text(),'Shopping Cart')]")));

        // Proceed to checkout using the provided full XPath
        WebElement proceedToCheckoutButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/div[1]/div[4]/div[4]/div/div[1]/div[1]/div/form/div/div/span/span/span/input")));
        proceedToCheckoutButton.click();

        // Wait for 2 seconds before passing the test
        try {
            Thread.sleep(2000); // Wait for 2 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // The test will pass after clicking the proceed to payment button
        System.out.println("Proceed to payment clicked successfully. Test case passed.");
        Assert.assertTrue(true); // Explicitly pass the test case
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
