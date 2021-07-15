package utils2;

import com.google.common.base.Function;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.time.Duration;
import java.time.temporal.TemporalUnit;


public class ElementWait {
    private static WebDriver driver;

    public ElementWait(WebDriver driver) {
        this.driver = driver;
    }
    public void waitUntilElementWithXpathIsDisplayed(String xpath, int timeout, TemporalUnit timeUnit, String failureMessage) throws TimeoutException {
        Wait<WebDriver> wait = new FluentWait(driver)
                .withTimeout(Duration.of(timeout, timeUnit))
                .withMessage(failureMessage)
                .ignoring(NoSuchElementException.class, StaleElementReferenceException.class);
        wait.until(elementIsDisplayed(xpath));
    }

    public void waitUntilElementWithXpathIsDisplayedWithPolling(){
    }

    /* -- private methods -- */

    private static Function<WebDriver, Boolean> elementIsDisplayed(final String xpath){
        return new Function<WebDriver, Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return driver.findElement(By.xpath(xpath)).isDisplayed();
            }
        };
    }
}
