package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils2.ElementWait;

import java.time.temporal.ChronoUnit;

public class ProductIframePage extends BasePage {
    private final String XPATH_BUTTON_ADD_TO_CART = ".//button[./span[text()='Add to cart']]";
    private final String XPATH_IFRAME_PRODUCT_ITEM = "//iframe[contains(@id,'fancybox')]";
    private static Logger logger = LogManager.getLogger();
    private ElementWait wait;

    public ProductIframePage(WebDriver driver) {
        super(driver);
    }

    public void switchToProductItemIframe() {
        wait = new ElementWait(driver);
        WebElement productIframe = driver.findElement(By.xpath(XPATH_IFRAME_PRODUCT_ITEM));
        wait.waitUntilElementWithXpathIsDisplayed(XPATH_IFRAME_PRODUCT_ITEM, 10, ChronoUnit.SECONDS, "Product item frame is not displayed");
        driver.switchTo().frame(productIframe);
    }

    public void addProductItemToCart() {
        driver.findElement(By.xpath(XPATH_BUTTON_ADD_TO_CART)).click();
    }


}
