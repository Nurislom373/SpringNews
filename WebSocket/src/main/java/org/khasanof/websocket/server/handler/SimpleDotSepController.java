package org.khasanof.websocket.server.handler;

import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

/**
 * @author Nurislom
 * @see org.khasanof.websocket.server.handler
 * @since 9/14/2023 3:59 PM
 */
@Controller
@MessageMapping("simple")
public class SimpleDotSepController {

    @MessageMapping("foo.{baz}")
    public void handleDot(@DestinationVariable String baz) {
        // ...
    }

}
