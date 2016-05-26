package lib;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.IOException;
import java.util.HashMap;

import java.util.concurrent.TimeUnit;

import static stepDefinitions.CommonStepDefinition.beforeClass;


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

    public static void clearStash(){
        Stash.clear();
    }

    public static void createWebDriver() {

        DesiredCapabilities capabilities = new DesiredCapabilities();
        try {
            beforeClass();
        } catch (IOException e) {
            e.printStackTrace();
        }
        switch (getStash().get("browser").toString()) {
            case "firefox":
                capabilities.setBrowserName("firefox");
                setDriver(new FirefoxDriver(capabilities));
                break;
            case "chrome":
                System.setProperty("webdriver.chrome.driver", getStash().get("filechrome").toString());
                setDriver(new ChromeDriver(capabilities));
                break;
            case "ie":
                System.setProperty("webdriver.internetexplorer.driver", getStash().get("fileie").toString());
                setDriver(new InternetExplorerDriver(capabilities));
                break;
            default:
                System.out.println("wtf");

        }
        driver.manage().timeouts().pageLoadTimeout(120, TimeUnit.SECONDS);
        driver.manage().window().maximize();

    }
}
