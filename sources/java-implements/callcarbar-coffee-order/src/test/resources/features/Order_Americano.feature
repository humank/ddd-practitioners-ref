Feature: Order Americano in seat

  Scenario Outline: Drink Americano here
    Given the price of a <coffee> is <price>
    When I order <quantity> quantity of <coffee>
    And decided to have it <HereToGo>
    Then the total price should be <quantity> * <price>
    And  the temperature should be <drinktemperature>

  Examples:
  |coffee     | quantity | price| drinktemperature| HereToGo|
  |Americano  | 2    | 80   | 70              | HERE    |
