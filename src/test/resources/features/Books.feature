@ui @book
Feature: handle all activities in book page

  Background:
    Given user clicks on "BOOKS" from the top menu

  Scenario Outline: test sorting books by different options
    When user clicks on sort by dropdown and selects "<sortByOption>"
    Then books should be sorted correctly by "<sortByOption>"
    Examples:
      | sortByOption       |
      | Name: Z to A       |
      | Name: A to Z       |
      | Price: Low to High |
      | Price: High to Low |

  Scenario Outline: test sorting books by display options
    When user clicks on display dropdown and selects "<displayOption>"
    Then books should be displayed correctly by "<displayOption>"
    Examples:
      | displayOption |
      | 4             |
      | 8             |
      | 12            |

  Scenario Outline: test view as options
    When user clicks on view as dropdown and selects "<viewAsOption>"
    Then books should be displayed in "<viewAsOption>" view

    Examples:
      | viewAsOption |
      | List         |
      | Grid         |

  Scenario: test that dropdowns are initially set to default values
    And sort by dropdown should be set to "Position"
    When display dropdown should be set to "8"
    Then view as dropdown should be set to "Grid"

  @flaky #TODO : scenario: 25.00 - 50.00 returns empty list- need to handle it
  Scenario Outline: test filtering books by price range
    When user sets price range filter to "<priceRange>"
    Then only books within the price range of "<priceRange>" should be displayed

    Examples:
      | priceRange    |
      | Under 25.00   |
      | 25.00 - 50.00 |
      | Over 50.00    |

  Scenario Outline: test that filter by price range works according to display options
    And user sets price range filter to "<priceRange>"
    And number of displayed filter by price elements is correct
    When user clicks on remove price range filter button
    Then all price range filters should be displayed again

    Examples:
      | priceRange    |
      | Under 25.00   |
      | 25.00 - 50.00 |
      | Over 50.00    |