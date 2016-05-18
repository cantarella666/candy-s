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
import static lib.Init.getDriver;


/**
 * Created by cantarella on 14.05.2016.
 */
public class StudyTest {
    public static String url;
    public static String browser;

    @BeforeClass
    public static void BeforeClass() throws IOException {
        Properties property = new Properties();

        browser = property.getProperty("browser");
        url = property.getProperty("url");
        System.out.println("test1");
        property.load(new FileInputStream("src/test/java/config/application.properties"));
        System.setProperty("browser", browser);

        browser = property.getProperty("db.browser");
        url = property.getProperty("db.url");

    }



    @Test
    public void Test() {

        getDriver().get(url);
        getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        //проверка заголовка
        //#1
        String stringTitle = getDriver().findElement(By.xpath("html/body/div[1]/header/div/h2"))
                .getAttribute("innerHTML");
        assertEquals("Страхование&nbsp;путешественников", stringTitle);

        //#2
        //проверка полей
        //регион действия полиса
        String stringRegion = getDriver().findElement(By.cssSelector(".ng-binding.ng-scope.b-dropdown-title"))
                .getAttribute("innerHTML");
        assertEquals("Весь&nbsp;мир,&nbsp;кроме&nbsp;США&nbsp;и&nbsp;РФ", stringRegion);

        //проверка интервала дат
        String stingStartDate = getDriver().findElement(By.name("startDate"))
                .getText();
        assertNotNull(stingStartDate);


        String stingFinishDate = getDriver().findElement(By.id("finishDate"))
                .getText();
        assertNotNull(stingFinishDate);

        //время действия полиса
        String stringIntervalDate = getDriver().findElement(By.xpath(".//*[@id='views']/form/section/section/section[1]/div[5]/fieldset/span[1]/input"))
                .getAttribute("innerHTML");
        assertEquals("15", stringIntervalDate);

        //количество людей для страховки
        String stringAdult = getDriver().findElement(By.xpath(".//*[@id='views']/form/section/section/section[1]/div[7]/fieldset[1]/div/input"))
                .getAttribute("innerHTML");
        assertEquals("1", stringAdult);


        String stringBaby = getDriver().findElement(By.xpath(".//*[@id='views']/form/section/section/section[1]/div[7]/fieldset[2]/div/input"))
                .getAttribute("innerHTML");
        assertEquals("0", stringBaby);


        String stringOld1 = getDriver().findElement(By.xpath(".//*[@id='views']/form/section/section/section[1]/div[7]/fieldset[3]/div/input"))
                .getAttribute("innerHTML");
        assertEquals("0", stringOld1);

        //выбор минимального тарифа
        WebElement minBlock = getDriver().findElement(By.xpath(".//*[@id='views']/form/section/section/section[2]/div[1]/div[1]/div"));
        minBlock.click();

        //проверка галочек "рекомендуется предусмотреть", снятие всех
        try {
            WebElement block1 = getDriver().findElement(By.xpath("./[@id='views']/form/section/section/section[3]/div/div[1]/span[@class='b-form-prog-box-check-pos b-checked-checkbox-field']/"));
            block1.click();
        } catch (NoSuchElementException e) {
        }

        try {
            WebElement block2 = getDriver().findElement(By.xpath("./[@id='views']/form/section/section/section[3]/div/div[2]/span[@class='b-form-prog-box-check-pos b-checked-checkbox-field']"));
            block2.click();
        } catch (NoSuchElementException e) {
        }

        try {
            WebElement block3 = getDriver().findElement(By.xpath("./[@id='views']/form/section/section/section[3]/div/div[3]/span[@class='b-form-prog-box-check-pos b-checked-checkbox-field']"));
            block3.click();
        } catch (NoSuchElementException e) {
        }

        try {
            WebElement block4 = getDriver().findElement(By.xpath("./[@id='views']/form/section/section/section[3]/div/div[4]/span[@class='b-form-prog-box-check-pos b-checked-checkbox-field']"));
            block4.click();
        } catch (NoSuchElementException e) {
        }

        try {
            WebElement block5 = getDriver().findElement(By.xpath("./[@id='views']/form/section/section/section[3]/div/div[5]/span[@class='b-form-prog-box-check-pos b-checked-checkbox-field']"));
            block5.click();
        } catch (NoSuchElementException e) {
        }

        //#3
        try {
            WebElement formalization = getDriver().findElement(By.xpath("html/body/div[1]/div[2]/div/ul/li[2]"));
            formalization.click();
        } catch (NoSuchElementException e) {
        }

        try {
            WebElement confirmation = getDriver().findElement(By.xpath("html/body/div[1]/div[2]/div/ul/li[3]"));
            confirmation.click();
        } catch (NoSuchElementException e) {
        }

        //#4
        String valueOfResult = getDriver().findElement(By.xpath("./[@id='views']/form/section/section/section[5]/div/dl[2]/dd[1]/span[1]"))
                .getAttribute("innerHTML");
        assertEquals("828,28&nbsp", valueOfResult);
        //минимальная стоимость стаховки уменьшилась после написания вами тест-кейса, она теперь не 850,26


        //#5
        WebElement middle = getDriver().findElement(By.xpath("./[@id='views']/form/section/section/section[5]/div/dl[2]/dd[1]/span[1]"));
        middle.click();
        try {
            WebElement middle2 = getDriver().findElement(By.xpath("./[@id='views']/form/section/section/section[2]/div[1]/div[2]/div/span[@class='b-form-prog-box-check-pos b-checked-checkbox-field']"));
        } catch (NoSuchElementException e) {
        }


        //#6
        valueOfResult = getDriver().findElement(By.xpath("./[@id='views']/form/section/section/section[5]/div/dl[2]/dd[1]/span[1]"))
                .getAttribute("innerHTML");
        assertEquals("1&nbsp;115,42&nbsp", valueOfResult);
        //минимальная стоимость стаховки уменьшилась после написания вами тест-кейса, она теперь не 1 145,02


        //#7
        WebElement sportBlock = getDriver().findElement(By.xpath("./[@id='views']/form/section/section/section[3]/div/div[1]"));
        sportBlock.click();
        valueOfResult = getDriver().findElement(By.xpath("./[@id='views']/form/section/section/section[5]/div/dl[2]/dd[1]/span[1]"))
                .getAttribute("innerHTML");
        //Float vlOfRslt = Float.valueOf(valueOfResult);
        assertEquals("3&nbsp;536,20&nbsp", valueOfResult);


        //#8
        String testSport = getDriver().findElement(By.xpath("./[@id='views']/form/section/section/section[3]/div/div[1]/span[1]")).getText();
        assertEquals("Спортивный", testSport);
        testSport = getDriver().findElement(By.xpath("./[@id='views']/form/section/section/section[3]/div/div[1]/ul/li[1]")).getText();
        assertEquals("Активные виды спорта", testSport);
        testSport = getDriver().findElement(By.xpath("./[@id='views']/form/section/section/section[3]/div/div[1]/ul/li[2]")).getText();
        assertEquals("Защита спортинвентаря", testSport);
        testSport = getDriver().findElement(By.xpath("./[@id='views']/form/section/section/section[3]/div/div[1]/ul/li[3]")).getText();
        assertEquals("Ski-pass / Лавина", testSport);
        testSport = getDriver().findElement(By.xpath("./[@id='views']/form/section/section/section[3]/div/div[1]/span[4]")).getText();
        assertEquals("2&nbsp;420,82&nbsp", testSport);

        //#9
        WebElement providentBlock = getDriver().findElement(By.xpath("./[@id='views']/form/section/section/section[3]/div/div[1]"));
        providentBlock.click();
        valueOfResult = getDriver().findElement(By.xpath("./[@id='views']/form/section/section/section[5]/div/dl[2]/dd[1]/span[1]"))
                .getAttribute("innerHTML");
        assertEquals("7&nbsp;070,19&nbsp", valueOfResult);

        //#10
        WebElement saveBag = getDriver().findElement(By.xpath("./[@id='views']/form/section/section/section[3]/div/div[1]"));
        saveBag.click();
        sportBlock.click();
        valueOfResult = getDriver().findElement(By.xpath("./[@id='views']/form/section/section/section[5]/div/dl[2]/dd[1]/span[1]"))
                .getAttribute("innerHTML");
        assertEquals("5&nbsp;538,79&nbsp", valueOfResult);

    }


    @AfterClass
    public static void postCondition() {
        System.out.println("3");
        getDriver().quit();
    }
}
