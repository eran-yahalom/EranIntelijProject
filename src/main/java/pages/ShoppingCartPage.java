package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ShoppingCartPage extends BasePage {

    @FindBy(css = ".page-title h1")
    private WebElement header;

    @FindBy(css = ".cart-item-row")
    private List<WebElement> cartItemRow;


    @FindBy(css = ".qty-input")
    private List<WebElement> quantityInputFields;

    @FindBy(css = "[name='removefromcart']")
    private List<WebElement> removeFromCartCheckboxes;

    @FindBy(css = "[name='updatecart']")
    private WebElement updateCartButton;

    @FindBy(css = ".product-subtotal")
    private List<WebElement> productTotalPrice;

    @FindBy(css = ".cart-total tr:nth-child(1) .cart-total-right")
    private WebElement subtotalPrice;

    @FindBy(css = ".cart-total tr:nth-child(2) .cart-total-right")
    private WebElement shippingCost;

    @FindBy(css = ".cart-total tr:nth-child(3) .cart-total-right")
    private WebElement taxCost;

    @FindBy(css = ".cart-total tr:nth-child(4) .cart-total-right")
    private WebElement totalPrice;

    public ShoppingCartPage(WebDriver driver) {
        super(driver);
    }

    public boolean isHeaderDisplayedCorrectly() {
        return confirmPageHeader(header, "shoppingCartPageHeader");
    }

    public int countNumberOfItemsInCart() {
        try {
            return cartItemRow.size();
        } catch (Exception e) {
            return 0;
        }
    }

    public int getQuantityOfItemInCart() {
        int qty = 0;
        try {
            for (int i = 0; i < cartItemRow.size(); i++) {
                int quantity = Integer.parseInt(quantityInputFields.get(i).getAttribute("value"));
                qty += quantity;
            }
            return qty;
        } catch (Exception e) {
            return 0;
        }
    }

    public boolean deleteItemFromCart() {
        if (cartItemRow == null || cartItemRow.isEmpty()) {
            return true;
        }
        for (WebElement checkbox : removeFromCartCheckboxes) {
            if (!checkbox.isSelected()) {
                click(checkbox);
            }
        }
        return click(updateCartButton);
    }

    public int sumPriceOfProductsInCart() {
        int total = 0;
        for (WebElement price : productTotalPrice) {
            String priceText = getText(price).replace(".00", "").trim();
            total += Integer.parseInt(priceText);
        }
        return total;
    }

    public int sumTaxesAndShippingCost() {
        String shippingCostText = getText(shippingCost).replace(".00", "").trim();
        String taxCostText = getText(taxCost).replace(".00", "").trim();
        return Integer.parseInt(shippingCostText) + Integer.parseInt(taxCostText);
    }

    public boolean isTotalPriceCalculatedCorrectly() {
        int expectedTotal = sumPriceOfProductsInCart() + sumTaxesAndShippingCost();
        String totalPriceText = getText(totalPrice).replace(".00", "").trim();
        int actualTotal = Integer.parseInt(totalPriceText);
        return expectedTotal == actualTotal;
    }
}