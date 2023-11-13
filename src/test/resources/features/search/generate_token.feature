Feature: Generación de Token con Credenciales de Usuario

  Scenario: Usuario solicita la creación de un token con sus credenciales
    Given un usuario registrado desea acceder al sistema
    When "Admin" genera un token
    Then "Admin" deberia obtener un codigo de estado 201
    And El token de "Admin" obtiene de respuesta el codigo del token

  Scenario: Usuario solicita la creación de un token con datos incompletos
    Given un usuario registrado diligencia datos incompletos
    When "Alvaro" genera un token
    Then "Alvaro" deberia obtener un codigo de estado 400

  Scenario: Usuario solicita la creación de un token con datos incorrectos
    Given un usuario registrado diligencia datos incorrectos
    When "Alvaro" genera un token
    Then "Alvaro" deberia obtener un codigo de estado 401