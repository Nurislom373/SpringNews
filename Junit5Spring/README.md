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
    long calculate = service.calculate(5, 5, '+');
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