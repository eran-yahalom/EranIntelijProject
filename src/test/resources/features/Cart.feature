@ui @cart
Feature: cart operations

  Background:
    Given user clicks on log in link
    And user enters email
    And user fills in email and password
    And user clicks on log in button
    And user clicks on shopping cart link in the top menu
    And user deletes all the items from the cart

#  Scenario Outline: user fails to add a product to the cart
#    And user clicks on "<category>" from the top menu
#    And user adds "<itemNane>" to the cart
#    And user clicks on add to cart button
#    Then user should see a message that the product was not added to the cart successfully
#    Examples:
#      | category   | itemNane             |
#      | Gift Cards | $5 Virtual Gift Card |
#
#  Scenario Outline: user adds a gift card to the cart successfully
#    And user counts the quantity of items in the cart
#    And user clicks on demo web shop link in the top menu
#    And user clicks on "<category>" from the top menu
#    And user adds "<itemNane>" to the cart
#    And user fills in recipient name
#    And user fills in recipient email
#    And user clicks on add to cart button
#    Then user should see a message that the product was added to the cart successfully
#    And user clicks on cart link in the notification
#    And user should be on the shopping cart page
#    And item was added to the cart successfully
#
#    Examples:
#      | category   | itemNane             |
#      | Gift Cards | $5 Virtual Gift Card |
#
#  Scenario Outline: user adds books/Digital downloads to the cart successfully
#    And user counts the quantity of items in the cart
#    And user clicks on demo web shop link in the top menu
#    And user clicks on "<category>" from the top menu
#    And user adds "<itemNane>" to the cart
#    Then user should see a message that the product was added to the cart successfully
#    And user clicks on cart link in the notification
#    And user should be on the shopping cart page
#    And item was added to the cart successfully
#
#    Examples:
#      | category          | itemNane   |
#      | Apparel & Shoes   | Blue Jeans |
#      | Digital downloads | 3rd Album  |
#      | BOOKS             | Fiction    |

#  Scenario Outline: add item to cart from dropdown menu
#    And user counts the quantity of items in the cart
#    And user clicks on demo web shop link in the top menu
#    And user clicks on "<topPageMenu>" from top menu and selects "<subMenu>" from the submenu
#    And user select item "<categoryName>" from the dropdown page that was opened
#    And user clicks on add to cart button
#    Then user should see a message that the product was added to the cart successfully
#    And user clicks on cart link in the notification
#    And user should be on the shopping cart page
#    And item was added to the cart successfully
#
#    Examples:
#      | topPageMenu | subMenu     | categoryName                  |
#      | Electronics | Cell phones | Smartphone                    |
#      | Electronics | Cell phones | Phone Cover                   |
#      | Computers   | Desktops    | Build your own cheap computer |
#      | Computers   | Notebooks   | 14.1-inch Laptop              |
#      | Computers   | Accessories | TCP Instructor Led Training   |

#  Scenario: check cart total price after adding items to the cart
#    And user clicks on demo web shop link in the top menu
#    And user clicks on "Electronics" from top menu and selects "Cell phones" from the submenu
#    And user select item "Smartphone" from the dropdown page that was opened
#    And user clicks on add to cart button
#    And user clicks on "Electronics" from top menu and selects "Cell phones" from the submenu
#    And user select item "Phone Cover" from the dropdown page that was opened
#    And user clicks on add to cart button
#    And user clicks on shopping cart link in the top menu
#    When user should be on the shopping cart page
#    Then user should see the correct total price for the items in the cart

  Scenario: check cart total items match cart header items
#    And user clicks on demo web shop link in the top menu
    And user clicks on "Electronics" from top menu and selects "Cell phones" from the submenu
    And user select item "Smartphone" from the dropdown page that was opened
    And user clicks on add to cart button
    And user clicks on "Electronics" from top menu and selects "Cell phones" from the submenu
    And user select item "Phone Cover" from the dropdown page that was opened
    And user clicks on add to cart button
    And user clicks on shopping cart link in the top menu
    When user should be on the shopping cart page
    Then number of items in cart header should match the number of items in the cart page

