package pages;

import com.google.inject.Inject;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

@Log4j2
public class CheckOutShippingAddressPage extends BasePage {

    @FindBy(css = "select[name='shipping_address_id']")
    private WebElement shippingAddressDropdown;

    @FindBy(css = "#ShippingNewAddress_FirstName")
    private WebElement firstNameField;

    @FindBy(css = "#ShippingNewAddress_LastName")
    private WebElement lastNameField;

    @FindBy(css = "#ShippingNewAddress_Email")
    private WebElement emailField;

    @FindBy(css = "#ShippingNewAddress_CountryId")
    private WebElement countryDropdown;

    @FindBy(css = "[name='ShippingNewAddress.StateProvinceId']")
    private WebElement stateDropdown;

    @FindBy(css = "#ShippingNewAddress_City")
    private WebElement cityField;

    @FindBy(css = "#ShippingNewAddress_Address1")
    private WebElement addressField;

    @FindBy(css = "#ShippingNewAddress_ZipPostalCode")
    private WebElement zipCodeField;

    @FindBy(css = "#ShippingNewAddress_PhoneNumber")
    private WebElement phoneNumberField;

    @FindBy(css = "#shipping-buttons-container [class='button-1 new-address-next-step-button']")
    private WebElement shippingContinueButton;

    @FindBy(css = "#PickUpInStore")
    private WebElement pickUpInStoreCheckbox;


    @Inject
    public CheckOutShippingAddressPage(WebDriver driver) {
        super(driver);
    }

    public String getFirstName() {
        return firstNameField.getAttribute("value");
    }

    public String getLastName() {
        return lastNameField.getAttribute("value");
    }

    public String getEmail() {
        return emailField.getAttribute("value");
    }

    public boolean selectCountry(String country) {
        if (!selectOptionFromDropdownByVisibleText(countryDropdown, country)) {
            log.error("Failed to select Country: {}", country);
            return false;
        }
        return true;
    }

    public boolean selectState(String state) {
        if (!selectOptionFromDropdownByVisibleText(stateDropdown, state)) {
            log.error("Failed to select State: {}", state);
            return false;
        }
        return true;
    }

    public boolean enterCity(String city) {
        if (!fillText(cityField, city)) {
            log.error("Failed to enter City: {}", city);
            return false;
        }
        return true;
    }

    public boolean enterAddress(String address) {
        if (!fillText(addressField, address)) {
            log.error("Failed to enter Address: {}", address);
            return false;
        }
        return true;
    }

    public boolean enterZipCode(String zipCode) {
        if (!fillText(zipCodeField, zipCode)) {
            log.error("Failed to enter Zip Code: {}", zipCode);
            return false;
        }
        return true;
    }

    public boolean enterPhoneNumber(String phoneNumber) {
        if (!fillText(phoneNumberField, phoneNumber)) {
            log.error("Failed to enter Phone Number: {}", phoneNumber);
            return false;
        }
        return true;
    }

    public boolean clickShippingContinueButton() {
        if (!click(shippingContinueButton)) {
            log.error("Failed to click Shipping Continue Button");
            return false;
        }
        return true;
    }

    public boolean selectPickUpInStoreOption() {
        if (!click(pickUpInStoreCheckbox)) {
            log.error("Failed to select Pick Up In Store option");
            return false;
        }
        return true;
    }

    public boolean selectShippingAddress(String billingAddress) {
        try {
            wait.until(ExpectedConditions.visibilityOf(shippingAddressDropdown));
            if (isDisplayed(shippingAddressDropdown)) {
                log.info("Billing address dropdown is displayed. Selecting option: {}", billingAddress);
                selectOptionFromDropdownByVisibleText(shippingAddressDropdown, billingAddress);
                return true;
            }

            log.info("Billing address dropdown is not displayed. Moving forward...");
            return true;

        } catch (Exception e) {
            log.info("Cant see the shipping address selector",
                    billingAddress, e.getMessage());
            return true;
        }
    }
}
