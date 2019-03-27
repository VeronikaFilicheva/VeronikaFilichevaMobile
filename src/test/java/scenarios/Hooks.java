package scenarios;

import org.testng.annotations.*;
import setup.DriverSetup;
import setup.Properties;

@Test(groups = {"native", "web"})
public class Hooks extends DriverSetup {
    private Properties currentProps;

    public Hooks(Properties props) {
        this.currentProps = props;
    }

    @BeforeSuite(description = "Prepare driver to run test")
    public void setUp() throws Exception {
        setPropertyFile(currentProps);
        driver();
    }

    @AfterSuite(description = "Close driver")
    public void tearDown() throws Exception {
        driver().closeApp();
    }
}
