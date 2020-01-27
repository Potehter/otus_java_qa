import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;

public class YaMarketTest extends BaseClass{
    private static final Logger logger = LogManager.getLogger(YaMarketTest.class);

    @Test
    public void testMarket() throws InterruptedException {
        driver.get("https://ya.ru");
        driver.get("https://market.yandex.ru/catalog--mobilnye-telefony/54726/list");
        searchSomeText("Redmi");
        searchSomeText("Мобильные телефоны Xiaomi");
        driver.findElement(By.cssSelector("a.header2-menu__item_type_compare")).click();
        assertEquals(getCountElem("div.n-compare-head__content div.n-compare-cell"), 2);
        driver.findElement(By.cssSelector("span.n-compare-show-controls__all")).click();
        System.out.println(driver.findElement(By.xpath("//div[text()=\"Операционная система\"]")).isDisplayed());
        driver.findElement(By.cssSelector("span.n-compare-show-controls__diff")).click();
        System.out.println(driver.findElement(By.xpath("//div[text()=\"Операционная система\"]")).isDisplayed());
    }

    public int getCountElem(String locator) {
        return driver.findElements(By.cssSelector(locator)).size();
    }

    public void searchSomeText(String searchText) throws InterruptedException {
        String currentProduct;
        String popupText;
        WebElement productToCompare = driver.findElement(By.cssSelector("div.layout"));
        driver.findElement(By.id("header-search")).clear();
        driver.findElement(By.id("header-search")).sendKeys(searchText);
        driver.findElement(By.cssSelector("span.search2__button > button")).submit();
        driver.findElement(By.xpath("//div[@data-bem]//a[text()='по цене']")).click();
        Thread.sleep(3000l);
        for (WebElement eachProduct : driver.findElements(By.cssSelector("div.n-snippet-cell2"))) {
            if (checkAbilityToCompare(eachProduct)) {
                productToCompare = eachProduct;
                break;
            }
        }
        currentProduct = productToCompare.findElement(By.cssSelector("div.n-snippet-cell2__header a.link")).getText();
        productToCompare.findElement(By.cssSelector("i.image_name_compare")).click();
        Thread.sleep(3000l);
        popupText = driver.findElement(By.cssSelector("div.popup-informer__title")).getText();
        System.out.println(currentProduct);
        System.out.println(popupText);
        assertThat(popupText, "Товар " + currentProduct " добавлен к сравнению");

    }

    public void moveMouseToElement(WebElement elem){
        Actions action = new Actions(driver);
        action.moveToElement(elem);
        action.perform();
    }

    public boolean checkAbilityToCompare(WebElement elem) {
        moveMouseToElement(elem);
        //в первом условии проверяем, что есть возможность сравнения,
        //а во втором условии проверяем что товар еще не добавлен в сравнение
        if (elem.findElements(By.cssSelector("div.n-snippet-cell2__hover i")).size() > 2
                && elem.findElement(By.cssSelector("div.n-snippet-cell2 i.image_name_compare")).isDisplayed()) {
            return true;
        }
        else return false;
    }
}
