package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RepositoryTopBarElement extends AbstractPage {
    private By issues = By.xpath("//nav[@aria-label='Repository']//span[text()='Issues']");

    public RepositoryTopBarElement(WebDriver driver) {
        super(driver);
    }

    public ListOfIssuesPage openIssues() {
        driver.findElement(issues).click();

        return new ListOfIssuesPage(driver);
    }

}
