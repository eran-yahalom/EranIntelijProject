package pages;

import com.google.inject.Inject;
import components.CategoryItemsComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.Utils;

public class SearchPage extends BasePage {

    CategoryItemsComponent categoryItems = new CategoryItemsComponent(driver);
    @FindBy(css = ".basic-search div label:nth-child(1)")
    public WebElement searchKeyWordHeader;

    @FindBy(css = "sic-search div label:nth-child(2)")
    public WebElement advanceSearchHeader;

    @FindBy(css = "[id='Q']")
    public WebElement advanceSearchField;

    @FindBy(css = "[id='As']")
    public WebElement advanceSearchCheckBox;

    @FindBy(css = ".button-1.search-button")
    public WebElement searchButton;

    @FindBy(css = ".search-results .result")
    public WebElement noSearchResultsText;

    @Inject
    public SearchPage(WebDriver driver) {
        super(driver);
    }


    public boolean doesAllSearchResultsContainSearchText(String textToSearch) {
        return Utils.isSearchedItemInCategoryItems(driver, categoryItems.getListOfWebElementTitles(), textToSearch);
    }

    public int getNumberOfSearchResults() {
        return categoryItems.getNumberOfProducts();
    }
}
