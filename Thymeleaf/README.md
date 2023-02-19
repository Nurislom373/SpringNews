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