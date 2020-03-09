package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public abstract class BaseClass {
        protected WebDriver driver;
        private static final Logger logger = LogManager.getLogger(BaseClass.class);

        @BeforeClass
        public static void setUpClass() {
            WebDriverManager.chromedriver().setup();
            logger.info("Set up class BaseClass");
        }

        @Before
        public void setUp() {
            driver = new ChromeDriver();
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            driver.manage().window().maximize();
            logger.info("Set up Chrome driver");
        }

        @After
        public void tearDown() {
            if (driver != null) {
                driver.quit();
            }
            logger.info("Tear down BaseClass");
        }
    }
