package com.amazon.automation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class RemoveFromCartTest extends BaseTest {

    @Test
    public void removeItemFromCart() {
        driver.get("https://www.amazon.com/");

        // Login
        login();

        // Search for a product
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement searchBox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("twotabsearchtextbox")));
        searchBox.sendKeys("laptop");
        searchBox.submit();

        // Add the first item to the cart using the provided XPath
        WebElement addToCartButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='a-autoid-2-announce']")));
        addToCartButton.click();

        // Wait for 4 seconds to ensure the item is added to the cart
        try {
            Thread.sleep(4000); // Wait for 4 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Go to cart using the provided XPath
        WebElement goToCartButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='ewc-compact-actions-container']/div/div[2]/span/span/a")));
        goToCartButton.click();

        // Wait for the cart to load
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[contains(text(),'Shopping Cart')]")));

        // Remove the item from the cart using the full XPath provided
        WebElement removeButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/div[1]/div[4]/div[4]/div/div[2]/div[1]/div/form/div[2]/div[3]/div[4]/div/div[3]/div[1]/span[2]/span/input")));
        removeButton.click();

        // Wait for 2 seconds after removing the item
        try {
            Thread.sleep(2000); // Wait for 2 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Verify that the cart is empty using the provided XPath
        WebElement emptyCartMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='sc-active-cart']/div/div/div/h2")));
        Assert.assertTrue(emptyCartMessage.isDisplayed(), "Cart is not empty!");

        System.out.println("Item removed from cart successfully and cart is empty.");
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
