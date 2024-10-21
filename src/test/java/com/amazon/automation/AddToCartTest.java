package com.amazon.automation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class AddToCartTest extends BaseTest {

    @Test
    public void addItemToCart() {
        driver.get("https://www.amazon.com/");

        // Use WebDriverWait to wait for the search box to be visible
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Search for a product
        WebElement searchBox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("twotabsearchtextbox")));
        searchBox.sendKeys("laptop");
        searchBox.submit();

        // Wait for the search results to load and display
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("search")));

        // Locate the "Add to Cart" button directly in the search results using its XPath
        WebElement addToCartButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//*[@id='a-autoid-2-announce'])[1]"))); // Updated to target the first product's button
        addToCartButton.click();

        // Wait for the cart icon to become visible to verify the item is added
        WebElement cartCount = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='ewc-compact-actions-container']/div/div[2]/span/span/a")));

        // Verify that the cart icon is visible
        Assert.assertTrue(cartCount.isDisplayed(), "Cart is not visible!");
        System.out.println("Item added to cart successfully, and the cart is visible.");
    }
}
