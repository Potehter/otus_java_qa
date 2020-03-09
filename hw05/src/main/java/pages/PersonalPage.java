package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PersonalPage extends AbstractPage{
    private static final Logger logger = LogManager.getLogger(PersonalPage.class);
    private static final String URL = "https://otus.ru/lk/biography/personal/";
    private By name = By.cssSelector("input[name='fname']");
    private By surname = By.cssSelector("input[name='lname']");
    private By btnSave = By.cssSelector("button[title='Сохранить и заполнить позже'");
    private By btnAddContact = By.cssSelector("button.js-lk-cv-custom-select-add");
    private By groupContact = By.cssSelector("div.js-formset-row");
    private By allContactLocator = By.cssSelector("div[data-prefix='contact'");
    private By listContactTypes = By.cssSelector("div.js-lk-cv-custom-select");
    private By contactValue = By.cssSelector("input[type='text']");



    public PersonalPage(WebDriver driver) {
        super(driver);
    }


    public PersonalPage open() {
        driver.get(URL);
        logger.info("Opening personal page");
        return this;
    }

    public PersonalPage fillPersonalData() {
        driver.findElement(name).clear();
        driver.findElement(name).sendKeys("Name");
        driver.findElement(surname).clear();
        driver.findElement(surname).sendKeys("Surname");
        logger.info("Filling personal data");
        return this;
    }

    public PersonalPage addContact(String name, String value) {
        Integer contactCount;
        WebElement groupNewElement;
        driver.findElement(btnAddContact).click();
        contactCount = getContactCount();
        logger.info("Current count of contacts: " + contactCount);
        groupNewElement = getGroupNewElement(contactCount);
        groupNewElement.findElement(listContactTypes).click();
        groupNewElement.findElement(By.cssSelector("button[data-value='" + name + "'")).click();
        groupNewElement.findElement(contactValue).sendKeys(value);
        logger.info("Added contact info: " + name + "; " + value);
        return this;
    }

    private WebElement getGroupNewElement(int contactCount) {
        if (contactCount == 0) contactCount = 1;
        By currentConactRow = By.cssSelector("div[data-num='" + (contactCount - 1) + "']");
        return driver.findElement(allContactLocator).findElement(currentConactRow);
    }

    public Integer getContactCount() {
        return driver.findElement(allContactLocator).findElements(groupContact).size();
    }

    public PersonalPage save() {
        driver.findElement(btnSave).click();
        logger.info("Saved contact data");
        return this;
    }
}
