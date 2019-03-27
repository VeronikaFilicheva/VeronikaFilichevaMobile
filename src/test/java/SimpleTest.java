import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import static java.lang.String.format;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;


public class SimpleTest {

    private static final String PROJECT_NAME = "EPM-TSTF";

    private static final String API_KEY = "a99c86ac-e119-4feb-9282-874d71adeb39";
    private static final String APPIUM_HUB = "mobilecloud.epam.com:8080";
    private static final String PLATFORM_NAME = "Android";
    private static final String PLATFORM_VERSION = "7.1.1";
    private static final String BROWSER_NAME = "chrome";
    private static final String DEVICE_NAME = "ZTE  BLADE A0620";

    private final DesiredCapabilities capabilities;

    private AppiumDriver driver = null;

    public SimpleTest() {
        capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", PLATFORM_NAME);
        capabilities.setCapability("platformVersion", PLATFORM_VERSION);
        capabilities.setCapability("browserName", BROWSER_NAME);
        capabilities.setCapability("deviceName", DEVICE_NAME);
    }

    @BeforeClass
    public void before() throws MalformedURLException {
        driver = new AndroidDriver(
                new URL(format("http://%s:%s@%s/wd/hub", PROJECT_NAME, API_KEY, APPIUM_HUB)), capabilities);

    }

    @Test
    public void demoTest() {
        final String epamUrl = "https://www.epam.com/";

        assertTrue(format("Focus is not on '%s'", BROWSER_NAME), driver.isBrowser());

        driver.get(epamUrl);


        assertEquals("Current url is incorrect", epamUrl, driver.getCurrentUrl());
        assertEquals("Page title is incorrect", "EPAM | Software Product Development Services", driver.getTitle());
    }

    @AfterClass
    public void after() {
        driver.close();
    }
}
