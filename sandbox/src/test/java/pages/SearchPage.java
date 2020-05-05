package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SearchPage extends AbstractPage {
    private By firstRepository = By.xpath("//a[@href='/selenide/selenide']");

    public SearchPage(WebDriver driver) {
        super(driver);
    }

    public RepositoryTopBarElement clickFirstRepository() {
        driver.findElement(firstRepository).click();

        return new RepositoryTopBarElement(driver);
    }

}
