package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SearchPage extends BasePage {

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

    public SearchPage(WebDriver driver) {
        super(driver);
    }


}
