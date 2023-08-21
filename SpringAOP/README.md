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

---

- Aspect - bu bir nechta classlarni kesib tashlaydigan concerning modulizaysiyasi. 
- Join Point - bu dasturni bajarilish paytidagi nuqta, masalan, methodning bajarilishi paytida yoki exceptionni 
  handle qilish nuqtasi. Join point doim methodni bajarilishi ifodalaydi.
- Advice - bu qo'shilish nuqtasida(Join point) aspect tomonidan bajariladigan harakat. Har xil turdagi advicelar mavjud
  `Around`, `Before` va `After`.

## Spring AOP Capabilities and Goals

Spring AOP is implemented in pure Java. There is no need for a special compilation process. Spring AOP does not need to
control the class loader hierarchy and is thus suitable for use in a servlet container or application server.

Spring AOP currently supports only method execution join points (advising the execution of methods on Spring beans).
Field interception is not implemented, although support for field interception could be added without breaking the core
Spring AOP APIs. If you need to advise field access and update join points, consider a language such as AspectJ.

Spring AOP’s approach to AOP differs from that of most other AOP frameworks. The aim is not to provide the most complete
AOP implementation (although Spring AOP is quite capable). Rather, the aim is to provide a close integration between AOP
implementation and Spring IoC, to help solve common problems in enterprise applications.

Thus, for example, the Spring Framework’s AOP functionality is normally used in conjunction with the Spring IoC
container. Aspects are configured by using normal bean definition syntax (although this allows powerful “auto-proxying”
capabilities). This is a crucial difference from other AOP implementations. You cannot do some things easily or
efficiently with Spring AOP, such as advise very fine-grained objects (typically, domain objects). AspectJ is the best
choice in such cases. However, our experience is that Spring AOP provides an excellent solution to most problems in
enterprise Java applications that are amenable to AOP.

Spring AOP never strives to compete with AspectJ to provide a comprehensive AOP solution. We believe that both
proxy-based frameworks such as Spring AOP and full-blown frameworks such as AspectJ are valuable and that they are
complementary, rather than in competition. Spring seamlessly integrates Spring AOP and IoC with AspectJ, to enable all
uses of AOP within a consistent Spring-based application architecture. This integration does not affect the Spring AOP
API or the AOP Alliance API. Spring AOP remains backward-compatible. See the following chapter for a discussion of the
Spring AOP APIs.

## Enabling @AspectJ Support with Java Configuration

To enable @AspectJ support with Java @Configuration, add the @EnableAspectJAutoProxy annotation, as the following
example shows:

<hr/>

Java-da dasturimiz @AspectJ qo'llab quvvatlashi uchun @Configuration va @EnableAspectJAutoProxy annotatsiyalarni
qo'yishimiz kerak.

```java
@Configuration
@EnableAspectJAutoProxy
public class AppConfig {

}
```

## Enabling @AspectJ Support with XML Configuration

To enable @AspectJ support with XML-based configuration, use the aop:aspectj-autoproxy element, as the following example
shows:

<hr/>

XML ga asoslangan configuratsiya orqali Aspect yoqishimiz mumkin.

```xml
<aop:aspectj-autoproxy/>
```

## Declaring an Aspect

With @AspectJ support enabled, any bean defined in your application context with a class that is an @AspectJ aspect (has
the @Aspect annotation) is automatically detected by Spring and used to configure Spring AOP.

<hr/>

@AspectJ yoqilgan bo'lsa @Aspect annotatsiyasi bilan belgilangan har qanday class Spring tomonidan avtomatik ravishda
aniqlanadi va Spring AOP ni sozlash uchun ishlatiladi.

```xml
<bean id="myAspect" class="org.xyz.NotVeryUsefulAspect">
    <!-- configure properties of the aspect here -->
</bean>
```

```java
@Aspect
public class NotVeryUsefulAspect {

}
```

Aspects (classes annotated with @Aspect) can have methods and fields, the same as any other class. They can also contain
pointcut, advice, and introduction (inter-type) declarations.

<hr/>

Aspect classlar ham boshqa classlarga o'xshab method va fieldlari bo'lishi mumkin. Ushbu classlar shuningdek, pointcut,
advice va introduction o'z ichiga olishi mumkin.

