#  Displaying Values From Message Source (Property Files)

We can use the th:text=”#{key}” tag attribute to display values from property files.

For this to work, we need to configure the property file as a messageSource bean:

Java configuration

```java
@Bean
@Description("Spring Message Resolver")
public ResourceBundleMessageSource messageSource() {
    ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
    messageSource.setBasename("messages");
    return messageSource;
}
```

XML configuration

```xml
<bean class="org.springframework.context.support.ResourceBundleMessageSource">
    <property name="basenames" value="messages" />
</bean>
```

Here is the Thymeleaf HTML code to display the value associated with the key welcome.message:

```html
<span th:text="#{welcome.message}" />
```

# Displaying Model Attributes

## Simple Attributes

We can use the th:text=”${attributename}” tag attribute to display the value of model attributes.

```java
model.addAttribute("serverTime", dateFormat.format(new Date()));
```

And here's the HTML code to display the value of serverTime attribute:

```html
Current time is <span th:text="${serverTime}" />
```

## Collection Attributes

If the model attribute is a collection of objects, we can use the th:each tag attribute to iterate over it.

```java
public class Student {
    private Integer id;
    private String name;
    // standard getters and setters
}
```

We will add a list of students as model attribute in the controller class:

```java
List<Student> students = new ArrayList<Student>();
// logic to build student data
model.addAttribute("students", students);
```

Finally, we can use Thymeleaf template code to iterate over the list of students and display all field values:

```html
<tbody>
    <tr th:each="student: ${students}">
        <td th:text="${student.id}" />
        <td th:text="${student.name}" />
    </tr>
</tbody>
```

## Keeping iteration status

When using th:each, Thymeleaf offers a mechanism useful for keeping track of the status of your iteration: the status 
variable.

Status variables are defined within a th:each attribute and contain the following data:

- The current iteration index, starting with 0. This is the `index` property.
- The current iteration index, starting with 1. This is the `count` property.
- The total amount of elements in the iterated variable. This is the `size` property.
- The iter variable for each iteration. This is the `current` property.
- Whether the current iteration is even or odd. These are the `even/odd` boolean properties.
- Whether the current iteration is the first one. This is the `first` boolean property.
- Whether the current iteration is the last one. This is the `last` boolean property.

```html
<table>
  <tr>
    <th>NAME</th>
    <th>PRICE</th>
    <th>IN STOCK</th>
  </tr>
  <tr th:each="prod,iterStat : ${prods}" th:class="${iterStat.odd}? 'odd'">
    <td th:text="${prod.name}">Onions</td>
    <td th:text="${prod.price}">2.41</td>
    <td th:text="${prod.inStock}? #{true} : #{false}">yes</td>
  </tr>
</table>
```

will output

```html
<!DOCTYPE html>

<html>

  <head>
    <title>Good Thymes Virtual Grocery</title>
    <meta content="text/html; charset=UTF-8" http-equiv="Content-Type"/>
    <link rel="stylesheet" type="text/css" media="all" href="/gtvg/css/gtvg.css" />
  </head>

  <body>

    <h1>Product list</h1>
  
    <table>
      <tr>
        <th>NAME</th>
        <th>PRICE</th>
        <th>IN STOCK</th>
      </tr>
      <tr class="odd">
        <td>Fresh Sweet Basil</td>
        <td>4.99</td>
        <td>yes</td>
      </tr>
      <tr>
        <td>Italian Tomato</td>
        <td>1.25</td>
        <td>no</td>
      </tr>
      <tr class="odd">
        <td>Yellow Bell Pepper</td>
        <td>2.50</td>
        <td>yes</td>
      </tr>
      <tr>
        <td>Old Cheddar</td>
        <td>18.75</td>
        <td>yes</td>
      </tr>
    </table>
  
    <p>
      <a href="/gtvg/" shape="rect">Return to home</a>
    </p>

  </body>
</html>
```