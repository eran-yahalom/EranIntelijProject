package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class TopPanelPage extends BasePage {

    @FindBy(css = ".ico-register")
    private WebElement registerLink;

    @FindBy(css = ".ico-login")
    private WebElement loginLink;

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


    public TopPanelPage(WebDriver driver) {
        super(driver);
    }

    public boolean clickOnRegisterLink() {
        return click(registerLink);
    }

    public boolean clickOnLoginLink() {
        return click(loginLink);
    }

    public boolean clickOnShoppingCartLink() {
        By cartLink = By.cssSelector("#topcartlink .ico-cart");

        for (int i = 0; i < 2; i++) {
            try {
                wait.until(ExpectedConditions.elementToBeClickable(cartLink));
                return click(shoppingCartLink);

            } catch (StaleElementReferenceException e) {
                // retry
            }
        }
        return false;
    }

    public int getShoppingCartItemCount() {
        try {
            String itemCountText =getText(shoppingCartItemCount).replaceAll("[()]", "").trim();
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

    public boolean clickOnWishlistLink() {
        return click(wishlistLink);
    }

    public boolean clickOnSearchButton() {
        return click(searchButton);
    }

    public boolean fillSearchInput(String searchTerm) {
        return fillText(searchInput, searchTerm);
    }

    public boolean clickOnHomeLogo() {
        return click(homeLogo);
    }
}


