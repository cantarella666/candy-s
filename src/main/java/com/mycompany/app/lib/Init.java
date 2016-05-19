package com.mycompany.app.lib;
import jdk.internal.org.objectweb.asm.tree.analysis.Value;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.security.Key;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by cantarella on 17.05.2016.
 */
public class Init {

    private  static WebDriver driver;

    public static  WebDriver getDriver(){
        if (null == driver){
            createWebDriver();
        }
        return driver;

    }

    public static void setDriver(WebDriver driver){
        Init.driver = driver;
    }

    public static void setStashElement(HashMap Stash, String string, Object object){
        Init.Stash = Stash;
        Stash.put(string, object);

    }
    //public static Map Stash = new HashMap<String, Object>();
    public static HashMap Stash;
    public static HashMap getStash(){
        if (null == Stash)
            Stash = new HashMap();
        return Stash;
    }


public static void createWebDriver(){

    DesiredCapabilities capabilities = new DesiredCapabilities();
    //String strngBrowser = "browser";
    //String browser = Stash.get(strngBrowser).toString();
    switch (Stash.get("browser").toString()){
        case "firefox":
            capabilities.setBrowserName("firefox");
            setDriver((WebDriver) new FirefoxDriver(capabilities));
            break;
        case "chrome":
            File chromeDriver = new File("C:/Users/cantarella/my-app/candy-s/src/test/resources/webdrivers/chromedriver.exe");
            System.setProperty("webdriver.chrome.driver", chromeDriver.getAbsolutePath());
            capabilities.setBrowserName("chrome");
            setDriver(new ChromeDriver(capabilities));
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
