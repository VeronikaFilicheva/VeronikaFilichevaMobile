package setup;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.net.URL;

import static io.appium.java_client.remote.MobileCapabilityType.APP;

// Initialize a driver with test properties
public class DriverSetup extends TestProperties{
    private static AppiumDriver driverSingle = null;
    private static WebDriverWait waitSingle;

    // Properties to be read
    private static String AUT; // (mobile) app under testing
    protected static String SUT; // site under testing
    private static String TEST_PLATFORM;
    private static String DRIVER;
    private static String DEVICE_NAME;
    private static String UDID;


    protected void prepareDriver() throws Exception {
        TEST_PLATFORM = getProp("platform");
        DEVICE_NAME = getProp("device");
        DRIVER = getProp("driver");
        AUT = getProp("aut");
        String t_sut = getProp("sut");
        SUT = t_sut == null ? null : "http://"+t_sut;
        UDID = getProp("udid");

        DesiredCapabilities capabilities = new DesiredCapabilities();
        String browserName;

        //Check type of test platform to set proper browserName capability
        switch (TEST_PLATFORM) {
            case "Android":
                capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, DEVICE_NAME);
                browserName = "Chrome";
                break;
            case "iOS":
                browserName = "Safari";
                capabilities.setCapability(MobileCapabilityType.UDID, UDID);
                break;
            default:
                throw new Exception("Unclear type of mobile platform");
        }
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, TEST_PLATFORM);

        //Setup type of application
        if(AUT != null && SUT == null){
            // Native
            File app = new File(AUT);
            capabilities.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
        }
        else if(SUT != null && AUT == null){
            // Web
            capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, browserName);
        }
        else{
            throw new Exception("Unclear type of mobile app");
        }

        // Init driver with new AppiumDriver object
        if (driverSingle == null) driverSingle = new AppiumDriver(new URL(DRIVER), capabilities);

        // Set an object to handle timeouts
        if (waitSingle == null) waitSingle = new WebDriverWait(driver(), 10);

    }

    //Method to access singleton
    protected AppiumDriver driver() throws Exception {
        if (driverSingle == null) prepareDriver();
        return driverSingle;
    }

    protected WebDriverWait driverWait() {
        return waitSingle;
    }
}