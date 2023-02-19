# Thymeleaf

Thymeleaf is a Java template engine for processing and creating HTML, XML, JavaScript, CSS and text.

The library is extremely extensible, and its natural templating capability ensures we can prototype templates without a
back end. This makes development very fast when compared with other popular template engines such as JSP.

The main goal of Thymeleaf is to provide an elegant and highly-maintainable way of creating templates. To achieve this,
it builds on the concept of Natural Templates to inject its logic into template files in a way that doesn’t affect the
template from being used as a design prototype. This improves communication of design and bridges the gap between design
and development teams.

Thymeleaf has also been designed from the beginning with Web Standards in mind – especially HTML5 – allowing you to
create fully validating templates if that is a need for you.

<hr/>

Thymeleaf - bu HTML, XML, JavaScript, CSS va matnlarni qayta ishlash va yaratish uchun Java shablon mexanizmi yani
template engine.

## What kind of templates can Thymeleaf process?

Out-of-the-box, Thymeleaf allows you to process six kinds of templates, each of which is called a Template Mode:

- HTML
- XML
- TEXT
- JAVASCRIPT
- CSS
- RAW

There are two markup template modes (HTML and XML), three textual template modes (TEXT, JAVASCRIPT and CSS) and a no-op
template mode (RAW).

The HTML template mode will allow any kind of HTML input, including HTML5, HTML 4 and XHTML. No validation or
well-formedness check will be performed, and template code/structure will be respected to the biggest possible extent in
output.

The XML template mode will allow XML input. In this case, code is expected to be well-formed – no unclosed tags, no
unquoted attributes, etc – and the parser will throw exceptions if well-formedness violations are found. Note that no
validation (against a DTD or XML Schema) will be performed.

The TEXT template mode will allow the use of a special syntax for templates of a non-markup nature. Examples of such
templates might be text emails or templated documentation. Note that HTML or XML templates can be also processed as
TEXT, in which case they will not be parsed as markup, and every tag, DOCTYPE, comment, etc, will be treated as mere
text.

The JAVASCRIPT template mode will allow the processing of JavaScript files in a Thymeleaf application. This means being
able to use model data inside JavaScript files in the same way it can be done in HTML files, but with
JavaScript-specific integrations such as specialized escaping or natural scripting. The JAVASCRIPT template mode is
considered a textual mode and therefore uses the same special syntax as the TEXT template mode.

The CSS template mode will allow the processing of CSS files involved in a Thymeleaf application. Similar to the
JAVASCRIPT mode, the CSS template mode is also a textual mode and uses the special processing syntax from the TEXT
template mode.

The RAW template mode will simply not process templates at all. It is meant to be used for inserting untouched
resources (files, URL responses, etc.) into the templates being processed. For example, external, uncontrolled resources
in HTML format could be included into application templates, safely knowing that any Thymeleaf code that these resources
might include will not be executed.

## Integrating Thymeleaf With Spring

First, let's see the configurations required to integrate with Spring. The thymeleaf-spring library is required for the
integration.

We'll add the following dependencies to our Maven POM file:

```xml
<dependency>
    <groupId>org.thymeleaf</groupId>
    <artifactId>thymeleaf</artifactId>
    <version>3.0.11.RELEASE</version>
</dependency>
<dependency>
    <groupId>org.thymeleaf</groupId>
    <artifactId>thymeleaf-spring5</artifactId>
    <version>3.0.11.RELEASE</version>
</dependency>
```

The SpringTemplateEngine class performs all of the configuration steps.

We can configure this class as a bean in the Java configuration file:

```java
@Bean
@Description("Thymeleaf Template Resolver")
public ServletContextTemplateResolver templateResolver(){
    ServletContextTemplateResolver templateResolver=new ServletContextTemplateResolver();
    templateResolver.setPrefix("/WEB-INF/views/");
    templateResolver.setSuffix(".html");
    templateResolver.setTemplateMode("HTML5");
    return templateResolver;
}

@Bean
@Description("Thymeleaf Template Engine")
public SpringTemplateEngine templateEngine(){
    SpringTemplateEngine templateEngine=new SpringTemplateEngine();
    templateEngine.setTemplateResolver(templateResolver());
    templateEngine.setTemplateEngineMessageSource(messageSource());
    return templateEngine;
}
```

The templateResolver bean properties prefix and suffix indicate the location of the view pages within the webapp
directory and their filename extension, respectively.

The ViewResolver interface in Spring MVC maps the view names returned by a controller to actual view objects.
ThymeleafViewResolver implements the ViewResolver interface, and it's used to determine which Thymeleaf views to render,
given a view name.

```java
@Bean
@Description("Thymeleaf View Resolver")
public ThymeleafViewResolver viewResolver(){
    ThymeleafViewResolver viewResolver=new ThymeleafViewResolver();
    viewResolver.setTemplateEngine(templateEngine());
    viewResolver.setOrder(1);
    return viewResolver;
}
```

## Thymeleaf in Spring Boot

Spring Boot provides auto-configuration for Thymeleaf by adding the spring-boot-starter-thymeleaf dependency:

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-thymeleaf</artifactId>
    <version>2.3.3.RELEASE</version>
