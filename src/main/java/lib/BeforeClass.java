package lib;

import org.junit.Before;
import org.openqa.selenium.WebDriver;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by cantarella on 18.05.2016.
 */
public class BeforeClass {
    public static String url;
    public static String browser;
    public static Properties property;

/*    public static String getUrl(){
        if (null == url){
            createUrl();
        }
        return url;
    }

    public static String getBrowser(){
        if (null == browser){
            createBrowser();
        }
        return browser;
    }

    public static void setBrowser(String browser){
        BeforeClass.browser = browser;

    }

    public static void setUrl(String url){
        BeforeClass.url = url;
    }*/


    @org.junit.BeforeClass
    public static void BeforeClass() throws IOException{
        Properties property = new Properties();

        browser = property.getProperty("browser");
        url = property.getProperty("url");
        System.out.println("test1");
        property.load(new FileInputStream("src/test/java/config/application.properties"));
        System.setProperty("browser", "url");

    }

    public static void createUrl(){
        url = property.getProperty("db.url");
    }

    public static void createBrowser(){
        url = property.getProperty("db.url");
    }






}
