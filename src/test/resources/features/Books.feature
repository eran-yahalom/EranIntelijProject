@ui @book
Feature: handle all activities in book page

  Background:
    Given user clicks on log in link
    And user fills in email and password
    And user clicks on log in button
    And user clicks on "BOOKS" from the top menu

  Scenario Outline: test sorting books by different options
    And user clicks on sort by dropdown and selects "<sortByOption>"
    Then books should be sorted correctly by "<sortByOption>"
    Examples:
      | sortByOption       |
      | Name: Z to A       |
      | Name: A to Z       |
      | Price: Low to High |
      | Price: High to Low |