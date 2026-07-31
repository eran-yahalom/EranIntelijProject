package pages;

import com.google.inject.Inject;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

@Log4j2
public class CheckOutThankYouPage extends BasePage {

    @FindBy(css = "[class='title']")
    private WebElement thankYouMessage;

    @FindBy(css = "[class='details'] li a")
    private WebElement orderDetailsLink;

    @FindBy(css = "ul[class='details'] li:nth-child(1)")
    private WebElement orderNumber;

    @FindBy(css = "[class='button-2 order-completed-continue-button']")
    private WebElement continueButton;

    @Inject
    public CheckOutThankYouPage(WebDriver driver) {
        super(driver);
    }

    public String getThankYouMessage() {
        return thankYouMessage.getText();
    }

    public String getOrderNumber() {
        wait.until(ExpectedConditions.visibilityOf(orderNumber));
        return orderNumber.getText().replaceAll("[^0-9]", "");
    }

    public boolean clickOrderDetailsLink() {
        return click(orderDetailsLink);
    }

    public boolean clickOnContinueButton() {
        return click(continueButton);
    }
}
