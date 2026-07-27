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
            // 1. ממתינים שהרשימה תתמלא באלמנטים (לפחות אלמנט אחד גלוי)
            wait.until(ExpectedConditions.visibilityOfAllElements(cellPhonesCategoryTitles));

            // 2. רצים על הרשימה בצורה יעילה
            for (WebElement title : cellPhonesCategoryTitles) {
                // קריאה ישירה ל-getText() של סלניום, כי כבר הבטחנו שהאלמנטים גלויים בשורה למעלה
                if (title.getText().contains(itemName)) {
                    return click(title); // משתמש במתודת ה-click הבטוחה שלך
                }
            }
        } catch (Exception e) {
            System.err.println("Failed to select item: " + itemName + ". Exception: " + e.getMessage());
        }

        return false; // אם הפריט לא נמצא או שהייתה שגיאה, מחזירים false בצורה חלקה
    }
}
