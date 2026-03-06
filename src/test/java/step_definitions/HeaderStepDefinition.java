package step_definitions;

import components.BreadcrumbComponent;
import components.HeaderComponent;
import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pages.*;
import utils.DriverManager;

public class HeaderStepDefinition {

    HeaderComponent headerComponent;

    public HeaderStepDefinition() {
        WebDriver driver = DriverManager.getDriver();
        this.headerComponent = new HeaderComponent(driver);
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
}
