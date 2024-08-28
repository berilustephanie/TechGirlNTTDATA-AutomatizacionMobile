package org.example.steps;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.example.core.AppiumManager;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SauceLabsStep {

    AppiumDriver<MobileElement> driver;

    public SauceLabsStep() {
        this.driver = AppiumManager.getDriver();
    }

    public boolean validateScreen(){
        By locator = By.id("com.saucelabs.mydemoapp.android:id/mTvTitle");
        System.out.println("Validando visibilidad del elemento: " + locator);
        try {
            WebDriverWait wait = new WebDriverWait(driver, 30);
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            boolean isDisplayed = driver.findElement(locator).isDisplayed();
            System.out.println("Elemento visible: " + isDisplayed);
            return isDisplayed;
        } catch (TimeoutException e) {
            System.err.println("El elemento no está visible después de 30 segundos: " + e.getMessage());
            return false;
        }
    }

}
