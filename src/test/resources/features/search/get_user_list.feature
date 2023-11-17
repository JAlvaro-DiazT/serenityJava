Feature: Generación de lista de usuarios

  Scenario: Generación exitosa de la lista de usuarios
    Given El admin esta autenticado en el sistema
    And El admin registra un usuario
    When El admin solicita el listado de los usuarios
    Then El admin deberia recibir un código de estado 200
    And El admin obtiene el formato de respuesta "users" del esquema

  Scenario: Usuario no autorizado al intentar generar la lista de usuarios
    Given El user no esta autorizado en el sistema
    When El user solicita el listado de los usuarios
    Then El user deberia recibir un código de estado 401
    And El user obtiene el formato de respuesta "error" del esquema

  Scenario: Administrador sin permisos intenta generar la lista de usuarios
    Given El admin autenticado no tiene permisos
    When El admin solicita el listado de los usuarios
    Then El admin deberia recibir un código de estado 403
    And El admin obtiene el formato de respuesta "error" del esquema

