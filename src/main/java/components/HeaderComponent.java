package components;

import com.google.inject.Inject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HeaderComponent extends BaseComponent {

    @FindBy(css = ".ico-register")
    private WebElement registerLink;

    @FindBy(css = ".ico-login")
    private WebElement loginLink;

    @FindBy(css = ".ico-logout")
    private WebElement logoutLink;

    @FindBy(css = "#topcartlink .ico-cart")
    private WebElement shoppingCartLink;

    @FindBy(css = "#topcartlink .ico-cart span:nth-child(2)")
    private WebElement shoppingCartItemCount;

    @FindBy(css = ".header-links li .ico-wishlist")
    private WebElement wishlistLink;

    @FindBy(css = ".header-links li .ico-wishlist span:nth-child(2)")
    private WebElement wishlistItemCount;

    @FindBy(css = "[value='Search store']")
    private WebElement searchInput;

    @FindBy(css = "[value='Search']")
    private WebElement searchButton;

    @FindBy(css = ".header-logo")
    private WebElement homeLogo;

    @FindBy(css = ".search-results .result")
    private WebElement noItemsInResultsMessage;

    @Inject
    public HeaderComponent(WebDriver driver) {
        super(driver); // שולח את הדרייבר ל-BaseComponent לאתחול ה-Wait
        PageFactory.initElements(driver, this); // ✅ חובה: מאתחל את ה-WebElements במחלקה זו
    }

    public boolean clickOnRegisterLink() {
        return click(registerLink);
    }

    public boolean clickOnLoginLink() {
        return click(loginLink);
    }

    public boolean clickOnWishlistLink() {
        return click(wishlistLink);
    }

    public boolean clickOnHomeLogo() {
        return click(homeLogo);
    }

    public boolean clickOnLogoutLink() {
        return click(logoutLink);
    }

    public boolean clickOnShoppingCartLink() {
        return click(shoppingCartLink);
    }

    public int getShoppingCartItemCount() {
        try {
            String itemCountText = getText(shoppingCartItemCount)
                    .replaceAll("[()]", "")
                    .trim();
            return Integer.parseInt(itemCountText);
        } catch (Exception e) {
            return 0;
        }
    }

    public int getWishlistItemCount() {
        try {
            String itemCountText = getText(wishlistItemCount).trim();
            return Integer.parseInt(itemCountText);
        } catch (Exception e) {
            return 0;
        }
    }

    public boolean fillSearchInput(String searchTerm) {
        return fillText(searchInput, searchTerm);
    }

    public boolean clickOnSearchButton() {
        return click(searchButton);
    }

    public String getAlertPopUpText() {
        return getAlertText();
    }

    public boolean closeAlertPopUp() {
        return clickOnAlertOKButton();
    }

    public String getNoResultsTextMessage() {
        return getText(noItemsInResultsMessage);
    }
}
