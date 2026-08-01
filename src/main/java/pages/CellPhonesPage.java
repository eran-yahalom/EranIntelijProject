package pages;

import com.google.inject.Inject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class CellPhonesPage extends BasePage {

    @FindBy(css = ".product-item")
    private List<WebElement> cellPhonesCategoryItems;

    @FindBy(css = ".details .product-title")
    private List<WebElement> cellPhonesCategoryTitles;

    @Inject
    public CellPhonesPage(WebDriver driver) {
        super(driver);
    }

    public boolean selectItem(String itemName) {
        try {
            wait.until(ExpectedConditions.visibilityOfAllElements(cellPhonesCategoryTitles));

            for (WebElement title : cellPhonesCategoryTitles) {
                if (title.getText().contains(itemName)) {
                    return click(title);
                }
            }
        } catch (Exception e) {
            System.err.println("Failed to select item: " + itemName + ". Exception: " + e.getMessage());
        }

        return false;
    }
}
