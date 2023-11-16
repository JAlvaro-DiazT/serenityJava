Feature: Actualizacion de los datos de un usuario en el Sistema

  Scenario: Actualizo los datos un usuario en el sistema
    Given El admin esta autenticado en el sistema
    And El admin registra un usuario
    When El admin solicita actualizar los datos del usuario
    Then El admin deberia recibir un código de estado 200
    And El admin obtiene el formato de respuesta "user" del esquema