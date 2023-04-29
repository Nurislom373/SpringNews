# Junit 5

## What is JUnit 5 ?

Unlike previous versions of JUnit, JUnit 5 is composed of several different modules from three different sub-projects.

JUnit 5 = JUnit Platform + JUnit Jupiter + JUnit Vintage

**JUnit Platform** serves as a foundation for launching testing frameworks on the JVM. It also defines the TestEngine
API for developing a testing framework that runs on the platform. Furthermore, the platform provides a Console Launcher
to launch the platform from the command line and the JUnit Platform Suite Engine for running a custom test suite using
one or more test engines on the platform. First-class support for the JUnit Platform also exists in popular IDEs (see
IntelliJ IDEA, Eclipse, NetBeans, and Visual Studio Code) and build tools (see Gradle, Maven, and Ant).

**JUnit Jupiter** is the combination of the programming model and extension model for writing tests and extensions in
JUnit
5 . The Jupiter sub-project provides a TestEngine for running Jupiter based tests on the platform.

**JUnit Vintage** provides a TestEngine for running JUnit 3 and JUnit 4 based tests on the platform. It requires JUnit
4.12
or later to be present on the class path or module path.

---

JUnit Javada test yozish uchun ommabop va open-source framework. JUnit 5 uni so'nggi versiyasi. JUnit 5 2017 yil sentabr
oyida chiqarilgan va o'zidan oldingi JUnit 4 ga nisbatan bir qancha yangi xususiyatlar va yaxshilanishlarni taqdim
etadi.
JUnit platformasi JVM da test tizimlarni ishga tushirish uchun asos bo'lib xizmat qiladi.

## Supported Java Versions

JUnit 5 requires Java 8 (or higher) at runtime. However, you can still test code that has been compiled with previous
versions of the JDK.

## Dependencies

1. JUnit Platform
    * **Group ID**: `org.junit.platform`
    * **Version**: `1.9.3`
2. JUnit Jupiter
    * **Group ID**: `org.junit.jupiter`
    * **Version**: `5.9.3`
3. JUnit Vintage
    * **Group ID**: `org.junit.vintage`
    * **Version**: `5.9.3`

# 2. Writing Tests

The following example provides a glimpse at the minimum requirements for writing a test in JUnit Jupiter.

```java
@Test
void simpleTest() {
    CalculateService service = new CalculateService();
    long calculate=service.calculate(5, 5, '+');
    Assertions.assertEquals(10, calculate);
}
```

## 2.1 Annotations

JUnit Jupiter supports the following annotations for configuring tests and extending the framework.

Unless otherwise stated, all core annotations are located in the `org.junit.jupiter.api` package in the
`junit-jupiter-api` module.

---

JUnit Jupiter testlarni sozlash uchun quyidagi annotation-larni qo'llab-quvvatlaydi.

