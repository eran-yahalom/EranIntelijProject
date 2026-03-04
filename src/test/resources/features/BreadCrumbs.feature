@breadCrumbs @ui
Feature: Breadcrumb reflects the correct navigation path

  Scenario Outline: Breadcrumb shows selected item name
    When the user navigates to the "<topPageMenu>" page
    And user selects "<itemName>"
    Then breadcrumb should display "<itemName>"

    Examples:
      | topPageMenu | itemName                          |
      | BOOKS       | Computing and Internet            |
      | BOOKS       | Copy of Computing and Internet EX |
      | BOOKS       | Fiction                           |
      | BOOKS       | Fiction EX                        |
      | BOOKS       | Health Book                       |
      | BOOKS       | Science                           |


  Scenario Outline: User can see the correct breadcrumb for the selected item when navigating through the submenu
    When user clicks on "<topPageMenu>" from top menu and selects "<subMenu>" from the submenu
    And user selects "<categoryName>"
    Then breadcrumb should display "<categoryName>"

    Examples:
      | topPageMenu | subMenu     | categoryName |
      | Electronics | Cell phones | Smartphone   |
      | Electronics | Cell phones | Phone Cover  |


  Scenario Outline: ser can navigate using breadcrumb links
    When the user navigates to the "<topPageMenu>" page
    And user selects "<itemName>"
    And user clicks on the selected breadcrumb "<topPageMenu>"
    Then the user should be redirected to the "<topPageMenu>" page

    Examples:
      | topPageMenu | itemName                          |
      | BOOKS       | Computing and Internet            |
      | BOOKS       | Copy of Computing and Internet EX |

  Scenario Outline: Breadcrumb displays correct number of links
    When the user navigates to the "<topPageMenu>" page
    And user selects "<itemName>"
    Then the breadcrumb should contain <numberOfLinksInBreadCrumb> links


    Examples:
      | topPageMenu       | itemName                | numberOfLinksInBreadCrumb |
      | BOOKS             | Computing and Internet  | 3                         |
      | Jewelry           | Diamond Tennis Bracelet | 3                         |
      | Gift Cards        | $50 Physical Gift Card  | 3                         |
      | Digital downloads | Music 2                 | 3                         |
      | Apparel & Shoes   | Blue Jeans              | 3                         |

  Scenario Outline: User can see the correct number of links in the breadcrumb for the selected item when navigating through the submenu
    When user clicks on "<topPageMenu>" from top menu and selects "<subMenu>" from the submenu
    And user selects "<categoryName>"
    Then the breadcrumb should contain <numberOfLinksInBreadCrumb> links


    Examples:
      | topPageMenu | subMenu     | categoryName | numberOfLinksInBreadCrumb |
      | Electronics | Cell phones | Smartphone   | 4                         |
