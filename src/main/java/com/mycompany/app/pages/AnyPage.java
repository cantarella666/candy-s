package com.mycompany.app.pages;

import com.mycompany.app.lib.Init;
import org.junit.Assert;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.concurrent.TimeUnit;


import static com.mycompany.app.lib.Init.getDriver;
import static org.junit.Assert.assertEquals;

/**
 * Created by cantarella on 20.05.2016.
 */
public abstract class AnyPage {
    public void AnyPage(){
        PageFactory.initElements(getDriver(), this);
        waitPageToLoad();
    }

    public void waitPageToLoad(){
        //браузер отдал управление страницей. javascript
        //у страницы есть статусы загрузки. проверка через compire
        ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {

            public Boolean apply(WebDriver driver) {
                return ((JavascriptExecutor) driver)
                        .executeScript("return document.readyState").toString().equals("complete");
            }
        };
    }

    public void click(WebElement element){
        PageFactory.initElements(Init.getDriver(), this);
        new WebDriverWait(Init.getDriver(), 10).until(ExpectedConditions.elementToBeClickable(element));
        System.out.println("click to element" + element.getText());
        element.click();
    }

    public void isChecked(WebElement element1, WebElement element2) throws InterruptedException {
        PageFactory.initElements(Init.getDriver(), this);
        if (!element1.isSelected()){
            click(element2);
            TimeUnit.SECONDS.sleep(5);
        }
    }

    public void notChecked(WebElement element1, WebElement element2) throws InterruptedException {
        PageFactory.initElements(Init.getDriver(), this);
        if (element1.isSelected()){
            click(element2);
            TimeUnit.SECONDS.sleep(5);
        }
    }


    public void assertEqualsText(String string, WebElement element){
        assertEquals(string, element.getText());

    }
    public void assertEqualsInnerHTML(String string, WebElement element){
        assertEquals(string, element.getAttribute("innerHTML"));

    }

    public void assertEqualsValue(String string, WebElement element){
        assertEquals(string, element.getAttribute("value"));

    }

    public void assertNotNull(WebElement element){
        Assert.assertNotNull(element.getText());

    }

    public void sendKeys(WebElement element, String string){
        element.sendKeys(string);
    }

    public void transfer(Object object, String string2, WebElement element){

        Double dbl1 = Double.valueOf(object.toString());
        Double dbl2 = Double.valueOf(string2);
        Double dbl3 = dbl1*dbl2;
        Double dbl4 = new BigDecimal(dbl3).setScale(2, RoundingMode.HALF_UP).doubleValue();
        String result = dbl4.toString();
        String result2 = element.getAttribute("value").toString().replace(" ", "");
        assertEquals(result, result2);

    }

    public void getSelect(WebElement element1, WebElement element2){
        click(element1);
        click(element2);
    }

    public void clearField(WebElement element){
        element.clear();
    }

}
