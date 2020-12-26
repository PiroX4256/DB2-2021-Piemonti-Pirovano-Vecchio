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