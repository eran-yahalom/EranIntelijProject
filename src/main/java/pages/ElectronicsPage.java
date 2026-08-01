package pages;

import com.google.inject.Inject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class ElectronicsPage extends BasePage {

    @FindBy(css = ".page-title h1")
    private WebElement header;

    @FindBy(css = ".sub-category-item h2")
    private List<WebElement> productTitles;

    @FindBy(css = ".sub-category-item")
    private List<WebElement> productItems;

    @Inject
    public ElectronicsPage(WebDriver driver) {
        super(driver);
    }

    public boolean selectItem(String itemName) {
        try {
            wait.until(ExpectedConditions.visibilityOfAllElements(productTitles));

            for (WebElement title : productTitles) {
                if (title.getText().contains(itemName)) {
                    return click(title);
                }
            }
        } catch (Exception e) {
            System.err.println("Failed to select product: " + itemName + ". Exception: " + e.getMessage());
        }

        return false;
    }
}
