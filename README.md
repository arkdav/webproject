# webproject
The project is online art goods store.

# setup
To run the project at you machine you should:
1. Install maven;
2. Open command line at directory where file pom.xml is.
3. "mvn clean install"
...

Исходное
Версия java – 1.8.0
Версия tomcat – 9.0.27
СУБД- MySQL
Открыть как проект Intellij Idea
Maven -> Lifecycle -> install (создаем war)
Project Settings -> SDK (java version 11.0.4)
Add configuration ->Tomcat server -> Local. Application server – Tomcat 9.0.27, URL – http://localhost:8082/, HTTP port 8082, JRE – Default (11- project SDK), JMX port 1097, deployment – webproject:war, application context: /
Указан порт для подключения к бд в application.properties.
Выполнить файл bd.sql для тестового создания таблиц в MySQL Workbench.

