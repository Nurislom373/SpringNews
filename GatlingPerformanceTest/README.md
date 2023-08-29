# Gatling Performance Testing

## Overview

Gatling is a performance testing tool to carry out load tests on applications. Gatling can spawn thousands of virtual
users/clients over a single machine as it is built on top of Akka and treats virtual users as messages and not threads.
Gatling’s architecture is asynchronous. This kind of architecture lets us implement virtual users as messages instead of
dedicated threads, making them very resource cheap.

Be careful as Gatling require a JDK8.

---

Gatling - bu ilovalarda performance testlarni o'tkazish orqali ilovalarni ishlash performance teskshiradigan tool.
Gatling minglab virtual foydalanuvchi/mijozlarni bitta mashinada yaratishi mumkin, sababi u [Akka](https://akka.io/)
ustiga qurilgan va virtual foydalanuvchilarga threadlar emas, xabarlar sifatida qaraydi. Gatling arxitekturasi
asinxrondir. Ushbu turdagi arxitektura virtual foydalanuvchilarni maxsus threadlar o'rniga xabarlar sifatida amalga
oshirishga imkon beradi va bu ularni juda arzon resourcelarga aylantiradi.

Gatling dan foydalanish uchun kamida JDK8 bo'lishi kerak!

## Performance Testing Types

performance testing include load testing, stress testing, soak testing, spike testing, and scalability testing.

- `Load Testing` - Load testing involves testing a system under a heavy load of concurrent virtual users over a period
  of time.
- `Stress Testing` - stress testing involves gradually increasing the load on a system to find its breaking point.
- `Soak Testing` - soak testing aims to put a steady traffic rate through a system for extended periods to identify
  bottlenecks.
- `Spike Testing` - spike testing consists in testing how a system performs when the number of requests increases to the
  stress level quickly, then decreasing it again soon after.
- `Scalability Testing` - Scalability testing involves testing how the system performs when the number of user requests
  scales up or down.

---

Performance testlarning har xil turlari mavjud. Ular - load testing, stress testing, soak testing, spike testing va
scability testing lar kiradi.

- `Load Testing` - ma'lum vaqt davomida bir vaqtning o'zida virtual foydalanuvchilarning og'ir yuki ostida tizimni
  sinovdan o'tkazishni o'z ichiga oladi.
- `Stress Testing` - boshqa tomondan tizimning buzilish nuqtasini topish uchun uning yukini bosqichma bosqich oshirishni
  o'z ichiga oladi.
- `Soak Testing` - to'siqlarni aniqlash uchun uzoq vaqt davomida tizim orqali barqaror trafik tezligini o'rnatishga
  qaratilgan.
- `Spike Testing` - so'rovlar sonini tezda stress darajasiga ko'tarilganda tizim qanday ishlashini sinab ko'rishga
  qaratilgan. keyin uni yana kamaytiradi.
- `Scalability Testing` - scalability testing foydalanuvchi so'rovlari soni ko'payib yoki kamayganda tizim qanday
  ishlashini tekshirishni o'z ichiga oladi.

# Key Terminologies

# 1. Virtual User

Ab yoki wrk kabi bazi load testing tool lar url ni buzishda juda samarali, ammo requestlar orasidagi logic bilan
shug'ullana olmaydi. 

Gatling kabi ilg'or load testing toollari virtual foydalanuvchilar bilan shug'ullanishi mumkin, ularning har biri o'z
ma'lumotlariga ega va ehitmol aniq ko'rish yo'lini oladi.

# 2. Scenario

bu foydalanuvchilarning odatiy xatti-harakatlarni ifodalaydi. Bu virtual foydalanuvchilar amal qiladigan
qiladigan ish jarayoni.

Misl uchun e-commerce ilovasini stsenariasyini olaylik:
1. Bosh sahifaga kirish.
2. Category tanlash.
3. Va ushbu category tegishli productlar uchun qidiruv berish.
4. productlarni description ko'rish.
5. ortga qaytib
6. boshqa productlarni description ko'rish
7. productni sotib olish va hokazolar.

## 2.1 Exec

The exec method is used to execute an action. Actions are usually requests (HTTP, WebSocket, JMS, MQTT…) that will be 
sent during the simulation. Any action that will be executed will be called with exec.

--- 

`exec` method amallarni bajarish uchun ishlatiladi. Amallar odatda simulatsiya paytida yuboriladigan requestlar (HTTP, 
WebSocker, MQTT...). Bajariladigan har qanday harakat exec method bilan amalga oshiriladi.

```java
// attached to a scenario
scenario("Scenario")
  .exec(http("Home").get("https://gatling.io"));

// directly created and stored in a reference
ChainBuilder chain = exec(http("Home").get("https://gatling.io"));

// attached to another
exec(http("Home").get("https://gatling.io"))
  .exec(http("Enterprise").get("https://gatling.io/enterprise"));
```

exec method Function functional interface qabul qiladigan overload versiyasi ham bor.
ushbu methodidan foydalanib qo'lda nosozliklarni tuzatish yoki sessionni tahrirlash uchun ishlatish mumkin.

```java
exec(session -> {
  // displays the content of the session in the console (debugging only)
  System.out.println(session);
  // return the original session
  return session;
});

exec(session ->
  // return a new session instance
  // with a new "foo" attribute whose value is "bar"
  session.set("foo", "bar")
);
```

## 2.2 Pause


```java
private static ScenarioBuilder buildPostScenario() {
    return CoreDsl.scenario("Load Test By Test Controller")
        .feed(FEED_DATA)
        .exec(http("create-employee-request")
            .post("/api/create-fake-data")
            .header("Content-Type", "application/json")
            .body(StringBody(stringBody))
            .check(HttpDsl.status().is(201)))
        .pause(2) // pause method
        .exec(http("get-employee-request")
            .get("/api/get-fake-data")
            .header("Content-Type", "application/json")
            .check(HttpDsl.status().is(200)))
        .pause(2, 3); // pause method
}
```

Pauses are used to simulate user think time. When a real user clicks on a link, the page has to be loaded in their
browser and they will, most likely, read it and then decide what to do next.

---

Ushbu tepada ishlatilgan Pauzalar foydalanuvchining fikrlash vaqtini taqlid qilish uchun ishlatiladi. Haqiqiy
foydalanuvchi havolani yani link bosganida, sahifa o'z brauzeriga yuklanishi kerak va ular, ehtimol, uni o'qib chiqadi
va keyin nima qilishni hal qiladi.

Pauza method faqat bitta parameter qabul qiladi va uni bir nechta variantlari bor. int, Duration, Gatling EL yoki
Function.

```java
// with a number of seconds
pause(10);
// with a java.time.Duration
pause(Duration.ofMillis(100));
// with a Gatling EL string resolving to a number of seconds or a java.time.Duration
pause("#{pause}");
// with a function that returns a java.time.Duration
pause(session -> Duration.ofMillis(100));
```

pauza method 2ta parameter qabul qiladigan varinati ham mavjud. Ushbu pauze berilgan orqalida random soni oladi va ushbu 
vaqt oralig'ida pauza qiladi.

```java
// with a number of seconds
pause(10, 20);
// with a java.time.Duration
pause(Duration.ofMillis(100), Duration.ofMillis(200));
// with a Gatling EL strings
pause("#{min}", "#{max}");
// with a function that returns a java.time.Duration
pause(session -> Duration.ofMillis(100), session -> Duration.ofMillis(200));
```

* `min`: can be an Int for a duration in seconds, a duration, a Gatling EL String or a function
* `max`: can be an Int for a duration in seconds, a duration, a Gatling EL String or a function

## 2.3 Loop Statements

## Repeat

Repeat the loop a specified amount of times.

---

`repeat` method belgilan miqdorda loopni takrorlaydi.

repeat method 2ta parameter qabul qiladi.

* `times` : sikl tarkibini takrorlash soni int, gatling EL yoki Function bo'lishi mumkin.
* `counterName` (Optional) : Sessionda sikl hisoblagichini 0 dan boshlab saqlash uchun kalit.

```java
// with an Int times
repeat(5).on(
  exec(http("name").get("/"))
);
// with a Gatling EL string resolving an Int
repeat("#{times}").on(
  exec(http("name").get("/"))
);
// with a function times
repeat(session -> 5).on(
  exec(http("name").get("/"))
);
// with a counter name
repeat(5, "counter").on(
  exec(session -> {
    System.out.println(session.getInt("counter"));
    return session;
  })
);
```

## Foreach

Repeat the loop for each element in the specified sequence.
It takes 3 parameters:

* `seq`: the list of elements to iterate over, can be a List, a Gatling EL String or a function
* `elementName`: the key to the current element in the `Session`
* `counterName` (optional): the key to store the loop counter in the Session, starting at 0

---

belgilangan ketma-ketlikda har bir element uchun tsiklni takrorlaydi.
U 3ta parameter qabul qiladi.

* `seq`: takrorlanadigan elementlar ro'yxati, List, Gatling EL yoki Function.
* `elementName`: current element `Session` olish uchun key.
* `counterName` (Optional): Sessionda sikl hisoblagichini 0 dan boshlab saqlash uchun kalit

```java
// with a static List
foreach(Arrays.asList("elt1", "elt2"), "elt").on(
  exec(http("name").get("/"))
);
// with a Gatling EL string
foreach("#{elts}", "elt").on(
  exec(http("name").get("/"))
);
// with a function
foreach(session -> Arrays.asList("elt1", "elt2"), "elt").on(
  exec(http("name").get("/"))
);
// with a counter name
foreach(Arrays.asList("elt1", "elt2"), "elt", "counter").on(
  exec(session -> {
    System.out.println(session.getString("elt2"));
    return session;
  })
);
```

## asLongAs

Iterate over the loop as long as the condition is satisfied.

It takes 3 parameters:

* `condition`: can be a boolean, a Gatling EL String resolving a boolean or a function
* `counterName` (optional): the key to store the loop counter in the Session, starting at 0
* `exitASAP` (optional, default true): if true, the condition will be evaluated for each element inside the loop, possibly 
  causing to exit the loop before reaching the end of the iteration.

---

Shart bajarulgunga qadar tsikl bo'ylab takrorlanadi.

U 3ta parameter qabul qiladi.

* `condition`: boolean, mantiqiy yoki functionni hal qiluvchi Gatling EL String bo'lishi mumkin.
* `counterName`: Sessionda sikl counterni 0 dan boshlab saqlash uchun kalit
* `exitASAP` (Optional): agar true bo'lsa, shart sikl ichidagi har bir element uchun baholanadi, bu iteratsiya
  oxiriga yetmas to'xtalishi mumkin.

```java
// with a Gatling EL string resolving to a boolean
asLongAs("#{condition}").on(
  exec(http("name").get("/"))
);
// with a function
asLongAs(session -> session.getBoolean("condition")).on(
  exec(http("name").get("/"))
);
// with a counter name and exitASAP
asLongAs("#{condition}", "counter", false).on(
  exec(http("name").get("/"))
);
```

## doWhile

Similar to asLongAs but the condition is evaluated after the loop.

It takes 2 parameters:

* `condition` can be a boolean, a Gatling EL String resolving a boolean or a function
* `counterName` (optional): the key to store the loop counter in the Session, starting at 0

---

`doWhile` `asLongAs` ga o'xshash, lekin shart tsikldan keyin bajariladi.

2ta parameter qabul qiladi:

* `condition` boolean yoki Gatling EL bo'lishi mumkin.
* `counterName` (Optional): Sessionda sikl counterni 0 dan boshlab saqlash uchun kalit

```java
// with a Gatling EL string resolving to a boolean
doWhile("#{condition}").on(
  exec(http("name").get("/"))
);
// with a function
doWhile(session -> session.getBoolean("condition")).on(
  exec(http("name").get("/"))
);
// with a counter name
doWhile("#{condition}", "counter").on(
  exec(http("name").get("/"))
);
```

## asLongAsDuring

Iterate over the loop as long as the condition is satisfied and the duration hasn’t been reached.

It takes 4 parameters:

* `condition` can be a boolean, a Gatling EL String resolving a boolean or a function
* `duration` can be an Int for a duration in seconds, a duration, a Gatling EL String or a function
* `counterName` (optional): the key to store the loop counter in the Session, starting at 0
* `exitASAP` (optional, default true). If true, the condition will be evaluated for each element inside the loop, possibly 
causing to exit the loop before reaching the end of the iteration.

---

shart bajarilsa va muddatga yetib bormasa tsikl takrorlanaveradi.

4ta parameter qabul qiladi:

* `condition`: boolean, mantiqiy yoki functionni hal qiluvchi Gatling EL String bo'lishi mumkin.
* `duration`: belgilangan soniyada tsikl aylanadi. int yoki gatling EL bo'lishi mumkin.
* `counterName`: Sessionda sikl counterni 0 dan boshlab saqlash uchun kalit
* `exitASAP` (Optional): agar true bo'lsa, shart sikl ichidagi har bir element uchun baholanadi, bu iteratsiya
  oxiriga yetmas to'xtalishi mumkin.

```java
// with a Gatling EL string resolving to a boolean and an int duration
asLongAsDuring("#{condition}", 5).on(
  exec(http("name").get("/"))
);
// with a counter name and exitASAP
asLongAsDuring(session -> true, Duration.ofMinutes(10), "counter", false).on(
  exec(http("name").get("/"))
);
```

## 2.4 Conditional Statements

Gatling’s DSL has conditional execution support.

It takes one single parameter:

* `condition` can be a boolean, a Gatling EL String resolving a boolean or a function

---

Gatling DSL conditional bajarishni qo'llab quvvatlaydi.

1ta parameter qabul qiladi:

* `condition` boolean yoki Gatling EL bo'lishi mumkin.

```java
// with a Gatling EL string resolving to a boolean
doIf("#{condition}").then(
  exec(http("name").get("/"))
);

// with a function
doIf(session -> session.getBoolean("condition")).then(
  exec(http("name").get("/"))
);
```


# 3. Injection

foydalanuvchilar kiritish Gatlingda ikki xil modelda amalga oshiriladi ular InjectOpen va InjectClosed modellar.

## Open and Closed Workload Models

yuklash modeli haqida gap ketganda, tizimlar 2 xil usulda ishlaydi:

- Bir vaqtning o'zida foydalanuvchilar sonini boshqaradigan yopiq tizimlar.
- Foydalanuvchilarning kelish tezligini nazorat qiladigan ochiq tizimlar.

Yopiq tizim - bu bir vaqtning o'zida foydalanuvchilar soni chegaranlangan tizim. To'liq quvvat bilan yangi foydalanuvchi
tizimga faqat ikkinchisi chiqqanidan keyin kirishi mumkin.

bunday ishlaydigan tizimlar.

- Call Center.

Aksincha ochiq tizimlar bir vaqtda foydalanuvchilarni sonini nazorat qila olmaydilar. Ular xizmat ko'rsatishda muammoga
duch kelgan bo'lsa ham, foydalanuvchilarni qabul qilishda davom etadilar.

## 3.1 Open Model

```java
setUp(
  scn.injectOpen(
    nothingFor(4), // 1
    atOnceUsers(10), // 2
    rampUsers(10).during(5), // 3
    constantUsersPerSec(20).during(15), // 4
    constantUsersPerSec(20).during(15).randomized(), // 5
    rampUsersPerSec(10).to(20).during(10), // 6
    rampUsersPerSec(10).to(20).during(10).randomized(), // 7
    stressPeakUsers(1000).during(20) // 8
  ).protocols(httpProtocol)
);
```

ochiq modelni qurish uchun quyidagi methodlardan foydalanishimiz mumkin.

1. `nothingFor(duration)` : belgilangan vaqt uchun pauza qiladi.
2. `atOnceUsers(nbUsers)` : bir vaqtning o'zida ma'lum miqdordagi foydalanuvchilarni kiritadi.
3. `rampUsers(nbUsers).during(duration)` : ma'lum bir vaqt oralig'ida teng taqsimlangan foydalanuvchilarning ma'lum sonini kiritadi.

# 4. Simulation

Simulatsiya - bu load testingning tavsifi. U bir nechta foydalanuvchi populatsiyalari qanday ishlashini tasvirlaydi.
Ular stsenariyni qanday bajaradi va yangi virtual foydalanuvchilar qanday kiritiladi.

Simulation class bu Gatling testlari uchun parent class. Ushbu class load testlarni ishga tushirish uchun foydalaniladi.

```java
// required for Gatling core structure DSL
import io.gatling.javaapi.core.*;
import static io.gatling.javaapi.core.CoreDsl.*;

// required for Gatling HTTP DSL
import io.gatling.javaapi.http.*;
import static io.gatling.javaapi.http.HttpDsl.*;
```

Simulatsiyadagi `setUp` methodni testlarni ro'yxatdan o'tkazish uchun o'z constructorida bir marta chaqirishlar.
`setup` methodni maqsadi testlar ro'yxatdan o'tkazish.

```java
 setUp(
        scenarioBuilder().injectOpen(OpenInjectionStep.atOnceUsers(2000)),
        scenarioBuilder().injectOpen(OpenInjectionStep.nothingFor(
                Duration.of(60, ChronoUnit.SECONDS)), CoreDsl.rampUsers(5).during(400)),
        scenarioBuilder().injectOpen(CoreDsl.rampUsers(500).during(200))
 );
```

## 4.1 Protocol Configuration

Protocols configurations can be attached

* either on the setUp, in which case they are applied on all the populations
* or on each population, so they can have different configurations

---

Gatling ikki xil protocol configuratsiyasini taqdim etadi.

* global protocol configuratsiyasi
* har bir populatsiya uchun alohida configuratsiya

```java
// HttpProtocol configured globally
setUp(
  scn1.injectOpen(atOnceUsers(1)),
  scn2.injectOpen(atOnceUsers(1))
).protocols(httpProtocol);

// different HttpProtocols configured on each population
setUp(
  scn1.injectOpen(atOnceUsers(1))
    .protocols(httpProtocol1),
  scn2.injectOpen(atOnceUsers(1))
    .protocols(httpProtocol2)
);
```

## 4.2 Acceptance Criteria

load test tuganidan so'ng undan qaytgan natijani assertionlar orqali biz kutgan natijani olganmizmi yoki yoqligini 
bilishimiz mumkin.

```java
setUp(scn.injectOpen(atOnceUsers(1)))
  .assertions(global().failedRequests().count().is(0L));
```

## 4.3 Global Pause Configuration

The pauses can be configured on Simulation with a bunch of methods:

---

Simulation pauzalarni bir qancha methodlar bilan configuratsiya qilish mumkin:

```java
// pause configuration configured globally
setUp(scn.injectOpen(atOnceUsers(1)))
  // disable the pauses for the simulation
  .disablePauses()
  // the duration of each pause is what's specified
  // in the `pause(duration)` element.
  .constantPauses()
  // make pauses follow a uniform distribution
  // where the mean is the value specified in the `pause(duration)` element.
  .uniformPauses(0.5)
  .uniformPauses(Duration.ofSeconds(2))
  // make pauses follow a normal distribution
  // where the mean is the value specified in the `pause(duration)` element.
  // and the standard deviation is the duration configured here.
  .normalPausesWithStdDevDuration(Duration.ofSeconds(2))
  // make pauses follow a normal distribution
  // where the mean is the value specified in the `pause(duration)` element.
  // and the standard deviation is a percentage of the mean.
  .normalPausesWithPercentageDuration(20)
  // make pauses follow an exponential distribution
  // where the mean is the value specified in the `pause(duration)` element.
  .exponentialPauses()
  // the pause duration is computed by the provided function (in milliseconds).
  // In this case the filled duration is bypassed.
  .customPauses(session -> 5L);

// different pause configurations configured on each population
setUp(
  scn1.injectOpen(atOnceUsers(1))
    .disablePauses(),
  scn2.injectOpen(atOnceUsers(1))
    .exponentialPauses()
);
```

## 4.4 Throttle

What throttle do is that it:

* disables all the pauses
* caps your throughput. It can’t generate a throughput that’s higher than the one normally generated by your simulation 
  once pauses are disabled.

```java
// throttling profile configured globally
setUp(scn.injectOpen(constantUsersPerSec(100).during(Duration.ofMinutes(30))))
  .throttle(
    reachRps(100).in(10),
    holdFor(Duration.ofMinutes(1)),
    jumpToRps(50),
    holdFor(Duration.ofHours(2))
  );

// different throttling profiles configured globally
setUp(
  scn1.injectOpen(atOnceUsers(1))
    .throttle(reachRps(100).in(10)),
  scn2.injectOpen(atOnceUsers(1))
    .throttle(reachRps(20).in(10))
);
```

This simulation will reach 100 req/s with a ramp of 10 seconds, then hold this throughput for 1 minute, jump to 50 req/s
and finally hold this throughput for 2 hours.

---

Ushbu simulatsiya 10 soniyali ramp bilan 100 req/s etadi, keyin bu o'tkazuvchanlikni 1 daqiqa ushlab turadi, va 50 req/s
o'tadi va bu o'tkazuvchanlikni 2 soat ushlab turadi.

## 4.5 Maximum Duration

Finally, with maxDuration you can force your run to terminate based on a duration limit, even if some virtual users are 
still running.

```java
setUp(scn.injectOpen(rampUsers(1000).during(Duration.ofMinutes(20))))
  .maxDuration(Duration.ofMinutes(10));
```

# Session

Har bir virtual foydalanuvchi `Session` tomonidan quvvatlanadi. Ushbu session stsenariy ish oqimiga tushadigan haqiqiy 
xabarlardir. Session asosan shtat to'ldiruvchisi bo'lib, u yerda sinovchilar ma'lumotlarni kiritishi yoki yozib olishi 
mumkin.

# Feeders

# Checks

har safar serverga request yuborilganda, javob odatda server tomonidan Gatling ga yuboriladi.

Gatling bu javobni `Checks` bilan tahlil yani analyze qila oladi.

Check - bu javob protsessor bo'lib, uning bir qismini ushlaydi va u berilgan shartlarga mos kelishini tasdiqlaydi.
Checklar shuningdek, ba'zi elementlarni qo'lga kiritish va ularni Sessionga saqlash uchun ishlatilishi mumkin, shunda
ular keyinchalik qayta ishlatilishi mumkin, masalan, keyingi request yaratish uchun.
birinchi token olib keladigan APi token olib ushbu qaytgan token orqali boshqa API larga call qilishimiz mumkin.

## Assertions

Assertionlar Gatling statistik ma'lumotlarni qabul qilish mezonlarini (masalan, 99% javob vaqti) aniqlash uchun 
ishlatiladi, bu gatlingni muvaffaqiyatsizlikka olib keladi va umuman test uchun xatolik holati kodini qaytaradi.

## Reports 

Hisoblar load testing tugaganidan so'ng oxirida avtomatik ravishda yaratiladi. Ular HTML fayllaridan iborat. Shuning
uchun ular ko'chma va veb-brauzerli har qanday qurilmada ko'rish mumkin.

## Benefits of performance testing

Performance testing o'tkazishning foydali tomonlari tizimda ish faoliyatini o'lchash uchun yordam beradi. Stress
testning xotiraning oqishni, sekinlashuvini, xavfsizlik zaifliklarni va ma'lumotlarnig buzilishini aniqlashga ham yordam
beradi.

## Run Simulation With Maven

required Gatling dependencies:

```xml
<dependency>
    <groupId>io.gatling</groupId>
    <artifactId>gatling-app</artifactId>
    <version>3.9.0</version>
</dependency>

<dependency>
    <groupId>io.gatling.highcharts</groupId>
    <artifactId>gatling-charts-highcharts</artifactId>
    <version>3.9.0</version>
</dependency>
```

and maven plugin:

```xml

<plugin>
    <groupId>io.gatling</groupId>
    <artifactId>gatling-maven-plugin</artifactId>
    <version>4.3.0</version>
    <configuration>
        <simulationClass>org.khasanof.gatlingperformancetest.GatlingSimulation</simulationClass>
    </configuration>
</plugin>
```
