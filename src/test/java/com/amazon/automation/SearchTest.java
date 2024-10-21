package com.amazon.automation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SearchTest extends BaseTest {

    @Test
    public void searchForProduct() {
        driver.get("https://www.amazon.com/");

        // Locate the search box
        WebElement searchBox = driver.findElement(By.id("twotabsearchtextbox"));
        searchBox.sendKeys("laptop");
        searchBox.submit();

        // Verify results
        String expectedTitle = "Amazon.com : laptop";
        String actualTitle = driver.getTitle();
        Assert.assertTrue(actualTitle.contains("laptop"), "Search results did not load correctly.");
        System.out.println("Search functionality works as expected.");
    }
}
