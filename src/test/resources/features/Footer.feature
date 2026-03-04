@footer @ui
Feature: Social media links in the footer

  Scenario Outline: User can navigate to social media pages
    When user clicks on "<socialMedia>" link in the footer
    Then social media "<socialMedia>" page will be opened in a new tab

    Examples:
      | socialMedia |
      | Facebook    |
      | Twitter     |
      | RSS         |
      | YouTube     |

  Scenario Outline: User can navigate to my account pages
    When user clicks on my account "<myAccountPage>" link in the footer
    Then my account "<myAccountPage>" page will be opened

    Examples:
      | myAccountPage |
      | My account    |
      | Orders        |
      | Addresses     |
      | Shopping cart |
      | Wishlist      |
