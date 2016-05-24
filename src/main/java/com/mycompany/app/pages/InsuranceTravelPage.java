package com.mycompany.app.pages;
import com.mycompany.app.lib.Init;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.TimeoutException;

import java.util.concurrent.TimeUnit;

import static com.mycompany.app.lib.Init.Stash;
import static com.mycompany.app.lib.Init.getDriver;

import static junit.framework.Assert.assertNotNull;


/**
 * Created by cantarella on 20.05.2016.
 */
public class InsuranceTravelPage extends AnyPage{
    @FindBy(name = "startDate")
    private WebElement strtDate;

    @FindBy(name = "finishDate")
    private WebElement fnshDate;

    @FindBy(xpath = "//h2[@text()='Страхование путешественников']")
    private WebElement initElement;

    @FindBy(css = ".ng-binding.ng-scope.b-dropdown-title")
    private WebElement region;

    @FindBy(name = "duration")
    private WebElement intervalDate;

    @FindBy(name = "insuredCount60")
    private WebElement adult;

    @FindBy(name = "insuredCount2")
    private WebElement baby;

    @FindBy(name = "insuredCount70")
    private WebElement old;

    @FindBy(xpath = "//div[text()='Минимальная']/../..")
    private WebElement minBlock;

    @FindBy(xpath = "//div[text()='Достаточная']/../..")
    private WebElement enough;

    @FindBy(xpath = "//span[text()='Спортивный']")
    private WebElement sport;

    @FindBy(xpath = "//span[text()='Защита багажа']")
    private WebElement saveBG;

    @FindBy(xpath = "//span[text()='Особый случай']")
    private WebElement specialCase;

    @FindBy(xpath = "//span[text()='Личный адвокат']")
    private WebElement personalLawyer;

    @FindBy(xpath = "//span[text()='Предусмотрительный']")
    private WebElement pred;

    @FindBy(xpath = "//span[text(), 'Оформление']")
    private  WebElement formalization;

    @FindBy(xpath = "//span[text(), 'Подтверждение']")
    private  WebElement confirmation;

    @FindBy(xpath = "//dt[text()='Итоговая стоимость']/..//dd[@class='b-form-outcome-contents b-form-big-font-size']/span[@class='ng-binding']")
    private WebElement valueOfResult;

    @FindBy(xpath = "//span[text()='Спортивный']/../ul/li[1]")
    private WebElement testSport2;

    @FindBy(xpath = "//span[text()='Спортивный']/../ul/li[2]")
    private WebElement testSport3;

    @FindBy(xpath = "//span[text()='Спортивный']/../ul/li[3]")
    private WebElement testSport4;

    @FindBy(xpath = "//span[text()='Спортивный']/../span[@class='b-form-sum-big-font-size ng-binding']")
    private WebElement testSport5;


    public InsuranceTravelPage(){
        PageFactory.initElements(Init.getDriver(), this);
        new WebDriverWait(Init.getDriver(), 30).until(ExpectedConditions.presenceOfElementLocated(By
                .xpath("//h2[text()='Страхование путешественников']")));
    }

    public void choosePolic(){
        assertEqualsInnerHTML("Весь мир, кроме США и РФ", region);

        //проверка интервала дат
        assertNotNull(strtDate.getText());

        assertNotNull(fnshDate.getText());

        //время действия полиса
        assertEqualsValue("15", intervalDate);

        //количество людей для страховки
        assertEqualsValue("1", adult);

        assertEqualsValue("0", baby);

        assertEqualsValue("0", old);

        //выбор минимального тарифа
        minBlock.click();

        //проверка галочек "рекомендуется предусмотреть", снятие всех
        try {
            WebElement sportCheck = getDriver().findElement(By.xpath("//span[text()='Спортивный']/../span[@class='b-form-prog-box-check-pos b-checked-checkbox-field']"));
            click(sport);
        } catch (NoSuchElementException e) {
        }

        try {
            WebElement saveBGcheck = getDriver().findElement(By.xpath("//span[text()='Защита багажа']/../span[@class='b-form-prog-box-check-pos b-checked-checkbox-field']"));
            click(saveBG);
        } catch (NoSuchElementException e) {
        }

        try {
            WebElement specialCaseCheck = getDriver().findElement(By.xpath("//span[text()='Особый случай']/../span[@class='b-form-prog-box-check-pos b-checked-checkbox-field']"));
            click(specialCase);
        } catch (NoSuchElementException e) {
        }

        try {
            WebElement personalLawyerCheck = getDriver().findElement(By.xpath("//span[text()='Личный адвокат']/../span[@class='b-form-prog-box-check-pos b-checked-checkbox-field']"));
            click(personalLawyer);
        } catch (NoSuchElementException e) {
        }

        try {
            WebElement predCheck = getDriver().findElement(By.xpath("//span[text()='Предусмотрительный']/../span[@class='b-form-prog-box-check-pos b-checked-checkbox-field']"));
            click(pred);
        } catch (NoSuchElementException e) {
        }

    }

    public void formalizationConformation(){
        try {
            click(formalization);
        } catch (TimeoutException e) {
        }

        try {
            click(confirmation);
        } catch (TimeoutException e) {
        }
    }

    public void minInsurance(){
        assertEqualsText(Stash.get("base").toString() + " ", valueOfResult);

    }

    public void enoughPrice() throws InterruptedException{
        click(enough);

        try {
            WebElement enoughCheck = getDriver().findElement(By.xpath("//div[text()='Достаточная']/../span[@class='b-form-prog-box-check-pos b-checked-checkbox-field']"));
        } catch (NoSuchElementException e) {
        }

    }

    public void enoughResultPrice() throws InterruptedException {
        click(valueOfResult);
        TimeUnit.SECONDS.sleep(5);
        assertEqualsText(Stash.get("dost").toString() + " ", valueOfResult);
    }

    public void withSportBlock() throws InterruptedException {
        click(sport);
        TimeUnit.SECONDS.sleep(8);
        assertEqualsText(Stash.get("dostSport").toString() + " ", valueOfResult);
    }

    public void fullTestSportBlock(){
        assertEqualsText("Спортивный", sport);
        assertEqualsText("Активные виды спорта", testSport2);
        assertEqualsText("Защита спортинвентаря", testSport3);
        assertEqualsText("Ski-pass / Лавина", testSport4);
        assertEqualsText(Stash.get("sport").toString(), testSport5);
    }

    public void providentBlock() throws InterruptedException {
        click(pred);
        TimeUnit.SECONDS.sleep(5);
        assertEqualsText(Stash.get("dostSportPred").toString() + " ", valueOfResult);
    }

    public void saveBag() throws InterruptedException {
        click(saveBG);
        click(sport);
        TimeUnit.SECONDS.sleep(5);
        assertEqualsText(Stash.get("dostPredBag").toString() + " ", valueOfResult);

    }


}
