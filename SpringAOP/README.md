# Spring AOP

Aspect Oriented Programming (AOP) is a programming paradigm aiming to extract cross-cutting functionalities, such as
logging, into what’s known as “Aspects”.

This is achieved by adding behavior (“Advice”) to existing code without changing the code itself. We specify which code
we want to add the behavior to using special expressions (“Pointcuts”).

For example, we can tell the AOP framework to log all method calls happening in the system without us having to add the
log statement in every method call manually.

AOP is a programming paradigm that aims to increase modularity by allowing the separation of cross-cutting concerns. It
does this by adding additional behavior to existing code without modifying the code itself.

<hr/>

AOP - bu o’zaro bog’liq muammolarni ajratish imkoni berish orqali modullikni oshirishga qaratilgan dasturlash
paradigmasi. U buni kodning o’zini o’zgartirmasdan mavjud kodga qo’shimcha xatti - harakatlar qo’shish orqali amalga
oshiradi. OOPda modul asosiy birligi Classdir, AOP da esa asosiy modul birligi Aspectdir.

# AOP Concepts

- Aspect - A modularization of a concern that cuts across multiple classes. Transaction management is a good example of
  a crosscutting concern in enterprise Java applications. In Spring AOP, aspects are implemented by using regular
  classes (the schema-based approach) or regular classes annotated with the `@Aspect` annotation (the @AspectJ style).
- Join point: A point during the execution of a program, such as the execution of a method or the handling of an
  exception. In Spring AOP, a join point always represents a method execution.
- Advice: Action taken by an aspect at a particular join point. Different types of advice include “around”, “before” and
  “after” advice. (Advice types are discussed later.) Many AOP frameworks, including Spring, model an advice as an
  interceptor and maintain a chain of interceptors around the join point.
- Pointcut: A predicate that matches join points. Advice is associated with a pointcut expression and runs at any join
  point matched by the pointcut (for example, the execution of a method with a certain name). The concept of join points
  as matched by pointcut expressions is central to AOP, and Spring uses the AspectJ pointcut expression language by
  default.
- Introduction: Declaring additional methods or fields on behalf of a type. Spring AOP lets you introduce new
  interfaces (and a corresponding implementation) to any advised object. For example, you could use an introduction to
  make a bean implement an `IsModified` interface, to simplify caching. (An introduction is known as an inter-type
  declaration in the AspectJ community.)
- Target object: An object being advised by one or more aspects. Also referred to as the “advised object”. Since Spring
  AOP is implemented by using runtime proxies, this object is always a proxied object.



