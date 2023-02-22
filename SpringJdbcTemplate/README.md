# Spring JdbcTemplate

## Overview

All the classes in Spring JDBC are divided into four separate packages:

- **core** — the core functionality of JDBC. Some of the important classes under this package include JdbcTemplate,
  SimpleJdbcInsert, SimpleJdbcCall and NamedParameterJdbcTemplate.
- **datasource** — utility classes to access a data source. It also has various data source implementations for testing
  JDBC code outside the Jakarta EE container.
- **object** — DB access in an object-oriented manner. It allows running queries and returning the results as a business
  object. It also maps the query results between the columns and properties of business objects.
- **support** — support classes for classes under core and object packages, e.g., provides the SQLException translation
  functionality

## Configuration

Let's start with some simple configuration of the data source.

```java

@Configuration
@ComponentScan("com.baeldung.jdbc")
public class SpringJdbcConfig {
    @Bean
    public DataSource mysqlDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/springjdbc");
        dataSource.setUsername("guest_user");
        dataSource.setPassword("guest_password");

        return dataSource;
    }
}
```

Here is a quick configuration that creates an instance of H2 embedded database and pre-populates it with simple SQL
scripts

```java
@Bean
public DataSource dataSource(){
        return new EmbeddedDatabaseBuilder()
        .setType(EmbeddedDatabaseType.H2)
        .addScript("classpath:jdbc/schema.sql")
        .addScript("classpath:jdbc/test-data.sql").build();
        }
```

the same can be done using XML configuring for the datasource:

```xml

<bean id="dataSource" class="org.apache.commons.dbcp.BasicDataSource"
      destroy-method="close">
    <property name="driverClassName" value="com.mysql.jdbc.Driver"/>
    <property name="url" value="jdbc:mysql://localhost:3306/springjdbc"/>
    <property name="username" value="guest_user"/>
    <property name="password" value="guest_password"/>
</bean>
```

## Configuration with Spring Boot

Spring Boot provides a starter spring-boot-starter-jdbc for using JDBC with relational databases.

As with every Spring Boot starter, this one helps us get our application up and running quickly.

```xml

<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-jdbc</artifactId>
</dependency>
<dependency>
<groupId>mysql</groupId>
<artifactId>mysql-connector-java</artifactId>
<scope>runtime</scope>
</dependency>
```

Spring Boot configures the data source automatically for us. We just need to provide the properties in a properties
file:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/springjdbc
spring.datasource.username=guest_user
spring.datasource.password=guest_password
```

And that's it. Our application is up and running just by doing these configurations only. We can now use it for other
database operations.

## JdbcTemplate

The JDBC template is the main API through which we'll access most of the functionality that we're interested in:

- creation and closing of connections
- running statements and stored procedure calls
- iterating over the _ResultSet_ and returning results

<hr/>

JDBC template asosiy API boʻlib, u orqali bizni qiziqtirgan koʻpgina funksiyalarga kirishimiz mumkin:

```java
int result=jdbcTemplate.queryForObject(
        "SELECT COUNT(*) FROM EMPLOYEE",Integer.class);
```

```java
public int addEmplyee(int id){
        return jdbcTemplate.update(
        "INSERT INTO EMPLOYEE VALUES (?, ?, ?, ?)",id,"Bill","Gates","USA");
        }
```

Notice the standard syntax of providing parameters using the ? character.

## NamedParameterJdbcTemplate

To get support for named parameters, we'll use the other JDBC template provided by the framework — the
NamedParameterJdbcTemplate.

Additionally, this wraps the JbdcTemplate and provides an alternative to the traditional syntax using ? to specify
parameters.

Under the hood, it substitutes the named parameters to JDBC ? placeholder and delegates to the wrapped JDCTemplate to
run the queries:

```java
SqlParameterSource namedParameters=new MapSqlParameterSource().addValue("id",1);
        return namedParameterJdbcTemplate.queryForObject(
        "SELECT FIRST_NAME FROM EMPLOYEE WHERE ID = :id",namedParameters,String.class);
```

Notice how we are using the MapSqlParameterSource to provide the values for the named parameters.

Let's look at using properties from a bean to determine the named parameters:

```java
Employee employee=new Employee();
        employee.setFirstName("James");

        String SELECT_BY_ID="SELECT COUNT(*) FROM EMPLOYEE WHERE FIRST_NAME = :firstName";

        SqlParameterSource namedParameters=new BeanPropertySqlParameterSource(employee);
        return namedParameterJdbcTemplate.queryForObject(
        SELECT_BY_ID,namedParameters,Integer.class);
```

## Mapping Query Results to Java Object

Another very useful feature is the ability to map query results to Java objects by implementing the RowMapper interface.

For example, for every row returned by the query, Spring uses the row mapper to populate the java bean:

```java
public class EmployeeRowMapper implements RowMapper<Employee> {
    @Override
    public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
        Employee employee = new Employee();

        employee.setId(rs.getInt("ID"));
        employee.setFirstName(rs.getString("FIRST_NAME"));
        employee.setLastName(rs.getString("LAST_NAME"));
        employee.setAddress(rs.getString("ADDRESS"));

