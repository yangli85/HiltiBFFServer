#Hilti BFF Server
Framework:Spring+Hibernate,a restful api server! It's just a sample,feel free to do any changes!
##How to run?
1.config database in src/main/resources/application.properties

2.create a sample table EMPLOYEE,you can find sql script in db_migrate/db.sql

3.run mvn clean install jetty:run
##How to test?
Use postman to test api.

1.get http://localhost:8080/employee/   list all

2.post http://localhost:8080/employee/  create.

3.get http://localhost:8080/employee/{sns} query one.

4.delete http://localhost:8080/employee/{sns} delete one 
