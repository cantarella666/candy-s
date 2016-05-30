import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        monochrome = true,
        //format = {"pretty"},
        glue = {"stepDefinitions"},
        features = "src/test/resources/features/",
        tags = {"@InsuranceTest"}
)


public class RunCucumberTest {

}