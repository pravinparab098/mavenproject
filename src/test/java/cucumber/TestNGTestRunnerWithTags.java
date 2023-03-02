package cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src/test/java/cucumber", glue = "pravinParab.stepDefinations", monochrome = true, tags = "@Regression" , plugin = {
		"html:target//cucumber.html" }) //System.getProperty("user.dir")+"//reports//

public class TestNGTestRunnerWithTags extends AbstractTestNGCucumberTests {

}