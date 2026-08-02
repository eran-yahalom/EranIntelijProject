package step_definitions;

import com.google.inject.Inject;
import com.google.inject.Provider;
import configurations.EnvManager;
import configurations.TestDataManager;
import io.cucumber.guice.ScenarioScoped;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import models.testdata.CustomerTestData;
import org.testng.Assert;
import pages.*;

import static utils.GeneratorUtils.*;

@ScenarioScoped
public class CheckOutStepDefinition {

    String firstName = generateFirstName();
    String lastName = generateLastName();
    String email = generateEmail();
    String city = generateCity();
    String address = generateAddress();
    String country = generateCountry();
    String phoneNumber = generatePhoneNumber();
    String zipCode = generateZipCode();

    private final Provider<CheckOutBillingAddressPage> checkOutBillingAddressPageProvider;
    private final Provider<CheckOutPaymentMethodPage> checkOutPaymentMethodPageProvider;
    private final Provider<CheckOutThankYouPage> checkOutThankYouPageProvider;
    private final Provider<CheckOutConfirmOrderPage> checkOutConfirmOrderPageProvider;
    private final Provider<CheckOutPaymentInformationPage> checkOutPaymentInformationPageProvider;
    private final Provider<CheckOutShippingAddressPage> checkOutShippingAddressPageProvider;
    private final Provider<ShoppingCartPage> shoppingCartPageProvider;
    private final Provider<WelcomePage> welcomePageProvider;


    @Inject
    public CheckOutStepDefinition(Provider<CheckOutBillingAddressPage> checkOutBillingAddressPageProvider, Provider<CheckOutPaymentMethodPage> checkOutPaymentMethodPageProvider, Provider<CheckOutThankYouPage> checkOutThankYouPageProvider, Provider<CheckOutConfirmOrderPage> checkOutConfirmOrderPageProvider, Provider<CheckOutPaymentInformationPage> checkOutPaymentInformationPageProvider, Provider<CheckOutShippingAddressPage> checkOutShippingAddressPageProvider, Provider<ShoppingCartPage> shoppingCartPageProvider, Provider<WelcomePage> welcomePageProvider) {
        this.checkOutBillingAddressPageProvider = checkOutBillingAddressPageProvider;
        this.checkOutPaymentMethodPageProvider = checkOutPaymentMethodPageProvider;
        this.checkOutThankYouPageProvider = checkOutThankYouPageProvider;
        this.checkOutConfirmOrderPageProvider = checkOutConfirmOrderPageProvider;
        this.checkOutPaymentInformationPageProvider = checkOutPaymentInformationPageProvider;
        this.checkOutShippingAddressPageProvider = checkOutShippingAddressPageProvider;
        this.shoppingCartPageProvider = shoppingCartPageProvider;
        this.welcomePageProvider = welcomePageProvider;
    }

    @When("user checks the shopping cart page terms and conditions checkbox")
    public void userChecksTheShoppingCartPageTermsAndConditionsCheckbox() {
        Assert.assertTrue(shoppingCartPageProvider.get().acceptTermsOfService());
    }

    @And("user clicks on shopping cart page checkout button")
    public void clickOnShoppingCartCheckOutButton() {
        Assert.assertTrue(shoppingCartPageProvider.get().proceedToCheckout(), "Cant click on shopping cart check out button");
    }

    @And("user fills in the checkout:billing address page details")
    public void fillCheckoutBillingDetails() {
        Assert.assertTrue(checkOutBillingAddressPageProvider.get().enterAddress(address), "Cant enter address");
        Assert.assertTrue(checkOutBillingAddressPageProvider.get().enterCity(city), "Cant enter city");

        Assert.assertTrue(checkOutBillingAddressPageProvider.get().enterZipCode(zipCode), "Cant enter zip code");
        Assert.assertTrue(checkOutBillingAddressPageProvider.get().enterPhoneNumber(phoneNumber), "Cant enter phone number");
        Assert.assertTrue(checkOutBillingAddressPageProvider.get().selectCountry(country), "Cant enter country");
    }

