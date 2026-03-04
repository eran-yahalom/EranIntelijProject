package step_definitions;

import components.BreadcrumbComponent;
import components.FooterComponent;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pages.*;
import utils.DriverManager;

public class FooterStepDefinition {

    WelcomePage welcomePage;
    RegisterPage registerPage;
    LoggedInPage loggedInPage;
    GiftCardsPage giftCardsPage;
    CartItemPage cartItemPage;
    ShoppingCartPage shoppingCartPage;
    ElectronicsPage electronicsPage;
    CellPhonesPage cellPhonesPage;
    BreadcrumbComponent breadcrumbComponent;
    FooterComponent footerComponent;

    public FooterStepDefinition() {
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
        this.footerComponent = new FooterComponent(driver);
    }

    @When("user clicks on {string} link in the footer")
    public void userClicksOnLinkInTheFooter(String socialMedia) {
        Assert.assertTrue(footerComponent.clickAndMoveToSelectedSocialMedia(socialMedia));
    }

    @Then("social media {string} page will be opened in a new tab")
    public void socialMediaPageWillBeOpenedInANewTab(String socialMedia) {
        Assert.assertTrue(footerComponent.isCorrectSocialMediaPageOpened(socialMedia));
    }
}