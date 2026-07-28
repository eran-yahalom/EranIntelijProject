@popularTags
Feature: Popular Tags

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