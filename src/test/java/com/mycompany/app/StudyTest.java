package com.mycompany.app;

import com.mycompany.app.stepDetinitions.CommonStepDetinition;
import cucumber.api.java.Before;
import org.junit.AfterClass;
import org.junit.Test;


import static com.mycompany.app.lib.Init.*;

/**
 * Created by cantarella on 14.05.2016.
 */
//@Before

public class StudyTest {
    public void Test1() throws InterruptedException {
        CommonStepDetinition study = new CommonStepDetinition();
        study.openInsuranceTravelTest();
        study.The_user_is_open_page();
        study.Check_default_values();
        study.Check_formalization_and_confirmation();
        study.Check_final_result();
        study.Choose_enough();
        study.Check_final_result_with_enough();
        study.Choose_sportblock_and_check_final_result();
        study.Check_all_information_in_sportblock();
        study.Choose_providentblock_and_check_final_result();
        study.Choose_savebag_and_delete_sportblock_and_check_final_result();
    }




    @AfterClass
    public static void postCondition() {
        clearStash();
        getDriver().close();
    }
}