### Autodetecting aspects through component scanning

You can register aspect classes as regular beans in your Spring XML configuration, via @Bean methods in @Configuration
classes, or have Spring autodetect them through classpath scanning — the same as any other Spring-managed bean. However,
note that the @Aspect annotation is not sufficient for autodetection in the classpath. For that purpose, you need to add
a separate @Component annotation (or, alternatively, a custom stereotype annotation that qualifies, as per the rules of
Spring’s component scanner).

<hr/>

Biz Aspect classlarni XML asoslangan konfiguratsiya bilan bean sifatida belgilasak yoki @Configuration classlarida @Bean
methodlar orqali registeratsiya qilishimiz mumkin. Yoki Spring Component qilib belgilasak, Spring ularni classpath
skanerlash orqali avtomatik aniqlashi mumkin. Spring tomonidan boshqariladigan Bean ga o'xshab. Lekin, @Aspect
annotatsiyasini o'zi avtomatik aniqlash uchun yetarli emas. Buning uchun siz alohida @Component annotatsiyani
qo'yishingiz kerak (yoki alternative annotatsiyalarni qo'yishingiz mumkin).

## Declaring a Pointcut

Pointcuts determine join points of interest and thus enable us to control when advice runs. Spring AOP only supports
method execution join points for Spring beans, so you can think of a pointcut as matching the execution of methods on
Spring beans. A pointcut declaration has two parts: a signature comprising a name and any parameters and a pointcut
expression that determines exactly which method executions we are interested in. In the @AspectJ annotation-style of
AOP, a pointcut signature is provided by a regular method definition, and the pointcut expression is indicated by using
the @Pointcut annotation (the method serving as the pointcut signature must have a void return type).

<hr/>

Pointcuts birlashish nuqtasni aniqlaydi yani mos keladigan method va shu tariqa advice qachon ishlayotganini nazorat
qilishimizga imkon beradi. Spring AOP faqat Spring Beanlari uchun methodlarni join pointlarini qo'llab quvvatlaydi.
Pointcut deklariyatsiyasi 2ta qismdan iborat: name va parameters, aynan qaysi method aniqlash uchun ishlatiladi.

# Pointcut Designators

A pointcut expression starts with a pointcut designator (PCD), which is a keyword telling Spring AOP what to match.
There are several pointcut designators, such as the execution of a method, type, method arguments, or annotations.

<hr/>

Pointcut expression **pointcut designator (PCD)** bilan boshlanadi, Bu Spring AOP ga nima mos kelishini aytadigan
keyword. Execution Method, Type, Method argumentlari yoki annotatsiyalar kabi bir nechta pointcut lar mavjud.

### 1. `Execution`

For matching method execution join points. This is the primary pointcut designator to use when working with Spring
AOP.<br/> // Pointcut match bo'lgan method execute ya'ni bajarilishi uchun birlashtiradi. Ushbu PCD Spring AOP uchun
asosiy belgisidir.

```java
@Pointcut("execution(public String org.khasanof.pointcutadvice.dao.FooDao.findById(Long))")
```

This example pointcut will exactly match the execution of the findById method of the FooDao class. This works, but it's
not very flexible. Suppose we'd like to match all the methods of the FooDao class, which may have different signatures,
return types, and arguments. To achieve this, we can use wildcards: <br/>
// Ushbu tepadagi misol _FooDao_ classning _findById_ methodining bajarilishiga to'liq mos keladi. Lekin u juda
moslashuvchan emas. Biz _FooDao_ classning barcha methodlariga mos kelishini hohlaymiz. Ular turli signature, return
type va argumentlarga ega bo'lishi mumkin. Bunga erishish uchun wilcard belgilaridan foydalanamiz.

```java
@Pointcut("execution(* org.khasanof.pointcutadvice.dao.FooDao.*(..))")
```

Here, the first wildcard matches any return value, the second matches any method name, and the (..) pattern matches any
number of parameters (zero or more).

### 2. `Within`

Limits matching to join points within certain types (the execution of a method declared within a matching type when
using Spring AOP).<br/> // Within PCD foydalanib package yoki class belgilasak ushbu class ichidagi methodlardan biri
bajarilganda ishlaydi. Packageda ham shunday.

```java
@Pointcut("within(org.khasanof.pointcutadvice.dao.FooDao)")
```

