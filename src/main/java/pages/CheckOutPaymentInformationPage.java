package pages;

import com.google.inject.Inject;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Log4j2
public class CheckOutPaymentInformationPage extends BasePage {

    @FindBy(css = "#CreditCardType")
    private WebElement creditCardTypeDropdown;

    @FindBy(css = "#CardholderName")
    private WebElement cardholderNameField;

    @FindBy(css = "#CardNumber")
    private WebElement cardNumberField;

    @FindBy(css = "#ExpireMonth")
    private WebElement expireMonthDropdown;

    @FindBy(css = "#ExpireYear")
    private WebElement expireYearDropdown;

    @FindBy(css = "#CardCode")
    private WebElement cardCodeField;

    @FindBy(css = "[class='button-1 payment-info-next-step-button']")
    private WebElement continueButton;


    @Inject
    public CheckOutPaymentInformationPage(WebDriver driver) {
        super(driver);
    }

    public boolean enterCardholderName(String cardholderName) {
        if (!fillText(cardholderNameField, cardholderName)) {
            log.error("Failed to enter Cardholder Name");
            return false;
        }
        return true;
    }

    public boolean enterCardNumber(String cardNumber) {
        if (!fillText(cardNumberField, cardNumber)) {
            log.error("Failed to enter Card Number");
            return false;
        }
        return true;
    }

    public boolean enterCardCode(String cardCode) {
        if (!fillText(cardCodeField, cardCode)) {
            log.error("Failed to enter Card Code");
            return false;
        }
        return true;
    }

    public boolean selectCreditCardType(String cardType) {
        if (!selectOptionFromDropdownByVisibleText(creditCardTypeDropdown, cardType)) {
            log.error("Failed to select Credit Card Type: " + cardType);
            return false;
        }
        return true;
    }

    public boolean selectExpireMonth(String month) {
        if (!selectOptionFromDropdownByVisibleText(expireMonthDropdown, month)) {
            log.error("Failed to select Expire Month: " + month);
            return false;
        }
        return true;
    }

    public boolean selectExpireYear(String year) {
        if (!selectOptionFromDropdownByVisibleText(expireYearDropdown, year)) {
            log.error("Failed to select Expire Year: " + year);
            return false;
        }
        return true;
    }

    public boolean clickContinueButton() {
        try {
            click(continueButton);
            return true;
        } catch (Exception e) {
            log.error("Failed to click Continue button: " + e.getMessage());
            return false;
        }
    }
}
