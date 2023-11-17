Feature: Generacion lista de tokens

  Scenario: Generar lista de tokens exitoso
    Given El admin esta autenticado en el sistema
    And El admin registra un usuario
    When El admin solicita el listado del tokens
    Then El admin deberia recibir un código de estado 200
    And El admin obtiene el formato de respuesta "tokens" del esquema

  Scenario: Generar lista de tokens user no autorizado
    Given El user no esta autorizado en el sistema
    When El user solicita el listado del tokens
    Then El user deberia recibir un código de estado 401
    And El user obtiene el formato de respuesta "error" del esquema

  Scenario: Generar lista token usuario sin permiso realizar la operación
    Given El admin autenticado no tiene permisos
    When El admin solicita el listado del tokens
    Then El admin deberia recibir un código de estado 403
    And El admin obtiene el formato de respuesta "error" del esquema

