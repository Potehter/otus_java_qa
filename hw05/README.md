# Домашняя работа №5 по занятию Java QA.
Домашнее задание:
Реализуйте автоматический тест, используя Java + Selenium + POM
Шаги теста:
- Открыть https://otus.ru
- Авторизоваться на сайте
- Войти в личный кабинет
- В разделе "О себе" заполнить все поля "Личные данные" и добавить не менее двух контактов
- Нажать сохранить
- Открыть https://otus.ru в "чистом браузере"
- Авторизоваться на сайе
- Войти в личный кабинет
- Проверить, что в разделе "О себе" отображаются указанные ранее данные

Критерии оценки: +1 балл - код компилируется и запускается
+1 балл - код запускается без дополнительных действий со стороны проверяющего (не нужно скачивать WebDriver, решать конфликты зависимостей и т.п.)
+1 балл - логин/пароль для авторизации не зашиты в код (передаются как параметры при старте)
+1 балл - логи пишутся в консоль и файл
+1 балл - тест проходит без падений (допускается падение теса только при некорректной работе SUT)
+1 балла - реализован паттерн PageObject

P.S. для хранения кред используется test.properties 