package utilitare;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class WebDriverManager {
    private static WebDriver driver;
    private PropertyFileManager propertyFileManager = PropertyFileManager.getInstance();
    private String browserType = propertyFileManager.getBrowserType();
    private String driverLocation = propertyFileManager.getDriverLocation();
    private Integer implicitWait = propertyFileManager.getImplicitWait();

    public void initializeDriver() {
        getBrowserByBrowserType(browserType);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(implicitWait, TimeUnit.SECONDS);
    }

    public WebDriver getDriver() {
        return driver;
    }

    /* --- Private methods --- */

    private WebDriver getBrowserByBrowserType(String browserType) {
        switch (browserType) {
            case "chrome": {
                System.setProperty("webdriver.chrome.driver", driverLocation);
                driver = new ChromeDriver();
                break;
            }
            case "ie": {
                // code for ie
                break;
            }
            case "firefox": {
                // code for firefox
                break;
            }
            default:
                throw new RuntimeException("Browser name not valid \"" + browserType + "\"");
        }
        return driver;
    }
}

//TODO: Logs