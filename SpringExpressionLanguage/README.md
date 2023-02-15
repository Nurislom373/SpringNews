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

Biz SpEL dan Java Reflectionga soddalashtirilgan muqobil sifatida foydalanamiz. Bu biz uchun ko’p kodni kamaytiradi.
Java reflectionning barcha foydalanish holatlari, shuningdek, qo'shimcha soddalik va qisqartirilgan kod bilan SpEL uchun
potentsial foydalanish holatlaridir.

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

## Referencing Bean

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

## Operators in Configuration

However, remember that in XML-based configuration, we can't use the angle bracket operator “<“. Instead, we should use
the alphabetic aliases, such as lt (less than) or le (less than or equals).

```java
public class SpelOperators {
    private boolean equal;
    private boolean notEqual;
    private boolean greaterThanOrEqual;
    private boolean and;
    private boolean or;
    private String addString;

    // Getters and setters
}
```

```xml

<bean id="spelOperators" class="com.baeldung.spring.spel.SpelOperators">
    <property name="equal" value="#{1 == 1}"/>
    <property name="notEqual" value="#{1 lt 1}"/>
    <property name="greaterThanOrEqual" value="#{someCar.engine.numberOfCylinders >= 6}"/>
    <property name="and" value="#{someCar.horsePower == 250 and someCar.engine.capacity lt 4000}"/>
    <property name="or" value="#{someCar.horsePower > 300 or someCar.engine.capacity > 3000}"/>
    <property name="addString" value="#{someCar.model + ' manufactured by ' + someCar.make}"/>
</bean>
```

```java
ApplicationContext context=new ClassPathXmlApplicationContext("applicationContext.xml");
        SpelOperators spelOperators=(SpelOperators)context.getBean("spelOperators");
```

# Parsing Expressions Programmatically

At times, we may want to parse expressions outside the context of configuration. Fortunately, this is possible using
SpelExpressionParser.

We can use all operators that we saw in previous examples but should use them without braces and hash symbol. That is,
if we want to use an expression with the + operator when used in Spring configuration, the syntax is #{1 + 1}; when used
outside of configuration, the syntax is simply 1 + 1.

In the following examples, we will use the Car and Engine beans defined in the previous section.

<hr/>

Ba'zida biz konfiguratsiya kontekstidan tashqaridagi iboralarni tahlil qilishni xohlashimiz mumkin. Yaxshiyamki, bu
SpelExpressionParser yordamida mumkin .

Biz oldingi misollarda ko'rgan barcha operatorlardan foydalanishimiz mumkin, lekin ularni qavslar va xesh belgisisiz
ishlatishimiz kerak. Ya'ni, Spring konfiguratsiyasida ishlatilganda + operatori bilan ifoda ishlatmoqchi bo'lsak ,
sintaksis #{1 + 1} ; konfiguratsiyadan tashqarida foydalanilganda, sintaksis oddiygina 1 + 1 bo'ladi .

## Using ExpressionParser

The SpEL classes and interfaces you are most likely to use are located in the packages org.springframework.expression
and its sub packages and spel.support.

The interface `ExpressionParser` is responsible for parsing an expression string. In this example the expression string
is
a string literal denoted by the surrounding single quotes. The interface `Expression` is responsible for evaluating the
previously defined expression string. There are two exceptions that can be thrown, `ParseException` and
`EvaluationException` when calling '`parser.parseExpression`' and '`exp.getValue`' respectively.

ExpressionParser is responsible for parsing expression strings. In this example, SpEL parser will simply evaluate the
string ‘Any String' as an expression. Unsurprisingly, the result will be ‘Any String'.

```java
ExpressionParser expressionParser=new SpelExpressionParser();
        Expression expression=expressionParser.parseExpression("'Any string'");
        String result=(String)expression.getValue();
```

As with using SpEL in configuration, we can use it to call methods, access properties or call constructors:

## Inline Lists

Lists can be expressed directly in an expression using {} notation.

