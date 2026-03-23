@ui @cart
Feature: User can manage shopping cart operations

  Background:
    Given the user is logged in
    And the shopping cart is empty

#  Scenario Outline: User cannot add a gift card to the cart without filling in required fields
#    When the user navigates to the "<category>" page
#    And user adds "<itemNane>" to the cart
#    And user clicks on add to cart button
#    Then an error message should be displayed
#
#    Examples:
#      | category   | itemNane             |
#      | Gift Cards | $5 Virtual Gift Card |
#
  Scenario Outline: Item is added to the cart successfully
    When user counts the quantity of items in the cart
    And user clicks on demo web shop link in the top menu
    And the user navigates to the "<category>" page
    And user adds "<itemNane>" to the cart
    And user fills in recipient name
    And user fills in recipient email
    And user clicks on add to cart button
    Then a success message should be displayed
    And the user clicks on cart link in the notification
    And the user should be redirected to the shopping cart page
    And the item was added to the cart successfully

    Examples:
      | category   | itemNane             |
      | Gift Cards | $5 Virtual Gift Card |

#  Scenario Outline: User can add products to the cart
#    When user counts the quantity of items in the cart
#    And user clicks on demo web shop link in the top menu
#    And the user navigates to the "<category>" page
#    And user adds "<itemNane>" to the cart
#    Then a success message should be displayed
#    And the user clicks on cart link in the notification
#    And the user should be redirected to the shopping cart page
#    And the item was added to the cart successfully
#
#    Examples:
#      | category          | itemNane   |
#      | Apparel & Shoes   | Blue Jeans |
#      | Digital downloads | 3rd Album  |
#      | BOOKS             | Fiction    |
#
#  Scenario Outline: User can add products to the cart by navigating through the submenu
#    When user counts the quantity of items in the cart
#    And user clicks on demo web shop link in the top menu
#    And user clicks on "<topPageMenu>" from top menu and selects "<subMenu>" from the submenu
#    And user selects "<categoryName>"
#    And user clicks on add to cart button
#    Then a success message should be displayed
#    And the user clicks on cart link in the notification
#    And the user should be redirected to the shopping cart page
#    And the item was added to the cart successfully
#
#    Examples:
#      | topPageMenu | subMenu     | categoryName                  |
#      | Electronics | Cell phones | Smartphone                    |
#      | Electronics | Cell phones | Phone Cover                   |
#      | Computers   | Desktops    | Build your own cheap computer |
#      | Computers   | Notebooks   | 14.1-inch Laptop              |
#      | Computers   | Accessories | TCP Instructor Led Training   |
#
#  Scenario: Cart total price is calculated correctly
#    When user clicks on demo web shop link in the top menu
#    And user clicks on "Electronics" from top menu and selects "Cell phones" from the submenu
#    And user selects "Smartphone"
#    And user clicks on add to cart button
#    And user clicks on "Electronics" from top menu and selects "Cell phones" from the submenu
#    And user selects "Phone Cover"
#    And user clicks on add to cart button
#    And user clicks on shopping cart link in the top menu
#    Then the user should be redirected to the shopping cart page
#    And the cart total price should be calculated correctly
#
#  Scenario: Cart header displays correct number of items in the cart
#    When user clicks on demo web shop link in the top menu
#    And user clicks on "Electronics" from top menu and selects "Cell phones" from the submenu
#    And user selects "Smartphone"
#    And user clicks on add to cart button
#    And user clicks on "Electronics" from top menu and selects "Cell phones" from the submenu
#    And user selects "Phone Cover"
#    And user clicks on add to cart button
#    And user clicks on shopping cart link in the top menu
#    Then the user should be redirected to the shopping cart page
#    Then the cart header number of items match the cart page number of items
#
