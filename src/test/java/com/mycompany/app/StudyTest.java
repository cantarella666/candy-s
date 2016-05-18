package com.mycompany.app;


import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.NoSuchElementException;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static com.mycompany.app.lib.Init.getDriver;


/**
 * Created by cantarella on 14.05.2016.
 */
public class StudyTest {
    public static String url;
    public static String browser;

    @BeforeClass
    public static void BeforeClass() throws IOException {
        Properties property = new Properties();

        System.out.println("test1");
        property.load(new FileInputStream("src/test/java/config/application.properties"));
        System.setProperty("browser", browser);
        browser = property.getProperty("db.browser");
        url = property.getProperty("db.url");

        /*browser = property.getProperty("db.browser");
        url = property.getProperty("db.url");*/
    }



    @Test
    public void Test() {

        getDriver().get(url);
        getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

    }


    @AfterClass
    public static void postCondition() {
        System.out.println("3");
        getDriver().quit();
    }
}
