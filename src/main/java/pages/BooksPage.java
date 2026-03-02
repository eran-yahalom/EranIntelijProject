package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class BooksPage extends BasePage {

    @FindBy(css = "#products-orderby")
    private WebElement sortByDropdown;

    @FindBy(css = "products-pagesize")
    private WebElement displayDropdown;

    @FindBy(css = "#products-viewmode")
    private WebElement viewModeDropdown;

    @FindBy(css = ".price-range-selector li:nth-child(1) a")
    private WebElement priceFilterUnder25;

    @FindBy(css = ".price-range-selector li:nth-child(2) a")
    private WebElement priceFilter25To50;

    @FindBy(css = ".price-range-selector li:nth-child(3) a")
    private WebElement priceFilterOver50;

    @FindBy(css = ".product-title")
    private List<WebElement> productTitle;

    @FindBy(css = ".description")
    private List<WebElement> productDescription;

    @FindBy(css = ".price.actual-price")
    private List<WebElement> productActualPrice;

    @FindBy(css = ".price.old-price")
    private List<WebElement> productOldPrice;

    @FindBy(css = ".rating")
    private List<WebElement> productRating;

    @FindBy(css = ".product-grid .product-item")
    private WebElement gridElement;

    @FindBy(css = ".product-list .product-item")
    private WebElement listElement;


    public BooksPage(WebDriver driver) {
        super(driver);
    }

    public boolean isViewDisplayed(String viewType) {

        Map<String, Supplier<Boolean>> viewMap = Map.of(
                "list", () -> listElement.isDisplayed(),
                "grid", () -> gridElement.isDisplayed()
        );

        return viewMap
                .getOrDefault(viewType.toLowerCase(),
                        () -> {
                            throw new IllegalArgumentException("Invalid view type: " + viewType);
                        })
                .get();
    }

    public boolean selectPriceFilter(String priceRange) {
        Map<String, WebElement> priceFilterMap = Map.of(
                "under25", priceFilterUnder25,
                "25to50", priceFilter25To50,
                "over50", priceFilterOver50
        );

        WebElement filterElement = priceFilterMap.get(priceRange.toLowerCase());
        if (filterElement != null) {
            return click(filterElement);
        } else {
            throw new IllegalArgumentException("Invalid price range: " + priceRange);
        }
    }

    public boolean selectSortByOption(String option) {
        return selectOptionFromDropdown(sortByDropdown, option);
    }

    public boolean selectDisplayOption(String option) {
        return selectOptionFromDropdown(displayDropdown, option);
    }

    public boolean selectViewModeOption(String option) {
        return selectOptionFromDropdown(viewModeDropdown, option);
    }


}
