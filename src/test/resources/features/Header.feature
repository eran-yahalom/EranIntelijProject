@header @ui
Feature: header activities (login,logout,search etc...)

  Scenario: Search field submitted without text
    When user clicks on the search button
    Then a pop up window will be displayed with text "Please enter some search keyword"
    And user closes the alert window

  Scenario: Search find no results
    When user enters "111" in the search field
    And user clicks on the search button
    Then error message "searchResultNoItemsFoundMessage" should be displayed

  Scenario: Search finds results
    When user enters "book" in the search field
    And user clicks on the search button
    Then search results should be displayed
    And search results should contain "book"
