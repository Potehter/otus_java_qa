import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class MainTest {
    public String baseUrl = "https://otus.ru/";
    public WebDriver driver;
    protected static String browser = System.getProperty("browser");

    @BeforeTest
    public void setUrl() throws Exception {
        driver = WebDriverFactory.createNewDriver(browser);
        driver.get(baseUrl);
    }

    @Test
    public void checkTitle(){
        String actualTitle = driver.getTitle();
        String expectedTitle  = "Онлайн‑курсы для профессионалов, дистанционное обучение современным профессиям";
        Assert.assertEquals(actualTitle, expectedTitle);
    }

    @AfterTest
    public void endSession(){
        if (driver != null) {
            driver.quit();
        }
    }
}
