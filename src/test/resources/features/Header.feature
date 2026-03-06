@header @ui
Feature: header activities (login,logout,search etc...)

  Scenario: Search field submitted without text
    When user clicks on the search button
    Then a pop up window will be displayed with text "Please enter some search keyword"
    And user closes the alert window

#    Scenario: Search find no results