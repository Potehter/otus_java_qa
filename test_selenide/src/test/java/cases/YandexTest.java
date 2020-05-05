package cases;

import com.codeborne.selenide.SelenideElement;
import org.junit.Test;
import org.openqa.selenium.By;
import utils.BaseHooks;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;

public class YandexTest {

    @Test
    public void MarketTest() {
        open("https://market.yandex.ru/");
        $(By.id("header-search")).setValue("Проточные водонагреватели thermex").pressEnter();
        $(By.xpath("//div[@class='n-filter-panel-dropdown__main']//a[text()='по цене']")).click();
        String locatorElemPrice = "//div[@class='price']";
        $(By.xpath(locatorElemPrice)).should(visible);
        SelenideElement priceElem = $(By.xpath(locatorElemPrice));
        System.out.println(priceElem.getText());
        System.out.println(priceElem.text());
        System.out.println(priceElem.getText());
    }
}
