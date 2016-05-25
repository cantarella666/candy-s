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

    @FindBy(xpath = "//label[@for='map-filter-type-filial']")
    private WebElement branchChecked;

    @FindBy(xpath = "//div[@class='branch-list-item-block']/a/span")
    private WebElement branchFirst;

    @FindBy(xpath = "//div[@id='branchList']")
    private WebElement branchList;

    @FindBy(xpath = "//input[@id='map-filter-type-terminal']")
    private WebElement trmnl;

    @FindBy(xpath = "//label[@for='map-filter-type-terminal']")
    private WebElement trmnlChecked;

    @FindBy(xpath = "//button[@class='sbf_button show-more']")
    private WebElement newLctn;

    public void open(){
        PageFactory.initElements(Init.getDriver(), this);
        getDriver().get(getStash().get("url").toString());
        waitPageToLoad();
        assertEqualsInnerHTML("Отделения и банкоматы", title);
    }

    public void branchSelected() throws InterruptedException {
        isChecked(branch, branchChecked);

    }

    public void branchNotNull(){
        try{
            assertNotNull(branchFirst);
        }catch (NoSuchElementException e){
            System.out.println("Нет ближайших отделений");
        }
    }

    public void allBranches(){
        checkLocation(9);
    }

    public void terminal() throws InterruptedException {
        isChecked(trmnl, trmnlChecked);
        try{
            WebElement trmnlFind = getDriver().findElement(By.xpath("//div[@id='branchList']/ul/li/div/span[@class='item-list-icon itt']"));
        }catch (NoSuchElementException e){
            System.out.println("Нет ближайших терминалов");
        }

    }

    public void allLocationOne(){
        checkLocation(9);


    }

    public void newLocation() throws InterruptedException {
        click(newLctn);

        //поставь значение кучи локаций
        checkLocation(18);
    }

    public void delBranch() throws InterruptedException {
        notChecked(branch, branchChecked);
        for(int i=1; i<9; i++){
            try{
                WebElement orange = getDriver().findElement(By.xpath("//div[@id='branchList']/ul/li" + i + "/div/span[@class='item-list-icon itt']"));
            }catch (NoSuchElementException e){
                break;

            }
        }
    }

    public void checkWithoutBranches(){
        checkLocation(9);
    }

}
