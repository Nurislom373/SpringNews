package org.khasanof.ratelimitingwithspring.test;

import lombok.RequiredArgsConstructor;
import org.khasanof.ratelimitingwithspring.domain.AuthUserEntity;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.Optional;

/**
 * Author: Nurislom
 * <br/>
 * Date: 4/11/2023
 * <br/>
 * Time: 1:51 PM
 * <br/>
 * Package: org.khasanof.ratelimitingwithspring.test
 */
@Service
@RequiredArgsConstructor
public class AuthUserService {

    private final AuthUserRepository repository;

    public void create(AuthUserEntity user) {
        if (user != null) {
            repository.save(user);
        }
    }

    public String login(AuthRequestDTO dto) {
        if (dto != null) {
            Optional<AuthUserEntity> optional = repository.findByUsername(dto.getUsername());
            if (optional.isPresent()) {
                AuthUserEntity authUser = optional.get();
                if (authUser.getPassword().equals(dto.getPassword())) {
                    String encode = base64Encode(authUser);
                    authUser.setApiKey(encode);
                    repository.save(authUser);
                    return encode;
                }
            }
        }
        throw new RuntimeException();
    }

    private String base64Encode(AuthUserEntity user) {
        return Base64.getEncoder().encodeToString(user.getUsername().getBytes());
    }

}
