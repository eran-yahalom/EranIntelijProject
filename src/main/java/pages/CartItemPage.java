package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Utils;

import java.time.Duration;

public class CartItemPage extends BasePage {

    @FindBy(css = ".add-to-cart-panel [value='Add to cart']")
    private WebElement addToCartButton;

    @FindBy(css = "#bar-notification p:nth-child(2)")
    private WebElement validRecipientNameErrorMessage;

    @FindBy(css = "#bar-notification p:nth-child(3)")
    private WebElement validRecipientEmailErrorMessage;

    @FindBy(css = ".recipient-name")
    private WebElement recipientNameInput;

    @FindBy(css = ".recipient-email")
    private WebElement recipientEmailInput;

    @FindBy(css = ".sender-name")
    private WebElement senderNameInput;

    @FindBy(css = ".sender-email")
    private WebElement senderEmailInput;

    @FindBy(css = "#bar-notification p a")
    private WebElement shoppingCartLinkInNotification;

    @FindBy(css = "#bar-notification p")
    private WebElement addToCartSuccessNotificationMessage;

    public CartItemPage(WebDriver driver) {
        super(driver);
    }

    public boolean clickAddToCartButton() {
        wait.until(ExpectedConditions.elementToBeClickable(addToCartButton));
        return click(addToCartButton);
    }

    public boolean isValidTRecipientNameMessagePresented() {
        return Utils.isMessageDisplayedCorrectly(driver, validRecipientNameErrorMessage, "cartEnterValidRecipientName");
    }

    public boolean isValidTRecipientEmailMessagePresented() {
        return Utils.isMessageDisplayedCorrectly(driver, validRecipientEmailErrorMessage, "cartEnterValidRecipientEmail");
    }

    public boolean fillRecipientNameFromSender() {
        wait.until(ExpectedConditions.elementToBeClickable(senderNameInput));
        String senderName = senderNameInput.getAttribute("value");
        return fillText(recipientNameInput, senderName);
    }

    public boolean fillRecipientEmailFromSender() {
        //  wait.until(ExpectedConditions.elementToBeClickable(senderEmailInput));
        String senderEmail = senderEmailInput.getAttribute("value");
        return fillText(recipientEmailInput, senderEmail);
    }

    public boolean addToCartSuccessMessagePresented() {
        return Utils.isMessageDisplayedCorrectly(driver, addToCartSuccessNotificationMessage, "addToCartSuccessNotificationMessage");
    }

    public boolean clickOnCartLinkInNotification() {
        wait.until(ExpectedConditions.elementToBeClickable(shoppingCartLinkInNotification));
        return click(shoppingCartLinkInNotification);
    }

}
