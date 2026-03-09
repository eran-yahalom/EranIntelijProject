package step_definitions;

import components.*;
import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pages.*;
import utils.DriverManager;

public class HeaderStepDefinition {

    HeaderComponent headerComponent;
    CategoryItemsComponent categoryItemsComponent;
    SearchPage searchPage;

    public HeaderStepDefinition() {
        WebDriver driver = DriverManager.getDriver();
        this.headerComponent = new HeaderComponent(driver);
        this.categoryItemsComponent = new CategoryItemsComponent(driver);
        this.searchPage = new SearchPage(driver);
    }

    @When("user clicks on the search button")
    public void userClicksOnTheSearchButton() {
        Assert.assertTrue(headerComponent.clickOnSearchButton());
    }

    @Then("a pop up window will be displayed with text {string}")
    public void aPopUpWindowWillBeDisplayedWithText(String textFromPopUpWindow) {
        String popUpText = headerComponent.getAlertPopUpText();
        Assert.assertEquals(popUpText, textFromPopUpWindow);
    }

    @And("user closes the alert window")
    public void userClosesTheAlertWindow() {
        Assert.assertTrue(headerComponent.closeAlertPopUp());
    }

    @And("user enters {string} in the search field")
    public void userEntersInTheSearchField(String searchTerm) {
        Assert.assertTrue(headerComponent.fillSearchInput(searchTerm));
    }

    @And("search results should contain {string}")
    public void searchResultsShouldContain(String expectedSearchTerm) {
        Assert.assertTrue(searchPage.doesAllSearchResultsContainSearchText(expectedSearchTerm));
    }

    @And("search results should be displayed")
    public void searchResultsShouldBeDisplayed() {
        Assert.assertTrue(searchPage.getNumberOfSearchResults() > 0);
    }
}
