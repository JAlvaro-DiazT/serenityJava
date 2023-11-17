Feature: Generación de lista de tokens

  Scenario: Generación exitosa de la lista de tokens
    Given El admin esta autenticado en el sistema
    And El admin registra un usuario
    When El admin solicita el listado del tokens
    Then El admin deberia recibir un código de estado 200
    And El admin obtiene el formato de respuesta "tokens" del esquema

  Scenario: Usuario no autorizado al intentar generar la lista de tokens
    Given El user no esta autorizado en el sistema
    When El user solicita el listado del tokens
    Then El user deberia recibir un código de estado 401
    And El user obtiene el formato de respuesta "error" del esquema

  Scenario: Administrador sin permisos intenta generar la lista de tokens
    Given El admin autenticado no tiene permisos
    When El admin solicita el listado del tokens
    Then El admin deberia recibir un código de estado 403
    And El admin obtiene el formato de respuesta "error" del esquema