```java
// evaluates to a Java list containing the four numbers
List numbers=(List)parser.parseExpression("{1,2,3,4}").getValue(context);

        List listOfLists=(List)parser.parseExpression("{{'a','b'},{'x','y'}}").getValue(context);
```

## Methods

Methods are invoked using typical Java programming syntax. You may also invoke methods on literals. Varargs are also
supported.

```java
// string literal, evaluates to "bc"
String c=parser.parseExpression("'abc'.substring(2, 3)").getValue(String.class);

// evaluates to true
        boolean isMember=parser.parseExpression("isMember('Mihajlo Pupin')").getValue(societyContext,Boolean.class);
```

## Constructor

Constructors can be invoked using the new operator. The fully qualified class name should be used for all but the
primitive type and String (where int, float, etc, can be used).

```java
Inventor einstein=
        p.parseExpression("new org.spring.samples.spel.inventor.Inventor('Albert Einstein',
        'German')")
        .getValue(Inventor.class);

//create new inventor instance within add method of List
        p.parseExpression("Members.add(new org.spring.samples.spel.inventor.Inventor('Albert Einstein',
        'German'))")
        .getValue(societyContext);
```

{} by itself means an empty list. For performance reasons, if the list is itself entirely composed of fixed literals
then a constant list is created to represent the expression, rather than building a new list on each evaluation.

## Array construction

Arrays can be built using the familiar Java syntax, optionally supplying an initializer to have the array populated at
construction time.

```java
int[]numbers1=(int[])parser.parseExpression("new int[4]").getValue(context);

// Array with initializer
        int[]numbers2=(int[])parser.parseExpression("new int[]{1,2,3}").getValue(context);

// Multi dimensional array
        int[][]numbers3=(int[][])parser.parseExpression("new int[4][5]").getValue(context);
```

## Relational operators

The relational operators; equal, not equal, less than, less than or equal, greater than, and greater than or equal are
supported using standard operator notation.

```java
// evaluates to true
boolean trueValue=parser.parseExpression("2 == 2").getValue(Boolean.class);

// evaluates to false
        boolean falseValue=parser.parseExpression("2 < -5.0").getValue(Boolean.class);

// evaluates to true
        boolean trueValue=parser.parseExpression("'black' < 'block'").getValue(Boolean.class);
```

Greater/less-than comparisons against `null` follow a simple rule: `null` is treated as nothing here (i.e. NOT as zero).
As a consequence, any other value is always greater than `null` (`X > null` is always `true`) and no other value is ever
less than nothing (`X < null` is always `false`). If you prefer numeric comparisons instead, please avoid
number-based `null` comparisons in favor of comparisons against zero (e.g. `X > 0` or `X < 0`).

In addition to standard relational operators SpEL supports the 'instanceof' and regular expression based 'matches'
operator.

```java
// evaluates to false
boolean falseValue=parser.parseExpression("'xyz' instanceof T(int)").getValue(Boolean.class);

// evaluates to true
        boolean trueValue=
        parser.parseExpression("'5.00' matches '^-?\\d+(\\.\\d{2})?$'").getValue(Boolean.class);

//evaluates to false
        boolean falseValue=
        parser.parseExpression("'5.0067' matches '^-?\\d+(\\.\\d{2})?$'").getValue(Boolean.class);
```

Each symbolic operator can also be specified as a purely alphabetic equivalent. This avoids problems where the symbols
used have special meaning for the document type in which the expression is embedded (eg. an XML document). The textual
equivalents are shown here: lt ('<'), gt ('>'), le ('<='), ge ('>='), eq ('=='), ne ('!='), div ('/'), mod ('%'),
not ('!'). These are case insensitive.

## Logical Operators

The logical operators that are supported are and, or, and not. Their use is demonstrated below.

