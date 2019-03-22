package scenarios.nativeTests;

import io.appium.java_client.HasOnScreenKeyboard;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import scenarios.Hooks;
import setup.Properties;

import java.util.Arrays;
import java.util.List;

@Test(groups = "native")
public class ContactManagerTest extends Hooks {
    // Send PropertyFile in the Hooks to read from correct property file in  prepareDriver() in @BeforeSuite
    protected ContactManagerTest() {
        super(Properties.NATIVE);
    }

    @Test(description = "Open Add Contact screen and check elements displaying")
    public void simplestAddContactScreenTest() throws Exception {

        String appPackageName = "com.example.android.contactmanager:id/";

        // click on Add Contact Button
        By addButton = By.id(appPackageName + "addContactButton");
        driver().findElement(addButton).click();

        //Check Add Contact Title
        By addContactTitle = By.id("android:id/title");
        Assert.assertTrue(driver().findElement(addContactTitle).isDisplayed());

        //Find WebElements for each title
        WebElement targetAccount = driver().findElement(By.id("Target Account"));
        WebElement contactName = driver().findElement(By.id("Contact Name"));
        WebElement contactPhone = driver().findElement(By.id("Contact Phone"));
        WebElement contactEmail = driver().findElement(By.id("Contact Email"));

        //Find List of WebElements for fields titles
        List<WebElement> actualTitles = Arrays.asList(targetAccount, contactName,
                contactPhone, contactEmail);
        List<String> expectedTitles = Arrays.asList("Target Account", "Contact Name",
                "Contact Phone", "Contact Email");

        //Check that each title is displayed
        for (WebElement title : actualTitles) {
            Assert.assertTrue(title.isDisplayed());
        }

        //Check titles' name
        for (int i = 0; i < expectedTitles.size(); i++) {
            Assert.assertEquals(actualTitles.get(i).getText(), expectedTitles.get(i));
        }

        //Find all necessary fields on "Add Contact" screen
        WebElement targetAccountSpinner = driver().findElement(By.id(appPackageName + "accountSpinner"));
        WebElement contactNameField = driver().findElement(By.id(appPackageName + "contactNameEditText"));
        WebElement contactPhoneField = driver().findElement(By.id(appPackageName + "contactPhoneEditText"));
        WebElement contactEmailField = driver().findElement(By.id(appPackageName + "contactEmailEditText"));

        //Assert all found fields are displayed
        Assert.assertTrue(targetAccountSpinner.isDisplayed());
        Assert.assertTrue(contactNameField.isDisplayed());
        Assert.assertTrue(contactPhoneField.isDisplayed());
        Assert.assertTrue(contactEmailField.isDisplayed());

        //Check virtual keyboard appears
        Assert.assertTrue(((HasOnScreenKeyboard) driver()).isKeyboardShown());

    }

}