</dependency>
```

No explicit configuration is necessary. By default, HTML files should be placed in the resources/templates location.

<hr/>

Spring Boot ga thymeleaf dependency qo'shasiz shuni o'zi yetarli hech qanday konfiguratsiya shart emas.
_resources/templates_ ga HTML larni yaratib thymeleaf bilan ishlayverasiz.

## Displaying Values From Message Source (Property Files)

We can use the th:text=”#{key}” tag attribute to display values from property files.

For this to work, we need to configure the property file as a messageSource bean:

<hr/>

properties filelaridagi valuelarni ko'rsatish uchun biz _th:text="#{key}"_ tag atributidan foydalanishimiz mumkin.

Buning ishlashi uchun biz properties faylini messageSource bean sifatida sozlashimiz kerak:

```java
@Bean
@Description("Spring Message Resolver")
public ResourceBundleMessageSource messageSource(){
    ResourceBundleMessageSource messageSource=new ResourceBundleMessageSource();
    messageSource.setBasename("messages");
    return messageSource;
}
```

Here is the Thymeleaf HTML code to display the value associated with the key welcome.message:

```html
<span th:text="#{welcome.message}"/>
```

```html
<!DOCTYPE html>

<html xmlns:th="http://www.thymeleaf.org">

<head>
    <title>Good Thymes Virtual Grocery</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <link rel="stylesheet" type="text/css" media="all"
          href="../../css/gtvg.css" th:href="@{/css/gtvg.css}"/>
</head>

<body>

<p th:text="#{home.welcome}">Welcome to our grocery store!</p>

</body>

</html>
```

The first thing you will notice is that this file is HTML5 that can be correctly displayed by any browser because it
does not include any non-HTML tags (browsers ignore all attributes they don’t understand, like th:text).

But you may also notice that this template is not really a valid HTML5 document, because these non-standard attributes
we are using in the th:* form are not allowed by the HTML5 specification. In fact, we are even adding an xmlns:th
attribute to our <html> tag, something absolutely non-HTML5-ish:

```html
<html xmlns:th="http://www.thymeleaf.org">
```

## Messages

As we already know, #{...} message expressions allow us to link this:

```html
<p th:utext="#{home.welcome}">Welcome to our grocery store!</p>
```

...to this:

```properties
home.welcome=¡Bienvenido a nuestra tienda de comestibles!
```

But there’s one aspect we still haven’t thought of: what happens if the message text is not completely static? What if,
for example, our application knew who is the user visiting the site at any moment and we wanted to greet them by name?

```html
<p>¡Bienvenido a nuestra tienda de comestibles, John Apricot!</p>
```

This means we would need to add a parameter to our message. Just like this:

```properties
home.welcome=¡Bienvenido a nuestra tienda de comestibles, {0}!
```

Parameters are specified according to the java.text.MessageFormat standard syntax, which means you can format to numbers
and dates as specified in the API docs for classes in the java.text.* package.

In order to specify a value for our parameter, and given an HTTP session attribute called user, we could have:

```html
<p th:utext="#{home.welcome(${session.user.name})}">
    Welcome to our grocery store, Sebastian Pepper!
</p>
```

Several parameters can be specified, separated by commas.
The message key itself can come from a variable:

```html
<p th:utext="#{${welcomeMsgKey}(${session.user.name})}">
    Welcome to our grocery store, Sebastian Pepper!
</p>
```

## Variables

We already mentioned that ${...} expressions are in fact OGNL (Object-Graph Navigation Language) expressions executed on
the map of variables contained in the context.

```
For detailed info about OGNL syntax and features, you should read the OGNL Language Guide

In Spring MVC-enabled applications OGNL will be replaced with SpringEL, but its syntax is very similar to that of OGNL (
actually, exactly the same for most common cases).
```

We can use the th:text=”${attributename}” tag attribute to display the value of model attributes.
Let's add a model attribute with the name serverTime in the controller class:

<hr/>

Model atributlari qiymatini ko‘rsatish uchun th:text=”${attributename}” teg atributidan foydalanishimiz mumkin.
Controller classiga serverTime nomli model atributini qo'shamiz:

```java
model.addAttribute("serverTime",dateFormat.format(new Date()));
```

And here's the HTML code to display the value of serverTime attribute:

```html
Current time is <span th:text="${serverTime}"/>
```

```html
/*
* Access to properties using the point (.). Equivalent to calling property getters.
*/
${person.father.name}

/*
* Access to properties can also be made by using brackets ([]) and writing
* the name of the property as a variable or between single quotes.
*/
${person['father']['name']}

/*
* If the object is a map, both dot and bracket syntax will be equivalent to
* executing a call on its get(...) method.
*/
${countriesByCode.ES}
${personsByName['Stephen Zucchini'].age}

/*
* Indexed access to arrays or collections is also performed with brackets,
* writing the index without quotes.
*/
${personsArray[0].name}