We can also match any type within the org.khasanof package or a sub-package:

```java
@Pointcut("within(org.khasanof..*)")
```

### 3. `This`

Limits matching to join points (the execution of methods when using Spring AOP) where the bean reference (Spring AOP
proxy) is an instance of the given type. <br/>
// This PCD ushbu berilgan Bean ning instancesi chegaralash uchun yani ushbu Bean methodlaridan biri bajarilganda
birlashadi.

```java
@Pointcut("this(org.khasanof.pointcutadvice.dao.FooDao)")
```

### 4. `Target`

Limits matching to join points (the execution of methods when using Spring AOP) where the target object (application
object being proxied) is an instance of the given type. <br/>
// target ham this PCD o'xshash faqat target objectlarni birlashtirish uchun ishlatiladi. Ya'ni berilgan object
methodlaridan biri bajarilganda.

```java
@Pointcut("target(org.khasanof.pointcutadvice.dao.BarDao)")
```

### 5. `Args`

Limits matching to join points (the execution of methods when using Spring AOP) where the arguments are instances of the
given types. <br/>
// Args PCD method argumentlarini moslashtirish uchun foydalanishimiz mumkin.

```java
@Pointcut("execution(* *..find*(Long))")
```

This pointcut matches any method that starts with find and has only one parameter of type Long. If we want to match a
method with any number of parameters, but still having the fist parameter of type Long. <br/>
// Ushbu pointcut kesishmasi find bilan boshlangan va Long typedagi bitta parameterga ega bo'lgan har qanday methodga
mos keladi. Agar method istalgancha parameterga moslashtirmoqchi bo'lsak, Lekin birinchi parameter Long typeda ega
bo'lsa

```java
@Pointcut("execution(* *..find*(Long,..))")
```

### 6. `@Target`

Limits matching to join points (the execution of methods when using Spring AOP) where the class of the executing object
has an annotation of the given type. <br/>
// @Target PCD (yuqoridagi Target PCD bilan adashtirmaslik kerak). @Target PCD bajaruvchi object classi berilgan
typedagi
annotatsiyaga ega bo'lgan classlarni methodlaridan bir bajarilgandan moslashtiradi.

```java
@Pointcut("@target(org.springframework.stereotype.Repository)")
```

### 7. `@Args`

Limits matching to join points (the execution of methods when using Spring AOP) where the runtime type of the actual
arguments passed have annotations of the given types. Suppose that we want to trace all the methods accepting beans
annotated with the @Entity annotation: <br/>
// Ushbu PCD actual parameterlarning runtime berilgan typelarning annotatsiyalariga ega bo'lganlarni birlashtirish uchun
moslashtiriadi. Aytaylik, biz @Entity annotatsiyasi bilan izohlangan beanni barcha methodlarini kuzatmoqchimiz:

```java
@Pointcut("@args(org.khasanof.pointcutadvice.annotations.Entity)")
public void methodsAcceptingEntities(){}
```

```java
@Before("methodsAcceptingEntities()")
public void logMethodAcceptionEntityAnnotatedBean(JoinPoint jp){
        logger.info("Accepting beans with @Entity annotation: "+jp.getArgs()[0]);
        }
```

### 8. `@within`

Limits matching to join points within types that have the given annotation (the execution of methods declared in types
with the given annotation when using Spring AOP). <br/>
// Ushbu PCD berilgan annotatsiyaga ega bo'lgan typelarni birlashtirish uchun moslikni cheklaydi:

```java
@Pointcut("@within(org.springframework.stereotype.Repository)")
```

### 9. `@annotation`

Limits matching to join points where the subject of the join point (the method being run in Spring AOP) has the given
annotation. <br/>
// Ushbu PCD berilgan annotatsiya qoyilgan method ishga tushirilayotganda join points yani birlashma nuqtalari uchun
moslikni cheklaydi. Masalan, @Loggable annotatsiyasini yaratishimiz mumkin:

```java
@Pointcut("@annotation(org.khasanof.pointcutadvice.annotations.Loggable)")
public void loggableMethods(){}
```

# Other pointcut types

