package com.mycompany.app.StepDetinitions;

import java.util.concurrent.TimeUnit;

import static com.mycompany.app.lib.Init.getStash;

import static com.mycompany.app.lib.Init.getDriver;



/**
 * Created by cantarella on 18.05.2016.
 */
public class CommonStepDetinition {
    public void openInsuranceTravelTest(){
        getDriver().get(getStash().get("url").toString());
        getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

    }

}
