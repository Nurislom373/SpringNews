# WebSocket

## Introduction

Http Protocoli request-response protocolidir. Bu shuni anglatadiki, faqat mijoz HTTP requestlarini serverga yuborishi
mumkin. Server faqat HTTP requestlariga HTTP responselarni yuborish orqali xizmat ko'rsatishi mumkin, ammo server 
mijozga so'ralmagan responselarni yubora olmaydi.

## Overview

WebSocket - bu web bruazer va server o'rtasidagi ikki tomonlama, to'liq dupleks doimiy ulanish. WebSocket ulanish 
o'rnatilgandan so'ng, mijoz yoki server ushbu ulanishni yopishga qaror qilmaguncha ulanish ochiq qoladi.

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-websocket</artifactId>
</dependency>

<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-messaging</artifactId>
</dependency>
```

## Enable WebSocket in Spring

Birinchidan, Biz WebSocket imkoniyatlarini yoqamiz. Buni amalga oshirish uchun biz configuratsiya classni qo'shishimiz 
kerak bo'ladi va ushbu classga `@EnableWebSocketMessageBroker` annotatsiyasini qo'yishimiz ham kerak.

```java
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker("/topic");
        config.setApplicationDestinationPrefixes("/app");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
         registry.addEndpoint("/chat");
         registry.addEndpoint("/chat").withSockJS();
    }
}
```

## STOMP

WebSocket ikki xil turdagi xabar almashish turlarni belgilaydi. Text va Binary, lekin ularning content aniqlanmagan.
Mijoz va Server uchun sub-protocol bo'yicha muzokaralar olib borish mexanizmni belgilaydi. Ya'ni yuqori darajadagi
xabar almashish protocoli.

STOMP oddiy matnga yo'naltirilgan(text-oriented) messaging protocol bo'lib, dastlab Ruby, Python va Perl kabi script
tillarda uchun korporativ brokerlarga ulanish uchun yaratilgan. STOMP TCP va WebSocket kabi har qanday ishonchli
ikki tomonlama tarmoq protocoli orqali ishlatilishi mumkin. STOMP text yo'naltirilgan protocol bo'lsada, xabarlarning
text yoki binary bo'lishi mumkin.

STOMP - bu framelarga asoslangan protocol bo'lib, uning framelari HTTP da modellashtirilgan.
The structure of a STOMP frame:

---

STOMP is a simple text-oriented messaging protocol that was originally created for scripting languages such as Ruby, 
Python, and Perl to connect to enterprise message brokers. It is designed to address a subset of commonly used messaging
patterns. STOMP can be used over any reliable 2-way streaming network protocol such as TCP and WebSocket. Although STOMP
is a text-oriented protocol, the payload of messages can be either text or binary.

STOMP is a frame based protocol whose frames are modeled on HTTP. The structure of a STOMP frame:

```
COMMAND
header1:value1
header2:value2

Body^@
```

Mijozlar xabar yuborish yoki ularga obuna bo'lish uchun SEND yoki SUBCRIBE buyruqlaridan hamda nima haqida ekanligini
va uni kim olishi kerakligini tavsiflovchi "destination" sarlavhasidan foydalanishlari mumkin. Bu broker orqali
boshqa ulangan mijozlarga xabar yuborish yoki ba'zi ishlarni bajarishni so'rash uchun serverga xabarlar yuborish uchun
ishlatilishi mumkin bo'lgan oddiy pub-sub mexanizmni ta'minlaydi.
