# Сервис "Облачное хранилище"

Сервис предоставляет REST-интерфейс для загрузки файлов и вывода списка уже загруженных файлов пользователя. Работает совместно с веб-приложением. Разработаено с использованием Spring Boot.

Сервис реализует следующие методы, описанные в протоколе:
- авторизация,
- logout,
- добавление файла,
- удаление файла,
- скачивание файла,
- переименование файла,
- вывод списка загруженных файлов.

Иноформация о пользователях сервиса и данные хранятся в базе данных.
В базу заведены два пользователя:
Login: user1, password: 101
Login: user2, password: 102

Полное описание задачи: https://github.com/netology-code/jd-homeworks/blob/master/diploma/cloudservice.md

Сборка образов:

    mvn clean install -DskipTests

Запуск контейнера:

    docker compose up