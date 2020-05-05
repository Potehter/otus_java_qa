package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverFactory {
    private static final Logger logger = LogManager.getLogger(BaseClass.class);
    public static WebDriver createDriver(String wdType) throws Exception {
        if (wdType.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            logger.info("Testing browser is " + wdType);
            return new FirefoxDriver();
        } else {
            WebDriverManager.chromedriver().setup();
            logger.info("Testing browser is " + wdType);
            return new ChromeDriver();
        }
    }

    public static void cleanCookies(WebDriver driver) {
        driver.manage().deleteAllCookies();
    }


}
