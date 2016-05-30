package pages;

import lib.Init;
import org.junit.Assert;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by cantarella on 20.05.2016.
 */
public abstract class AnyPage {
    public void AnyPage(){
        PageFactory.initElements(Init.getDriver(), this);
        waitPageToLoad();
    }

    public void waitPageToLoad(){
        PageFactory.initElements(Init.getDriver(), this);
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
        //ожидание логирование и т д обвеску сюда вписывать
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

}
