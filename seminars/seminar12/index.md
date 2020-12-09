Семинар 12
--

# Автоматизированое тестирование Web-интерфейсов. Selenium WebDriver.

В данной практической работе будет использоваться Selenium WebDriver.

Кроме этого Selenium WebDriver для Java доступен для скачивания из Maven-репозитория:
http://docs.seleniumhq.org/download/maven.jsp
Пример
Пример TestNG-теста, использующего Selenium WebDriver находится в папке <seminar07>\examples\exampleSelenium. Этот тест запускает браузер Firefox, заходит на страницу google.com, вводит в окно поиска ключевое слово и ждет получения результата.

Для успешной работы с проектом в IntelliJ IDEA необходимо:
* Импортировать Gradle-проект build.gradle (Import Project и выбрать нужный файл).
  При этом будут скачаны все зависимости и создан IDEA-проект.
* Открыть файл BasicSeleniumTestNGTest, который содержит код примера.
* Если нужно использовать браузер, отличный от Firefox,
  то необходимо вместо FirefoxDriver использовать другой драйвер (например, ChromeDriver).
* Для запуска Java-драйвера требуется внешнее приложение драйвера,
  которое необходимо скачать и задать к нему путь в переменой окружения
  (для Firefox – "webdriver.gecko.driver", для Chrome –"webdriver.chrome.driver” и т.д.).
  Переменная окружения задается в методе setUpClass. Нужный драйвер для нужно ОС ищется в Google.

Для того, чтобы записывать Web-сценарии необходимо поставить Selenium IDE в свой браузер.
Список возможных вариантов можно найти здесь: http://www.seleniumhq.org/download/

Selenium IDE позволяет экспортировать записанные тесты в тесты на Java, Python и других языках.

### Пример 

__TODO__

### Слайды

* [PDF](Seminar12.pdf)
* [PPTX](Seminar12.pptx)

### Видео

__TODO__

### Домашнее задание

__Срок сдачи без [штрафа](../../grading.md): ??.12.2020__

* Описание задания ([PDF](Homework09.pdf), [DOC](Homework09.doc)).

### Ссылки

#### Литература

1. [Selenium IDE](https://www.selenium.dev/selenium-ide/).
1. [Simon Stewart. Selenium IDE.](https://applitools.com/blog/selenium-ide-by-simon-stewart/)
1. [Хабр про Selenium](https://habr.com/ru/post/152653/).
1. [Пример](https://automated-testing.info/t/pishem-testy-na-selenium-ide-passhirenie-k-brauzeru-firefox/2455).
