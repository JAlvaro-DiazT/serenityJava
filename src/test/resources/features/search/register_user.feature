Feature: Registro de Usuarios en el Sistema

  Scenario: Registro como usuario en el sistema
    Given existe un usuario no registrado
    When "Alvaro" utiliza el servicio de registro
    Then "Alvaro" deberia recibir un código de estado 201

  Scenario: Registro con datos incompletos
    Given intento registrarme con datos faltantes
    When "Alvaro" utiliza el servicio de registro
    Then "Alvaro" deberia recibir un código de estado 400

  Scenario: Usuario ya registrado
    Given intento registrarme como un usuario ya existente
    When "Alvaro" utiliza el servicio de registro
    Then "Alvaro" deberia recibir un código de estado 409

