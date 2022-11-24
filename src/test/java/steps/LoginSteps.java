package steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginSteps {
   private WebDriver driver;

    @Before()
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\sirun\\Desktop\\exe\\chromedriver.exe");
        driver = new ChromeDriver();
    }
    @Given("I am on the Login page")
    @Given("I am on the Login page of OrangeHRM")
    public void iAmOnTheLoginPageOfOrangeHRM() {
        driver.get("https://opensource-demo.orangehrmlive.com/");
        driver.manage().window().maximize();
    }

    @When("I enter valid {string} and {string}")
    public void iEnterValidCredentials(String username, String password) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement userName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("username")));
        userName.sendKeys(username);
        WebElement passWord = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("password")));
        passWord.sendKeys(password);
    }

    @Then("I should be taken to a new page")
    public void iIShouldBeTakenToANewPage() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement submit = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button[type=\"submit\"]")));
        submit.click();
        Thread.sleep(5000);
        String text = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[1]/div/div[1]/div/p")))
                .getText();
        String expectedText = "Time at Work";
        assertEquals(expectedText, text);
    }

    @After()
    public void tearDown() {
        driver.quit();
        System.out.println("Quit");

    }
}