        return employee;
    }
}
```

Subsequently, we can now pass the row mapper to the query API and get fully populated Java objects:

```java
String query="SELECT * FROM EMPLOYEE WHERE ID = ?";
        Employee employee=jdbcTemplate.queryForObject(
        query,new Object[]{id},new EmployeeRowMapper());
```

# JDBC Operations Using SimpleJdbc Classes

SimpleJdbc classes provide an easy way to configure and run SQL statements. These classes use database metadata to build
basic queries. So, SimpleJdbcInsert and SimpleJdbcCall classes provide an easier way to run insert and stored procedure
calls.

<hr/>

_SimpleJdbc_ classlari SQL querylarni yozish va execute qilishning oson methodlarni taqdim etadi. Ushbu classlar asosiy
querylarni yaratish uchun database meta-ma'lumotlaridan foydalanadi. SimpleJdbcInsert va SimpleJdbcCall class oson
insert qilish va protseduralarni chaqirish osonroq usulini ta'minlaydi.

## SimpleJdbcInsert

Let's take a look at running simple insert statements with minimal configuration.

The INSERT statement is generated based on the configuration of SimpleJdbcInsert. All we need is to provide the Table
name, Column names and values.

<hr/>

SimpleJdbcInsert minimal konfiguratsiya ega oddiy insert iboralarini bajarishimiz uchun ishlatamiz. INSERT iborasi yani
statementi SimpleJdbcInsert asosida yaratilgan. Bizga kerak bo'ladigan yagona narsa table nomi, columnlar va valuelarni
ko'rsatish.

```java
SimpleJdbcInsert simpleJdbcInsert=
        new SimpleJdbcInsert(dataSource).withTableName("EMPLOYEE");
```

Columns va values

```java
public int addEmplyee(Employee emp){
        Map<String, Object> parameters=new HashMap<String, Object>();
        parameters.put("ID",emp.getId());
        parameters.put("FIRST_NAME",emp.getFirstName());
        parameters.put("LAST_NAME",emp.getLastName());
        parameters.put("ADDRESS",emp.getAddress());

        return simpleJdbcInsert.execute(parameters);
        }
```

Further, we can use the executeAndReturnKey() API to allow the database to generate the primary key. We'll also need to
configure the actual auto-generated column:

```java
SimpleJdbcInsert simpleJdbcInsert=new SimpleJdbcInsert(dataSource)
        .withTableName("EMPLOYEE")
        .usingGeneratedKeyColumns("ID");

        Number id=simpleJdbcInsert.executeAndReturnKey(parameters);
        System.out.println("Generated id - "+id.longValue());
```

## SimpleJdbcCall

Let's also take a look at running stored procedures.

We'll make use of the SimpleJdbcCall abstraction:

```java
SimpleJdbcCall simpleJdbcCall=new SimpleJdbcCall(dataSource)
        .withProcedureName("READ_EMPLOYEE");
```

```java
public Employee getEmployeeUsingSimpleJdbcCall(int id){
        SqlParameterSource in=new MapSqlParameterSource().addValue("in_id",id);
        Map<String, Object> out=simpleJdbcCall.execute(in);

        Employee emp=new Employee();
        emp.setFirstName((String)out.get("FIRST_NAME"));
        emp.setLastName((String)out.get("LAST_NAME"));

        return emp;
        }
```

# JDBC Batch Operations

Another simple use case is batching multiple operations together.

## Basic Batch Operations Using JdbcTemplate

Using JdbcTemplate, Batch Operations can be run via the batchUpdate() API.

The interesting part here is the concise but highly useful BatchPreparedStatementSetter implementation:

```java
public int[]batchUpdateUsingJdbcTemplate(List<Employee> employees){
        return jdbcTemplate.batchUpdate("INSERT INTO EMPLOYEE VALUES (?, ?, ?, ?)",
        new BatchPreparedStatementSetter(){
@Override
public void setValues(PreparedStatement ps,int i)throws SQLException{
        ps.setInt(1,employees.get(i).getId());
        ps.setString(2,employees.get(i).getFirstName());
        ps.setString(3,employees.get(i).getLastName());
        ps.setString(4,employees.get(i).getAddress();
        }
@Override
public int getBatchSize(){
        return 50;
        }
        });
        }
```

## Batch Operations Using NamedParameterJdbcTemplate

We also have the option of batching operations with the NamedParameterJdbcTemplate – batchUpdate() API.

This API is simpler than the previous one. So, there's no need to implement any extra interfaces to set the parameters,
as it has an internal prepared statement setter to set the parameter values.

Instead, the parameter values can be passed to the batchUpdate() method as an array of SqlParameterSource.

```java
SqlParameterSource[]batch=SqlParameterSourceUtils.createBatch(employees.toArray());
        int[]updateCounts=namedParameterJdbcTemplate.batchUpdate(
        "INSERT INTO EMPLOYEE VALUES (:id, :firstName, :lastName, :address)",batch);
        return updateCounts;
```