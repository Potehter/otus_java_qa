package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import utils.BaseClass;

import java.security.AuthProvider;

public abstract class AbstractPage {
    protected WebDriver driver;
    private static final Logger logger = LogManager.getLogger(AbstractPage.class);

    public AbstractPage(WebDriver driver) {
        this.driver = driver;
    }

}

