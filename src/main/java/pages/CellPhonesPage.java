package pages;

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

    public CellPhonesPage(WebDriver driver) {
        super(driver);
    }

    public boolean selectItem(String itemName) {
        wait.until(ExpectedConditions.elementToBeClickable(cellPhonesCategoryTitles.get(cellPhonesCategoryTitles.size() - 1)));
        for (WebElement title : cellPhonesCategoryTitles) {
            if (getText(title).contains(itemName)) {
                return click(title);
            }
        }
        return false;
    }
}
