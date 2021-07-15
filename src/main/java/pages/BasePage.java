package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import utils2.ElementWait;


public class BasePage {
    protected WebDriver driver;
    protected Actions actions;
    private By logo = By.xpath("//div[@id='header_logo']");
    private static final String XPATH_MENIU_BUTTON = "//ul[contains(@class,'menu-content')]//a[@title ='%s']";
    private static final String XPATH_SEARCH_INPUT = "//input[@id='search_query_top']";
    private static final String XPATH_SERACH_BUTTON = "//button[contains(@class,'button-search')]";
    private static final String XPATH_CART_LINK = "//div[@class='shopping_cart']/a";
    private static final String XPATH_SIGN_IN_BUTTON = "//a[@class='login']";
    private static final String XPATH_CONTACT_LINK = "//div[@id='contact-link']/a";
    private static final String XPATH_CART_CHECK_OUT_BUTTON = "//a[@id='button_order_cart']/span";

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isProductDisplayed(String product) {
        return true;
    }

    public void search(String input) {
        driver.findElement(By.xpath(XPATH_SEARCH_INPUT)).sendKeys(input);
        driver.findElement(By.xpath(XPATH_SERACH_BUTTON)).click();
    }

    public boolean isPageLoaded() {
        // code
        return true;
    }

    public HomePage clickOnLogo() {
        driver.findElement(logo).click();
        return new HomePage(driver);
    }

    public SignInPage clickOnSignInButton() {
        driver.findElement(By.xpath(XPATH_SIGN_IN_BUTTON)).click();
        return new SignInPage(driver);
    }

    public ShoppingCartPage clickOnShoppingCart() {
        driver.findElement(By.xpath(XPATH_CART_LINK)).click();
        return new ShoppingCartPage(driver);
    }

    public void hoverOverShoppingCart() {
        actions = new Actions(driver);
        actions.moveToElement(driver.findElement(By.xpath(XPATH_CART_LINK))).perform();
    }

    public ShoppingCartPage hoverOverShoppingCartAndCheckOut() {
        actions = new Actions(driver);
        hoverOverShoppingCart();
        actions.moveToElement(driver.findElement(By.xpath(XPATH_CART_CHECK_OUT_BUTTON))).click().perform();
        return new ShoppingCartPage(driver);
    }

    public void logOut() {
    }
}
