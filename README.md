application.properties (src/main/resources)

```
spring.datasource.url=jdbc:mysql://localhost:3306/db?useSSL=false
spring.datasource.username=
spring.datasource.password=
spring.jpa.show-sql = true
spring.jpa.database-platform = org.hibernate.dialect.MySQL8Dialect
spring.jpa.generate-ddl=true
spring.jpa.hibernate.ddl-auto = update
spring.jpa.properties.hibernate.dialect = org.hibernate.dialect.MySQL8Dialect
```


## Logical database project

* **User**(<u>id</u>, username, email, password, lastLogin, active, score)
* **Questionnaire**(<u>id</u>, productName, productImage, date)
* **UserFilled**(<u>userId, questionnaireId</u>, status)
* **QuestionGroup**(<u>id</u>, name)
* **QuestionnaireQuestionGroup**(<u>questionnaireId</u>, <u>questionGroupId</u>)
* **Question**(<u>id</u>, questionContent)
* **QuestionAssociate**(<u>questionGroupId</u>, <u>questionId</u>)
* **Answer**(<u>id</u>, answerContent)
* **QuestionAnswer**(<u>questionId</u>, <u>answerId</u>)
* **UserAnswer**(<u>userId</u>, <u>answerId</u>)
* **OffensiveWord**(<u>word</u>)
* **Admin**(<u>id</u>, username, email, password)

