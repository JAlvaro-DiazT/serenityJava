Feature: Eliminar un usuario registrado

  Scenario: Eliminar un usuario exitosamente
    Given El admin esta autenticado en el sistema
    And El admin registra un usuario temporal
    When El admin elimina un usuario
    Then El admin deberia recibir un código de estado 204

  Scenario: Eliminar usuario no autorizado
    Given El user no esta autorizado en el sistema
    When El user elimina un usuario
    Then El user deberia recibir un código de estado 401
    And El user obtiene el formato de respuesta "error" del esquema

  Scenario: Eliminar un usuario, sin embargo, no se me permite realizar dicha operación.
    Given El admin autenticado no tiene permisos
    And El admin registra un usuario temporal
    When El admin elimina un usuario
    Then El admin deberia recibir un código de estado 403
    And El admin obtiene el formato de respuesta "error" del esquema

  Scenario: Intento eliminar un usuario que no existe
    Given El admin esta autenticado en el sistema
    When El admin elimina un usuario que no existe
    Then El admin deberia recibir un código de estado 404
    And El admin obtiene el formato de respuesta "error" del esquema
