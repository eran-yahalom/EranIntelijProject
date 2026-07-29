package pages;

import com.google.inject.Inject;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

@Log4j2
public class CheckOutPaymentMethodPage extends BasePage {

    @FindBy(css = "[name='paymentmethod']")
    private List<WebElement> cardsSelectionRadioButtons;

    @FindBy(css = "[class='button-1 payment-method-next-step-button']")
    private WebElement continueButton;

    @Inject
    public CheckOutPaymentMethodPage(WebDriver driver) {
        super(driver);
    }

    public boolean selectPaymentMethod(String paymentMethod) {
        for (WebElement radioButton : cardsSelectionRadioButtons) {
            String value = radioButton.getAttribute("value");
            if (value.equalsIgnoreCase(paymentMethod)) {
                if (!click(radioButton)) {
                    log.error("Failed to select payment method: " + paymentMethod);
                    return false;
                }
                return true;
            }
        }
        log.error("Payment method not found: " + paymentMethod);
        return false;
    }
}
