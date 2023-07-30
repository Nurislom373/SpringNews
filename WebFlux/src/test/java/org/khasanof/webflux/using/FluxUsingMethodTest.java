package org.khasanof.webflux.using;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;

import java.util.Arrays;
import java.util.Random;

/**
 * @author Nurislom
 * @see org.khasanof.webflux.using
 * @since 22.07.2023 22:17
 */
@Slf4j
public class FluxUsingMethodTest {

    @Test
    void imperativeTest() {
        try (Connection connection = Connection.newConnection()) {
            connection.getData().forEach(data -> log.info("Received data: {}", data));
        } catch (Exception e) {
            log.warn("Error: {}", e.getMessage());
        }
    }

    @Test
    void reactiveTest() {
        Flux<String> ioRequestResults = Flux.using( // (1)
                Connection::newConnection, // (1.1)
                connection -> Flux.fromIterable(connection.getData()), // (1.2)
                Connection::close // (1.3)
        );

        ioRequestResults.subscribe( // (2)
                data -> log.info("Received data: {}", data), //
                e -> log.info("Error: {}", e.getMessage()), //
                () -> log.info("Stream finished"));
    }

    static class Connection implements AutoCloseable {

        private final Random rnd = new Random();

        public Iterable<String> getData() {
            if (rnd.nextInt(10) < 3) {
                throw new RuntimeException("Communication error");
            }
            return Arrays.asList("Some", "data");
        }

        public void close() {
            log.info("IO Connection closed");
        }

        public static Connection newConnection() {
            log.info("IO Connection created");
            return new Connection();
        }
    }

}
