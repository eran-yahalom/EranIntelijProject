package step_definitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
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
    TopPanelPage topPanelPage;
    TopMenuPage topMenuPage;
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
        this.topPanelPage = new TopPanelPage(driver);
        this.topMenuPage = new TopMenuPage(driver);
        this.giftCardsPage = new GiftCardsPage(driver);
        this.cartItemPage = new CartItemPage(driver);
        this.shoppingCartPage = new ShoppingCartPage(driver);
        this.electronicsPage = new ElectronicsPage(driver);
        this.cellPhonesPage = new CellPhonesPage(driver);
        this.booksPage = new BooksPage(driver);
    }

    @And("user clicks on sort by dropdown and selects {string}")
    public void userClicksOnSortByDropdownAndSelects(String sortOption) {
        Assert.assertTrue(booksPage.selectSortByOption(sortOption));
    }

    @Then("books should be sorted correctly by {string}")
    public void booksShouldBeSortedCorrectly(String sortOption) {
        Assert.assertTrue(booksPage.areBooksSortedCorrectly(sortOption));
    }
}
