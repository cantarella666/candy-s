package com.mycompany.app.pages;

import com.mycompany.app.lib.Init;
import org.junit.Assert;

import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static com.mycompany.app.lib.Init.getDriver;

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
    }

    public void click(WebElement element){
        PageFactory.initElements(Init.getDriver(), this);
        new WebDriverWait(Init.getDriver(), 10).until(ExpectedConditions.elementToBeClickable(element));
        System.out.println("click to element" + element.getText());
        element.click();
        //ожидание логирование и т д обвеску сюда вписывать
    }

    public void isChecked(WebElement element){
        PageFactory.initElements(Init.getDriver(), this);
        if (!element.isSelected()){
            click(element);
        }
    }

    public void notChecked(WebElement element){
        PageFactory.initElements(Init.getDriver(), this);
        if (element.isSelected()){
            click(element);
        }
    }
    //clear
    //setText(установить значение, clear)
    //select для выпадающих настоящих
    //select для ненастоящих
    //чекбоксы
    //JavaDoc
    /**
     *
     * @param string
     * @param element
     */
    public void assertEqualsText(String string, WebElement element){
        Assert.assertEquals(string, element.getText());

    }
    public void assertEqualsInnerHTML(String string, WebElement element){
        Assert.assertEquals(string, element.getAttribute("innerHTML"));

    }

    public void assertEqualsValue(String string, WebElement element){
        Assert.assertEquals(string, element.getAttribute("value"));

    }

    public void assertNotNull(WebElement element){
        Assert.assertNotNull(element.getText());

    }

}