<table>
   <tr>
      <th>Annotation</th>
      <th>Description</th>
   </tr>
   <tr>
      <td>@Test</td>
      <td>Denotes that a method is a test method. Unlike JUnit 4’s `@Test` annotation, this annotation does not declare 
         any attributes, since test extensions in JUnit Jupiter operate based on their own dedicated annotations. 
         Such methods are inherited unless they are overridden.</td>
   </tr>
   <tr>
      <td>@ParameterizedTest</td>
      <td>Denotes that a method is a parameterized test. Such methods are inherited unless they are overridden.</td>
   </tr>
   <tr>
      <td>@RepeatedTest</td>
      <td>Denotes that a method is a test template for a repeated test. Such methods are inherited unless they are 
         overridden.</td>
   </tr>
   <tr>
      <td>@TestFactory</td>
      <td>Denotes that a method is a test factory for dynamic tests. Such methods are inherited unless they are 
         overridden.</td>
   </tr>
   <tr>
      <td>@TestTemplate</td>
      <td>Denotes that a method is a template for test cases designed to be invoked multiple times depending on the 
         number of invocation contexts returned by the registered providers. Such methods are inherited unless they are 
         overridden.</td>
   </tr>
   <tr>
      <td>@TestClassOrder</td>
      <td>Used to configure the test class execution order for @Nested test classes in the annotated test class. Such 
         annotations are inherited.</td>
   </tr>
   <tr>
      <td>@TestMethodOrder</td>
      <td>Used to configure the test method execution order for the annotated test class; similar to JUnit 4’s 
         `@FixMethodOrder`. Such annotations are inherited.</td>
   </tr>
   <tr>
      <td>@TestInstance</td>
      <td>Used to configure the test instance lifecycle for the annotated test class. Such annotations are inherited.</td>
   </tr>
   <tr>
      <td>@DisplayName</td>
      <td>Declares a custom display name for the test class or test method. Such annotations are not inherited.</td>
   </tr>
   <tr>
      <td>@DisplayNameGeneration</td>
      <td>Declares a custom display name generator for the test class. Such annotations are inherited.</td>
   </tr>
   <tr>
      <td>@BeforeEach</td>
      <td>Denotes that the annotated method should be executed before each @Test, @RepeatedTest, @ParameterizedTest, or 
         @TestFactory method in the current class; analogous to JUnit 4’s @Before. Such methods are inherited – unless 
         they are overridden or superseded (i.e., replaced based on signature only, irrespective of Java’s visibility 
         rules).</td>
   </tr>
   <tr>
      <td>@AfterEach</td>
      <td>Denotes that the annotated method should be executed after each @Test, @RepeatedTest, @ParameterizedTest, or 
         @TestFactory method in the current class; analogous to JUnit 4’s @After. Such methods are inherited – unless 
         they are overridden or superseded (i.e., replaced based on signature only, irrespective of Java’s visibility 
         rules).</td>
   </tr>
   <tr>
      <td>@BeforeAll</td>
      <td>Denotes that the annotated method should be executed before all @Test, @RepeatedTest, @ParameterizedTest, and 
         @TestFactory methods in the current class; analogous to JUnit 4’s @BeforeClass. Such methods are inherited – 
         unless they are hidden, overridden, or superseded, (i.e., replaced based on signature only, irrespective of 
         Java’s visibility rules) – and must be static unless the "per-class" test instance lifecycle is used.</td>
   </tr>
   <tr>
      <td>@AfterAll</td>
      <td>Denotes that the annotated method should be executed after all @Test, @RepeatedTest, @ParameterizedTest, and 
         @TestFactory methods in the current class; analogous to JUnit 4’s @AfterClass. Such methods are inherited – 
         unless they are hidden, overridden, or superseded, (i.e., replaced based on signature only, irrespective of 
         Java’s visibility rules) – and must be static unless the "per-class" test instance lifecycle is used.</td>
   </tr>
   <tr>
      <td>@DisplayNameGeneration</td>
      <td>Declares a custom display name generator for the test class. Such annotations are inherited.</td>
   </tr>
   <tr>
      <td>@DisplayNameGeneration</td>
      <td>Declares a custom display name generator for the test class. Such annotations are inherited.</td>
   </tr>
   <tr>
      <td>@Nested</td>
      <td>Denotes that the annotated class is a non-static nested test class. On Java 8 through Java 15, @BeforeAll and 
         @AfterAll methods cannot be used directly in a @Nested test class unless the "per-class" test instance 
         lifecycle is used. Beginning with Java 16, @BeforeAll and @AfterAll methods can be declared as static in a 
         @Nested test class with either test instance lifecycle mode. Such annotations are not inherited.</td>
   </tr>
   <tr>
      <td>@Tag</td>
      <td>Used to declare tags for filtering tests, either at the class or method level; analogous to test groups in 
         TestNG or Categories in JUnit 4. Such annotations are inherited at the class level but not at the method level.</td>
   </tr>
   <tr>
      <td>@Disabled</td>
      <td>Used to disable a test class or test method; analogous to JUnit 4’s @Ignore. Such annotations are not inherited.</td>
   </tr>
   <tr>
      <td>@Timeout</td>
      <td>Used to fail a test, test factory, test template, or lifecycle method if its execution exceeds a given 
         duration. Such annotations are inherited.</td>
   </tr>
   <tr>
      <td>@ExtendWith</td>
      <td>Used to register extensions declaratively. Such annotations are inherited.</td>
   </tr>
   <tr>
      <td>@RegisterExtension</td>
      <td>Used to register extensions programmatically via fields. Such fields are inherited unless they are shadowed.</td>
   </tr>
   <tr>
      <td>@TempDir</td>
      <td>Used to supply a temporary directory via field injection or parameter injection in a lifecycle method or test 
         method; located in the `org.junit.jupiter.api.io` package.</td>
   </tr>
