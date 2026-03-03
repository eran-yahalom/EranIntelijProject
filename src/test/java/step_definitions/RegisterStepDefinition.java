package step_definitions;

import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pages.*;
import utils.*;

public class RegisterStepDefinition {
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

    public RegisterStepDefinition() {
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
    }


    @When("user clicks on register link")
    public void clickOnRegisterLink() {
        Assert.assertTrue(topPanelPage.clickOnRegisterLink());
    }

    @When("user clicks on log in link")
    public void clickOnLogInLink() {
        Assert.assertTrue(topPanelPage.clickOnLoginLink());
    }

    @And("user fills in email and password")
    public void userFillsEmailAndPassword() {
        Assert.assertTrue(welcomePage.fillEmail(Utils.readProperty("userEmail")));
        Assert.assertTrue(welcomePage.fillPassword(Utils.readProperty("userPassword")));
    }

    @And("user fills in email and password with {string} and {string}")
    public void userFillsEmailAndPasswordWith(String email, String password) {
        Assert.assertTrue(welcomePage.fillEmail(email));
        Assert.assertTrue(welcomePage.fillPassword(password));
    }

    @And("user clicks on log in button")
    public void userClicksOnLogInButton() {
        Assert.assertTrue(welcomePage.clickOnLoginButton());
    }

    @When("user enters gender {string}")
    public void userEntersGender(String gender) {
        Assert.assertTrue(registerPage.selectGender(gender));
    }

    @When("user enters first and last name")
    public void userEntersFirstNameAndLastName() {

        Assert.assertTrue(registerPage.fillFirstName(GeneratorUtils.generateFirstName()));
        Assert.assertTrue(registerPage.fillLastName(GeneratorUtils.generateLastName()));
    }

    @When("user enters email")
    public void userEntersEmail() {
        Assert.assertTrue(registerPage.fillEmail(email));
    }

    @And("user enters existing email")
    public void userEntersExistingEmail() {
        Assert.assertTrue(registerPage.fillEmail(Utils.readProperty("userEmail")));
    }

    @And("user enters password and confirm password")
    public void userEntersPasswordAndConfirmPassword() {
        Assert.assertTrue(registerPage.fillPassword(password));
        Assert.assertTrue(registerPage.fillConfirmPassword(password));
    }

    @And("user enters non matching password and confirm password")
    public void userEntersNonMatchingPasswordAndConfirmPassword() {
        Assert.assertTrue(registerPage.fillPassword(password));
        Assert.assertTrue(registerPage.fillConfirmPassword(password + "A"));
    }

    @And("user clicks on register button")
    public void userClicksOnRegisterButton() {
        Assert.assertTrue(registerPage.clickOnRegisterButton());
    }

    @And("user should see the registration success message")
    public void userShouldSeeRegistrationSuccessMessage() {
        String actualMessage = registerPage.getSuccessMessage();
        Assert.assertEquals(actualMessage, Utils.readProperty("registerSuccessMessage"), "Registration success message does not match expected.");
    }

    @And("user clicks on continue button")
    public void userClicksOnContinueButton() {
        Assert.assertTrue(registerPage.clickOnContinueButton());
    }

    @And("user is logged in with email {string}")
    public void userIsLoggedIn(String email) {
        Assert.assertTrue(loggedInPage.isUserLoggedIn(email));
    }

    @And("new user is logged in successfully")
    public void newUserIsLoggedInSuccessfully() {
        Assert.assertTrue(loggedInPage.isUserLoggedIn(email));
    }

    @And("existing user is logged in successfully")
    public void existingUserIsLoggedInSuccessfully() {
        Assert.assertTrue(loggedInPage.isUserLoggedIn(Utils.readProperty("userEmail")));
    }

    @Then("user should see an error message for existing email")
    public void userShouldSeeErrorMessageForExistingEmail() {
        String actualMessage = registerPage.getEmailRegistrationErrorMessage();
        Assert.assertEquals(actualMessage, Utils.readProperty("emailAlreadyExistsText"));
    }

    @Then("user should see an error message for non matching passwords")
    public void userShouldSeeErrorMessageForNonMatchingPasswords() {
        String actualMessage = registerPage.getPasswordValidationErrorMessage();
        Assert.assertEquals(actualMessage, Utils.readProperty("passwordsDontMatchErrorMessage"));
    }

    @Then("user should see an error message for all empty fields")
    public void userShouldSeeErrorMessageForAllEmptyFields() {
        String emptyFirstname = registerPage.getFirstNameValidationErrorMessage();
        String emptyLastname = registerPage.getLastNameValidationErrorMessage();
        String emptyEmail = registerPage.getEmailValidationErrorMessage();
        String emptyPassword = registerPage.getEmptyPasswordValidationErrorMessage();
        String emptyConfirmPassword = registerPage.getEmptyConfirmPasswordValidationErrorMessage();
        Assert.assertEquals(
                emptyFirstname,
                Utils.readProperty("firstNameRequired"),
                "First name required message is incorrect!"
        );

        Assert.assertEquals(
                emptyLastname,
                Utils.readProperty("lastNameRequired"),
                "Last name required message is incorrect!"
        );

        Assert.assertEquals(
                emptyEmail,
                Utils.readProperty("emailRequired"),
                "Email required message is incorrect!"
        );

        Assert.assertEquals(
                emptyPassword,
                Utils.readProperty("passwordRequired"),
                "Password required message is incorrect!"
        );

        Assert.assertEquals(
                emptyConfirmPassword,
                Utils.readProperty("confirmPasswordRequired"),
                "Confirm password required message is incorrect!"
        );
    }

