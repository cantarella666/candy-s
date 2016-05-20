package com.mycompany.app.StepDetinitions;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import java.util.concurrent.TimeUnit;

import static com.mycompany.app.lib.Init.Stash;
import static com.mycompany.app.lib.Init.getStash;
import static junit.framework.Assert.assertEquals;
import static com.mycompany.app.lib.Init.getDriver;
import static junit.framework.Assert.assertNotNull;


/**
 * Created by cantarella on 18.05.2016.
 */
public class CommonStepDetinition {
    public void openInsuranceTravelTest(){
        getDriver().get(getStash().get("url").toString());
        getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        String stringTitle = getDriver().findElement(By.xpath("html/body/div[1]/header/div/h2"))
                .getAttribute("innerHTML");
        assertEquals("Страхование путешественников", stringTitle);
    }



    public void formalizationConformation(){
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


    }

    public void minInsurance(){
        String valueOfResult = getDriver().findElement(By.xpath(".//*[@id='views']/form/section/section/section[5]/div/dl[2]/dd[1]/span[1]"))
                .getText();
        assertEquals(Stash.get("base").toString() + " ", valueOfResult);

    }

    public void enoughPrice() throws InterruptedException{
        WebElement middle = getDriver().findElement(By.xpath(".//*[@id='views']/form/section/section/section[2]/div[1]/div[2]/div"));
        middle.click();
        TimeUnit.SECONDS.sleep(5);
        try {
            WebElement middle2 = getDriver().findElement(By.xpath(".//*[@id='views']/form/section/section/section[2]/div[1]/div[2]/div/span[@class='b-form-prog-box-check-pos b-checked-checkbox-field"));
        } catch (NoSuchElementException e) {
        }

    }

    public void enoughResultPrice() throws InterruptedException {
        TimeUnit.SECONDS.sleep(5);
        String valueOfResult = getDriver().findElement(By.xpath(".//*[@id='views']/form/section/section/section[5]/div/dl[2]/dd[1]/span[1]"))
                .getText();

        assertEquals(Stash.get("dost").toString() + " ", valueOfResult);

    }

    public void withSportBlock() throws InterruptedException {

        WebElement sportBlock = getDriver().findElement(By.xpath(".//*[@id='views']/form/section/section/section[3]/div/div[1]"));
        sportBlock.click();
        TimeUnit.SECONDS.sleep(5);
        WebElement rslt = getDriver().findElement(By.xpath(".//*[@id='views']/form/section/section/section[5]/div/dl[2]/dd[1]/span[@class='ng-binding']"));
        String valueOfResult = rslt.getText();
        assertEquals(Stash.get("dostSport").toString() + " ", valueOfResult);
    }

    public void fullTestSportBlock(){

        String testSport = getDriver().findElement(By.xpath(".//*[@id='views']/form/section/section/section[3]/div/div[1]/span[1]"))
                .getText();
        assertEquals("Спортивный", testSport);
        testSport = getDriver().findElement(By.xpath(".//*[@id='views']/form/section/section/section[3]/div/div[1]/ul/li[1]"))
                .getText();
        assertEquals("Активные виды спорта", testSport);
        testSport = getDriver().findElement(By.xpath(".//*[@id='views']/form/section/section/section[3]/div/div[1]/ul/li[2]"))
                .getText();
        assertEquals("Защита спортинвентаря", testSport);
        testSport = getDriver().findElement(By.xpath(".//*[@id='views']/form/section/section/section[3]/div/div[1]/ul/li[3]"))
                .getText();
        assertEquals("Ski-pass / Лавина", testSport);
        testSport = getDriver().findElement(By.xpath(".//*[@id='views']/form/section/section/section[3]/div/div[1]/span[4]"))
                .getText();
        assertEquals(Stash.get("sport").toString(), testSport.substring(0,8));
    }

    public void providentBlock() throws InterruptedException {

        WebElement providentBlock = getDriver().findElement(By.xpath(".//*[@id='views']/form/section/section/section[3]/div/div[5]"));
        providentBlock.click();
        TimeUnit.SECONDS.sleep(5);
        String valueOfResult = getDriver().findElement(By.xpath(".//*[@id='views']/form/section/section/section[5]/div/dl[2]/dd[1]/span[1]"))
                .getText();
        assertEquals(Stash.get("dostSportPred").toString() + " ", valueOfResult);
    }

    public void saveBag() throws InterruptedException {
        WebElement saveBag = getDriver().findElement(By.xpath(".//*[@id='views']/form/section/section/section[3]/div/div[2]"));
        saveBag.click();
        WebElement sportBlock = getDriver().findElement(By.xpath(".//*[@id='views']/form/section/section/section[3]/div/div[1]"));
        sportBlock.click();
        TimeUnit.SECONDS.sleep(5);
        String valueOfResult = getDriver().findElement(By.xpath(".//*[@id='views']/form/section/section/section[5]/div/dl[2]/dd[1]/span[1]"))
                .getText();
        assertEquals(Stash.get("dostPredBag").toString() + " ", valueOfResult);

    }
}