</table>

# Jupiter Concepts

**Lifecycle Method**

- any method that is directly annotated or meta-annotated with `@BeforeAll`, `@AfterAll`, `@BeforeEach`,
  or `@AfterEach`.

**Test Class**

- any top-level class, static member class, or @Nested class that contains at least one test method, i.e. a container.
  Test classes must not be abstract and must have a single constructor.

**Test Method**

- any instance method that is directly annotated or meta-annotated with `@Test`, `@RepeatedTest`, `@ParameterizedTest`,
  `@TestFactory`, or `@TestTemplate`. With the exception of @Test, these create a container in the test tree that groups
  tests or, potentially (for @TestFactory), other containers.

--- 

**Lifecycle Method**

- `@BeforeAll`, `@AfterAll`, `@BeforeEach`, or `@AfterEach`. Ushbu 4ta annotatsiyadan istalgan biri qoyilgan method
  _Lifecycle Method_ deyiladi. Yani Test Classlar bajarilishidan oldin yoki keyin ishlaydigan methodlar.

**Test Class**

- Har qanday top-level class, static member class yoki @Nested annotatsiyasi qoyilgan class. Ushbu classlarni ichida
  kamida bitta test method bo'lsa ushbu class _Test Class_ deb ataladi. Test Classlarni nomi **Test** bilan tugashi
  majburiy emas. Lekin test class ekanligini bildirish uchun test qo'shimchasini qo'shimiz kerak.

**Test Method**

- `@Test`, `@RepeatedTest`, `@ParameterizedTest`, `@TestFactory` yoki `@TestTemplate` annotatsiyalaridan biri qoyilgan
  method test method deyiladi. 

# Test Classes and Methods

Test methods and lifecycle methods may be declared locally within the current test class, inherited from superclasses, 
or inherited from interfaces (see Test Interfaces and Default Methods). In addition, test methods and lifecycle methods 
must not be `abstract` and must not return a value (except `@TestFactory` methods which are required to return a value).

---

Test Methodlar va Lifecycle Methodlar superclasslardan meros bo'lib o'tishi mumkin. Bundan tashqari test va lifecycle
methodlar `abstract` bo'lmasligi va qiymat qaytarmasligi kerak.    

### Class and method visibility

Test classes, test methods, and lifecycle methods are not required to be public, but they must not be private.

---

Test Classlar, test methodlar va lifecycle methodlar `public` bo'lishi shart emas. `private` bo'lishi mumkin emas.

----

`It is generally recommended to omit the public modifier for test classes, test methods, and lifecycle methods unless 
there is a technical reason for doing so – for example, when a test class is extended by a test class in another 
package. Another technical reason for making classes and methods public is to simplify testing on the module path when 
using the Java Module System.`

```java
public class LifecycleMethodTests {

    private static final Logger log = LoggerFactory.getLogger(LifecycleMethodTests.class);

    @BeforeAll
    static void initAll() {
        log.info("initAll Method Start");
    }

    @BeforeEach
    void init() {
        log.info("init Method Start");
    }

    @Test
    void failingTest() {
        log.error("failingTest Method Start");
    }

    @Test
    @Disabled("for demonstration purposes")
    void skippedTest() {
        // not executed!
    }

    @Test
    void abortedTest() {
        log.info("test should have been aborted");
    }

    @AfterEach
    void tearDown() {
        log.warn("tearDown Method Start");
    }

    @AfterAll
    static void tearDownAll() {
        log.warn("tearDownAll Method Start");
    }

}
```

# Display Names

Test classes and test methods can declare custom display names via @DisplayName — with spaces, special characters,
and even emojis — that will be displayed in test reports and by test runners and IDEs.

---

`@DisplayName` annotatsiyasi bilan test class va methodlariga nom berishimiz mumkin. Har xil turdagi belgi va emojilar
qo'yishimiz mumkin.

```java
@DisplayName("Display Name Test Class")
public class DisplayNameTest {

    @Test
    @DisplayName("Custom test name containing spaces")
    void testWithDisplayNameContainingSpaces() {
    }

    @Test
    @DisplayName("╯°□°）╯")
    void testWithDisplayNameContainingSpecialCharacters() {
    }

    @Test
    @DisplayName("😱")
    void testWithDisplayNameContainingEmoji() {
    }

}
```

