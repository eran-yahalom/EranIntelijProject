package step_definitions;

import com.google.inject.Inject;
import com.google.inject.Provider;
import components.CategoryItemsComponent;
import components.LeftPaneComponent;
import components.TopMenuComponent;
import io.cucumber.guice.ScenarioScoped;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import pages.WelcomePage;
import utils.Utils;

@ScenarioScoped
public class PopularTagsStepDefinition {

    private final Provider<LeftPaneComponent> leftPaneComponentProvider;
    private final Provider<CategoryItemsComponent> categoryItemsComponentProvider;
    private final Provider<TopMenuComponent> topMenuComponentProvider;
    private final Provider<WelcomePage> welcomePageProvider;

    @Inject
    public PopularTagsStepDefinition(Provider<LeftPaneComponent> popularTagsComponentProvider,
                                     Provider<CategoryItemsComponent> categoryItemsComponentProvider,
                                     Provider<TopMenuComponent> topMenuComponentProvider, Provider<WelcomePage> welcomePageProvider) {
        this.leftPaneComponentProvider = popularTagsComponentProvider;
        this.categoryItemsComponentProvider = categoryItemsComponentProvider;
        this.topMenuComponentProvider = topMenuComponentProvider;
        this.welcomePageProvider = welcomePageProvider;
    }

    @When("user clicks on popular tags {string} tag")
    public void userClicksOnTag(String tagName) {
        Assert.assertTrue(leftPaneComponentProvider.get().clickOnTagName(tagName),
                "Failed to click on the tag: " + tagName);
    }

    @Then("user should see the correct page for {string} tag")
    public void userShouldSeeTheCorrectPageForTag(String tagName) {
        String pageTitle = categoryItemsComponentProvider.get().getPageTitle();
        Assert.assertTrue(pageTitle.contains(tagName),
                "The page title does not match the expected tag name.");
    }

    @And("popular tags block should not be visible")
    public void popularTagsBlockShouldNotBeVisible() {
        Assert.assertFalse(leftPaneComponentProvider.get().isPopularTagsBlockDisplayed(),
                "Popular tags block is not visible.");
    }

    @And("popular tags block should be visible")
    public void popularTagsBlockShouldBeVisible() {
        Assert.assertTrue(leftPaneComponentProvider.get().isPopularTagsBlockDisplayed(),
                "Popular tags block is not visible.");
    }

    @When("user navigates to the home page")
    public void userNavigatesToTheHomePage() {
        Assert.assertTrue(topMenuComponentProvider.get().clickOnLogoLink(),
                "Failed to click on the logo link.");

        Assert.assertTrue(welcomePageProvider.get().isWelcomeMessageDisplayed(),
                "Failed to navigate to the home page.");
    }

    @And("user clicks on the demo web shop logo link")
    public void userClicksOnTheDemoWebShopLogoLink() {
        Assert.assertTrue(topMenuComponentProvider.get().clickOnLogoLink(),
                "Failed to click on the logo link.");
    }

    @When("user clicks on the browser back button")
    public void userClicksOnTheBrowserBackButton() {
        welcomePageProvider.get().goBackToPreviousPage();
    }

    @And("user see the all products tag page")
    public void userSeeTheAllProductsTagPage() {
        String pageTitle = categoryItemsComponentProvider.get().getPageTitle();
        Assert.assertTrue(pageTitle.contains("All Products"),
                "The page title does not match the expected 'All Products' tag.");
    }

    @And("user clicks on popular tags view all link")
    public void userClicksOnPopularTagsViewAllLink() {
        Assert.assertTrue(leftPaneComponentProvider.get().clickOnViewAllTagsLink(),
                "Failed to click on the 'View All Tags' link.");
    }

    @Then("number of tags in the popular tags block should match number of tags in the view all tags page")
    public void numberOfTagsInThePopularTagsBlockShouldMatchNumberOfTagsInTheViewAllTagsPage() {
        int popularTagsCount = leftPaneComponentProvider.get().getPopularTagsCount();
        int viewAllTagsCount = categoryItemsComponentProvider.get().getPopularTagsCount();

        Assert.assertTrue(popularTagsCount < viewAllTagsCount,
                "The number of tags in the popular tags block does not match the number of tags in the view all tags page.");
    }

    @When("user clicks on categories {string} link and {string} sub-category link")
    public void clickOnCategoryLink(String categoryName, String subCategoryName) {
        Assert.assertTrue(leftPaneComponentProvider.get().clickOnLeftPaneCategory(categoryName, subCategoryName),
                "Failed to click on the category link: " + categoryName);
    }

    @When("user enters an email {string} in the news letter field")
    public void enterEmailToNewsletterSubscriptionField(String email) {
        Assert.assertTrue(leftPaneComponentProvider.get().enterEmailInNewsletter(email),
                "Failed to enter email in the newsletter subscription field: " + email);
    }

    @And("user clicks on the news letter subscribe button")
    public void clickOnNewsLetterSubscribeButton() {
        Assert.assertTrue(leftPaneComponentProvider.get().clickOnNewsletterSubscribeButton(),
                "Failed to click on the newsletter subscribe button.");
    }

    @Then("a success message should be displayed for the news letter subscription")
    public void verifyNewsletterSubscriptionSuccessMessage() {
        Assert.assertTrue(leftPaneComponentProvider.get().isNewsletterResultBlockTextCorrect(Utils.readProperty("newsLetterSuccessMessage")),
                "The success message for the newsletter subscription is not displayed.");
    }

    @Then("a fail message should be displayed for the news letter subscription")
    public void verifyNewsletterSubscriptionFailMessage() {
        Assert.assertTrue(leftPaneComponentProvider.get().isNewsletterResultBlockTextCorrect(Utils.readProperty("newsletterInvalidEmailErrorMessage")),
                "The success message for the newsletter subscription is not displayed.");
    }
}


