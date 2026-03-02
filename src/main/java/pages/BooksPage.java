package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import utils.Utils;

import java.util.*;
import java.util.NoSuchElementException;
import java.util.function.Supplier;

import static utils.Utils.getPricesFromUI;

public class BooksPage extends BasePage {

    @FindBy(css = "#products-orderby")
    private WebElement sortByDropdown;

    @FindBy(css = "#products-pagesize")
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
        return selectOptionFromDropdownByVisibleText(sortByDropdown, option);
    }

    public boolean selectDisplayOption(String option) {
        return selectOptionFromDropdownByVisibleText(displayDropdown, option);
    }

    public boolean selectViewModeOption(String option) {
        return selectOptionFromDropdownByVisibleText(viewModeDropdown, option);
    }

    public boolean areBooksOrderedByPriceLowToHigh() {
        try {
            List<Double> pricesFromBooksPage = getPricesFromUI(productActualPrice);
            Utils.verifyPricesSortedHighToLow(pricesFromBooksPage);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean areBooksOrderedByPriceHighToLow() {
        try {
            List<Double> pricesFromBooksPage = getPricesFromUI(productActualPrice);
            List<Double> sortedPrices = Utils.verifyPricesSortedHighToLow(pricesFromBooksPage);
            return pricesFromBooksPage.equals(sortedPrices);
        } catch (Exception e) {
            return false;
        }
    }

    public boolean areBooksSortedAToZ() {
        return Utils.verifyNameSortedAToZ(productTitle);
    }

    public boolean areBooksOrderedZToA() {
        return Utils.verifyNameSortedZToA(productTitle);
    }

    public boolean areBooksSortedCorrectly(String orderType) {
        Map<String, Supplier<Boolean>> sortingChecks = Map.of(
                "price: low to high", this::areBooksOrderedByPriceLowToHigh,
                "price: high to low", this::areBooksOrderedByPriceHighToLow,
                "name: a to z", this::areBooksSortedAToZ,
                "name: z to a", this::areBooksOrderedZToA
        );

        return sortingChecks.getOrDefault(orderType.toLowerCase(),
                        () -> {
                            throw new IllegalArgumentException("Invalid order type: " + orderType);
                        })
                .get();
    }

    public int getNumberOfDisplayedBooks() {
        return productTitle.size();
    }

    public boolean areBooksDisplayedInGridView() {
        return isViewDisplayed("grid");
    }

    public boolean areBooksDisplayedInListView() {
        return isViewDisplayed("list");
    }

    public boolean doesNumberOfDisplayedBooksMatchDisplayOption(String displayOption) {
        int expectedCount = switch (displayOption.toLowerCase()) {
            case "4" -> 4;
            case "8" -> 8;
            case "12" -> 12;
            default -> throw new IllegalArgumentException("Invalid display option: " + displayOption);
        };

        return getNumberOfDisplayedBooks() == expectedCount;
    }

    public boolean isSortByDropdownIsSetToDefault() {
        return getSelectedOptionText(sortByDropdown).equals("Position");
    }

    public boolean isDisplayDropdownIsSetToDefault() {
        return getSelectedOptionText(displayDropdown).equals("8");
    }

    public boolean isViewModeDropdownIsSetToDefault() {
        return getSelectedOptionText(viewModeDropdown).equals("Grid");
    }

    public String getSelectedOptionText(WebElement dropdown) {
        try {
            return dropdown.findElement(By.cssSelector("option:checked")).getText();
        } catch (NoSuchElementException e) {
            throw new RuntimeException("No option is currently selected in the dropdown.");
        }
    }

    public boolean areDropDownsSetToDefault(String orderType) {
        Map<String, Supplier<Boolean>> sortingChecks = Map.of(
                "position", this::isSortByDropdownIsSetToDefault,
                "8", this::isDisplayDropdownIsSetToDefault,
                "grid", this::isViewModeDropdownIsSetToDefault
        );

        return sortingChecks.getOrDefault(orderType.toLowerCase(),
                        () -> {
                            throw new IllegalArgumentException("Invalid order type: " + orderType);
                        })
                .get();
    }
}