The full AspectJ pointcut language supports additional pointcut designators that are not supported in Spring: call, get,
set, preinitialization, staticinitialization, initialization, handler, adviceexecution, withincode, cflow, cflowbelow,
if, @this, and @withincode. Use of these pointcut designators in pointcut expressions interpreted by Spring AOP results
in an IllegalArgumentException being thrown.

The set of pointcut designators supported by Spring AOP may be extended in future releases to support more of the
AspectJ pointcut designators.

Because Spring AOP limits matching to only method execution join points, the preceding discussion of the pointcut
designators gives a narrower definition than you can find in the AspectJ programming guide. In addition, AspectJ itself
has type-based semantics and, at an execution join point, both this and target refer to the same object: the object
executing the method. Spring AOP is a proxy-based system and differentiates between the proxy object itself (which is
bound to this) and the target object behind the proxy (which is bound to target).

# Common Pointcut Definitions

```java
execution(modifiers-pattern?ret-type-pattern declaring-type-pattern?name-pattern(param-pattern)
        throws-pattern?)
```

All parts except the returning type pattern (`ret-type-pattern` in the preceding snippet), the name pattern, and the
parameters pattern are optional. The returning type pattern determines what the return type of the method must be in
order for a join point to be matched. `*` is most frequently used as the returning type pattern. It matches any return
type. A fully-qualified type name matches only when the method returns the given type. The name pattern matches the
method name. You can use the `*` wildcard as all or part of a name pattern. If you specify a declaring type pattern,
include a trailing `.` to join it to the name pattern component. The parameters pattern is slightly more complex: `()`
matches a method that takes no parameters, whereas `(..)` matches any number (zero or more) of parameters. The `(*)`
pattern matches a method that takes one parameter of any type. `(*,String)` matches a method that takes two parameters.
The first can be of any type, while the second must be a `String`. Consult the Language Semantics section of the AspectJ
Programming Guide for more information.

# Declaring Advice

Advice is associated with a pointcut expression and runs before, after, or around method executions matched by the
pointcut. The pointcut expression may be either a simple reference to a named pointcut or a pointcut expression declared
in place.

<hr/>

Advice - ma'lum bir qo'shilish nuqtasida aspect tomonidan amalga oshiriladigan harakat. Har xil turdagi advicelar bor
ular "before", "after" va "around". Aspektlarning asosiy maqsadi ro'yxatga olish, profillash, keshlash va
tranzaktsiyalarni boshqarish kabi o'zaro bog'liq muammolarni qo'llab-quvvatlashdir.

Advice pointcut bilan bog'lanadi va pointcut bilan mos keladigan methodlarni bajarishdan oldin(before), keyin(after)
yoki atrofida(around) ishlaydi.

## 1. Before Advice

**This advice, as the name implies, is executed before the join point**. It doesn't prevent the continued execution of
the method it advises unless an exception is thrown.

<hr/>

Ushbu Advice, nomidan ko'rinib turibdiki, qo'shilish nuqtasidan oldin bajariladi. Agar exception bo'lmasa, u tavsiya
qilingan method davom etiradi.

```java
@Component
@Aspect
public class LoggingAspect {

    private Logger logger = Logger.getLogger(LoggingAspect.class.getName());

    @Pointcut("@target(org.springframework.stereotype.Repository)")
    public void repositoryMethods() {
    }

    @Before("repositoryMethods()")
    public void logMethodCall(JoinPoint jp) {
        String methodName = jp.getSignature().getName();
        logger.info("Before " + methodName);
    }
}
```

The logMethodCall advice will be executed before any repository method defined by the repositoryMethods pointcut.
// LogMethodCall advice repositoryMethods nuqtasi tomonidan belgilangan har qanday repository methodidan oldin
bajariladi.

## 2. After Advice

After advice, declared by using the @After annotation, is executed after a matched method's execution, whether or not an
exception was thrown. <br/>
In some ways, it is similar to a finally block. In case you need advice to be triggered only after normal execution, you
should use the returning advice declared by @AfterReturning annotation. If you want your advice to be triggered only
when the target method throws an exception, you should use throwing advice, declared by using the @AfterThrowing
annotation.

<hr/>

