package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.Properties;

import static utils.CredentialsData.getProps;

public class MainPage extends AbstractPage {
    private static final Logger logger = LogManager.getLogger(MainPage.class);
    static Properties credProp = getProps();
    private String loginData = credProp.getProperty("login");
    private String passData = credProp.getProperty("password");
    private static final String URL = "https://otus.ru/";
    private By buttonLoginReg = By.cssSelector("button[data-modal-id]");
    private By login = By.cssSelector("input.js-email-input");
    private By password = By.cssSelector("input.js-psw-input");
    private By buttonLoginSubmit = By.cssSelector("button.new-button_full");
    private By hrefToPersonal = By.cssSelector("a[href='/lk/biography/personal/']");
    private By dropDownMenu = By.cssSelector("div.header2-menu__icon");
    public MainPage(WebDriver driver) {
        super(driver);
    }

    public MainPage open() {
        driver.get(URL);
        logger.info("Open main page");
        return this;
    }

    public MainPage login() {
        driver.findElement(buttonLoginReg).click();
        driver.findElement(login).sendKeys(loginData);
        driver.findElement(password).sendKeys(passData);
        driver.findElement(buttonLoginSubmit).submit();
        logger.info("Login from main page");
        return this;
    }

    public PersonalPage goToPersonal() {
        new WebDriverWait(driver, 5l)
                .until(ExpectedConditions.visibilityOfElementLocated(dropDownMenu));
        driver.findElement(dropDownMenu).click();
        driver.findElement(hrefToPersonal).click();
        logger.info("Click to go to profile");
        return new PersonalPage(driver);
    }
}