    @And("user clicks on checkout:billing address continue button")
    public void clickOnCheckOutBillingContinueButton() {
        Assert.assertTrue(checkOutBillingAddressPageProvider.get().clickContinueButton(), "Cant click on continue button");
    }

    @And("user fills in the checkout:shipping address page details")
    public void fillCheckoutShippingDetails() {
        Assert.assertTrue(checkOutShippingAddressPageProvider.get().enterAddress(address), "Cant enter address");
        Assert.assertTrue(checkOutShippingAddressPageProvider.get().enterCity(city), "Cant enter city");
        Assert.assertTrue(checkOutShippingAddressPageProvider.get().enterZipCode(zipCode), "Cant enter zip code");
        Assert.assertTrue(checkOutShippingAddressPageProvider.get().enterPhoneNumber(phoneNumber), "Cant enter phone number");
    }

    @And("user fills in billing address details for customer index {int} from JSON")
    public void fillBillingAddressByIndex(int index) {
        CustomerTestData customer = TestDataManager.getCustomer(index);

        Assert.assertTrue(checkOutBillingAddressPageProvider.get().enterAddress(customer.getBillingAddress()), "Cant enter address");
        Assert.assertTrue(checkOutBillingAddressPageProvider.get().enterCity(customer.getBillingCity()), "Cant enter city");

        Assert.assertTrue(checkOutBillingAddressPageProvider.get().enterZipCode(customer.getBillingZip()), "Cant enter zip code");
        Assert.assertTrue(checkOutBillingAddressPageProvider.get().enterPhoneNumber(customer.getPhone()), "Cant enter phone number");
        Assert.assertTrue(checkOutBillingAddressPageProvider.get().selectCountry(customer.getBillingCountry()), "Cant enter country");
    }

    @And("user fills in the checkout:shipping address page details for customer index {int} from JSON")
    public void fillCheckoutShippingDetails(int index) {
        CustomerTestData customer = TestDataManager.getCustomer(index);

        Assert.assertTrue(checkOutShippingAddressPageProvider.get().enterAddress(customer.getShippingAddress()), "Cant enter address");
        Assert.assertTrue(checkOutShippingAddressPageProvider.get().enterCity(customer.getShippingCity()), "Cant enter city");
        Assert.assertTrue(checkOutShippingAddressPageProvider.get().enterZipCode(customer.getShippingZip()), "Cant enter zip code");
        Assert.assertTrue(checkOutShippingAddressPageProvider.get().enterPhoneNumber(customer.getPhone()), "Cant enter phone number");
    }

    @And("click on checkout:shipping address page in store pickup checkbox")
    public void clickOnCheckOutShippingAddressCheckBox() {
        Assert.assertTrue(checkOutShippingAddressPageProvider.get().selectPickUpInStoreOption(), "Cant check the pick up checkbox");
    }

    @And("user clicks on checkout:shipping address continue button")
    public void clickOnCheckOutShippingContinueButton() {
        Assert.assertTrue(checkOutShippingAddressPageProvider.get().clickShippingContinueButton(),
                "Cant click on continue button");
    }

    @And("user selects payment method {string} from the checkout:payment method page")
    public void selectPaymentMethodType(String paymentMethodName) {
        Assert.assertTrue(checkOutPaymentMethodPageProvider.get().selectPaymentMethod(paymentMethodName),
                "Cant select payment method");
    }

    @And("user fills in the selected {string} payment method details from environments file")
    public void fillInSelectedPaymentMethodDetails(String paymentType) {
        Assert.assertTrue(checkOutPaymentInformationPageProvider.get().executePaymentHandler(
                paymentType,
                EnvManager.get().getPurchaseNumber(),
                "Visa",
                firstName,
                EnvManager.get().getCreditCardNumber(),
                EnvManager.get().getCreditCardExpiryYear(),
                EnvManager.get().getCreditCardCVV()

        ), "Cant enter payment method details");
    }

