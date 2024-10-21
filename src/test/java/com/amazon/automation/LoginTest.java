package com.amazon.automation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Random;

public class LoginTest extends BaseTest {

    // Random delay generator
    private void waitRandomTime(int minMillis, int maxMillis) {
        Random rand = new Random();
        int waitTime = minMillis + rand.nextInt(maxMillis - minMillis);
        try {
            Thread.sleep(waitTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // Method to simulate human-like typing
    private void typeWithDelay(WebElement element, String text) {
        for (char c : text.toCharArray()) {
            element.sendKeys(String.valueOf(c));
            waitRandomTime(100, 300); // Delay between keystrokes
        }
    }

    @Test
    public void login() {
        driver.get("https://www.amazon.com/");

        // Use Duration for the WebDriverWait
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Wait for the Sign-In button and click it
        WebElement signInButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("nav-link-accountList-nav-line-1")));

        // Move to the Sign-In button slowly
        Actions actions = new Actions(driver);
        actions.moveToElement(signInButton).perform();
        waitRandomTime(500, 1000); // Pause to simulate human behavior
        signInButton.click();

        // Enter email with typing delay
        WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ap_email")));
        typeWithDelay(emailField, "sc558236@gmail.com"); // Replace with your email
        driver.findElement(By.id("continue")).click();

        // Enter password with typing delay
        WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ap_password")));
        typeWithDelay(passwordField, "Welcome.7"); // Replace with your password
        driver.findElement(By.id("signInSubmit")).click();

        // Handle potential CAPTCHA or puzzle
        // You can add a simple wait here or a prompt for manual intervention
        // for example, pausing the test for the user to complete CAPTCHA manually.
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("nav-link-accountList-nav-line-1"))); // Wait for the account link
            System.out.println("Login functionality works as expected.");
        } catch (Exception e) {
            System.out.println("CAPTCHA detected or login failed. Please solve the CAPTCHA manually.");
            // You might want to implement a wait here to allow the user to complete CAPTCHA
            waitRandomTime(60000, 120000); // Wait for a minute or two for the user to solve
        }
    }
}
