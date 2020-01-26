import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public abstract class BaseClass {
        protected WebDriver driver;

        @BeforeClass
        public static void setUpClass() {
            WebDriverManager.chromedriver().setup();
        }

        @Before
        public void setUp() {
            driver = new ChromeDriver();
        }

        @After
        public void tearDown() {
            if (driver != null) {
                driver.quit();
            }
        }
    }
