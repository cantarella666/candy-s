package stepDefinitions;


import pages.InsuranceTravelPage;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.Given;

import cucumber.api.java.en.When;
import org.openqa.selenium.By;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import static lib.Init.*;
import static junit.framework.Assert.assertEquals;


/**
 * Created by cantarella on 18.05.2016.
 */

public class CommonStepDefinition {
    public InsuranceTravelPage insuranceTravelPage = new InsuranceTravelPage();

@Before
public static void beforeClass() throws IOException {
        System.out.println("test1");
        Properties property = new Properties();
        property.load(new FileInputStream("src/test/java/config/application.properties"));
        Stash = getStash();
        setStashElement("browser", property.getProperty("browser"));
        setStashElement("url", property.getProperty("url"));
        setStashElement("filechrome", property.getProperty("filechrome"));
        setStashElement("fileie", property.getProperty("fileie"));
        setStashElement("base", property.getProperty("base"));
        setStashElement("dost", property.getProperty("dost"));
        setStashElement("dostSport", property.getProperty("dostSport"));
        setStashElement("sport", property.getProperty("sport"));
        setStashElement("dostSportPred", property.getProperty("dostSportPred"));
        setStashElement("dostPredBag", property.getProperty("dostPredBag"));
        }
    //типо стэпы
    /*@Given("^set Stash$")
    public void Before{

    }*/
    @Given("^url 'https://online.sberbankins.ru/store/vzr/index.html#/viewCalc'$")
    public void The_user_is_open_page() {
        insuranceTravelPage.openPage();
    }
    //(" \"([^\"]*)\" message")
    @Then("^Проверить значения по умолчанию$")
    public void Check_default_values() {
        insuranceTravelPage.choosePolic();
    }

    @And("^Проверить доступность вкладов 'Оформление' и 'Подтверждение'$")
    public void Check_formalization_and_confirmation(){
        insuranceTravelPage.formalizationConformation();
    }

    @Given("^Итоговая стоимость должна быть примерно \"826,42\"$")
    public void Check_final_result(){
        insuranceTravelPage.minInsurance();
    }

    @When("^Выбрать блок 'Достаточная' в блоке 'Выберите сумму страховой защиты'$")
    public void Choose_enough() throws InterruptedException {
        insuranceTravelPage.enoughPrice();
    }

    @And("^Теперь 'Итоговая стоимость' должна быть примерно \"1 112,91\"$")
    public void Check_final_result_with_enough() throws InterruptedException {
        insuranceTravelPage.enoughResultPrice();
    }

    @When("^В секции 'Рекомендуем предусмотреть' выбрать блок 'Спортивный' и проверить 'Итоговая стоимость', она должна быть равна \"3 528,26\"$")
    public void Choose_sportblock_and_check_final_result() throws InterruptedException {
        insuranceTravelPage.withSportBlock();
    }

    @Given("^Проверить текст значения 'Спортивный' в блоке 'Рекомендуем предусмотреть'. Стоимость услуги \"2 425,33\"$")
    public void Check_all_information_in_sportblock(){
        insuranceTravelPage.fullTestSportBlock();
    }
    @When("^Выбрать дополнительно 'Предусмотрительный' и проверить значение 'Итоговая стоимость', \"7 054,33\"$")
    public void Choose_providentblock_and_check_final_result() throws InterruptedException {
        insuranceTravelPage.providentBlock();
    }
    @Given("^Выбрать дополнительно 'Защита багажа', отключить значение 'Спортивный' и проверить значение 'Итоговая стоимость' - \"5 526,37\"$")
    public void Choose_savebag_and_delete_sportblock_and_check_final_result() throws InterruptedException {
        insuranceTravelPage.saveBag();
    }





    public void openInsuranceTravelTest(){
        getDriver().get(getStash().get("url").toString());
        getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        String stringTitle = getDriver().findElement(By.xpath("html/body/div[1]/header/div/h2"))
                .getAttribute("innerHTML");
        assertEquals("Страхование путешественников", stringTitle);
    }

}
