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
            // 1. ממתינים פעם אחת בלבד שכל הרשימה תופיע בדף (מונע קריסות של רשימה ריקה)
            wait.until(ExpectedConditions.visibilityOfAllElements(productTitles));

            // 2. רצים על הרשימה בצורה סופר מהירה
            for (WebElement title : productTitles) {
                // משתמשים ב-getText() המקורי של סלניום כי כבר הבטחנו שהאלמנטים גלויים לעין
                if (title.getText().contains(itemName)) {
                    return click(title); // משתמש במתודת ה-click הבטוחה שלך ומחזיר true
                }
            }
        } catch (Exception e) {
            System.err.println("Failed to select product: " + itemName + ". Exception: " + e.getMessage());
        }

        return false; // אם המוצר לא נמצא או שהייתה שגיאה, מחזירים false בצורה חלקה
    }
}
