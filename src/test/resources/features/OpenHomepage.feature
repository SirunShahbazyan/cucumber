Feature: OrangeHRM Login

  Scenario: Logo presence on OrangeHRM page

    Given I launch chrome driver
    When I openOrange hrm page
    Then I verify that logo present on the page
    And Close driver
