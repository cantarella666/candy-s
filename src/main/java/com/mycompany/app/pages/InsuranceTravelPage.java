package com.mycompany.app.pages;
import com.mycompany.app.lib.Init;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static com.mycompany.app.lib.Init.getDriver;
import static junit.framework.Assert.assertEquals;
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

    @FindBy(xpath = "//div[contains(@class, 'box-title') and contains(text(), 'Минимальная')]/../..']")
    private WebElement minBlock;

    @FindBy(xpath = "//span[text()='Спортивный']/../span[@class='b-form-prog-box-check-pos b-checked-checkbox-field']")
    private WebElement sport;

    @FindBy(xpath = "//span[text()='Защита багажа']/../span[@class='b-form-prog-box-check-pos b-checked-checkbox-field']")
    private WebElement saveBG;

    @FindBy(xpath = "//span[text()='Особый случай']/../span[@class='b-form-prog-box-check-pos b-checked-checkbox-field']")
    private WebElement specialCase;

    @FindBy(xpath = "//span[text()='Личный адвокат']/../span[@class='b-form-prog-box-check-pos b-checked-checkbox-field']")
    private WebElement personalLawyer;

    @FindBy(xpath = "//span[text()='Предусмотрительный']/../span[@class='b-form-prog-box-check-pos b-checked-checkbox-field']")
    private WebElement pred;




    public InsuranceTravelPage(){
        PageFactory.initElements(Init.getDriver(), this);
        new WebDriverWait(Init.getDriver(), 30).until(ExpectedConditions.presenceOfElementLocated(By
                .xpath("//h2[text()='Страхование путешественников']")));
    }

    public void choosePolic(){

        String stringRegion = getDriver().findElement(By.cssSelector(".ng-binding.ng-scope.b-dropdown-title"))
                .getAttribute("innerHTML");
        assertEquals("Весь мир, кроме США и РФ", region.getAttribute("innerHTML"));

        //проверка интервала дат
        assertNotNull(strtDate.getText());

        assertNotNull(fnshDate.getText());

        //время действия полиса
        assertEquals("15", intervalDate.getAttribute("value"));

        //количество людей для страховки
        assertEquals("1", adult.getAttribute("value"));

        assertEquals("0", baby.getAttribute("value"));

        assertEquals("0", old.getAttribute("value"));

        //выбор минимального тарифа
        minBlock.click();

        //проверка галочек "рекомендуется предусмотреть", снятие всех
        try {
            sport.click();
        } catch (NoSuchElementException e) {
        }

        try {
            saveBG.click();
        } catch (NoSuchElementException e) {
        }

        try {
            specialCase.click();
        } catch (NoSuchElementException e) {
        }

        try {
            personalLawyer.click();
        } catch (NoSuchElementException e) {
        }

        try {
            pred.click();
        } catch (NoSuchElementException e) {
        }

    }
}