After advice before advice aks method bajarilgan so'ng bajariladi. After Advice method exception tashlasa yoki
yo'qligidan qat'i nazar, mos keladigan method bajarilgan so'ng bajariladi. <br/>
Qaysidir ma'noda, u _finally_ blockga o'xshaydi. Agar sizga method oddiy bajarilgan keyin advice kerak bo'lsa,
@AfterReturning annotatsiyasidan foydalaning. Agar advice faqat method exception tashlaganda ishga tushishini
istasangiz, @AfterThrowing annotatsiyasidan foydalaning.

```java
@Aspect
public class AfterFinallyExample {

    @After("com.xyz.myapp.CommonPointcuts.dataAccessOperation()")
    public void doReleaseLock() {
        // ...
    }
}
```

```java
@Component
@Aspect
public class PublishingAspect {

    private ApplicationEventPublisher eventPublisher;

    @Autowired
    public void setEventPublisher(ApplicationEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    @Pointcut("@target(org.springframework.stereotype.Repository)")
    public void repositoryMethods() {
    }

    @Pointcut("execution(* *..create*(Long,..))")
    public void firstLongParamMethods() {
    }

    @Pointcut("repositoryMethods() && firstLongParamMethods()")
    public void entityCreationMethods() {
    }

    @AfterReturning(value = "entityCreationMethods()", returning = "entity")
    public void logMethodCall(JoinPoint jp, Object entity) throws Throwable {
        eventPublisher.publishEvent(new FooCreationEvent(entity));
    }
}
```

Notice, first, that by using the @AfterReturning annotation we can access the target method's return value. Second, by
declaring a parameter of type JoinPoint, we can access the arguments of the target method's invocation.

<hr/>

E'tibor bering, birinchi navbatda, @AfterReturning annotatsiyasidan foydalanib, biz target methodning qaytish qiymatiga
kira olamiz. Ikkinchidan, JoinPoint tipidagi parametrni e'lon qilish orqali biz target methodni chaqirish argumentlariga
kirishimiz mumkin.

## 3. Around Advice

The last kind of advice is around advice. Around advice runs "around" a matched method’s execution. It has the
opportunity to do work both before and after the method runs and to determine when, how, and even if the method actually
gets to run at all. Around advice is often used if you need to share state before and after a method execution in a
thread-safe manner – for example, starting and stopping a timer.

<hr/>

Advicelarni oxirgisi around advice. Around advice mos keladigan methodning atrofida ishlaydi. U methodning ishga
tushganga qadar ham, keyin ham ishni bajarish va qachon, qanday va hatto method umuman ishga tushishini aniqlash
imkoniyatiga ega. Around advice ko'pincha agar siz methodning bajarilishidan oldingi va keyingi holatni thread-safe
tarzda baham ko'rishingiz kerak bo'lsa ishlatiladi - masalan, timerni ishga tushirish va to'xtatish.

```java
@Aspect
@Component
public class PerformanceAspect {

    private Logger logger = Logger.getLogger(getClass().getName());

    @Pointcut("within(@org.springframework.stereotype.Repository *)")
    public void repositoryClassMethods() {
    }

    ;

    @Around("repositoryClassMethods()")
    public Object measureMethodExecutionTime(ProceedingJoinPoint pjp) throws Throwable {
        long start = System.nanoTime();
        Object retval = pjp.proceed();
        long end = System.nanoTime();
        String methodName = pjp.getSignature().getName();
        logger.info("Execution of " + methodName + " took " +
                TimeUnit.NANOSECONDS.toMillis(end - start) + " ms");
        return retval;
    }
}
```

Around advice is declared by annotating a method with the @Around annotation. The method should declare Object as its
return type, and the first parameter of the method must be of type ProceedingJoinPoint. Within the body of the advice
method, you must invoke proceed() on the ProceedingJoinPoint in order for the underlying method to run. Invoking
proceed() without arguments will result in the caller’s original arguments being supplied to the underlying method when
it is invoked. For advanced use cases, there is an overloaded variant of the proceed() method which accepts an array of
arguments (Object[]). The values in the array will be used as the arguments to the underlying method when it is invoked.

### The JoinPoint interface provides a number of useful methods

- `getArgs()`: Returns the method arguments.
- `getThis()`: Returns the proxy object.
- `getTarget()`: Returns the target object.
- `getSignature()`: Returns a description of the method that is being advised.
- `toString()`: Prints a useful description of the method being advised.









