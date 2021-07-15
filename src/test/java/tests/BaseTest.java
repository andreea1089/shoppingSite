package tests;
import com.sun.xml.internal.bind.v2.TODO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import pages.BasePage;
import pages.HomePage;
import utilitare.PropertyFileManager;
import utilitare.TestWatcherManager;
import utilitare.WebDriverManager;

@ExtendWith(TestWatcherManager.class)
public class BaseTest {
    private static Logger logger = LogManager.getLogger(BaseTest.class);
    private WebDriverManager webDriverManager = new WebDriverManager();
    protected PropertyFileManager propertyFileManager = PropertyFileManager.getInstance();
    private String url = propertyFileManager.getUrl();
    protected HomePage homePage;
    // TODO : cum istantiem clasele cel mai binee???? Aici in base cu before class?
    // TODO: padac suntem pe o pagina care are un elemet centrat, ex, cart, de care ne folosim mereu, putem sa creem o variabila webElement cart, ppe care
    // sa o instantiem in constructor (adica sa o gasim cu findElementBY)

    @BeforeEach
    public void initialize() {
        logger.info("Starting to initialize the webdriver");
        webDriverManager.initializeDriver();
        logger.info("Webdriver was initialized");
        getDriver().get(url);
        homePage = new HomePage(getDriver());
    }

    public WebDriver getDriver() {
        return webDriverManager.getDriver();
    }
}
