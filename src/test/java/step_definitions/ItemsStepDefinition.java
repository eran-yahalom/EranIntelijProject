package step_definitions;

import components.CategoryItemsComponent;
import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pages.*;
import utils.DriverManager;
import utils.GeneratorUtils;

public class ItemsStepDefinition {

    private String email = GeneratorUtils.generateEmail();
    private String password = GeneratorUtils.generatePassword();

    CategoryItemsComponent categoryItemsComponent;


    public ItemsStepDefinition() {
        WebDriver driver = DriverManager.getDriver();
        this.categoryItemsComponent = new CategoryItemsComponent(driver);
    }

    @When("user clicks on sort by dropdown and selects {string}")
    public void userClicksOnSortByDropdownAndSelects(String sortOption) {
        Assert.assertTrue(categoryItemsComponent.selectSortByOption(sortOption));
    }

    @Then("the books should be sorted by {string}")
    public void booksShouldBeSortedCorrectly(String sortOption) {
        Assert.assertTrue(categoryItemsComponent.areBooksSortedCorrectly(sortOption));
    }

    @When("user clicks on display dropdown and selects {string}")
    public void userClicksOnDisplayDropdownAndSelects(String sortOption) {
        Assert.assertTrue(categoryItemsComponent.selectDisplayOption(sortOption));
    }

    @Then("{string} books should be displayed")
    public void booksShouldBeDisplayedCorrectly(String displayOption) {
        Assert.assertTrue(
                categoryItemsComponent.doesNumberOfDisplayedBooksMatchDisplayOption(displayOption),
                "Displayed books count does NOT match selected display option: " + displayOption
        );
    }

    @When("user clicks on view as dropdown and selects {string}")
    public void userClicksOnViewAsDropdownAndSelects(String viewOption) {
        Assert.assertTrue(categoryItemsComponent.selectViewModeOption(viewOption));
    }

    @Then("the books should be displayed in {string} view")
    public void booksShouldBeDisplayedInView(String viewOption) {
        Assert.assertTrue(categoryItemsComponent.isViewDisplayed(viewOption));
    }

    @And("sort by dropdown should be set to {string}")
    public void sortByDropdownShouldBeSetTo(String expectedOption) {
        Assert.assertTrue(categoryItemsComponent.areDropDownsSetToDefault(expectedOption));
    }

    @And("display dropdown should be set to {string}")
    public void displayDropdownShouldBeSetTo(String expectedOption) {
        Assert.assertTrue(categoryItemsComponent.areDropDownsSetToDefault(expectedOption));
    }

    @And("view as dropdown should be set to {string}")
    public void viewAsDropdownShouldBeSetTo(String expectedOption) {
        Assert.assertTrue(categoryItemsComponent.areDropDownsSetToDefault(expectedOption));
    }

    @And("user sets price range filter to {string}")
    public void userSetsPriceRangeFilterTo(String priceRangeFilter) {
        Assert.assertTrue(categoryItemsComponent.selectPriceFilter(priceRangeFilter));
    }

    @Then("only books within the price range of {string} should be displayed")
    public void onlyBooksWithinPriceRangeShouldBeDisplayed(String priceRangeFilter) {
        Assert.assertTrue(categoryItemsComponent.areDisplayedBooksWithinPriceRange(priceRangeFilter));
    }

    @And("number of displayed filter by price elements is correct")
    public void numberOfDisplayedFilterByPriceElementsIsCorrect() {
        Assert.assertTrue(categoryItemsComponent.isNumberOfFilterByPriceElementsAfterFilterCorrect());
        Assert.assertTrue(categoryItemsComponent.isRemovePriceRangeFilterButtonDisplayed());
    }

    @And("user clicks on remove price range filter button")
    public void userClicksOnRemovePriceRangeFilterButton() {
        Assert.assertTrue(categoryItemsComponent.clickRemovePriceRangeFilterButton());
    }

    @Then("all price range filters should be displayed again")
    public void allPriceRangeFiltersShouldBeDisplayedAgain() {
        Assert.assertTrue(categoryItemsComponent.isNumberOfFilterByPriceElementsCorrect());
    }
}
