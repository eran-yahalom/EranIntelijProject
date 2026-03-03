package step_definitions;

import components.BreadcrumbComponent;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pages.*;
import utils.DriverManager;

public class GeneralStepDefinition {

    WelcomePage welcomePage;
    RegisterPage registerPage;
    LoggedInPage loggedInPage;
    GiftCardsPage giftCardsPage;
    CartItemPage cartItemPage;
    ShoppingCartPage shoppingCartPage;
    ElectronicsPage electronicsPage;
    CellPhonesPage cellPhonesPage;
    BreadcrumbComponent breadcrumbComponent;

    public GeneralStepDefinition() {
        WebDriver driver = DriverManager.getDriver();
        this.welcomePage = new WelcomePage(driver);
        this.registerPage = new RegisterPage(driver);
        this.loggedInPage = new LoggedInPage(driver);
        this.giftCardsPage = new GiftCardsPage(driver);
        this.cartItemPage = new CartItemPage(driver);
        this.shoppingCartPage = new ShoppingCartPage(driver);
        this.electronicsPage = new ElectronicsPage(driver);
        this.cellPhonesPage = new CellPhonesPage(driver);
        this.breadcrumbComponent = new BreadcrumbComponent(driver);
    }

    @Then("breadcrumb should match the item name {string}")
    public void breadcrumbShouldMatchItemName(String expectedBreadcrumb) {
        Assert.assertTrue(cartItemPage.doesBreadcrumbContainProductName(expectedBreadcrumb));
    }

    @And("user clicks on the selected breadcrumb {string}")
    public void userClicksOnTheSelectedBreadcrumb(String breadcrumbName) {
        Assert.assertTrue(breadcrumbComponent.selectAndClickOnSpecificBreadCrumb(breadcrumbName));
    }

    @Then("user should be navigated to the {string} page")
    public void userShouldBeNavigatedToThePage(String expectedPageHeader) {
        String actualPageHeader = welcomePage.getPageHeader().toLowerCase();
        Assert.assertEquals(actualPageHeader, expectedPageHeader.toLowerCase());
    }

    @Then("number of links in page bread crumb should be {int}")
    public void numberOfLinksInPageBreadCrumbShouldBe(int expectedNumberOfLinks) {
        int actualNumberOfLinks = breadcrumbComponent.getBreadcrumbSteps().size();
        Assert.assertEquals(actualNumberOfLinks, expectedNumberOfLinks);
    }
}
