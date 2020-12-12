Семинар 12
--

# Автоматизированое тестирование Web-интерфейсов. Selenium WebDriver.

В данной практической работе будет использоваться
[Selenium WebDriver](https://ru.wikipedia.org/wiki/Selenium).
Его можно скачать [здесь](https://www.selenium.dev/downloads/).

Кроме этого Selenium WebDriver для Java доступен для скачивания из
[Maven-репозитория](https://mvnrepository.com/artifact/org.seleniumhq.selenium).
Системы сборки Maven и Gradle позволяют автоматически скачать нужную версию.

### Пример

Пример JUnit-теста, использующего Selenium WebDriver, находится [здесь](examples/exampleSelenium).
Этот тест запускает браузер, заходит на страницу google.com,
вводит в окно поиска ключевое слово и ждет получения результата.
Тест позволяет использовать несколько различных браузеров: Chrome, Safari, Firefox. 

Для успешной работы с проектом в IntelliJ IDEA необходимо:

* Импортировать Gradle-проект `build.gradle` (Import Project и выбрать нужный файл).
  При этом будут скачаны все зависимости и создан IDEA-проект.

* Открыть файл `src/test/java/ru/hse/BasicSeleniumTest.java`, который содержит код примера.

* Для запуска теста для нужного типа браузера требуется скачать внешнее приложение драйвера и
  разместить его в корневой папке проекта. Путь к нему задается в переменой окружения:
  для Safari - ничего не нужно (в MacOS все уже есть),
  для Chrome – `webdriver.chrome.driver`,
  для Firefox – `webdriver.gecko.driver`, и т.д.
  Ссылки для скачивани нужных версий драйвера можно найти [здесь](https://www.selenium.dev/downloads/).

Для того, чтобы записывать Web-сценарии необходимо установить Selenium IDE в свой браузер.
Список возможных вариантов можно найти [здесь](https://www.selenium.dev/downloads/).
Selenium IDE позволяет экспортировать записанные тесты в тесты на Java, Python и других языках.

### Слайды

* [PDF](Seminar12.pdf)
* [PPTX](Seminar12.pptx)

### Видео

* [Видео](https://yadi.sk/i/Ca4_1azaLxzhjA)

### Домашнее задание

__Срок сдачи без [штрафа](../../grading.md): 19.12.2020__

* Описание задания ([PDF](Homework09.pdf), [DOC](Homework09.doc)).

### Ссылки

#### Литература

1. [Simon Stewart. Selenium WebDriver.](
   https://www.aosabook.org/en/selenium.html)
1. [Учусь Selenium WebDriver. Бесплатная электронная книга.](
   https://github.com/andrewt0301/qa-testing-course/tree/master/related/selenium-webdriver-ru.pdf)
1. [Пример.](
   https://automated-testing.info/t/pishem-testy-na-selenium-ide-passhirenie-k-brauzeru-firefox/2455)
