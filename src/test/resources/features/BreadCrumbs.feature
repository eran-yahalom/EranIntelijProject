@breadCrumbs @ui
Feature: test all breadcrumbs in the website

  Scenario Outline: check that breadcrumb matches the item name
    And user clicks on "<topPageMenu>" from the top menu
    And user selects the item "<itemName>" from the selected category
    Then breadcrumb should match the item name "<itemName>"

    Examples:
      | topPageMenu | itemName                          |
      | BOOKS       | Computing and Internet            |
      | BOOKS       | Copy of Computing and Internet EX |
      | BOOKS       | Fiction                           |
      | BOOKS       | Fiction EX                        |
      | BOOKS       | Health Book                       |
      | BOOKS       | Science                           |


  Scenario Outline: check that breadcrumb matches the item name in a sub menu dropdown
    And user clicks on "<topPageMenu>" from top menu and selects "<subMenu>" from the submenu
    And user selects the item "<categoryName>" from the selected category
    Then breadcrumb should match the item name "<categoryName>"

    Examples:
      | topPageMenu | subMenu     | categoryName |
      | Electronics | Cell phones | Smartphone   |
      | Electronics | Cell phones | Phone Cover  |


  Scenario Outline: test that clicking on the breadcrumb link takes the user back to the correct page
    And user clicks on "<topPageMenu>" from the top menu
    And user selects the item "<itemName>" from the selected category
    When user clicks on the selected breadcrumb "<topPageMenu>"
    Then user should be navigated to the "<topPageMenu>" page

    Examples:
      | topPageMenu | itemName                          |
      | BOOKS       | Computing and Internet            |
      | BOOKS       | Copy of Computing and Internet EX |

  Scenario Outline: thest number of links in the breadcrumb matches the expected number of links for the selected item
    And user clicks on "<topPageMenu>" from the top menu
    And user selects the item "<itemName>" from the selected category
    Then number of links in page bread crumb should be <numberOfLinksInBreadCrumb>


    Examples:
      | topPageMenu       | itemName                | numberOfLinksInBreadCrumb |
      | BOOKS             | Computing and Internet  | 3                         |
      | Jewelry           | Diamond Tennis Bracelet | 3                         |
      | Gift Cards        | $50 Physical Gift Card  | 3                         |
      | Digital downloads | Music 2                 | 3                         |
      | Apparel & Shoes   | Blue Jeans              | 3                         |

  Scenario Outline: thest number of links in the breadcrumb matches the expected number of links for the selected item
    And user clicks on "<topPageMenu>" from top menu and selects "<subMenu>" from the submenu
    And user selects the item "<categoryName>" from the selected category
    Then number of links in page bread crumb should be <numberOfLinksInBreadCrumb>


    Examples:
      | topPageMenu | subMenu     | categoryName | numberOfLinksInBreadCrumb |
      | Electronics | Cell phones | Smartphone   | 4                         |
