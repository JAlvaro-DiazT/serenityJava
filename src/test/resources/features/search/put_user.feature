Feature: Actualizar información de un usuario registrado

  Scenario: Actualización exitosa de usuario

    Given El admin esta autenticado en el sistema
    And El admin registra un usuario
    When El admin solicita actualizar un usuario
    Then El admin deberia obtener un codigo de estado 200
    And El admin obtiene los datos del usuario
    And El token de El admin obtiene de respuesta el "user" del esquema

  Scenario: Actualización de usuario con datos incompletos
    Given El admin esta autenticado en el sistema
    And El admin registra un usuario
    When El admin solicita actualizar un usuario con datos incompletos
    Then El admin deberia obtener un codigo de estado 400
    And El token de El admin obtiene de respuesta el "error" del esquema


  Scenario: Usuario no autorizado intenta actualizar
    Given El user no esta autorizado en el sistema
    When El user solicita actualizar un usuario
    Then El user deberia obtener un codigo de estado 401
    And El token de El user obtiene de respuesta el "error" del esquema

  Scenario: Administrador sin permisos intenta actualizar
    Given El admin autenticado no tiene permisos
    And El admin registra un usuario
    When El admin solicita actualizar un usuario
    Then El admin deberia obtener un codigo de estado 403
    And El token de El admin obtiene de respuesta el "error" del esquema

  Scenario: Recurso de usuario no encontrado para actualizar
    Given El admin esta autenticado en el sistema
    And existe un usuario no registrado
    When El admin solicita actualizar un usuario
    Then El admin deberia obtener un codigo de estado 404
    And El token de El admin obtiene de respuesta el "error" del esquema