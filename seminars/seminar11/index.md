Семинар 11
--

# Автоматизированое тестирование Web-интерфейсов. Selenium IDE.

Одним из основных средств тестирования Web-приложений является
[Selenium](http://www.seleniumhq.org/) ([русский сайт](https://selenium2.ru/)).
Это набор приложений и библиотек для управления работой Web-браузеров,
который позволяет записывать и воспроизводить действия пользователя с Web-приложениями,
запущенными в различных браузерах. Selenium включает в себя следующие части:

* Selenium IDE – расширение для браузеров (для каждого существует своя версия: Firefox, IE, Chrome и т.д.),
  которое позволяет записывать и воспроизводить действия пользователя в браузере.

* Selenium WebDriver – набор библиотек для различных языков программирования,
  позволяющих управлять браузером из программы, написанной на этом языке программирования.

* Selenium Server – приложение, которое позволяет принимать команды с удалённой машины,
  где работает сценарий автоматизации, и исполнять их в браузере.

* Selenium Grid – решение, которое позволяет объединить несколько серверов Selenium в распределённую сеть,
  таким образом масштабируя стенд автоматизации.

В данной практической работе будет использоваться Selenium IDE.
Сценарии Web-тестирования будут создаваться путем запись и редактирования DSL-кода.

### Примеры 

Примеры тестов для Selenium IDE (DSL на основе JSON, нужно открыть в Selenium IDE):

* [Пример 1](https://github.com/andrewt0301/qa-testing-course/blob/master/seminars/seminar11/example.side).
* [Пример 2](https://github.com/andrewt0301/qa-testing-course/blob/master/seminars/seminar11/test.side).

### Слайды

* [PDF](Seminar11.pdf)
* [PPTX](Seminar11.pptx)

### Видео

* [Часть 1](https://yadi.sk/i/V_-OJKirmjNwJA)
* [Часть 2](https://yadi.sk/i/EvkV_R6Cr9BMxA)
* [Часть 3 - кратко самое важное](https://yadi.sk/i/FsQNKoYJkk-9Wg)

### Домашнее задание

__Срок сдачи без [штрафа](../../grading.md): 12.12.2020__

* Описание задания ([PDF](Homework08.pdf), [DOC](Homework08.doc)).

### Ссылки

#### Литература

1. [Selenium IDE](https://www.selenium.dev/selenium-ide/).
1. [Simon Stewart. Selenium IDE.](https://applitools.com/blog/selenium-ide-by-simon-stewart/)
1. [Хабр про Selenium](https://habr.com/ru/post/152653/).
1. [Пример](https://automated-testing.info/t/pishem-testy-na-selenium-ide-passhirenie-k-brauzeru-firefox/2455).