# Assertions

```java
public class AssertionsExampleTest {

    private final CalculateService service = new CalculateService();
    private final Person person = new Person("Nurislom", 18);

    @Test
    void standardAssertions() {
        assertEquals(4, service.calculate(2, 2, '+'));
        assertEquals(5, service.calculate(10, 5, '-'));
        assertTrue('a' < 'b', "Assertion messages can be lazily evaluated -- " +
                "to avoid constructing complex messages unnecessarily.");
    }

    @Test
    void groupedAssertions() {
        // In a grouped assertion all assertions are executed, and all failures will be reported together.
        assertAll(
                () -> assertEquals("Nurislom", person.getName()),
                () -> assertEquals(18, person.getAge()),
                () -> assertEquals(5, 5)
        );
    }

    @Test
    void dependentAssertions() {
        // Within a code block, if an assertion fails the subsequent code in the same block will be skipped.
        assertAll(
                () -> {
                    String name = person.getName();
                    assertNotNull(name);

                    // Executed only if the previous assertion is valid.
                    assertAll(name,
                            () -> assertTrue(name.startsWith("N")),
                            () -> assertTrue(name.endsWith("m"))
                    );
                },
                () -> {
                    Integer age = person.getAge();
                    assertNotNull(age);

                    // Executed only if the previous assertion is valid.
                    assertAll(String.valueOf(age),
                            () -> assertEquals(18, (int) age),
                            () -> assertTrue(true)
                    );
                }
        );
    }

    @Test
    void exceptionTesting() {
        ArithmeticException exception = assertThrows(ArithmeticException.class,
                () -> service.calculate(1L, 0L, '/'));
        assertEquals("cannot be divided by zero.", exception.getMessage());
    }

    @Test
    void timeoutNotExceeded() {
        // The following assertion succeeds.
        assertTimeout(Duration.ofSeconds(5), () -> {
            // Perform task that takes less than 5 seconds.
            System.out.println("Nurislom");
        });
    }

    @Test
    void timeoutNotExceededWithResult() {
        // The following assertion succeeds, and returns the supplied object.
        String result = assertTimeout(Duration.ofSeconds(3), () -> "Abdulloh");
        assertEquals("Abdulloh", result);
    }

    @Test
    void timeoutNotExceededWithMethod() {
        // The following assertion invokes a method reference and returns an object.
        String greeting = assertTimeout(Duration.ofSeconds(3), service::greeting);
        assertEquals("Boom", greeting);
    }

    @Test
    void timeoutExceeded() {
        // The following assertion fails with an error message similar to:
        // execution exceeded timeout of 10 ms by 91 ms
        assertTimeout(Duration.ofMillis(10), () -> {
            // Simulate task that takes more than 10 ms.
            Thread.sleep(100);
        }, () -> "Hello World");
    }

    @Test
    void timeoutExceededWithAssertion() {
        assertAll(() -> {
            assertThrows(AssertionFailedError.class, () -> {
                assertTimeout(Duration.ofMillis(10), () -> {
                    Thread.sleep(20);
                    return new RuntimeException("Not Executed!");
                }, () -> "Not Executed!");
            });
        });
    }

    @Test
    void timeoutExceededWithPreemptiveTermination() {
        // The following assertion fails with an error message similar to:
        // execution timed out after 10 ms
        assertTimeoutPreemptively(Duration.ofMillis(10), () -> {
            // Simulate task that takes more than 10 ms.
            new CountDownLatch(1).await();
        });
    }

}
```

# Third-party Assertion Libraries

Even though the assertion facilities provided by JUnit Jupiter are sufficient for many testing scenarios, there are 
times when more power and additional functionality such as matchers are desired or required. In such cases, the JUnit 
team recommends the use of third-party assertion libraries such as [AssertJ](https://joel-costigliola.github.io/assertj/),
[Hamcrest](https://hamcrest.org/JavaHamcrest/), [Truth](https://truth.dev/), etc. Developers are therefore free to use 
the assertion library of their choice.

# Disabling Tests

```java
@Disabled("Disabled until bug #99 has been fixed")
class DisabledClassDemo {

    @Test
    void testWillBeSkipped() {
    }

}
```
