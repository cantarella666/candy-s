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
import static org.junit.Assert.assertEquals;


/**
 * Created by cantarella on 20.05.2016.
 */
public class CurrenncyConverter extends AnyPage{
    @FindBy(xpath = "//span[@class='personalized-widget-title aa-widget-head-draggable currency-icon']")
    private WebElement title;

    @FindBy(xpath = "//span[@class='currency-converter-date']")
    private WebElement date;

    @FindBy(xpath = "//label[text()='Поменять']")
    private WebElement change;

    @FindBy(xpath = "//label[text()='На']")
    private WebElement NA;

    @FindBy(id = "from")
    private WebElement from;

    @FindBy(id = "to")
    private WebElement to;

    @FindBy(xpath="//label[text()='Поменять']/../div/div/div")
    private WebElement fromMoney;

    @FindBy(xpath = "//label[text()='На']/../div/div/div")
    private WebElement toMoney;

    @FindBy(xpath = "//div[@id='select2-drop']/ul/li/div[text()='RUB']")
    private WebElement moneyRUB;

    @FindBy(xpath = "//div[@id='select2-drop']/ul/li/div[text()='EUR']")
    private WebElement moneyEUR;

    @FindBy(xpath = "//div[@id='select2-drop']/ul/li/div[text()='USD']")
    private WebElement moneyUSD;

    public void open(){
        PageFactory.initElements(Init.getDriver(), this);
        getDriver().get(getStash().get("url").toString());
        waitPageToLoad();

        assertEqualsText("Конвертер валют", title);
    }

    public void checkDate(){
        String[] newDate = date.getAttribute("innerHTML").trim().split("\\s+");
        assertEquals(Stash.get("day"), newDate[0]);
        assertEquals("мая", newDate[1]);
        assertEquals(Stash.get("year"), newDate[2]);
    }

    public void checkField(){
        assertEqualsText("Поменять", change);
        assertEqualsText("На", NA);
        fromMoney.isDisplayed();
        toMoney.isDisplayed();
        from.isDisplayed();
        to.isDisplayed();


        WebElement checkTextCurrenccy = getDriver().findElement(By.xpath("//div[@class='currency-converter-result']/span[1]"));
        assertEqualsText("1", checkTextCurrenccy);
        checkTextCurrenccy = getDriver().findElement(By.xpath("//div[@class='currency-converter-result']/span[3]"));
        assertEqualsText("RUB", checkTextCurrenccy);
        checkTextCurrenccy = getDriver().findElement(By.xpath("//div[@class='currency-converter-result']/span[4]"));
        assertEqualsText("=", checkTextCurrenccy);
        checkTextCurrenccy = getDriver().findElement(By.xpath("//div[@class='currency-converter-result']/span[5]"));
        assertEqualsText(Stash.get("euro").toString(), checkTextCurrenccy);
        checkTextCurrenccy = getDriver().findElement(By.xpath("//div[@class='currency-converter-result']/span[7]"));
        assertEqualsText("EUR", checkTextCurrenccy);
    }

    public void rubTOeuro(){
        to.sendKeys("");
        getSelect(fromMoney, moneyRUB);
        getSelect(toMoney, moneyEUR);
        sendKeys(from, "34");
        transfer(Stash.get("euro"), "34", to);
    }

    public void usdTOeuro() throws InterruptedException {
        to.sendKeys("");
        getSelect(fromMoney, moneyUSD);
        getSelect(toMoney, moneyEUR);
        sendKeys(from, "10023");
        transfer(Stash.get("toEuroFromUsd"), "10023", to);
    }

    public void usdTOusd(){
        to.sendKeys("");
        getSelect(fromMoney, moneyUSD);
        getSelect(toMoney, moneyUSD);
        sendKeys(from, "5");
        transfer(Stash.get("toUsdFromUsd"), "5", to);
    }
}
