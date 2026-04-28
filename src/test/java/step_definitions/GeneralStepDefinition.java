package step_definitions;

import com.google.inject.Inject;
import com.google.inject.Provider;
import components.BreadcrumbComponent;
import io.cucumber.guice.ScenarioScoped;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.testng.Assert;
import pages.CartItemPage;
import pages.WelcomePage;

@ScenarioScoped
public class GeneralStepDefinition {

    private final Provider<WelcomePage> welcomePageProvider;
    private final Provider<CartItemPage> cartItemPageProvider;
    private final Provider<BreadcrumbComponent> breadcrumbComponentProvider;

    @Inject
    public GeneralStepDefinition(Provider<WelcomePage> welcomePageProvider, Provider<CartItemPage> cartItemPageProvider, Provider<BreadcrumbComponent> breadcrumbComponentProvider) {
        this.welcomePageProvider = welcomePageProvider;
        this.cartItemPageProvider = cartItemPageProvider;
        this.breadcrumbComponentProvider = breadcrumbComponentProvider;
    }

    @Then("breadcrumb should display {string}")
    public void breadcrumbShouldMatchItemName(String expectedBreadcrumb) {
        Assert.assertTrue(cartItemPageProvider.get().doesBreadcrumbContainProductName(expectedBreadcrumb));
    }

    @And("user clicks on the selected breadcrumb {string}")
    public void userClicksOnTheSelectedBreadcrumb(String breadcrumbName) {
        Assert.assertTrue(breadcrumbComponentProvider.get().selectAndClickOnSpecificBreadCrumb(breadcrumbName));
    }

    @Then("the user should be redirected to the {string} page")
    public void userShouldBeNavigatedToThePage(String expectedPageHeader) {
        String actualPageHeader = welcomePageProvider.get().getPageHeader().toLowerCase();
        Assert.assertEquals(actualPageHeader, expectedPageHeader.toLowerCase());
    }

    @Then("the breadcrumb should contain {int} links")
    public void numberOfLinksInPageBreadCrumbShouldBe(int expectedNumberOfLinks) {
        int actualNumberOfLinks = breadcrumbComponentProvider.get().getBreadcrumbSteps().size();
        Assert.assertEquals(actualNumberOfLinks, expectedNumberOfLinks);
    }
}
