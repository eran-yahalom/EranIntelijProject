@ui @book
Feature: User can manage sorting, filtering and display on Books page

  Background:
    Given the user navigates to the "BOOKS" page

  Scenario Outline: User can sort books by different options
    When user clicks on sort by dropdown and selects "<sortByOption>"
    Then the books should be sorted by "<sortByOption>"
    Examples:
      | sortByOption       |
      | Name: Z to A       |
      | Name: A to Z       |
      | Price: Low to High |
      | Price: High to Low |

  Scenario Outline: User can change the number of displayed books by different options
    When user clicks on display dropdown and selects "<displayOption>"
    Then "<displayOption>" books should be displayed
    Examples:
      | displayOption |
      | 4             |
      | 8             |
      | 12            |

  Scenario Outline: User can change the view of displayed books by different options
    When user clicks on view as dropdown and selects "<viewAsOption>"
    Then the books should be displayed in "<viewAsOption>" view

    Examples:
      | viewAsOption |
      | List         |
      | Grid         |

  Scenario: Dropdown options should be set to default values
    Then sort by dropdown should be set to "Position"
    And display dropdown should be set to "8"
    And view as dropdown should be set to "Grid"

  @flaky #TODO : scenario: 25.00 - 50.00 returns empty list- need to handle it
  Scenario Outline: User can filter books by price range
    When user sets price range filter to "<priceRange>"
    Then only books within the price range of "<priceRange>" should be displayed

    Examples:
      | priceRange    |
      | Under 25.00   |
      | 25.00 - 50.00 |
      | Over 50.00    |

  Scenario Outline: User can remove price filter
    When user sets price range filter to "<priceRange>"
    Then number of displayed filter by price elements is correct
    When user clicks on remove price range filter button
    Then all price range filters should be displayed again

    Examples:
      | priceRange    |
      | Under 25.00   |
      | 25.00 - 50.00 |
      | Over 50.00    |

    #TODO : scenario: 25.00 - 50.00 returns empty list- need to handle it
  Scenario Outline: User can filter books by price range and sort the filtered books by different options
    When user clicks on sort by dropdown and selects "<sortByOption>"
    And user sets price range filter to "<priceRange>"
    Then only books within the price range of "<priceRange>" should be displayed
    And the books should be sorted by "<sortByOption>"

    Examples:
      | sortByOption       | priceRange    |
      | Name: A to Z       | Under 25.00   |
      | Price: Low to High | 25.00 - 50.00 |
      | Price: High to Low | Over 50.00    |
      | Name: Z to A       | Under 25.00   |




