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
    CartItemPage cartItemPage;
    BreadcrumbComponent breadcrumbComponent;

    public GeneralStepDefinition() {
        WebDriver driver = DriverManager.getDriver();
        this.welcomePage = new WelcomePage(driver);
        this.cartItemPage = new CartItemPage(driver);
        this.breadcrumbComponent = new BreadcrumbComponent(driver);
    }

    @Then("breadcrumb should display {string}")
    public void breadcrumbShouldMatchItemName(String expectedBreadcrumb) {
        Assert.assertTrue(cartItemPage.doesBreadcrumbContainProductName(expectedBreadcrumb));
    }

    @And("user clicks on the selected breadcrumb {string}")
    public void userClicksOnTheSelectedBreadcrumb(String breadcrumbName) {
        Assert.assertTrue(breadcrumbComponent.selectAndClickOnSpecificBreadCrumb(breadcrumbName));
    }

    @Then("the user should be redirected to the {string} page")
    public void userShouldBeNavigatedToThePage(String expectedPageHeader) {
        String actualPageHeader = welcomePage.getPageHeader().toLowerCase();
        Assert.assertEquals(actualPageHeader, expectedPageHeader.toLowerCase());
    }

    @Then("the breadcrumb should contain {int} links")
    public void numberOfLinksInPageBreadCrumbShouldBe(int expectedNumberOfLinks) {
        int actualNumberOfLinks = breadcrumbComponent.getBreadcrumbSteps().size();
        Assert.assertEquals(actualNumberOfLinks, expectedNumberOfLinks);
    }
}
