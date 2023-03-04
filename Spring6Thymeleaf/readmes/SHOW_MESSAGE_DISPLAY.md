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