# Spring Expression Language (SpEL)

# Introduction

The Spring Expression Language (SpEL for short) is a powerful expression language that supports querying and
manipulating an object graph at runtime. The language syntax is similar to Unified EL but offers additional features,
most notably method invocation and basic string templating functionality.

While there are several other Java expression languages available, OGNL, MVEL, and JBoss EL, to name a few, the Spring
Expression Language was created to provide the Spring community with a single well supported expression language that
can be used across all the products in the Spring portfolio. Its language features are driven by the requirements of the
projects in the Spring portfolio, including tooling requirements for code completion support within the eclipse based
SpringSource Tool Suite. That said, SpEL is based on a technology agnostic API allowing other expression language
implementations to be integrated should the need arise.

While SpEL serves as the foundation for expression evaluation within the Spring portfolio, it is not directly tied to
Spring and can be used independently. In order to be self contained, many of the examples in this chapter use SpEL as if
it were an independent expression language. This requires creating a few bootstrapping infrastructure classes such as
the parser. Most Spring users will not need to deal with this infrastructure and will instead only author expression
strings for evaluation. An example of this typical use is the integration of SpEL into creating XML or annotated based
bean definitions as shown in the section Expression support for defining bean definitions.

This chapter covers the features of the expression language, its API, and its language syntax. In several places an
Inventor and Inventor's Society class are used as the target objects for expression evaluation. These class declarations
and the data used to populate them are listed at the end of the chapter.

<hr/>

Spring Expression Language (SpEL) - bu runtimeda object grafigini so’rash va manipulatsya qilishni qo’llab
quvvatlaydigan kuchli ifoda tili. Biz uni XML yoki Annotatsiyaga asoslangan Spring konfiguratsiyalari bilan
ishlatishimiz mumkin. Til sintaksisi Unified EL ga o'xshaydi, lekin qo'shimcha funksiyalarni taklif qiladi, xususan,
usulni chaqirish va asosiy string shablonlash funksiyasi.

OGNL, MVEL va JBoss EL kabi bir nechta boshqa Java Expression Languagelari mavjud bo'lsa-da, Spring Expression Language
spring hamjamiyatini barcha productlarida ishlatilishi mumkin bo'lgan yagona yaxshi qo'llab quvvatlanadigan expression
language bilan ta'minlash uchun yaratilgan.

# Features Overview

The expression language supports the following functionality

- Literal expressions
- Boolean and relational operators
- Regular expressions
- Class expressions
- Accessing properties, arrays, lists, maps
- Method invocation
- Relational operators
- Assignment
- Calling constructors
- Bean references
- Array construction
- Inline lists
- Ternary operator
- Variables
- User defined functions
- Collection projection
- Collection selection
- Templated expressions

# Operators

For these examples, we will use annotation-based configuration. Find more details about XML configuration in later
sections of this article.

SpEL expressions begin with the # symbol and are wrapped in braces: #{expression}.

Properties can be referenced in a similar fashion, starting with a $ symbol and wrapped in braces: ${property.name}.

<hr/>

// SpEL expressionlari # belgisi bilan boshlanadi va qavslar ichiga o'raladi: #{expression} .

// Propertieslarga xuddi shunday tarzda murojaat qilish mumkin, $ belgisidan boshlab va qavslar ichiga o'ralgan:
${property.name}.

```spel
#{${someProperty} + 2}
```

## Arithmetic Operators

SpEL supports all basic arithmetic operators:

```java
@Value("#{19 + 1}") // 20
private double add;

@Value("#{'String1 ' + 'string2'}") // "String1 string2"
private String addString;

@Value("#{20 - 1}") // 19
private double subtract;

@Value("#{10 * 2}") // 20
private double multiply;

@Value("#{36 / 2}") // 19
private double divide;

@Value("#{36 div 2}") // 18, the same as for / operator
private double divideAlphabetic;

@Value("#{37 % 10}") // 7
private double modulo;

@Value("#{37 mod 10}") // 7, the same as for % operator
private double moduloAlphabetic;

@Value("#{2 ^ 9}") // 512
private double powerOf;

@Value("#{(2 + 2) * 2 + 9}") // 17
private double brackets;
```

## Relational Operators

SpEL also supports all basic relational and logical operations:

```java
@Value("#{1 == 1}") // true
private boolean equal;

@Value("#{1 eq 1}") // true
private boolean equalAlphabetic;

@Value("#{1 != 1}") // false
private boolean notEqual;

@Value("#{1 ne 1}") // false
private boolean notEqualAlphabetic;

@Value("#{1 < 1}") // false
private boolean lessThan;

@Value("#{1 lt 1}") // false
private boolean lessThanAlphabetic;

@Value("#{1 <= 1}") // true
private boolean lessThanOrEqual;

@Value("#{1 le 1}") // true
private boolean lessThanOrEqualAlphabetic;

@Value("#{1 > 1}") // false
private boolean greaterThan;

@Value("#{1 gt 1}") // false
private boolean greaterThanAlphabetic;

@Value("#{1 >= 1}") // true
private boolean greaterThanOrEqual;

@Value("#{1 ge 1}") // true
private boolean greaterThanOrEqualAlphabetic;
```

All relational operators have alphabetic aliases as well. For example, in XML-based configs we can't use operators
containing angle brackets (<, <=, >, >=). Instead, we can use lt (less than), le (less than or equal), gt (greater than)
or ge (greater than or equal).

<hr/>

