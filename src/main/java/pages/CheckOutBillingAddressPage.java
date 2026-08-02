package pages;

import com.google.inject.Inject;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.Utils;

import java.util.List;

@Log4j2
public class CheckOutBillingAddressPage extends BasePage {

    @FindBy(css = "[name='billing_address_id']")
    private WebElement billingAddressDropDown;

    @FindBy(css = "#BillingNewAddress_FirstName")
    private WebElement firstNameField;

    @FindBy(css = "#BillingNewAddress_LastName")
    private WebElement lastNameField;

    @FindBy(css = "#BillingNewAddress_Email")
    private WebElement emailField;

    @FindBy(css = "#BillingNewAddress_CountryId")
    private WebElement countryDropdown;

    @FindBy(css = "[name='BillingNewAddress.StateProvinceId']")
    private WebElement stateDropdown;

    @FindBy(css = "#BillingNewAddress_City")
    private WebElement cityField;

    @FindBy(css = "#BillingNewAddress_Address1")
    private WebElement addressField;

    @FindBy(css = "#BillingNewAddress_ZipPostalCode")
    private WebElement zipCodeField;

    @FindBy(css = "#BillingNewAddress_PhoneNumber")
    private WebElement phoneNumberField;

    @FindBy(css = "#billing-buttons-container [class='button-1 new-address-next-step-button']")
    private WebElement continueButton;

    @FindBy(css = "[class='field-validation-error']")
    private List<WebElement> billingPageRequiredErrorMessages;

    @FindBy(css = "#opc-billing [class='step-title'] h2")
    private WebElement billingAddressTitle;

    @FindBy(css = "[for='billing-address-select']")
    private WebElement selectBillingAddressText;

    @Inject
    public CheckOutBillingAddressPage(WebDriver driver) {
        super(driver);
    }

    public boolean enterFirstName(String firstName) {
        if (!fillText(firstNameField, firstName)) {
            log.error("Failed to enter First Name");
            return false;
        }
        return true;
    }

    public boolean enterLastName(String lastName) {
        if (!fillText(lastNameField, lastName)) {
            log.error("Failed to enter Last Name");
            return false;
        }
        return true;
    }

    public boolean enterEmail(String email) {
        if (!fillText(emailField, email)) {
            log.error("Failed to enter Email");
            return false;
        }
        return true;
    }

    public boolean selectCountry(String country) {
        if (!selectOptionFromDropdownByVisibleText(countryDropdown, country)) {
            log.error("Failed to select Country: {}", country);
            return false;
        }
        return true;
    }

    public boolean selectBillingAddress(String billingAddress) {
        try {
            wait.until(ExpectedConditions.visibilityOf(billingAddressDropDown));
            if (isDisplayed(billingAddressDropDown)) {
                log.info("Billing address dropdown is displayed. Selecting option: {}", billingAddress);
                selectOptionFromDropdownByVisibleText(billingAddressDropDown, billingAddress);
                return true;
            }

            log.info("Billing address dropdown is not displayed. Moving forward...");
            return true;

        } catch (Exception e) {
            log.error("Failed to process billing address selection for address: {}. Exception: {}",
                    billingAddress, e.getMessage());
            return true;
        }
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
            log.error("Failed to enter City");
            return false;
        }
        return true;
    }

    public boolean enterAddress(String address) {
        if (!fillText(addressField, address)) {
            log.error("Failed to enter Address");
            return false;
        }
        return true;
    }

    public boolean enterZipCode(String zipCode) {
        if (!fillText(zipCodeField, zipCode)) {
            log.error("Failed to enter Zip Code");
            return false;
        }
        return true;
    }

    public boolean enterPhoneNumber(String phoneNumber) {
        if (!fillText(phoneNumberField, phoneNumber)) {
            log.error("Failed to enter Phone Number");
            return false;
        }
        return true;
    }

    public boolean clickContinueButton() {
        return click(continueButton);
    }

    public boolean isSelectBillingAddressHeaderDisplayed() {
        return isDisplayed(selectBillingAddressText)
                && getText(selectBillingAddressText).equals(Utils.readProperty("checkoutBillingAddressPageHeader"));
    }
}
