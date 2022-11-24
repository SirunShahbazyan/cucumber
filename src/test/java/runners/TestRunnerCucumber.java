package runners;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(glue= {"steps"},
        features = "src/test/resources/features",
        plugin = { "pretty", "json:target/cucumber.json", "html:target/cucumber-reports.html",


                "junit:target/cucumber-reports/Cucumber.xml" },
        monochrome = true)

public class TestRunnerCucumber {
<<<<<<< HEAD


=======
    System.out.print("7896");
    System.out.print("1478");
>>>>>>> c22c40004f39600d8df982f9200c6862cfbcb826
}

