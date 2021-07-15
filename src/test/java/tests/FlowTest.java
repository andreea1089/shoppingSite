package tests;

import components.Category;
import components.ProductItem;

import org.junit.jupiter.api.Test;
import pages.BasePage;
import pages.HomePage;
import pages.ProductIframePage;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FlowTest extends BaseTest {
//    private static Logger logger = LogManager.getLogger();
    private ProductItem productItem;
    private ProductIframePage productIframePage;

    @Test
    public void test() {
        productItem = new ProductItem(getDriver());
        productIframePage = new ProductIframePage(getDriver());

        homePage.search("Faded Short Sleeve T-shirts");
        productItem.hoverOverProductItemFromCategoryAndClickOnQuickView("Faded Short Sleeve T-shirts", Category.ProductList);
        productIframePage.switchToProductItemIframe();
        productIframePage.addProductItemToCart();

        //todo: shouldn't this productIframePage be actually inside product item?
    }
}
