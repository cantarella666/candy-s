package com.mycompany.app;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "C:/Users/cantarella/my-app/candy-s/src/test/java/resources/feature"
        ,glue = {"com.mycompany.app.stepDefinitions"}
        ,tags = "@test1"
)


public class runCucumber {

}