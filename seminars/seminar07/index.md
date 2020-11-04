Семинар 7
--

# Мутации исходного кода.

### Слайды

* [PDF](Seminar07.pdf)
* [PPTX](Seminar07.pptx)

### Видео

* __TODO__

### Пример

Java-проект со скриптом сборки для Maven,
который демонстрирует использование [Pitest](https://pitest.org) для мутационного тестирования,
находится [здесь](https://github.com/andrewt0301/qa-testing-course/blob/master/seminars/seminar07/example).
В примере используются тесты на [JUnit 5](https://junit.org/junit5/).

Для запуска мутационного тестирования нужно запустить следующие задания Maven-а:

1. compile - скомпилировать код;
1. test - скомпилировать тесты;
1. pitest:mutationCoverage - запустить Pitest.

Отчет о тестировании в формате HTML будет в папке `example/target/pit-reports`.

В IntelliJ IDEA все выглядит так:

![Pitest в IntelliJ IDEA](example/Screenshot.png).

### Домашнее задание

__Срок сдачи без [штрафа](../../grading.md): ??.11.2020__

#### Задание 

* Построить покрытие тестами класса [Account](../seminar06/index.md).
* Провести мутационное тестирование класса Account:
   * Привести пример убитых мутантов (для каждого тестового метода).
   * Привести пример выжившего мутанта (если будет обнаружен) и изменение в тестах его убивающее.
   * Привести пример эквивалентного мутанта.

#### Результат

* Файл с примерами мутантов.
* Отчет о покрытии.

#### Примеры мутантов

* [PDF](Mutants_Example.pdf)
* [DOC](Mutants_Example.doc)


### Ссылки

#### Литература

1. [Мутационное тестирование](https://ru.wikipedia.org/wiki/Мутационное_тестирование) (Wikipedia).
1. [Про мутационное тестирование](https://habr.com/ru/post/334394/) (Хабр).
1. [Pitest](https://pitest.org).
1. [Пример работы с PIT mutator](https://habr.com/ru/post/139337/) (Хабр).
1. [Еще про мутационное тестирование](http://getbug.ru/mutatsionnoe-testirovanie-na-prostom-primere/).
1. [JS](https://habr.com/ru/post/341094/).
1. [Python](https://habr.com/ru/company/vdsina/blog/512630/).
