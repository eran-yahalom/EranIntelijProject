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