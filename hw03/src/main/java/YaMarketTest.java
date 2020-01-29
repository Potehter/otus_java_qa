import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static org.hamcrest.MatcherAssert.assertThat;

public class YaMarketTest extends BaseClass{
    private static final Logger logger = LogManager.getLogger(YaMarketTest.class);
    private long startTime;

    @Before
    public void getStartTime(){
        startTime = System.currentTimeMillis();
    }

    @After
    public void printTime(){
        long finish = System.currentTimeMillis();
        logger.info("Время выполнения теста: {} секунд", (finish - startTime)/1000);
    }

    @Test
    public void testMarket() throws InterruptedException {
        logger.info("Set timeout of implicitlyWait");
        driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
        WebElement elem;
        driver.get("https://ya.ru");
        driver.get("https://market.yandex.ru/catalog--mobilnye-telefony/54726/list");
        searchSomeText("Redmi");
        searchSomeText("Мобильные телефоны Xiaomi");

        elem = driver.findElement(By.cssSelector("a.header2-menu__item_type_compare"));
        //не всегда работает переход, если не наводить мышку
        moveMouseToElement(elem);
        elem.click();

        logger.info("Count of comparing products: "
                + getCountElem("div.n-compare-head__content div.n-compare-cell"));
        Assert.assertEquals(getCountElem("div.n-compare-head__content div.n-compare-cell"), 2);
        driver.findElement(By.cssSelector("span.n-compare-show-controls__all")).click();
        new WebDriverWait(driver, 2l)
                .until(ExpectedConditions.invisibilityOfElementLocated(
                        By.cssSelector("div.n-compare-table_loading_yes")));
        boolean visibilityOS = driver.findElement(By.xpath("//div[text()=\"Операционная система\"]")).isDisplayed();
        logger.info("Операционная система отображается: " + visibilityOS);
        Assert.assertTrue(visibilityOS);
        driver.findElement(By.cssSelector("span.n-compare-show-controls__diff")).click();
        visibilityOS = driver.findElement(By.xpath("//div[text()=\"Операционная система\"]")).isDisplayed();
        logger.info("Операционная система отображается: " + visibilityOS);
        Assert.assertFalse(visibilityOS);
    }

    public int getCountElem(String locator) {
        return driver.findElements(By.cssSelector(locator)).size();
    }

    public void searchSomeText(String searchText) throws InterruptedException {
        String currentProduct;
        String popupText;
        logger.info("Start search " + searchText);
        WebElement productToCompare = driver.findElement(By.cssSelector("div.layout"));
        driver.findElement(By.id("header-search")).clear();
        driver.findElement(By.id("header-search")).sendKeys(searchText);
        driver.findElement(By.cssSelector("span.search2__button > button")).submit();
        driver.findElement(By.xpath("//div[@data-bem]//a[text()='по цене']")).click();
        new WebDriverWait(driver, 5l)
                .until(ExpectedConditions.attributeToBe(
                        By.cssSelector("div.n-filter-applied-results__content"),
                        "style",
                        "height: auto;"));

        for (WebElement eachProduct : driver.findElements(By.cssSelector("div.n-filter-applied-results div.n-snippet-cell2"))) {
            if (checkAbilityToCompare(eachProduct)) {
                productToCompare = eachProduct;
                break;
            }
        }

        currentProduct = productToCompare.findElement(By.cssSelector("div.n-snippet-cell2__header a.link")).getAttribute("title");
        productToCompare.findElement(By.cssSelector("i.image_name_compare")).click();
        new WebDriverWait(driver, 5l)
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.popup-informer__title")));
        popupText = driver.findElement(By.cssSelector("div.popup-informer__title")).getText();
        logger.info("Find product " + currentProduct);
        logger.info("Popup text: " + popupText);
        currentProduct = "Товар " + currentProduct + " добавлен к сравнению";
        assertThat(popupText, popupText.equals(currentProduct));

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
