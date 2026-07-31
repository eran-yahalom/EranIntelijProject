@CheckOut
Feature: Checkout feature to verify checkout actions

  Background:
    Given random user is logged in successfully
    And the shopping cart is empty

  Scenario Outline: Verify that the user can complete checkout with data from environments.json file
    When the user navigates to the "BOOKS" page
    And user adds "Computing and Internet" to the cart
    Then a success message should be displayed

    When the user clicks on cart link in the notification
    Then the user should be redirected to the shopping cart page

    When user checks the shopping cart page terms and conditions checkbox
    And user clicks on shopping cart page checkout button
    And user selects "New Address" billing address selector from checkout:billing address
    And user fills in the checkout:billing address page details
    And user clicks on checkout:billing address continue button
    And click on checkout:shipping address page in store pickup checkbox
    And user clicks on checkout:shipping address continue button
    And user selects payment method "<paymentMethod>" from the checkout:payment method page
    And user clicks on checkout:payment method continue button
    And user fills in the selected "<paymentMethod>" payment method details from environments file
    And user clicks on checkout:payment information continue button
    And user clicks on checkout:confirm order continue button
    Then checkout:thank you order number is saved successfully in DB

    When user clicks on checkout:thank you order continue button
    Then user is in welcome page
    Examples:
      | paymentMethod                 |
      | Cash On Delivery (COD) (7.00) |
      | Check / Money Order (5.00)    |
      | Credit Card                   |
      | Purchase Order                |

#    And user fills in the checkout:shipping address page detailscreditCardExpiryYear
  #    And user fills in the checkout:payment information page details