Barcha relyatsion operatorlarning alifbo taxalluslari ham mavjud. Masalan, XML-ga asoslangan konfiguratsiyalarda burchak
qavslari ( _< , <= , > , >=_ ) bo'lgan operatorlardan foydalana olmaymiz. Buning o'rniga _lt_ (kichikroq), _le_ (kichik
yoki teng), _gt_ (kattaroq) yoki _ge_ (katta yoki teng) dan foydalanishimiz mumkin.

## Logical Operators

SpEL also supports all basic logical operations:

```java
@Value("#{250 > 200 && 200 < 4000}") // true
private boolean and;

@Value("#{250 > 200 and 200 < 4000}") // true
private boolean andAlphabetic;

@Value("#{400 > 300 || 150 < 100}") // true
private boolean or;

@Value("#{400 > 300 or 150 < 100}") // true
private boolean orAlphabetic;

@Value("#{!true}") // false
private boolean not;

@Value("#{not true}") // false
private boolean notAlphabetic;
```

As with arithmetic and relational operators, all logical operators also have alphabetic clones.

<hr/>

Arifmetik va relyatsion operatorlarda bo'lgani kabi, barcha mantiqiy operatorlarda ham alifbo klonlari mavjud.

## Conditional Operators

We use conditional operators for injecting different values depending on some condition:

// Biz ba'zi shartlarga qarab turli qiymatlarni kiritish uchun shartli operatorlardan foydalanamiz:

```java
@Value("#{2 > 1 ? 'a' : 'b'}") // "a"
private String ternary;
```

We use the ternary operator for performing compact if-then-else conditional logic inside the expression.

// Biz expressionni ichida ixcham if-then-else shartli mantiqni bajarish uchun ternary operatoridan foydalanamiz.

<br/>

Another common use for the ternary operator is to check if some variable is null and then return the variable value or a
default:

// Ternary operator uchun yana bir keng tarqalgan foydalanish ba'zi o'zgaruvchilarning null yoki yo'qligini tekshirish
va
keyin o'zgaruvchi qiymatini yoki defaula qiymatni qaytarishdir

```java
@Value("#{someBean.someProperty != null ? someBean.someProperty : 'default'}")
private String ternary;
```

The Elvis operator is a way of shortening of the ternary operator syntax for the case above used in the Groovy language.
It is also available in SpEL.

// Elvis operatori Groovy tilida ishlatiladigan yuqoridagi holat uchun ternary operator sintaksisini qisqartirish
usulidir. U SpEL-da ham mavjud.

```java
@Value("#{someBean.someProperty ?: 'default'}") // Will inject provided string if someProperty is null
private String elvis;
```

## Using Regex in SpEL

We can use the matches operator to check whether or not a string matches a given regular expression:

// berilgan String oddiy ifoda(expression)ga mos kelishi yoki mos kelmasligini tekshirish uchun matches operatoridan
foydalanishimiz mumkin:

```java
@Value("#{'100' matches '\\d+' }") // true
private boolean validNumericStringResult;

@Value("#{'100fghdjf' matches '\\d+' }") // false
private boolean invalidNumericStringResult;

@Value("#{'valid alphabetic string' matches '[a-zA-Z\\s]+' }") // true
private boolean validAlphabeticStringResult;

@Value("#{'invalid alphabetic string #$1' matches '[a-zA-Z\\s]+' }") // false
private boolean invalidAlphabeticStringResult;

@Value("#{someBean.someValue matches '\d+'}") // true if someValue contains only digits
private boolean validNumericValue;
```

## Accessing _List_ and _Map_ Objects

Now we can access the values of the collections using SpEL:

// Endi biz SpEL yordamida collectionlarning qiymatlariga kirishimiz mumkin:

```java
@Value("#{workersHolder.salaryByWorkers['John']}") // 35000
private Integer johnSalary;

@Value("#{workersHolder.salaryByWorkers['George']}") // 14000
private Integer georgeSalary;

@Value("#{workersHolder.salaryByWorkers['Susie']}") // 47000
private Integer susieSalary;

@Value("#{workersHolder.workers[0]}") // John
private String firstWorker;

@Value("#{workersHolder.workers[3]}") // George
private String lastWorker;

@Value("#{workersHolder.workers.size()}") // 4
private Integer numberOfWorkers;
```

# Spring XML Configuration

In this example, we'll look at how to use SpEL in XML-based configuration. We can use expressions to reference beans or
bean fields/methods.

# Referencing Bean

```java
public class Engine {
    private int capacity;
    private int horsePower;
    private int numberOfCylinders;

    // Getters and setters
}

public class Car {
    private String make;
    private int model;
    private Engine engine;
    private int horsePower;

    // Getters and setters
}
```

Now we create an application context in which expressions are used to inject values

```xml

<bean id="engine" class="com.baeldung.spring.spel.Engine">
    <property name="capacity" value="3200"/>
    <property name="horsePower" value="250"/>
    <property name="numberOfCylinders" value="6"/>
</bean>
<bean id="someCar" class="com.baeldung.spring.spel.Car">
<property name="make" value="Some make"/>
<property name="model" value="Some model"/>
<property name="engine" value="#{engine}"/>
<property name="horsePower" value="#{engine.horsePower}"/>
</bean>
```

Take a look at the someCar bean. The engine and horsePower fields of someCar use expressions that are bean references to
the engine bean and horsePower field respectively.

// SomeCar beaniga qarang. SomeCar engine va horsePower fieldlariga mos ravishda engine beani va horsePower fieldiga
ishora qiluvchi iboralardan foydalanadi .

Annotatsiyaga asoslangan konfiguratsiyalar bilan ham xuddi shunday qilish uchun @Value("#{expression}") annotatsiyasidan
foydalaning.