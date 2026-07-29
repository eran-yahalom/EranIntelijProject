package pages;

import com.google.inject.Inject;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Log4j2
public class CheckOutConfirmOrderPage extends BasePage {

    @FindBy(css = "[class='billing-info'] [class='name']")
    private WebElement billingInfoName;

    @FindBy(css = "[class='billing-info'] [class='email']")
    private WebElement billingInfoEmail;

    @FindBy(css = "[class='billing-info'] [class='phone']")
    private WebElement billingInfoPhone;

    @FindBy(css = "[class='shipping-method']")
    private WebElement shippingMethodInfo;

    @FindBy(css = "[class='payment-method']")
    private WebElement paymentMethodInfo;

    @FindBy(css = "[class='product-price order-total']")
    private WebElement orderTotalPrice;

    @FindBy(css = "[class='button-1 confirm-order-next-step-button']")
    private WebElement confirmOrderButton;


    @Inject
    public CheckOutConfirmOrderPage(WebDriver driver) {
        super(driver);
    }

    public String getBillingInfoName() {
        return billingInfoName.getText();
    }

    public String getBillingInfoEmail() {
        return billingInfoEmail.getText();
    }

    public String getBillingInfoPhone() {
        return billingInfoPhone.getText();
    }

    public String getShippingMethodInfo() {
        return shippingMethodInfo.getText();
    }

    public String getPaymentMethodInfo() {
        return paymentMethodInfo.getText();
    }

    public String getOrderTotalPrice() {
        return orderTotalPrice.getText();
    }

    public boolean clickConfirmOrderButton() {
        if (!click(confirmOrderButton)) {
            log.error("Failed to click Confirm Order button");
            return false;
        }
        return true;
    }
}
