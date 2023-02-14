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