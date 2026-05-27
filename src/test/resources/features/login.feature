Feature: Авторизация

  Scenario: Успешный вход
    Given Я открываю страницу авторизации
    When Я ввожу логин "admin" и пароль "12345"
    And Нажимаю кнопку войти
    Then Я вижу сообщение "Success for admin"