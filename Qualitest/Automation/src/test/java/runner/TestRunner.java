package runner;

import org.testng.annotations.DataProvider;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

    @CucumberOptions(
            features = "./src/test/resources/Features/TestScriptDemo.feature",
            glue= "stepDefs"
    )

    public class TestRunner extends AbstractTestNGCucumberTests {
        @DataProvider(parallel = true)
        public Object[][] scenarios(){
            return super.scenarios();
        }

    }


