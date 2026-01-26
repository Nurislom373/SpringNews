package org.khasanof;

import lombok.RequiredArgsConstructor;
import org.khasanof.domain.User;
import org.khasanof.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author Nurislom
 * @see org.khasanof
 * @since 11/5/25
 */
@Component
@RequiredArgsConstructor
public class DefaultDataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;

    /**
     *
     * @param args
     * @throws Exception
     */
    @Override
    public void run(String... args) throws Exception {
        userRepository.saveAll(getUsers());
    }

    private List<User> getUsers() {
        return IntStream.range(0, 10)
                .mapToObj(i -> createUser(UUID.randomUUID().toString()))
                .collect(Collectors.toList());
    }

    private User createUser(String name) {
        User user = new User();
        user.setName(name);
        return user;
    }
}
