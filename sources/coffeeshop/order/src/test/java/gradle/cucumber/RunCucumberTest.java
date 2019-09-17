package gradle.cucumber;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = "json:target/cucumber-report.json", features = {"src/test/resources/features/OrderAmericano.feature"})
public class RunCucumberTest {
}