    @Then("user should see an error message:{string} for invalid credentials")
    public void userShouldSeeErrorMessageForInvalidCredentials(String errorMessageFormat) {
        String actualMessage = welcomePage.getInvalidLoginErrorMessage();
        Assert.assertEquals(actualMessage, Utils.readProperty(errorMessageFormat));
    }

    @And("user clicks on {string} from the top menu")
    public void userClicksOnFromTheTopMenu(String menuOption) {
        Assert.assertTrue(topMenuPage.clickOnTopMenuLink(menuOption));
    }

    @And("user adds {string} to the cart")
    public void userAddsToTheCart(String productName) {
        Assert.assertTrue(giftCardsPage.addGiftCardToCart(productName));
    }

    @And("user clicks on add to cart button")
    public void clickOnAddToCartButton() {
        Assert.assertTrue(cartItemPage.clickAddToCartButton());
    }

    @And("user should see a message that the product was not added to the cart successfully")
    public void userShouldSeeMessageProductNotAddedToCart() {
        Assert.assertTrue(cartItemPage.isValidTRecipientEmailMessagePresented());
        Assert.assertTrue(cartItemPage.isValidTRecipientNameMessagePresented());
    }

    @And("user fills in recipient name")
    public void userFillsInRecipientName() {
        Assert.assertTrue(cartItemPage.fillRecipientNameFromSender());
    }

    @And("user fills in recipient email")
    public void userFillsInRecipientEmail() {
        Assert.assertTrue(cartItemPage.fillRecipientEmailFromSender());
    }

    @And("user should see a message that the product was added to the cart successfully")
    public void userShouldSeeMessageProductAddedToCart() {
        Assert.assertTrue(cartItemPage.addToCartSuccessMessagePresented());
    }

    @And("user clicks on cart link in the notification")
    public void userClicksOnCartLinkInTheNotification() {
        Assert.assertTrue(cartItemPage.clickOnCartLinkInNotification());
    }

    @Then("user should be on the shopping cart page")
    public void userShouldBeOnShoppingCartPage() {
        Assert.assertTrue(shoppingCartPage.isHeaderDisplayedCorrectly());
    }

    @Then("item was added to the cart successfully")
    public void itemWasAddedToTheCartSuccessfully() {
        int numberOfItemsBeforeAdd = ScenarioContext.get("quantityOfItemsInCart", Integer.class);
        int numberOfItemsAfterAdd = shoppingCartPage.getQuantityOfItemInCart();
        Assert.assertEquals(numberOfItemsAfterAdd, numberOfItemsBeforeAdd + 1, "Expected number of items in cart to be increased by 1 after adding a product, but it was not.");
    }

    @And("user clicks on shopping cart link in the top menu")
    public void userClicksOnShoppingCartLinkInTheTopMenu() {
        Assert.assertTrue(topPanelPage.clickOnShoppingCartLink());
    }

    @And("user counts the number of items in the cart")
    public void userCountsTheNumberOfItemsInTheCart() {
        int numberOfItemsInCart = shoppingCartPage.countNumberOfItemsInCart();
        ScenarioContext.save("numberOfItemsInCart", numberOfItemsInCart);
        Assert.assertTrue(numberOfItemsInCart > 0, "Expected at least one item in the cart, but found: " + numberOfItemsInCart);
    }

    @And("user clicks on demo web shop link in the top menu")
    public void userClicksOnDemoWebShopLinkInTheTopMenu() {
        Assert.assertTrue(topMenuPage.clickOnLogoLink());
    }

    @And("user counts the quantity of items in the cart")
    public void userCountsTheQuantityOfItemsInTheCart() {
        int quantityOfItemsInCart = shoppingCartPage.getQuantityOfItemInCart();
        ScenarioContext.save("quantityOfItemsInCart", quantityOfItemsInCart);
        Assert.assertTrue(quantityOfItemsInCart >= 0, "Expected at least one item in the cart, but found quantity: " + quantityOfItemsInCart);
    }

    @And("user deletes all the items from the cart")
    public void userDeletesItemFromTheCart() {
        Assert.assertTrue(shoppingCartPage.deleteItemFromCart());
    }

    @And("user clicks on {string} from top menu and selects {string} from the submenu")
    public void userSelectsFromTheTopMenu(String menuOption, String subMenuOption) {
        Assert.assertTrue(topMenuPage.clickOnSubMenuLink(menuOption, subMenuOption));
    }

    @And("user selects {string} from categories page")
    public void userSelectsItemFromPage(String categoryOption) {
        Assert.assertTrue(electronicsPage.selectItem(categoryOption));
    }

    @And("user selects the item {string} from the selected category")
    public void userSelectsItemFromDropdownPage(String itemName) {
        Assert.assertTrue(cellPhonesPage.selectItem(itemName));
    }

    @And("user should see the correct total price for the items in the cart")
    public void userShouldSeeCorrectTotalPriceForItemsInCart() {
        Assert.assertTrue(shoppingCartPage.isTotalPriceCalculatedCorrectly());
    }

    @And("number of items in cart header should match the number of items in the cart page")
    public void numberOfItemsInCartHeaderShouldMatchNumberOfItemsInCartPage() {
        int numberOfItemsInCart = shoppingCartPage.countNumberOfItemsInCart();
        int numberOfItemsInHeader = topPanelPage.getShoppingCartItemCount();
        Assert.assertEquals(numberOfItemsInHeader, numberOfItemsInCart, "Expected number of items in cart header to match the actual number of items in the cart, but it did not.");
    }
}
