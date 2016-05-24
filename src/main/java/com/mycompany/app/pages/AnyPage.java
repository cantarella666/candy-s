package com.mycompany.app.pages;

import com.mycompany.app.lib.Init;
import org.junit.Assert;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    public void isChecked(WebElement element1, WebElement element2){
        PageFactory.initElements(Init.getDriver(), this);
        if (!element1.isSelected()){
            click(element2);
        }
    }

    public void notChecked(WebElement element){
        PageFactory.initElements(Init.getDriver(), this);
        if (element.isSelected()){
            click(element);
        }
    }

    public void checkLocation(){
        Double[] allBrnch = new Double[9];
        for(int i=1; i<9; i++){
            try {
                WebElement branch1 = getDriver().findElement(By.xpath("//div[@id='branchList']/ul/li[" + i + "]/div/p[1]"));
                Pattern pat = Pattern.compile("[-]?[0-9]+(.[0-9]+)?");
                Matcher matcher=pat.matcher(branch1.getText());
                while (matcher.find()) {
                    String str = matcher.group();
                    allBrnch[i] = Double.valueOf(str);

                    if(allBrnch[i]>10.0){
                        allBrnch[i]=(allBrnch[i]/1000.0);
                    }

                    if((i>1)&&(allBrnch[i]<allBrnch[i-1])){System.out.println("Отделения расположены не верно");}

                }

            }catch (NoSuchElementException e){
                break;
            }
        }
    }

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
