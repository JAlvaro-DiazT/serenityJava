Feature: Actualizacion parcial de un usuario en el Sistema

  Scenario: Actualizo parcialmente un usuario en el sistema
    Given El admin esta autenticado en el sistema
    And El admin registra un usuario
    When El admin solicita actualizar la clave temporalmente de un usuario
    Then El admin deberia recibir un código de estado 200
    And El admin obtiene el formato de respuesta "user" del esquema

  Scenario: Actualizo parcialmente un usuario con datos incompletos
    Given El admin esta autenticado en el sistema
    And El admin registra un usuario
    When El admin solicita actualizar la clave temporalmente con datos imcompletos
    Then El admin deberia recibir un código de estado 400
    And El admin obtiene el formato de respuesta "error" del esquema

  Scenario: Actualizo parcialmente un usuario no autorizado
    Given El user no esta autorizado en el sistema
    When El user solicita actualizar la clave temporalmente de un usuario
    Then El user deberia recibir un código de estado 401
    And El user obtiene el formato de respuesta "error" del esquema

  Scenario: Intento actualizar un usuario, sin embargo, no se me permite realizar dicha operación.
    Given El admin autenticado no tiene permisos
    And El admin registra un usuario
    When El admin solicita actualizar la clave temporalmente de un usuario
    Then El admin deberia recibir un código de estado 403
    And El admin obtiene el formato de respuesta "error" del esquema

  Scenario: Actualizar usuario - Recurso no encontrado
    Given El admin esta autenticado en el sistema
    When El admin solicita actualizar usuario que no existe
    Then El admin deberia recibir un código de estado 404
    And El admin obtiene el formato de respuesta "error" del esquema

