# Домашняя работа №1 по занятию Java QA.
Домашнее задание:
Создать maven-проект
Создать новый maven-проект для автоматизации тестирования

- Откройте IDE
- Создайте новый проект (maven)
- Настройте для проекта файл .gitignore (https://www.gitignore.io/)
- В файле pom.xml укажите зависимости для
-- Selenium Java (https://mvnrepository.com/artifact/org.seleniumhq.selenium/selenium-java)
-- WebDriverManager (https://mvnrepository.com/artifact/io.github.bonigarcia/webdrivermanager)
-- jUnit (https://mvnrepository.com/artifact/junit/junit/4.12)
- Создайте тест, который:
-- с помощью WebDriverManager, настраивает ChromeDriver
-- открывает в браузерах Chrome, FF (параметризодано) страницу https://otus.ru/
- Подключить и настроить log4j