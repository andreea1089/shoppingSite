package utilitare;

import com.google.common.io.Files;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import tests.BaseTest;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

public class TestWatcherManager implements TestWatcher {

    private BaseTest baseTest = new BaseTest();
    private PropertyFileManager propertyFileManager = PropertyFileManager.getInstance();

    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        takeScreenshot(context);
//        closeDriver();
    }

    @Override
    public void testDisabled(ExtensionContext context, Optional<String> reason) {
        closeDriver();
    }

    @Override
    public void testSuccessful(ExtensionContext context) {
        takeScreenshot(context);
//        closeDriver();
    }

    @Override
    public void testAborted(ExtensionContext context, Throwable cause) {
        closeDriver();
    }

    /* -- private methods -- */

    private void takeScreenshot(ExtensionContext context) {
        int lengthTestName = context.getDisplayName().length() - 2;
        TakesScreenshot takesScreenshot = (TakesScreenshot) baseTest.getDriver();
        File screenshot = takesScreenshot.getScreenshotAs(OutputType.FILE);
        try {
            Files.move(screenshot, new File(propertyFileManager.getScreenshotsLocation() + context.getDisplayName().substring(0, lengthTestName) + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void closeDriver() {
        baseTest.getDriver().close();
    }

}

//TODO: Logs