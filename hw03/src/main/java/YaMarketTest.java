import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

public class YaMarketTest extends BaseClass{
    private static final Logger logger = LogManager.getLogger(YaMarketTest.class);

    @Test
    public void testMarket() {
        driver.get("https://ya.ru");
        driver.get("https://market.yandex.ru/catalog--mobilnye-telefony/54726/list");

    }
}
