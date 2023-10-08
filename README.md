# heroku_restfulbooker_tests

<h1 >Демопроект автоматизации тестирования API на <a href="https://restful-booker.herokuapp.com/apidoc/index.html">restful-booker</a></h1>

##  Содержание 

* <a href="#tools">Технологии и инструменты</a>

* <a href="#console">Запуск тестов из терминала</a>

* <a href="#jenkins">Запуск тестов в Jenkins</a>

* <a href="#telegram">Уведомления в Telegram</a>

* <a href="#allure">Allure Report отчеты</a>

* <a href="#allure-testops">Интеграция с Allure TestOps</a>

* <a href="#jira">Интеграция с Jira</a>


<a id="tools"></a>
## Технологии и инструменты

| Java | IntelliJ Idea | Allure | Allure TestOps | GitHub | JUnit 5 | Gradle | REST Assured | Jenkins | Jira |
| ---- | ------------- | ------ | ------------- | ------ | -------| ------ | ------------| --------| ----- |
| <a href="https://www.java.com/"><img src="media/logos/Java.svg" width="50" height="50"  alt="Java"/></a> | <div align="center"><a id ="tech" href="https://www.jetbrains.com/idea/"><img src="media/logos/Intelij_IDEA.svg" width="50" height="50"  alt="IDEA"/></a></div> | <a href="https://github.com/allure-framework"><img src="media/logos/Allure_Report.svg" width="50" height="50"  alt="Allure"/></a> | <div align="center"><a href="https://qameta.io/"><img src="media/logos/AllureTestOps.svg" width="50" height="50"  alt="Allure TestOps"/></a></div> | <a href="https://github.com/"><img src="media/logos/GitHub.svg" width="50" height="50"  alt="Github"/></a> | <a href="https://junit.org/junit5/"><img src="media/logos/JUnit5.svg" width="50" height="50"  alt="JUnit 5"/></a> | <a href="https://gradle.org/"><img src="media/logos/Gradle.svg" width="50" height="50"  alt="Gradle"/></a> | <div align="center"><a href="https://rest-assured.io/"><img src="media/logos/RestAssured.svg" width="50" height="50"  alt="RestAssured"/></a></div>  |   <a href="https://www.jenkins.io/"><img src="media/logos/Jenkins.svg" width="50" height="50"  alt="Jenkins"/></a> | <a href="https://www.atlassian.com/ru/software/jira"><img src="media/logos/Jira.svg" width="50" height="50"  alt="Jira"/></a> |





Автотесты в этом проекте написаны на Java .\
<code>Gradle</code> — используется как инструмент автоматизации сборки.\
<code>JUnit5</code> — для выполнения тестов.\
<code>REST Assured</code> — для тестирования REST-API сервисов.\
<code>Allure Report</code> — для визуализации результатов тестирования.\
<code>Allure TestOps</code> — как система управления тестированием.\
<code>Jira</code> — как инструмент управления проектом и таск-трекер.\
<code>Telegram Bot</code> — для уведомлений о результатах тестирования.


<a id="console"></a>

***Удалённый запуск реализован через сборку в  Jenkins:***

```bash  
clean test
```

<a id="jenkins"></a>
## <img src="media/logos/Jenkins.svg" width="25" height="25"/></a> Запуск тестов в [Jenkins](https://jenkins.autotests.cloud/job/EphimSh_qa_guru_20_heroku_restfulbooker/)

<p align="center">

> Для запуска необходимо нажать "Собрать с параметрами".

<a href="https://jenkins.autotests.cloud/job/AD_demo_api_reqres/"><img src="media/screenshots/jenkins_build.png" alt="Jenkins"/></a>

<a id="telegram"></a>
## <img src="media/logos/Telegram.svg" width="25" height="25"/></a> Уведомления в Telegram

<p >

> С помощью настроенного бота после завершения прогона в Jenkins поступают уведомления в Telegram.

<img title="telegram bot" src="media/screenshots/telegram.png">
</p>

<a id="allure"></a>
## <img src="media/logos/Allure_Report.svg" width="25" height="25"/></a> [Allure Report](https://jenkins.autotests.cloud/job/EphimSh_qa_guru_20_heroku_restfulbooker/allure/) отчеты
### Основное окно

<p align="center">
<img title="Allure Dashboard" src="media/screenshots/allure_report_dashboard.png">
</p>

### Отчеты по тестам

<p align="center">

> В отчете по тестам присутствует развернутая информация по запросам и ответам.

<img title="Allure Tests" src="media/screenshots/allure_report_testcase.png">
</p>

<a id="allure-testops"></a>
## <img src="media/logos/AllureTestOps.svg" width="25" height="25"/></a> Интеграция с [Allure TestOps](https://allure.autotests.cloud/launch/30743)

### Основное окно

<p align="center">
<img title="Allure TestOps" src="media/screenshots/alluretestops_dashboard.png">
</p>

### Иерархия тестов в ветке

<p align="center">
<img title="Allure TestOps" src="media/screenshots/alluretestops_testcase.png">
</p>

<a id="jira"></a>
## <img src="media/logos/Jira.svg" width="25" height="25"/></a> Интеграция с [Jira](https://jira.autotests.cloud/browse/HOMEWORK-907)

<p align="center">
<img title="Jira" src="media/screenshots/jira.png">
</p>
