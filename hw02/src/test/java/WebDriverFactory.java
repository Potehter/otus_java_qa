import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

public class WebDriverFactory {
    public static WebDriver createNewDriver(String webDriverName) throws Exception{
        System.out.println(webDriverName);
        if (webDriverName == null) webDriverName = "chrome";
        if (webDriverName.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            return new ChromeDriver();
        }
        else if (webDriverName.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            return new FirefoxDriver();
        }
        else throw new Exception("Cannot define browser");
    }

    public static WebDriver createNewDriver(String webDriverName, MutableCapabilities newCapabilities) throws Exception{
        if (webDriverName.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            return new ChromeDriver(newCapabilities);
        }
        else if (webDriverName.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            return new FirefoxDriver(newCapabilities);
        }
        else throw new Exception("Cannot define browser");
    }
}
