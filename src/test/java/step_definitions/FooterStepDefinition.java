package step_definitions;

import com.google.inject.Inject;
import components.FooterComponent;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import utils.DriverManager;

public class FooterStepDefinition {

    @Inject
    FooterComponent footerComponent;

    public FooterStepDefinition() {
        WebDriver driver = DriverManager.getDriver();
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

    @When("user clicks on my account {string} link in the footer")
    public void userClicksOnMyAccountLinkInTheFooter(String linkText) {
        Assert.assertTrue(footerComponent.clickOnMyAccountLinks(linkText));
    }

    @Then("my account {string} page will be opened")
    public void myAccountPageWillBeOpened(String expectedPage) {
        Assert.assertTrue(footerComponent.isCorrectMyAccountPageOpenedForAnonymousUser(expectedPage));
    }

    @Then("my account {string} page will be opened for logged in user")
    public void myAccountPageWillBeOpenedForLoggedInUser(String expectedPage) {
        Assert.assertTrue(footerComponent.isCorrectMyAccountPageOpenedForLoggedInUser(expectedPage));
    }

    @When("user clicks on customer service {string} link in the footer")
    public void userClicksOnCustomerServiceLinkInTheFooter(String customerServiceLink) {
        Assert.assertTrue(footerComponent.clickOnCustomerServiceLinks(customerServiceLink));
    }

    @Then("customer service {string} page will be opened")
    public void customerServicePageWillBeOpened(String customerServiceLink) {
        Assert.assertTrue(footerComponent.isCorrectCustomerServicePageOpenedF(customerServiceLink));
    }

    @When("user clicks on information {string} link in the footer")
    public void userClicksOnInformationLinkInTheFooter(String informationLink) {
        Assert.assertTrue(footerComponent.clickOnInformationLinks(informationLink));
    }

    @Then("information {string} page will be opened")
    public void informationPageWillBeOpened(String informationLink) {
        Assert.assertTrue(footerComponent.isCorrectInformationPageOpenedF(informationLink));
    }
}