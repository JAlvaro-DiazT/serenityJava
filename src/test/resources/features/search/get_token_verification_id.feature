Feature: Permite obtener los datos de un token, permitiendo verificar su validez

  Scenario: verificar token por medio del ID exitosamente
    Given El admin esta autenticado en el sistema
    And El admin registra un usuario
    When El admin genere un token para el nuevo usuario
    And El admin obtenga la informacion por medio del id
    Then El admin deberia recibir un código de estado 200
    And El admin obtiene el formato de respuesta "token" del esquema


