package org.khasanof.websocket.server.kotlin

import org.slf4j.LoggerFactory
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.handler.annotation.SendTo
import org.springframework.stereotype.Controller

@Controller
class WebSocketKtController {

    private val log = LoggerFactory.getLogger(this.javaClass)

    @MessageMapping("/kt/chat")
    @SendTo("/topic/messages")
    fun sendKotlinMessageTopic(messageKtDTO: MessageKtDTO) : MessageKtDTO {
        log.info("message mapping to kotlin : $messageKtDTO")
        return messageKtDTO
    }

}
