package org.khasanof.service;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @author Nurislom
 * @see org.khasanof.service
 * @since 5/4/26
 */
@Component
@RequiredArgsConstructor
public class DefaultUsersFeedRunner implements CommandLineRunner {

    private final UserService service;

    @Override
    public void run(String... args) throws Exception {
        service.create("Aomine Daiki", 21);
        service.create("Kise", 21);
    }
}
