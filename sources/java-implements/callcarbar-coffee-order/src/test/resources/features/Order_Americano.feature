Feature: Order Americano in seat

  Scenario Outline: Drink Americano here
    Given the price of a <coffee> is <price>
    When I order <cups> cups of <coffee>
    And decided to have it <HereToGo>
    Then the total price should be <cups> * <price>
    And  the temperature should be <drinktemperature>

  Examples:
  |coffee     | cups | price| drinktemperature| HereToGo|
  |Americano  | 2    | 80   | 70              | HERE    |
