import config.MyConfig;
import org.aeonbits.owner.ConfigFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class FirstTest {
    private static final Logger logger = LogManager.getLogger();
    MyConfig config = ConfigFactory.create(MyConfig.class);
    public String baseUrl = config.url();
    public WebDriver driver;

    @BeforeTest
    @Parameters("browser")
    public void setUrl(String browser) throws Exception {
        logger.info("Before test");
        if (browser.equalsIgnoreCase("firefox")) {
            driver = new FirefoxDriver();
            logger.info("Testing browser is " + browser);
        }
        else if (browser.equalsIgnoreCase("chrome")) {
            System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver");
            driver = new ChromeDriver();
            logger.info("Testing browser is " + browser);
        }
        else throw new Exception("Cannot define browser");
        logger.info("Trying to open url " + baseUrl);
        driver.get(baseUrl);
    }

    @Test
    public void checkTitle(){
        logger.info("start checkTitle");
        String actualTitle = driver.getTitle();
        //String expectedTitle  = "Онлайн‑курсы для профессионалов, дистанционное обучение современным профессиям";
        String expectedTitle = config.title();
        Assert.assertEquals(actualTitle, expectedTitle);
    }

    @AfterTest
    public void endSession(){
        logger.info("start endSession");
        if (driver != null) {
            driver.quit();
        }
    }
}
