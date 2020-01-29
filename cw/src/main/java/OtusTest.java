import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class OtusTest {
    public WebDriver driver;

    @BeforeTest
    public void setUrl()  {
            WebDriverManager.chromedriver().setup();;
            driver = new ChromeDriver();
    }

    @Test
    public void checkTitle(){
        driver.get("https://otus.ru");
        authorizeOtus();


    }

    public void authorizeOtus() {
        driver.findElement(By.cssSelector("button[data-modal-id]")).click();
        driver.findElement(By.cssSelector("input.js-email-input")).sendKeys("user50436@otus-student.ru");
        driver.findElement(By.cssSelector("input.js-psw-input")).sendKeys("yHDm5foEIOdA");
        driver.findElement(By.cssSelector("button.new-button_full")).submit();
    }

    @AfterTest
    public void endSession(){
        if (driver != null) {
            driver.quit();
        }
    }
}
