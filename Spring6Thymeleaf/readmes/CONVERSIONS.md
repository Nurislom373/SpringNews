# Using Conversions

We use the double bracket syntax {{}} to format data for display. This makes use of the formatters configured for that 
type of field in the conversionService bean of the context file.

```html
<tr th:each="student: ${students}">
    <td th:text="${{student.name}}" />
</tr>
```

We can also use the #conversions utility to convert objects for display. The syntax for the utility function is 
#conversions.convert(Object, Class) where Object is converted to Class type.

Here's how to display student object percentage field with the fractional part removed:

```html
<tr th:each="student: ${students}">
    <td th:text="${#conversions.convert(student.percentage, 'Integer')}" />
</tr>
```