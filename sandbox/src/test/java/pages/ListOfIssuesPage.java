package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ListOfIssuesPage extends AbstractPage {
    private By issueList = By.xpath("//a[@data-hovercard-type='issue']");

    public ListOfIssuesPage(WebDriver driver) {
        super(driver);
    }

    public IssuePage clickIssuesByNumber(int number) {
        List<WebElement> elements = driver.findElements(issueList);
        elements.get(number).click();

        return new IssuePage(driver);
    }

}
