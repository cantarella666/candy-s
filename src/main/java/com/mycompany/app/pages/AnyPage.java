package com.mycompany.app.pages;

import com.mycompany.app.lib.Init;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.WebElement;

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
        element.click();
        //ожидание логирование и т д обвеску сюда вписывать

    }

    public void click(By by){
        WebElement element = getDriver().findElement(by);
        element.click();

    }

}
