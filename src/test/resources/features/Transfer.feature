#language:ru

Функциональность: Перевод денежных средств с одной карты на другую авторизованным пользователем в личном кабинете
  Сценарий: Успешный перевод с карты на карту
    Пусть пользователь залогинен с именем "vasya" и паролем "qwerty123"
    Когда пользователь переводит "5 000" рублей с карты с номером "5559 0000 0000 0002" на свою 1 карту с главной страницы,
    Тогда баланс его 1 карты из списка на главной странице должен стать 15000 рублей.