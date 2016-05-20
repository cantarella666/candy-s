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
        System.out.println("test1");
        Properties property = new Properties();
        property.load(new FileInputStream("src/test/java/config/application.properties"));
        Stash = getStash();
        setStashElement("browser", property.getProperty("browser"));
        setStashElement("url", property.getProperty("url"));
        setStashElement("webdriver.chrome.driver", property.getProperty("webdriver.chrome.driver"));
        setStashElement("base", property.getProperty("base"));
        setStashElement("dost", property.getProperty("dost"));
        setStashElement("dostSport", property.getProperty("dostSport"));
        setStashElement("sport", property.getProperty("sport"));
        setStashElement("dostSportPred", property.getProperty("dostSportPred"));
        setStashElement("dostPredBag", property.getProperty("dostPredBag"));

    }



    @Test
    public void Test() throws InterruptedException {
        CommonStepDetinition study = new CommonStepDetinition();


        study.openInsuranceTravelTest();
        study.choosePolic();
        study.formalizationConformation();
        study.minInsurance();
        study.enoughPrice();
        study.enoughResultPrice();
        study.withSportBlock();
        study.fullTestSportBlock();
        study.providentBlock();
        study.saveBag();
    }




    @AfterClass
    public static void postCondition() {
        getDriver().close();
    }
}
