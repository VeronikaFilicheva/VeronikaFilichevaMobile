package scenarios.nativeTests;

import org.testng.annotations.Test;
import pageObjects.ContactManager;
import scenarios.Hooks;
import setup.Properties;

@Test(groups = "native")
public class ContactManagerTest extends Hooks {
    // Send PropertyFile in the Hooks to read from correct property file in  prepareDriver() in @BeforeSuite
    protected ContactManagerTest() throws Exception {
        super(Properties.NATIVE);
    }

    @Test(description = "Open Add Contact screen and check elements displaying")
    public void simplestAddContactScreenTest() throws Exception {
        ContactManager contactManager = new ContactManager(driver());

        // Click on Add Contact Button
        contactManager.clickOnAddButton();

        //Check Add Contact Title
        contactManager.checkTitle();

        //Check Elements Target Account,Contact Name, Contact Phone, Contact Email  Are Displayed
        contactManager.checkElementsDisplayed();

        //Check virtual keyboard appears
       // contactManager.checkVirtualKeybordIsShown();

    }

}
