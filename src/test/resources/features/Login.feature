Feature: OrangeHRM Login

  Scenario Outline: Login successfully

    Given I am on the Login page of OrangeHRM
    When I enter valid <username> and <password>
    Then I should be taken to a new page

    Examples:
    |username|password|
    |"Admin"|"admin123"|
    |"Admin"|"admin123"|
