Feature: Validación del carrito de compras en SauceLabs

  Scenario Outline: Agregar productos al carrito y validar la actualización
    Given estoy en la aplicación de SauceLabs
    And valido que carguen correctamente los productos en la galería
    When agrego unidades <unidades> del siguiente producto "<producto>"
    Then valido el carrito de compra actualice correctamente

    Examples:
      | producto                        | unidades |
      | Sauce Labs Backpack             | 1        |
      | Sauce Labs Bolt - T-Shirt       | 1        |
      | Sauce Labs Bike Light           | 2        |
