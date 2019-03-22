package scenarios.webTests;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import scenarios.Hooks;
import setup.Properties;
import io.restassured.RestAssured;

@Test(groups = "web")
public class IanaWebTest extends Hooks {
    // Send PropertyFile in the Hooks to run correct prepareDriver() in @BeforeSuite.
    protected IanaWebTest() {
        super(Properties.WEB);
    }

    @Test(description = "Open a site")
    public void webTest() throws Exception {
        driver().get(SUT);
        driverWait().until(ExpectedConditions.urlToBe(SUT + "/"));

        //Assert that page returns "200" status code
        Assert.assertEquals(RestAssured.get(SUT).statusCode(), 200);

        //Check that Title is correct
        Assert.assertEquals(driver().getTitle(), "Internet Assigned Numbers Authority");
    }
}