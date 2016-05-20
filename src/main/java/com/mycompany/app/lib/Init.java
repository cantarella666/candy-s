package com.mycompany.app.lib;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;

import java.util.HashMap;

import java.util.concurrent.TimeUnit;


public class Init {

    private static WebDriver driver;

    public static WebDriver getDriver() {
        if (null == driver) {
            createWebDriver();
        }
        return driver;

    }

    public static void setDriver(WebDriver driver) {
        Init.driver = driver;
    }


    public static HashMap Stash;

    public static HashMap getStash() {
        if (null == Stash)
            Stash = new HashMap();
        return Stash;
    }

    public static void setStashElement(Object object1, Object object2) {
        Stash.put(object1, object2);


    }


    public static void createWebDriver() {

        DesiredCapabilities capabilities = new DesiredCapabilities();

        switch (getStash().get("browser").toString()) {
            case "firefox":
                capabilities.setBrowserName("firefox");
                setDriver(new FirefoxDriver(capabilities));
                break;
            case "chrome":
                /*File chromeDriver = new File("C:/Users/cantarella/my-app/candy-s/src/test/resources/webdrivers/chromedriver.exe");
                System.setProperty("webdriver.chrome.driver", chromeDriver.getAbsolutePath());*/
                /*capabilities.setCapability("webdriver.chrome.driver", getStash().get("webdriver.chrome.driver"));
                setDriver(new ChromeDriver(capabilities));*/
                /*HashMap<String, Object> chromeOptions = new HashMap<String, Object>();
                chromeOptions.put("binary", "/usr/lib/chromium-browser/chromium-browser");
                DesiredCapabilities capabilities = DesiredCapabilities.chrome();
                capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
                WebDriver driver = new ChromeDriver(capabilities);*/
                break;
            case "ie":
                File IEDriver = new File("C:/Users/cantarella/my-app/candy-s/src/test/resources/webdrivers/IEDriverServer.exe");
                System.setProperty("webdriver.ie.driver", IEDriver.getAbsolutePath());
                capabilities.setBrowserName("internet explorer");
                setDriver(new InternetExplorerDriver(capabilities));
                break;
            default:
                System.out.println("wtf");

        }
        driver.manage().timeouts().pageLoadTimeout(120, TimeUnit.SECONDS);
        driver.manage().window().maximize();

    }
}
