package com.mycompany.app;

import com.mycompany.app.pages.CurrenncyConverter;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;


import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import static com.mycompany.app.lib.Init.*;

/**
 * Created by cantarella on 14.05.2016.
 */
public class StudyTest {

    @BeforeClass
    public static void BeforeClass() throws IOException {
        System.out.println("test1");
        Properties property = new Properties();
        property.load(new FileInputStream("src/test/java/config/application.properties"));
        Stash = getStash();
        setStashElement("browser", property.getProperty("browser"));
        setStashElement("url", property.getProperty("url"));
        setStashElement("filechrome", property.getProperty("filechrome"));
        setStashElement("fileie", property.getProperty("fileie"));
        setStashElement("base", property.getProperty("base"));
        setStashElement("dost", property.getProperty("dost"));
        setStashElement("dostSport", property.getProperty("dostSport"));
        setStashElement("sport", property.getProperty("sport"));
        setStashElement("dostSportPred", property.getProperty("dostSportPred"));
        setStashElement("dostPredBag", property.getProperty("dostPredBag"));

    }



    @Test
    public void Test() throws InterruptedException {

        getDriver().get(getStash().get("url").toString());
        TimeUnit.SECONDS.sleep(20);
        CurrenncyConverter currenncyConverter = new CurrenncyConverter();
        currenncyConverter.searchATM();
        currenncyConverter.open();
        currenncyConverter.branchSelected();
        currenncyConverter.branchNotNull();
        currenncyConverter.allBranches();
        currenncyConverter.terminal();
        currenncyConverter.allLocationOne();

    }




    @AfterClass
    public static void postCondition() {
        clearStash();
        getDriver().close();
    }
}
