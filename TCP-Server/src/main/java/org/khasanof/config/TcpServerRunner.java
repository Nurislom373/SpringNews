package org.khasanof.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @author Nurislom
 * @see org.khasanof
 * @since 5/4/26
 */
@Component
@RequiredArgsConstructor
public class TcpServerRunner implements CommandLineRunner {

    private final TcpServer tcpServer;

    @Override
    public void run(String... args) throws Exception {
        tcpServer.start();
    }
}
