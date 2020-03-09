import com.opencsv.CSVWriter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class CrawlerClass extends BaseClass {
    private static final Logger logger = LogManager.getLogger(CrawlerClass.class);

    @Test
    public void testBooks() {
    int maxValue = 0;
    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    driver.get("https://www.mann-ivanov-ferber.ru/books/allbooks/?booktype=audiobook");
    while (checkVisiabilityLoader()) {
        maxValue = scroll();
        }
    logger.info("Count of books: " + maxValue);
    writeDataLineByLine("C:/otus/java_QA/test.csv", driver);
    }

    private int scroll() {
        int result;
        //Чтоб загрузился список нужно не только скролить, но и сдвинуться с элемента loader
        moveToTop();
        Actions action = new Actions(driver);
        WebElement elem = driver.findElement(By.cssSelector("div.js-page-loader"));
        action.moveToElement(elem);
        action.perform();
        result = driver.findElements(By.cssSelector("div.js-item")).size();
        logger.info("Now count of elements is: " + result);
        return result;
    }

    private void moveToTop() {
        Actions action = new Actions(driver);
        WebElement elem = driver.findElement(By.cssSelector("div.js-item"));
        action.moveToElement(elem);
        action.perform();
    }

    private boolean checkVisiabilityLoader() {
        return driver.findElement(By.cssSelector("div.js-page-loader")).isDisplayed();
    }

    public static void writeDataLineByLine(String filePath, WebDriver driver) {
        // first create file object for file placed at location
        // specified by filepath
        File file = new File(filePath);
        try {
            // create FileWriter object with file as parameter
            FileWriter outputfile = new FileWriter(file);

            // create CSVWriter object filewriter object as parameter
            CSVWriter writer = new CSVWriter(outputfile);

            // adding header to csv
            String[] header = { "Link", "Name", "Author", "Price", "Link to download sample" };
            writer.writeNext(header);

            // add data to csv
            fillBookInfo(driver, writer);

            // closing writer connection
            writer.close();
        }
        catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private static void fillBookInfo(WebDriver driver, CSVWriter writer) {
        String link;
        String name;
        String author;
        String price;
        String downloadLink;
        String mainWindow = driver.getWindowHandle();
        Integer count = 0;
        for (WebElement book: driver.findElements(By.cssSelector("div.js-item"))) {
            count ++;
            logger.info("Collect data book # " + count);
            link = book.findElement(By.tagName("a")).getAttribute("href");
            book.click();
            switchToNewTab(driver);
            name = driver.findElement(By.cssSelector("h1.p-sky-title > span")).getText();
            author = driver.findElement(By.cssSelector("div.authors span")).getText();
            price = driver.findElement(By.cssSelector("div[ng-if='bookData.types.audiobook.sale']")).getAttribute("data-price");
            downloadLink = driver.findElement(By.id("read-pdf")).getAttribute("modal-params");
            addLineToFile(writer, new String[]{link, name, author, price, downloadLink});
            driver.close();
            driver.switchTo().window(mainWindow);
            if (count > 20) break;
        }
    }

    private static void switchToNewTab(WebDriver driver) {
        Set<String> handles = driver.getWindowHandles();
        List<String> tabs = new ArrayList<>(handles);
        String currentTab = driver.getWindowHandle();
        tabs.remove(currentTab);
        String anotherTab = tabs.get(0);
        driver.switchTo().defaultContent();
        driver.switchTo().window(anotherTab);
    }

    private static void addLineToFile(CSVWriter writer, String[] header) {
        writer.writeNext(header);
    }
}
