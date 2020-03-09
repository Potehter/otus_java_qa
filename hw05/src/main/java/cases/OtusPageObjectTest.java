package cases;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import pages.MainPage;
import pages.PersonalPage;
import utils.BaseClass;

import static org.junit.Assert.assertTrue;

public class OtusPageObjectTest extends BaseClass {
    private static final Logger logger = LogManager.getLogger(OtusPageObjectTest.class);

    @Test
    public void otusPageObjectTest() {
        Integer startCountContact = 0;
        logger.info("Start main logic");
        MainPage mainPage = new MainPage(driver);
        mainPage.open()
                .login()
                .goToPersonal();

        PersonalPage personalPage = new PersonalPage(driver);
        startCountContact = personalPage.getContactCount();
        personalPage.fillPersonalData()
                    .addContact("facebook", "test")
                    .addContact("vk", "test2")
                    .save();

        //закроем браузер и заново поднимем сессию
        driver.quit();
        setUp();


        MainPage mainPageCheck = new MainPage(driver);
        mainPageCheck.open()
                     .login()
                     .goToPersonal();

        PersonalPage personalPageCheck = new PersonalPage(driver);
        logger.info("startCountContact = " + startCountContact);
        assertTrue(personalPageCheck.getContactCount().equals(startCountContact + 2));
    }
}
