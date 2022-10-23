package com.example.completablefuturecrud.service;

import com.example.completablefuturecrud.entity.user.User;
import com.example.completablefuturecrud.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;

    Logger logger = LoggerFactory.getLogger(UserService.class);

    @Async
    public void saveUser(MultipartFile file) {
        long start = System.currentTimeMillis();
        List<User> users = parseCSVFile(file);
        logger.info("saving list of users of size {}", users.size(), "" + Thread.currentThread().getName());
        repository.saveAll(users);
        long end = System.currentTimeMillis();
        logger.info("Total time {}", (end - start));
    }

    @Async
    public CompletableFuture<List<User>> findAll() {
        logger.info("get list of user by " + Thread.currentThread().getName());
        return CompletableFuture.completedFuture(repository.findAll());
    }

    @Async
    public CompletableFuture<User> findById(Integer id) {
        logger.info("get user by " + Thread.currentThread().getName());
        return CompletableFuture.completedFuture(repository.findById(id).orElseThrow());
    }

    @SneakyThrows
    private List<User> parseCSVFile(final MultipartFile file) {
        final List<User> users = new ArrayList<>();
        try {
            try (final BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
                String line;
                while ((line = br.readLine()) != null) {
                    final String[] data = line.split(",");
                    final User user = new User();
                    user.setName(data[0]);
                    user.setEmail(data[1]);
                    user.setGender(data[2]);
                    users.add(user);
                }
                return users;
            }
        } catch (final IOException e) {
            throw new Exception("Failed to parse CSV file {}", e);
        }
    }
}
