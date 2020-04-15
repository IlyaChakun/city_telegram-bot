
Стек :

    * JDK 1.8
    * Spring Boot 2.1.7
    * H2   
    * Maven

Задача:

Необходимо создать web приложение по управлению собственным туристическим телеграм ботом.
    
    1) Телеграм бот выдает пользователю справочную информацию о введенном городе.
    Например, пользователь вводит: «Москва», чат-бот отвечает:
    «Не забудьте посетить Красную Площадь. Ну а в ЦУМ можно и не заходить)))».
    2) Данные о городах должны храниться в базе данных.
    3) Управлять данными о городах (добавлять новые города и информацию о них,
    изменять и удалять любую информацию) необходимо через REST WebService.
    4) Используемые технологии: SpringBoot, SpringMVC, SpringData, Hibernate, Java не ниже 1.8.
    Для сборки проекта использовать Maven.
    5) Исходный код приложения должен быть залит на GitHub, в файле readme указать,
    что необходимо для запуска (в том числе имя телеграм бота и его токен).

Для запуска необходимо:

    1) Скачать и запустить телеграм
    2) Найти в телеграмме бота с именем СityBotHelper.
    Воспользоваться им.
    Токен бота:      1285699038:AAE7DwF9txqxRnt6O0zofSFD7E8E4X9ADhA
    Полное имя бота: СityBotHelper_bot
    3) Для проверки REST использовать postman

REST (without jwt token) spec:

| URL                   | Method    | Resources             |
| ---------             | -----     | --------              |
| /cities               | GET       | get all cities        |
| /cities               | POST      | create city           |
| /cities/{cityId}      | GET       | get specific city     |
| /cities/{cityId}      | PUT       | update specific city  |
| /cities/{cityId}      | DELETE    | delete specific city  |
| /cities/{cityId}      | PATCH     | partial update city   |



