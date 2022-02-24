package com.doctor.base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import util.WebdriverListener;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class TestBase {
    public static WebDriver driver;
    public static Properties props = new Properties();

//    connect the TestBase class with WebDriverListener class
    public static EventFiringWebDriver e_driver;
    public static WebdriverListener webdriverListener;

    public TestBase(){
        try{
            File file = new File("src/main/java/properties/config.properties");
            FileInputStream fis = new FileInputStream(file);
            props.load(fis);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void launchBrowser(){
        if(props.getProperty("browser").equalsIgnoreCase("chrome")){
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        }else if(props.getProperty("browser").equalsIgnoreCase("firefox")){
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        }else {
            throw new Error("browser not supported");
        }
        // connecting the listener to e_driver
        e_driver = new EventFiringWebDriver(driver);
        webdriverListener = new WebdriverListener();
        e_driver.register(webdriverListener);
        driver = e_driver;

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(6000, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(6000, TimeUnit.SECONDS);
        driver.get(props.getProperty("URL"));
    }
}
