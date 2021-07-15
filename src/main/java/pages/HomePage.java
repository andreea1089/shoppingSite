package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage {
    private final String XPATH_HOME_PAGE_TAB = "//ul[@id='home-page-tabs']//a[text()='%s']";

    public HomePage(WebDriver driver) {
        super(driver);
    }


    public void clickOnHomePageTab(String tabName){
        driver.findElement(By.xpath(String.format(XPATH_HOME_PAGE_TAB, tabName))).click();
    }

}

