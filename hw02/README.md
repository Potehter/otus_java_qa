# Домашняя работа №2 по занятию Java QA.
Домашнее задание:
Реализовать WebDriverFactory класс
Цель: В результате выполнения дз, участники создадут для себя Factory-класс, предоставляющй удобный интерфейс для запуска драйверов разных браузеров
Создайте класс WebDriverFactory со статическим методом create();

Метод create() принимает обязательный параметр webDriverName и необязтельный параметр options, а возвращает соответствующий имени вебдрайвер с заданными (если были) options

Примеры использования
WebDriver wd = WebDriverFactory.createNewDriver("chrome");
или
FirefoxOptions options = new FirefoxOptions();
WebDriver wd = WebDriverFactory.createNewDriver("firefox", options);