package cases;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import utils.BaseHooks;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class GithubTest extends BaseHooks {

    @Test
    public void githubTest() {
        driver.get("https://github.com/");
        driver.findElement(By.xpath("//input[@name='q']")).sendKeys("Selenide");
        driver.findElement(By.xpath("//input[@name='q']")).sendKeys(Keys.ENTER);
        driver.findElement(By.xpath("//a[@href='/selenide/selenide']")).click();
        driver.findElement(By.xpath("//nav[@aria-label='Repository']//span[text()='Issues']")).click();
        driver.findElement(By.xpath("//a[@data-hovercard-type='issue']")).click();

        String title =
                driver.findElement(By.xpath("//div[@class='TableObject-item']/span")).getAttribute("title");

        assertThat(title, is("Status: Open"));
    }

}
