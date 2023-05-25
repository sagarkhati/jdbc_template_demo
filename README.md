# jdbc_template_demo

**Step1:** Go to [Spring Initializr](https://start.spring.io/) and create the project with following dependencies included: 
- Spring Web
- Spring Data JDBC
- MySQL Driver (As per the database)
- Spring Boot DevTools (Optional)


**Step2:** Extract the downloaded code and import it into IDE (Eclipse).


**Step3:** In application.properties file, add the following properties: 

```
spring.datasource.url=jdbc:mysql://localhost:3306/jdbc_template_demo
spring.datasource.username=root
spring.datasource.password=root
```


**Step4:** Now launch the application as Java Application, It should successfully started.
 