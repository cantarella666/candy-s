package com.mycompany.app;



import com.mycompany.app.StepDetinitions.CommonStepDetinition;
import com.mycompany.app.lib.Init;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;


import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static com.mycompany.app.lib.Init.*;

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

        //System.setProperty("browser", property.getProperty("browser"));
       /* browser = property.getProperty("browser");
        url = property.getProperty("url");*/
        //String strngBrowser = "browser";
        //String strngUrl = "url";
        Init.setStashElement(Stash, "browser", property.getProperty("browser"));
        Init.setStashElement(Stash, "url", property.getProperty("url"));
        //System.setProperty("url", property.getProperty("url"));

        /*browser = property.getProperty("db.browser");
        url = property.getProperty("db.url");*/
    }



    @Test
    public void Test() {
        CommonStepDetinition study = new CommonStepDetinition();

        study.openInsuranceTravelTest();
        /*study.choosePolic();
        study.formalizationConformation();
        study.minInsurance();
        study.enoughPrice();
        study.enoughResultPrice();
        study.withSportBlock();
        study.fullTestSportBlock();
        study.providentBlock();
        study.saveBag();*/
    }




    @AfterClass
    public static void postCondition() {
        System.out.println("3");
        getDriver().quit();
    }
}
