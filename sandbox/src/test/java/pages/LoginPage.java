package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class LoginPage extends AbstractPage {
    private static final String URL = "https://github.com/";
    private By search = By.xpath("//input[@name='q']");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public LoginPage open() {
        driver.get(URL);

        return this;
    }

    public SearchPage search(String text) {
        driver.findElement(search).sendKeys(text);
        driver.findElement(search).sendKeys(Keys.ENTER);

        return new SearchPage(driver);
    }

}
