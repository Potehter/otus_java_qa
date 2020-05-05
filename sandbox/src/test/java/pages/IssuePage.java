package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class IssuePage extends AbstractPage {
    private By issueTitle = By.xpath("//div[@class='TableObject-item']/span");

    public IssuePage(WebDriver driver) {
        super(driver);
    }

    public String getIssueTitle() {
        return driver.findElement(issueTitle).getAttribute("title");
    }

}
