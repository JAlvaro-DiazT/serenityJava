Feature: Consulta de un usuario registrado

  Scenario: Obtener información de un usuario exitosamente
    Given El admin esta autenticado en el sistema
    When El admin solicita informacion de un usuario
    Then El admin deberia recibir un código de estado 200
    And El admin obtiene los datos del usuario
    And El admin obtiene el formato de respuesta "user" del esquema

  Scenario: Obtener usuario no autorizado
    Given El user no esta autorizado en el sistema
    When El user solicita informacion de un usuario
    Then El user deberia recibir un código de estado 401
    And El user obtiene el formato de respuesta "error" del esquema

  Scenario: Obtener usuario sin permisos para realizar la operación
    Given El user autenticado no tiene permisos
    When El user solicita informacion de un usuario
    Then El user deberia recibir un código de estado 403
    And El user obtiene el formato de respuesta "error" del esquema

  Scenario: Obtener usuario - Recurso no encontrado
    Given El admin esta autenticado en el sistema
    When El admin solicita informacion de usuario no existente
    Then El admin deberia recibir un código de estado 404
    And El admin obtiene el formato de respuesta "error" del esquema
