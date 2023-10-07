# heroku_restfulbooker_tests


<h1 >Демопроект автоматизации тестирования API на <a href="https://reqres.in/">reqres.in</a></h1>

![WB_logo.jpg](media/logo/reqres_logo.png)

##  Содержание 

* <a href="#tools">Технологии и инструменты</a>

* <a href="#cases">Реализованные проверки</a>

* <a href="#console">Запуск тестов из терминала</a>

* <a href="#jenkins">Запуск тестов в Jenkins</a>

* <a href="#telegram">Уведомления в Telegram</a>

* <a href="#allure">Allure Report отчеты</a>

* <a href="#allure-testops">Интеграция с Allure TestOps</a>

* <a href="#jira">Интеграция с Jira</a>


<a id="tools"></a>
## Технологии и инструменты

- [Java](https://www.java.com/) ![Java Logo](media/logos/Java.svg)
- [IntelliJ Idea](https://www.jetbrains.com/idea/) ![IntelliJ Idea Logo](media/logos/Intelij_IDEA.svg)
- [Allure](https://github.com/allure-framework/) ![Allure Logo](media/logos/Allure_Report.svg)
- [Allure TestOps](https://qameta.io/) ![Allure TestOps Logo](media/logos/AllureTestOps.svg)
- [GitHub](https://github.com/) ![GitHub Logo](media/logos/GitHub.svg)
- [JUnit 5](https://junit.org/junit5/) ![JUnit 5 Logo](media/logos/JUnit5.svg)
- [Gradle](https://gradle.org/) ![Gradle Logo](media/logos/Gradle.svg)
- [REST Assured](https://rest-assured.io/) ![REST Assured Logo](media/logos/RestAssured.svg)
- [Jenkins](https://www.jenkins.io/) ![Jenkins Logo](media/logos/Jenkins.svg)
- [Jira](https://www.atlassian.com/ru/software/jira/) ![Jira Logo](media/logos/Jira.svg)





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
## <img src="media/logos/Jenkins.svg" width="25" height="25"/></a> Запуск тестов в [Jenkins](https://jenkins.autotests.cloud/job/vskltsv_hw_rest-api_jenkins/)

<p align="center">

> Для запуска необходимо нажать "Собрать с параметрами".

<a href="https://jenkins.autotests.cloud/job/AD_demo_api_reqres/"><img src="media/screenshots/JenkinsSborka.png" alt="Jenkins"/></a>

> При клике на сборку после завершения можно увидеть артефакты запуска и полезные ссылки для более детального изучения прогона.

<a href="https://jenkins.autotests.cloud/job/AD_demo_api_reqres/"><img src="media/screenshots/JenkinsIntegrations.png" alt="Jenkins"/></a>
</p>

<a id="telegram"></a>
## <img src="media/logos/Telegram.svg" width="25" height="25"/></a> Уведомления в Telegram

<p >

> С помощью настроенного бота после завершения прогона в Jenkins поступают уведомления в Telegram.

<img title="telegram bot" src="media/screenshots/TelegramBotAPI.png">
</p>

<a id="allure"></a>
## <img src="media/logos/Allure_Report.svg" width="25" height="25"/></a> [Allure Report](https://jenkins.autotests.cloud/job/vskltsv_hw_rest-api_jenkins/allure/) отчеты

### Основное окно

<p align="center">
<img title="Allure Dashboard" src="media/screenshots/AllureDashboard.png">
</p>

### Отчеты по тестам

<p align="center">

> В отчете по тестам присутствует развернутая информация по запросам и ответам.

<img title="Allure Tests" src="media/screenshots/AllureWithInfo.png">
</p>

<a id="allure-testops"></a>
## <img src="media/logos/AllureTestOps.svg" width="25" height="25"/></a> Интеграция с [Allure TestOps](https://allure.autotests.cloud/project/3625/dashboards)

### Основное окно

<p align="center">
<img title="Allure TestOps" src="media/screenshots/Dashboard_TO.png">
</p>

### Иерархия тестов в ветке

<p align="center">
<img title="Allure TestOps" src="media/screenshots/TreeByFeatures_TO.png">
</p>

<a id="jira"></a>
## <img src="media/logos/Jira.svg" width="25" height="25"/></a> Интеграция с [Jira](https://jira.autotests.cloud/browse/HOMEWORK-872)

<p align="center">
<img title="Jira" src="media/screenshots/JiraAPI.png">
</p>
