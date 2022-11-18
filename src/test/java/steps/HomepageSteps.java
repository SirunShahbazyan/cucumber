package steps;

import io.cucumber.java.en.*;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.time.Duration;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class HomepageSteps {

    private WebDriver driver;

    @Given("I launch chrome driver")
    public void iLaunchChromeDriver() {
            System.setProperty("webdriver.chrome.driver", "C:\\Users\\sirun\\Desktop\\exe\\chromedriver.exe");
            driver = new ChromeDriver();
    }

    @When("I openOrange hrm page")
    public void iOpenOrangeHrmPage() {
    driver.get("https://opensource-demo.orangehrmlive.com/");
    driver.manage().window().maximize();
    }

    @Then("I verify that logo present on the page")
    public void iVerifyThatLogoPresentOnThePage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"app\"]//form/div[4]/p ")));
        Boolean status = element.isDisplayed();
        assertEquals(true, status);

    }

    @And("Close driver")
    public void closeDriver() {
    }
}


