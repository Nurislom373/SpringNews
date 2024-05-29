package org.khasanof.reactivewebsocket.web.rest;

import org.khasanof.reactivewebsocket.NotificationWsMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Nurislom
 * @see org.khasanof.reactivewebsocket.context
 * @since 5/29/2024 6:35 PM
 */
@RestController
@RequestMapping("/api/notification")
public class NotificationResource {

    private final NotificationWsMethod notificationWsMethod;

    public NotificationResource(NotificationWsMethod notificationWsMethod) {
        this.notificationWsMethod = notificationWsMethod;
    }

    @GetMapping("")
    public ResponseEntity<Void> sendMessagesAllSession() {
        notificationWsMethod.executeV1();
        return ResponseEntity.ok().build();
    }
}