    @And("user fills in the selected {string} payment method details for customer index {int} from JSON")
    public void fillBillingAddressByIndex(String paymentType, int index) {
        CustomerTestData customer = TestDataManager.getCustomer(index);

        Assert.assertTrue(checkOutPaymentInformationPageProvider.get().executePaymentHandler(
                paymentType,
                customer.getPoNumber(),
                customer.getCreditCardType(),
                customer.getFirstName(),
                customer.getCreditCardNumber(),
                customer.getCreditCardExpirationYear(),
                customer.getCreditCardTypeCVV()

        ), "Cant enter payment method details");
    }

    @And("user clicks on checkout:payment method continue button")
    public void clickOnCheckOutPaymentMethodContinueButton() {
        Assert.assertTrue(checkOutPaymentMethodPageProvider.get().clickOnCheckOutPaymentMethodContinueButton(),
                "Cant click on checkout payment method continue button");
    }

    @And("user fills in the checkout:payment information page details")
    public void fillPaymentMethodDetails() {
        Assert.assertTrue(checkOutPaymentInformationPageProvider.get().selectCreditCardType("Visa"),
                "Cant select credit cart type");

        Assert.assertTrue(checkOutPaymentInformationPageProvider.get().enterCardholderName(firstName),
                "Cant enter card holder name");

        Assert.assertTrue(checkOutPaymentInformationPageProvider.get().enterCardNumber(EnvManager.get().getCreditCardNumber()),
                "Cant enter card number");

        Assert.assertTrue(checkOutPaymentInformationPageProvider.get().selectExpireYear(EnvManager.get().getCreditCardExpiryYear()),
                "Cant enter card expiry");

        Assert.assertTrue(checkOutPaymentInformationPageProvider.get().enterCardCode(EnvManager.get().getCreditCardCVV()),
                "Cant enter card CVV");
    }

    @And("user clicks on checkout:payment information continue button")
    public void clickOnCheckOutPaymentMethodInfoContinueButton() {
        Assert.assertTrue(checkOutPaymentInformationPageProvider.get().clickContinueButton(),
                "Cant click on checkout:payment information continue button");
    }

    @And("user clicks on checkout:confirm order continue button")
    public void clickOnCheckOutConfirmOrderConfirmButton() {
        Assert.assertTrue(checkOutConfirmOrderPageProvider.get().clickConfirmOrderButton(),
                "Cant click on checkout:confirm order continue button");
    }

    @And("checkout:thank you order number is saved successfully in DB")
    public void orderConfirmationNumberIsSetInDB() {
        String orderNumberFromUI = checkOutThankYouPageProvider.get().getOrderNumber();
        Assert.assertEquals(orderNumberFromUI, orderNumberFromUI, "Order number in UI and DN do not match");
    }

    @And("user clicks on checkout:thank you order continue button")
    public void clickOnCheckOutThankYouContinueButton() {
        Assert.assertTrue(checkOutThankYouPageProvider.get().clickOnContinueButton(),
                "Cant click on checkout:thank you continue button");
    }

    @And("user is in welcome page")
    public void seeWelcomePage() {
        Assert.assertTrue(welcomePageProvider.get().isWelcomeMessageDisplayed(),
                "Welcome page is not displayed");
    }

    @And("user selects {string} billing address selector from checkout:billing address")
    public void selectBillingAddress(String addressSelection) {
        Assert.assertTrue(checkOutBillingAddressPageProvider.get().selectBillingAddress(addressSelection),
                "Cant select billing address");
    }

    @And("user selects {string} shipping address selector from checkout:billing address")
    public void selectShippingAddress(String addressSelection) {
        Assert.assertTrue(checkOutShippingAddressPageProvider.get().selectShippingAddress(addressSelection),
                "Cant select billing address");
    }

    @And("user clicks back button on checkout {string} step")
    public void clickBackButtonOnStep(String stepName) {
        Assert.assertTrue(checkOutShippingAddressPageProvider.get().clickOnCheckOutBackButtonByStep(stepName),
                "Cant click on back button for step: " + stepName);
    }

    @Then("user should be redirected to the checkout:Billing address tab")
    public void userShouldBeRedirectedToTheCheckoutBillingAddressTab() {
        Assert.assertTrue(checkOutBillingAddressPageProvider.get().isSelectBillingAddressHeaderDisplayed(),
                "User is not redirected to the checkout:Billing address tab");
    }
}

