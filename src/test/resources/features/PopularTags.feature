@LeftPane
Feature: Left pane tests

  Background:
    Given random user is logged in successfully

  Scenario Outline: Click on tag by name and see that the correct page will open
    And user clicks on popular tags "<tabName>" tag
    Then user should see the correct page for "<tabName>" tag
    And popular tags block should not be visible
    Examples:
      | tabName  |
      | apparel  |
      | computer |
      | TCP      |


  Scenario: Check that popular tags block is visible on the home page
    When user navigates to the home page
    Then popular tags block should be visible

  Scenario: Check that popular tags block is not visible on the product page
    When user clicks on popular tags "apparel" tag
    Then user should see the correct page for "apparel" tag
    Then popular tags block should not be visible

  Scenario: :Check that we can see the popular tags block after clicking on the demo web shop logo
    When user clicks on popular tags "apparel" tag
    Then user should see the correct page for "apparel" tag
    And user clicks on the demo web shop logo link
    Then popular tags block should be visible

  Scenario: :Check that we can see the popular tags block after clicking on the navigation back button
    When user clicks on popular tags "apparel" tag
    Then user should see the correct page for "apparel" tag
    And user clicks on the browser back button
    Then popular tags block should be visible

  Scenario: Number of tags in the popular tags block match number of view all tags link
    When user clicks on popular tags view all link
    Then number of tags in the popular tags block should match number of tags in the view all tags page

  Scenario: Click on tag by name from popular tags view all and see that the correct page will open
    When user clicks on popular tags view all link
    And user clicks on popular tags "apparel" tag
    Then user should see the correct page for "apparel" tag
    Then popular tags block should not be visible

  Scenario Outline: Click on the categories links and see correct page will open
    When user clicks on categories "<categoryName>" link and "<subCategoryName>" sub-category link
    Then user should see the correct page for "<pageHeaderText>" tag
    Examples:
      | categoryName      | subCategoryName | pageHeaderText |
      | Books             |                 | Books          |
      | Electronics       | Camera, photo   | Camera, photo  |
      | Electronics       | Cell phones     | Cell phones    |
      | Computers         | Desktops        | Desktops       |
      | Computers         | Notebooks       | Notebooks      |
      | Computers         | Accessories     | Accessories    |
      | Digital downloads |                 | downloads      |

  Scenario: Logged in user success to subscribe to the newsletter with email
    When user enters an email "dan@gmail.com" in the news letter field
    And user clicks on the news letter subscribe button
    Then a success message should be displayed for the news letter subscription

  Scenario: logged out user success to subscribe to the newsletter with email
    When user clicks on log out link
    And user enters an email "dan@gmail.com" in the news letter field
    And user clicks on the news letter subscribe button
    Then a success message should be displayed for the news letter subscription

  Scenario Outline: logged out user success to subscribe to the newsletter with email
    When user clicks on log out link
    And user enters an email "<nonValidMail>" in the news letter field
    And user clicks on the news letter subscribe button
    Then a fail message should be displayed for the news letter subscription
    Examples:
      | nonValidMail  |
      | dan@gmail,com |
      | dan.com       |

  Scenario: Subscribe to newsletter from left pane
    When the user navigates to the "BOOKS" page
    And user enters an email "dan@gmail.com" in the news letter field
    And user clicks on the news letter subscribe button
    Then a success message should be displayed for the news letter subscription