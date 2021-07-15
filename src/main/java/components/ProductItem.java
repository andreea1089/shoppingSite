package components;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import pages.ProductIframePage;

import java.util.List;

public class ProductItem {
    private final String XPATH_PRODUCT_ITEMS = "//li[contains(@class,'block_product')]";
    private final String XPATH_PRODUCT_ITEM = XPATH_PRODUCT_ITEMS + "//div[@class='right-block']//a[normalize-space(text())='%s']";
    private final String XPATH_BUTTON_QUICK_VIEW = ".//span[text()='Quick view']";
    private final String XPATH_ITEMS_FROM_HOME_PARENT = "//div[.//li[@class='active']/a[text()='%s']]//ul[contains(@class,'active')]//li";
    private final String XPATH_ITEMS_FROM_PRODUCT_LIST = "//ul[not(@id) and contains(@class, 'product_list')]/li";
    private final String XPATH_ITEM_FROM_HOME_PARENT = XPATH_ITEMS_FROM_HOME_PARENT + "[.//a[normalize-space(text())='%s']]/div";
    private final String XPATH_ITEM_FROM_PRODUCT_LIST = XPATH_ITEMS_FROM_PRODUCT_LIST + "[.//a[normalize-space(text())='%s']]/div";

    WebDriver driver;
    private static Logger logger = LogManager.getLogger();

    public ProductItem(WebDriver driver) {
        this.driver = driver;
    }

    public int getNumberOfDisplayedProductItemsFromCategory(Category category) {
        return setItemsObjectsInParentCategory(category).size();
    }

    public void hoverOverProductItemFromCategoryAndClickOnQuickView(String item, Category category) {
        Actions builder = new Actions(driver);
        WebElement targetItem = setItemObjectInParentCategory(item, category);
        Action multipleActions = builder.moveToElement(targetItem)
                                        .moveToElement(targetItem.findElement(By.xpath(XPATH_BUTTON_QUICK_VIEW))).click().build();
        multipleActions.perform();

    }

    public void hoverOverProductItemFromCategory(String item, Category category) {
        Actions action = new Actions(driver);
        WebElement targetItem = setItemObjectInParentCategory(item, category);
        action.moveToElement(targetItem).perform();
    }

    /* -- private methods -- */

    private WebElement setItemObjectInParentCategory(String item, Category category) {
        WebElement productItem;
        switch (category) {
            case Popular: case BestSellers:
                productItem = driver.findElement(By.xpath(String.format(XPATH_ITEM_FROM_HOME_PARENT, category, item)));
                break;
            case ProductList:
                productItem = driver.findElement(By.xpath(String.format(XPATH_ITEM_FROM_PRODUCT_LIST, item)));
                break;
            default:
                throw new RuntimeException("Unknown category");
        }
        return productItem;
    }

    private List<WebElement> setItemsObjectsInParentCategory(Category category) {
        List<WebElement> productItems;
        switch (category) {
            case Popular: case BestSellers:
                productItems = driver.findElements(By.xpath(String.format(XPATH_ITEMS_FROM_HOME_PARENT, category)));
                break;
            case ProductList:
                productItems = driver.findElements(By.xpath(String.format(XPATH_ITEMS_FROM_PRODUCT_LIST)));
                break;
            default:
                throw new RuntimeException("Unknown category");
        }
        return productItems;
    }
}
