#language:ru
#encoding:UTF-8

@test @login
Функционал: Проверка входа пользователя на сайт

  Сценарий: Проверка, что пользователь с логином standard_user может войти на сайт
    Допустим открыта страница "https://www.saucedemo.com/"
    И в поле Username введено значение "standard_user"
    И в поле Password введено значение "secret_sauce"
    И нажата кнопка LOGIN
    Тогда открыта следующая страница "https://www.saucedemo.com/inventory.html"

  Сценарий: Проверка, что пользователь не может войти на сайт без ввода пароля
    Допустим открыта страница "https://www.saucedemo.com/"
    И в поле Username введено значение "standard_user"
    И в поле Password введено значение ""
    И нажата кнопка LOGIN
    Тогда открыто сообщение ошибки "Epic sadface: Password is required"
    И открыта следующая страница "https://www.saucedemo.com/"

  Структура сценария: Проверка, что заблокированный пользователь <userName> не может войти на сайт
    Допустим открыта страница "https://www.saucedemo.com/"
    И в поле Username введено значение "<userName>"
    И в поле Password введено значение "<userPassword>"
    И нажата кнопка LOGIN
    Тогда открыто сообщение ошибки "Epic sadface: Sorry, this user has been locked out."
    И открыта следующая страница "https://www.saucedemo.com/"

    Примеры:
      | userName        | userPassword |
      | locked_out_user | secret_sauce |
      | locked_out_user | secret_sauce |

  @fail
  Структура сценария: Проверка, что пользователь <userName> может войти на сайт
    Допустим открыта страница "https://www.saucedemo.com/"
    И в поле Username введено значение "<userName>"
    И в поле Password введено значение "<userPassword>"
    И нажата кнопка LOGIN
    Тогда открыта следующая страница "https://www.saucedemo.com/inventory.html"

    Примеры:
      | userName                | userPassword |
      | standard_user           | secret_sauce |
      | locked_out_user         | secret_sauce |
      | problem_user            | secret_sauce |
      | performance_glitch_user | secret_sauce |