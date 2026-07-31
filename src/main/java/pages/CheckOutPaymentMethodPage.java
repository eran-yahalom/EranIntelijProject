package pages;

import com.google.inject.Inject;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

@Log4j2
public class CheckOutPaymentMethodPage extends BasePage {

    @FindBy(css = "[name='paymentmethod']")
    private List<WebElement> cardsSelectionRadioButtons;

    @FindBy(css = "[class='button-1 payment-method-next-step-button']")
    private WebElement continueButton;

    @FindBy(css = "[class='payment-details']")
    private List<WebElement> paymentMethods;

    @Inject
    public CheckOutPaymentMethodPage(WebDriver driver) {
        super(driver);
    }

    public boolean selectPaymentMethod(String paymentMethod) {
        wait.until(ExpectedConditions.visibilityOfAllElements(paymentMethods));
        for (WebElement radioButton : paymentMethods) {
            String value = radioButton.findElement(By.cssSelector("[for^='paymentmethod']")).getText();
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

    public boolean clickOnCheckOutPaymentMethodContinueButton(){
        return click(continueButton);
    }
}