```java
// -- AND --

// evaluates to false
boolean falseValue=parser.parseExpression("true and false").getValue(Boolean.class);

// evaluates to true
        String expression="isMember('Nikola Tesla') and isMember('Mihajlo Pupin')";
        boolean trueValue=parser.parseExpression(expression).getValue(societyContext,Boolean.class);

// -- OR --

// evaluates to true
        boolean trueValue=parser.parseExpression("true or false").getValue(Boolean.class);

// evaluates to true
        String expression="isMember('Nikola Tesla') or isMember('Albert Einstein')";
        boolean trueValue=parser.parseExpression(expression).getValue(societyContext,Boolean.class);

// -- NOT --

// evaluates to false
        boolean falseValue=parser.parseExpression("!true").getValue(Boolean.class);


// -- AND and NOT --
        String expression="isMember('Nikola Tesla') and !isMember('Mihajlo Pupin')";
        boolean falseValue=parser.parseExpression(expression).getValue(societyContext,Boolean.class);
```

## Mathematical operators

The addition operator can be used on numbers, strings and dates. Subtraction can be used on numbers and dates.
Multiplication and division can be used only on numbers. Other mathematical operators supported are modulus (%) and
exponential power (^). Standard operator precedence is enforced. These operators are demonstrated below.

```java
// Addition
int two=parser.parseExpression("1 + 1").getValue(Integer.class); // 2

        String testString=
        parser.parseExpression("'test' + ' ' + 'string'").getValue(String.class);  // 'test string'

// Subtraction
        int four=parser.parseExpression("1 - -3").getValue(Integer.class); // 4

        double d=parser.parseExpression("1000.00 - 1e4").getValue(Double.class); // -9000

// Multiplication
        int six=parser.parseExpression("-2 * -3").getValue(Integer.class); // 6

        double twentyFour=parser.parseExpression("2.0 * 3e0 * 4").getValue(Double.class); // 24.0

// Division
        int minusTwo=parser.parseExpression("6 / -3").getValue(Integer.class); // -2

        double one=parser.parseExpression("8.0 / 4e0 / 2").getValue(Double.class); // 1.0

// Modulus
        int three=parser.parseExpression("7 % 4").getValue(Integer.class); // 3

        int one=parser.parseExpression("8 / 5 % 2").getValue(Integer.class); // 1

// Operator precedence
        int minusTwentyOne=parser.parseExpression("1+2-3*8").getValue(Integer.class); // -21
```

## Assignment

Setting of a property is done by using the assignment operator. This would typically be done within a call to `setValue`
but can also be done inside a call to `getValue`.

```java
Inventor inventor = new Inventor();
StandardEvaluationContext inventorContext = new StandardEvaluationContext(inventor);

parser.parseExpression("Name").setValue(inventorContext, "Alexander Seovic2");

// alternatively

String aleks = parser.parseExpression("Name = 'Alexandar Seovic'").getValue(inventorContext,
                                                                            String.class);
```

## Variables

Variables can be referenced in the expression using the syntax #variableName. Variables are set using the method
setVariable on the StandardEvaluationContext.

```java
Inventor tesla=new Inventor("Nikola Tesla","Serbian");
        StandardEvaluationContext context=new StandardEvaluationContext(tesla);
        context.setVariable("newName","Mike Tesla");

        parser.parseExpression("Name = #newName").getValue(context);

        System.out.println(tesla.getName()) // "Mike Tesla"
```

It is not currently allowed to supply an initializer when constructing a multi-dimensional array.

### The #this and #root variables

The variable #this is always defined and refers to the current evaluation object (against which unqualified references
are resolved). The variable #root is always defined and refers to the root context object. Although #this may vary as
components of an expression are evaluated, #root always refers to the root.

```java
// create an array of integers
List<Integer> primes=new ArrayList<Integer>();
        primes.addAll(Arrays.asList(2,3,5,7,11,13,17));

// create parser and set variable 'primes' as the array of integers
        ExpressionParser parser=new SpelExpressionParser();
        StandardEvaluationContext context=new StandardEvaluationContext();
        context.setVariable("primes",primes);

// all prime numbers > 10 from the list (using selection ?{...})
// evaluates to [11, 13, 17]
        List<Integer> primesGreaterThanTen=
        (List<Integer>)parser.parseExpression("#primes.?[#this>10]").getValue(context);
```


