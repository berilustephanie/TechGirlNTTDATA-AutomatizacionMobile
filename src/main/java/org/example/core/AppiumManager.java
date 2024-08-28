package org.example.core;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class AppiumManager {

    private static AppiumDriver<MobileElement> driver;
    private static Scenario scenario;

    public static AppiumDriver<MobileElement> getDriver() {
        return driver;
    }

    @Before(order = 0)
    public void setUp() {
        try {
            System.out.println("Inicializando el Appium driver...");

            Properties props = new Properties();
            // Carga propiedades desde un archivo o configura aquí directamente
            props.setProperty("appium.platformName", "Android");
            props.setProperty("appium.deviceName", "emulator-5556");
            props.setProperty("appium.platformVersion", "12L");
            props.setProperty("appium.automationName", "UiAutomator2");
            props.setProperty("appium.app", "C:\\Users\\piero\\Downloads\\mda-2.0.2-23.apk");
            props.setProperty("appium.appPackage", "com.saucelabs.mydemoapp.android");
            props.setProperty("appium.appActivity", "com.saucelabs.mydemoapp.android.view.activities.SplashActivity");
            props.setProperty("appium.autoGrantPermissions", "true");
            props.setProperty("appium.noReset", "true");

            // Configura las capacidades deseadas
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("platformName", "Android");
            capabilities.setCapability("deviceName", "emulator-5556");
            capabilities.setCapability("platformVersion", "12L"); // Asegúrate de que coincida con la versión de tu emulador
            capabilities.setCapability("app", "C:\\Users\\piero\\Downloads\\mda-2.0.2-23.apk");
            capabilities.setCapability("appPackage", "com.saucelabs.mydemoapp.android");
            capabilities.setCapability("appActivity", "com.saucelabs.mydemoapp.android.view.activities.SplashActivity");
            capabilities.setCapability("autoGrantPermissions", true);
            capabilities.setCapability("noReset", true);

            // Inicializa el driver
            driver = new AppiumDriver<MobileElement>(new URL("http://localhost:4723/wd/hub"), capabilities);
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            System.out.println("Driver inicializado correctamente.");
        } catch (MalformedURLException e) {
            System.err.println("La URL del servidor Appium está mal formada: " + e.getMessage());
            throw new RuntimeException(e);
        } catch (Exception e) {
            System.err.println("Error al inicializar el Appium driver: " + e.getMessage());
            throw e;
        }
    }

    @Before(order = 1)
    public static void setScenario(Scenario scenario){
        AppiumManager.scenario = scenario;
    }

    @After
    public void quitDriver(){
        if (driver != null) {
            driver.quit();
        }
    }

    public static void screenShot() {
        if (driver != null) {
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            try {
                File destinationFile = new File("images/screenshot.png");
                FileUtils.copyFile(screenshot, destinationFile);
                if (scenario != null) {
                    byte[] evidencia = FileUtils.readFileToByteArray(destinationFile);
                    scenario.attach(evidencia, "image/png", "evidencias");
                }
                System.out.println("Captura de pantalla guardada en: " + destinationFile.getAbsolutePath());
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Error al guardar la captura de pantalla.");
            }
        }
    }

}
