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

## 2.2 Exec

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


## Simulation

Simulatsiya - bu load testingning tavsifi. U bir nechta foydalanuvchi populatsiyalari qanday ishlashini tasvirlaydi.
Ular stsenariyni qanday bajaradi va yangi virtual foydalanuvchilar qanday kiritiladi.

```java
 setUp(
        scenarioBuilder().injectOpen(OpenInjectionStep.atOnceUsers(2000)),
        scenarioBuilder().injectOpen(OpenInjectionStep.nothingFor(
                Duration.of(60, ChronoUnit.SECONDS)), CoreDsl.rampUsers(5).during(400)),
        scenarioBuilder().injectOpen(CoreDsl.rampUsers(500).during(200))
 );
```

## Session

Har bir virtual foydalanuvchi `Session` tomonidan quvvatlanadi. Ushbu session stsenariy ish oqimiga tushadigan haqiqiy 
xabarlardir. Session asosan shtat to'ldiruvchisi bo'lib, u yerda sinovchilar ma'lumotlarni kiritishi yoki yozib olishi 
mumkin.

## Feeders

## Checks

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

## Pause

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
