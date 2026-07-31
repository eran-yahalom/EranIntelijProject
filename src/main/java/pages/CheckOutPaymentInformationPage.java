package pages;

import com.google.inject.Inject;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

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

    @FindBy(css = "[class='info'] p")
    private WebElement CODText;

    @FindBy(css = "[class='info'] td>p:nth-child(1)")
    private WebElement checkMoneyOrderText;

    @FindBy(css = "#PurchaseOrderNumber")
    private WebElement purchaseOrderInput;


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

    public boolean executePaymentHandler(String paymentMethod,
                                         String purchaseNo,
                                         String cardType,
                                         String cardName,
                                         String cardNumber,
                                         String expiryYear,
                                         String cardCode) {
        Map<String, Supplier<Boolean>> paymentHandlers = new HashMap<>();


        paymentHandlers.put("Purchase Order", () -> fillPurchaseOrderDetails(purchaseNo));
        paymentHandlers.put("Credit Card", () -> fillCreditCardDetails(cardType, cardName, cardNumber, expiryYear, cardCode));
        paymentHandlers.put("Cash On Delivery (COD) (7.00)", this::verifyCodInformation);
        paymentHandlers.put("Check / Money Order (5.00)", this::verifyCheckInformation);

        Supplier<Boolean> handler = paymentHandlers.get(paymentMethod.trim());

        if (handler != null) {
            return handler.get();
        } else {
            log.error("Unsupported payment method provided: {}", paymentMethod);
            return false;
        }
    }

    private boolean fillPurchaseOrderDetails(String poNumber) {
        if (poNumber == null || poNumber.trim().isEmpty()) {
            log.error("PO Number is required for Purchase Order payment method.");
            return false;
        }
        return fillText(purchaseOrderInput, poNumber);
    }

    private boolean fillCreditCardDetails(String cardType, String cardName, String cardNumber, String cardYear, String cardCode) {
        if (creditCardTypeDropdown == null) {
            log.error("CreditCardDetails object cannot be null.");
            return false;
        }
        return selectOptionFromDropdownByVisibleText(
                creditCardTypeDropdown, cardType) &&
                fillText(cardholderNameField, cardName) &&
                fillText(cardNumberField, cardNumber) &&
                selectOptionFromDropdownByVisibleText(expireYearDropdown, cardYear) &&
                fillText(cardCodeField, cardCode);
    }

    private boolean verifyCodInformation() {
        wait.until(ExpectedConditions.visibilityOf(CODText));
        String text = CODText.getText().trim();
        return text.contains("You will pay by COD");
    }

    private boolean verifyCheckInformation() {
        wait.until(ExpectedConditions.visibilityOf(checkMoneyOrderText));
        String text = checkMoneyOrderText.getText().trim();
        return text.contains("Mail Personal or Business Check");
    }
}
