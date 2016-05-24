package com.mycompany.app.pages;
import com.mycompany.app.lib.Init;
import org.openqa.selenium.*;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.mycompany.app.lib.Init.Stash;
import static com.mycompany.app.lib.Init.getDriver;

import static com.mycompany.app.lib.Init.getStash;



/**
 * Created by cantarella on 20.05.2016.
 */
public class CurrenncyConverter extends AnyPage{

    @FindBy(xpath = "//h1[text()='Отделения и банкоматы']")
    private WebElement title;

    @FindBy(xpath = "//input[@id='map-filter-type-filial']")
    private WebElement branch;

    @FindBy(xpath = "//div[@class='branch-list-item-block']/a/span")
    private WebElement branchFirst;

    @FindBy(xpath = "//div[@id='branchList']")
    private WebElement branchList;

    public void searchATM(){
        PageFactory.initElements(Init.getDriver(), this);
        new WebDriverWait(Init.getDriver(), 30).until(ExpectedConditions.presenceOfElementLocated(By
                .xpath("//h1[text()='Отделения и банкоматы']")));
    }

    public void open(){
        assertEqualsInnerHTML("Отделения и банкоматы", title);
    }

    public void branchSelected(){
        isChecked(branch);

    }

    public void branchNotNull(){
        try{
            assertNotNull(branchFirst);
        }catch (NoSuchElementException e){
            System.out.println("Нет ближайших отделений");
        }
    }

    public void allBranches(){
        int k = 1;
//        WebElement branch1 = getDriver().findElement(By.xpath("//div[@class='branch-list-item-block']/p[" + k + "]"));
//        Pattern pat = Pattern.compile("[-]?[0-9]+(.[0-9]+)?");
//        Matcher matcher=pat.matcher(branch1.getText());
//        while (matcher.find()) {
//            System.out.println(matcher.group());
//        }
        String[] allBrnch = new String[9];
        for(int i=1; i<9; i++){
            try {
                WebElement branch1 = getDriver().findElement(By.xpath("//div[@id='branchList']/ul/li[" + i + "]/div/p[1]"));
                Pattern pat = Pattern.compile("[-]?[0-9]+(.[0-9]+)?");
                Matcher matcher=pat.matcher(branch1.getText());
                while (matcher.find()) {
                    String str = matcher.group();
                    allBrnch[i] = str;
                }
                System.out.println(allBrnch[i]);
            }catch (NoSuchElementException e){
                break;
            }


        }


    }

}
