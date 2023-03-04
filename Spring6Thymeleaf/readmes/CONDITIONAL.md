# Conditional Evaluation

## _if_ and _unless_

We use the _th:if=”${condition}”_ attribute to display a section of the view if the condition is met. And we use the 
_th:unless=”${condition}”_ attribute to display a section of the view if the condition is not met.

```java
public class Student {
    private Integer id;
    private String name;
    private Character gender;
    
    // standard getters and setters
}
```

Suppose this field has two possible values (M or F) to indicate the student's gender.

If we wish to display the words “Male” or “Female” instead of the single character, we could do this using this 
Thymeleaf code:

```html
<td>
    <span th:if="${student.gender} == 'M'" th:text="Male" /> 
    <span th:unless="${student.gender} == 'M'" th:text="Female" />
</td>
```

## _switch_ and _case_

We use the th:switch and th:case attributes to display content conditionally using the switch statement structure.

```html
<td th:switch="${student.gender}">
    <span th:case="'M'" th:text="Male" /> 
    <span th:case="'F'" th:text="Female" />
</td>
```

## Handling User Input

We can handle form input using the _th:action=”@{url}”_ and _th:object=”${object}”_ attributes. We use _th:action_ to 
provide the form action URL and th:object to specify an object to which the submitted form data will be bound.

Individual fields are mapped using the _th:field=”*{name}”_ attribute, where the name is the matching property of 
the object.

```html
<form action="#" th:action="@{/saveStudent}" th:object="${student}" method="post">
    <table border="1">
        <tr>
            <td><label th:text="#{msg.id}" /></td>
            <td><input type="number" th:field="*{id}" /></td>
        </tr>
        <tr>
            <td><label th:text="#{msg.name}" /></td>
            <td><input type="text" th:field="*{name}" /></td>
        </tr>
        <tr>
            <td><input type="submit" value="Submit" /></td>
        </tr>
    </table>
</form>
```

In the above code, /saveStudent is the form action URL and a student is the object that holds the form data submitted.

```java
@Controller
public class StudentController {
    @RequestMapping(value = "/saveStudent", method = RequestMethod.POST)
    public String saveStudent(@ModelAttribute Student student, BindingResult errors, Model model) {
        // logic to process input data
    }
}
```

The @RequestMapping annotation maps the controller method with the URL provided in the form. The annotated method 
saveStudent() performs the required processing for the submitted form. Finally, the @ModelAttribute annotation binds 
the form fields to the student object.