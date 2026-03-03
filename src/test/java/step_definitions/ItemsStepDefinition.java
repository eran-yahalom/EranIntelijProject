package step_definitions;

import io.cucumber.java.bs.A;
import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pages.*;
import utils.DriverManager;
import utils.GeneratorUtils;

public class ItemsStepDefinition {

    private String email = GeneratorUtils.generateEmail();
    private String password = GeneratorUtils.generatePassword();

    WelcomePage welcomePage;
    RegisterPage registerPage;
    LoggedInPage loggedInPage;
    GiftCardsPage giftCardsPage;
    CartItemPage cartItemPage;
    ShoppingCartPage shoppingCartPage;
    ElectronicsPage electronicsPage;
    CellPhonesPage cellPhonesPage;
    BooksPage booksPage;

    public ItemsStepDefinition() {
        WebDriver driver = DriverManager.getDriver();
        this.welcomePage = new WelcomePage(driver);
        this.registerPage = new RegisterPage(driver);
        this.loggedInPage = new LoggedInPage(driver);
        this.giftCardsPage = new GiftCardsPage(driver);
        this.cartItemPage = new CartItemPage(driver);
        this.shoppingCartPage = new ShoppingCartPage(driver);
        this.electronicsPage = new ElectronicsPage(driver);
        this.cellPhonesPage = new CellPhonesPage(driver);
        this.booksPage = new BooksPage(driver);
    }

    @When("user clicks on sort by dropdown and selects {string}")
    public void userClicksOnSortByDropdownAndSelects(String sortOption) {
        Assert.assertTrue(booksPage.selectSortByOption(sortOption));
    }

    @Then("books should be sorted correctly by {string}")
    public void booksShouldBeSortedCorrectly(String sortOption) {
        Assert.assertTrue(booksPage.areBooksSortedCorrectly(sortOption));
    }

    @When("user clicks on display dropdown and selects {string}")
    public void userClicksOnDisplayDropdownAndSelects(String sortOption) {
        Assert.assertTrue(booksPage.selectDisplayOption(sortOption));
    }

    @Then("books should be displayed correctly by {string}")
    public void booksShouldBeDisplayedCorrectly(String displayOption) {
        Assert.assertTrue(
                booksPage.doesNumberOfDisplayedBooksMatchDisplayOption(displayOption),
                "Displayed books count does NOT match selected display option: " + displayOption
        );
    }

    @When("user clicks on view as dropdown and selects {string}")
    public void userClicksOnViewAsDropdownAndSelects(String viewOption) {
        Assert.assertTrue(booksPage.selectViewModeOption(viewOption));
    }

    @Then("books should be displayed in {string} view")
    public void booksShouldBeDisplayedInView(String viewOption) {
        Assert.assertTrue(booksPage.isViewDisplayed(viewOption));
    }

    @And("sort by dropdown should be set to {string}")
    public void sortByDropdownShouldBeSetTo(String expectedOption) {
        Assert.assertTrue(booksPage.areDropDownsSetToDefault(expectedOption));
    }

    @And("display dropdown should be set to {string}")
    public void displayDropdownShouldBeSetTo(String expectedOption) {
        Assert.assertTrue(booksPage.areDropDownsSetToDefault(expectedOption));
    }

    @And("view as dropdown should be set to {string}")
    public void viewAsDropdownShouldBeSetTo(String expectedOption) {
        Assert.assertTrue(booksPage.areDropDownsSetToDefault(expectedOption));
    }

    @And("user sets price range filter to {string}")
    public void userSetsPriceRangeFilterTo(String priceRangeFilter) {
        Assert.assertTrue(booksPage.selectPriceFilter(priceRangeFilter));
    }

    @Then("only books within the price range of {string} should be displayed")
    public void onlyBooksWithinPriceRangeShouldBeDisplayed(String priceRangeFilter) {
        Assert.assertTrue(booksPage.areDisplayedBooksWithinPriceRange(priceRangeFilter));
    }

    @And("number of displayed filter by price elements is correct")
    public void numberOfDisplayedFilterByPriceElementsIsCorrect() {
        Assert.assertTrue(booksPage.isNumberOfFilterByPriceElementsAfterFilterCorrect());
        Assert.assertTrue(booksPage.isRemovePriceRangeFilterButtonDisplayed());
    }

    @And("user clicks on remove price range filter button")
    public void userClicksOnRemovePriceRangeFilterButton() {
        Assert.assertTrue(booksPage.clickRemovePriceRangeFilterButton());
    }

    @Then("all price range filters should be displayed again")
    public void allPriceRangeFiltersShouldBeDisplayedAgain() {
        Assert.assertTrue(booksPage.isNumberOfFilterByPriceElementsCorrect());
    }
}
