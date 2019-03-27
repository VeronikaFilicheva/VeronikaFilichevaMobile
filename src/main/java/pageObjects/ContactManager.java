package pageObjects;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.HasOnScreenKeyboard;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class ContactManager {

    private final String appPackageName = "com.example.android.contactmanager:id/";
    private AppiumDriver driver;
    private By contactTitle = By.id("android:id/title");
    private By addContactButton = By.id(appPackageName + "addContactButton");
    private By targetAccountSpinner = By.id(appPackageName + "accountSpinner");
    private By contactNameField = By.id(appPackageName + "contactNameEditText");
    private By contactFormEditField = By.id(appPackageName + "contactPhoneEditText");
    private By contactEmailField = By.id(appPackageName + "contactEmailEditText");

    public ContactManager (AppiumDriver driver) {this.driver = driver;}


    public void clickOnAddButton() {
        driver.findElement(addContactButton).click();
    }

    public void checkTitle() {
        assertTrue(driver.findElement(contactTitle).isDisplayed());
    }

    public void checkElementsDisplayed() {
        assertTrue(driver.findElement(targetAccountSpinner).isDisplayed());
        assertTrue(driver.findElement(contactNameField).isDisplayed());
        assertTrue(driver.findElement(contactFormEditField).isDisplayed());
        assertTrue(driver.findElement(contactEmailField).isDisplayed());

    }

    public void checkVirtualKeybordIsShown() {
     //   assertTrue(((HasOnScreenKeyboard) driver).isKeyboardShown());
    }


}