Семинар 9
--

# Тестирование пользовательского интерфейса: настольные приложения.

### Слайды

* [PDF](Seminar09.pdf)
* [PPTX](Seminar09.pptx)

### Видео

* [Часть 1](TODO)
* [Часть 2](TODO)

![MacOS Security](MacOsSecurity.png)

### Домашнее задание

__Срок сдачи без [штрафа](../../grading.md): ??.??.2020__

#### Задание

* Используя фреймворк [AssertJ](https://joel-costigliola.github.io/assertj/assertj-swing.html),
  разработать GUI-тесты для приложения [Calculator](
  https://github.com/andrewt0301/qa-testing-course/blob/master/seminars/seminar09/example/calculator/src/main/java/ru/hse/Calculator.java)
  на JUnit (заготовка [здесь](https://github.com/andrewt0301/qa-testing-course/blob/master/seminars/seminar09/example/calculator/src/test/java/ru/hse/CalculatorJUnitTest.java))
  или TestNG (заготовка [здесь](https://github.com/andrewt0301/qa-testing-course/blob/master/seminars/seminar09/example/calculator/src/test/java/ru/hse/CalculatorTestNGTest.java)).
* Тесты должны опеспечивать полное покрытие кода (JaCoCo) и демонстрировать ошибку в реализации.
* Оценить тестабельность приложения. Кратко описать проблемы, которые возникли при разработке
  тестов и которые могут возникнуть в будущем из-за изменений в GUI приложения.
  Кратко 2-5 предложений.
* Изменять код приложения Calculator для улучшения тестабельности не разрешается.

Проект с приложением Calculator и заготовками для тестов находится [здесь](
https://github.com/andrewt0301/qa-testing-course/tree/master/seminars/seminar09/example/calculator).

### Ссылки

#### Литература

Тестирование графического интерфейса:
* [Тестирование графического интерфейса](
  https://en.wikipedia.org/wiki/Graphical_user_interface_testing) (Wikipedia)
* [Инструменты тестирования графического интерфейса](
  https://en.wikipedia.org/wiki/Comparison_of_GUI_testing_tools) (Wikipedia)

Коммерческие инструменты тестирования графического интерфейса:
* [Visual Studio](https://docs.microsoft.com/en-us/visualstudio/test/use-ui-automation-to-test-your-code?view=vs-2017)
* [TestComplete](https://smartbear.com/product/testcomplete/overview/). [Russian](https://smartbear.ru/company/products/testcomplete.aspx). 
* [Ranorex](https://www.ranorex.com/)

Открытая библиотека AssertJ для тестирования Swing-приложений:
* [AssertJ Swing](http://joel-costigliola.github.io/assertj/assertj-swing.html) (GitHub)