/*
* Methods can be called, even with arguments.
*/
${person.createCompleteName()}
${person.createCompleteNameWithSeparator('-')}
```

## Expression Basic Objects

When evaluating OGNL expressions on the context variables, some objects are made available to expressions for higher
flexibility. These objects will be referenced (per OGNL standard) starting with the # symbol:

- #ctx: the context object.
- #vars: the context variables
- #locale: the context locale

So we can do this:

```html
Established locale country: <span th:text="${#locale.country}">US</span>.
```

### Expression Utility Objects

Besides these basic objects, Thymeleaf will offer us a set of utility objects that will help us perform common tasks in
our expressions.

- #execInfo: information about the template being processed.
- #messages: methods for obtaining externalized messages inside variables expressions, in the same way as they would be
  obtained using #{…} syntax.
- #uris: methods for escaping parts of URLs/URIs
- #conversions: methods for executing the configured conversion service (if any).
- #dates: methods for java.util.Date objects: formatting, component extraction, etc.
- #calendars: analogous to #dates, but for java.util.Calendar objects.
- #temporals: for dealing with dates and times using the java.time API in JDK8+.
- #numbers: methods for formatting numeric objects.
- #strings: methods for String objects: contains, startsWith, prepending/appending, etc.
- #objects: methods for objects in general.
- #bools: methods for boolean evaluation.
- #arrays: methods for arrays.
- #lists: methods for lists.
- #sets: methods for sets.
- #maps: methods for maps.
- #aggregates: methods for creating aggregates on arrays or collections.
- #ids: methods for dealing with id attributes that might be repeated (for example, as a result of an iteration).

### Collection Attributes

If the model attribute is a collection of objects, we can use the th:each tag attribute to iterate over it.
Let's define a Student model class with two fields, id and name:

<hr/>

Agar model atributi ob'ektlar to'plami bo'lsa, biz uni takrorlash uchun th:each teg atributidan foydalanishimiz mumkin.

```java
public class Student implements Serializable {
    private Integer id;
    private String name;
    // standard getters and setters
}
```

Now we will add a list of students as model attribute in the controller class:

```java
List<Student> students=new ArrayList<Student>();
// logic to build student data
        model.addAttribute("students",students);
```

Finally, we can use Thymeleaf template code to iterate over the list of students and display all field values:

```html
<tbody>
<tr th:each="student: ${students}">
    <td th:text="${student.id}"/>
    <td th:text="${student.name}"/>
</tr>
</tbody>
```

## Standard Expression Syntax

We will take a small break in the development of our grocery virtual store to learn about one of the most important
parts of the Thymeleaf Standard Dialect: the Thymeleaf Standard Expression syntax.

We have already seen two types of valid attribute values expressed in this syntax: message and variable expressions:

```html
<p th:utext="#{home.welcome}">Welcome to our grocery store!</p>

<p>Today is: <span th:text="${today}">13 february 2011</span></p>
```

But there are more types of expressions, and more interesting details to learn about the ones we already know. First,
let’s see a quick summary of the Standard Expression features:

- Simple Expressions:
    - Variable Expressions: ${...}
    - Selection Variable Expressions: *{...}
    - Message Expressions: #{...}
    - Link URL Expressions: @{...}
    - Fragment Expressions: ~{...}

- Literals
    - Text literals: 'one text', 'Another one!',…
    - Number literals: 0, 34, 3.0, 12.3,…
    - Boolean literals: true, false
    - Null literal: null
    - Literal tokens: one, sometext, main,…
- Text operations
    - String concatenation: +
    - Literal substitutions: |The name is ${name}|
- Arithmetic operations:
    - Binary operators: +, -, *, /, %
    - Minus sign (unary operator): -
- Boolean operations:
    - Binary operators: and, or
    - Boolean negation (unary operator): !, not
- Comparisons and equality:
    - Comparators: >, <, >=, <= (gt, lt, ge, le)
    - Equality operators: ==, != (eq, ne)
- Conditional operators:
    - If-then: (if) ? (then)
    - If-then-else: (if) ? (then) : (else)
    - Default: (value) ?: (defaultvalue)
- Special tokens
    - No-Operation: _

```html
'User is of type ' + (${user.isAdmin()} ? 'Administrator' : (${user.type} ?: 'Unknown'))
```

## 4.3 Expressions on selections (asterisk syntax)

Not only can variable expressions be written as ${...}, but also as *{...}.

There is an important difference though: the asterisk syntax evaluates expressions on selected objects rather than on
the whole context. That is, as long as there is no selected object, the dollar and the asterisk syntaxes do exactly the
same.

And what is a selected object? The result of an expression using the th:object attribute. Let’s use one in our user
profile (userprofile.html) page:

```html
<div th:object="${session.user}">
    <p>Name: <span th:text="*{firstName}">Sebastian</span>.</p>
    <p>Surname: <span th:text="*{lastName}">Pepper</span>.</p>
    <p>Nationality: <span th:text="*{nationality}">Saturn</span>.</p>
</div>
```

Which is exactly equivalent to:

```html
<div>
    <p>Name: <span th:text="${session.user.firstName}">Sebastian</span>.</p>
    <p>Surname: <span th:text="${session.user.lastName}">Pepper</span>.</p>
    <p>Nationality: <span th:text="${session.user.nationality}">Saturn</span>.</p>
</div>
```