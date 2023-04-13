package org.khasanof.ratelimitingwithspring.test;

import org.khasanof.ratelimitingwithspring.domain.AuthUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Author: Nurislom
 * <br/>
 * Date: 4/11/2023
 * <br/>
 * Time: 2:00 PM
 * <br/>
 * Package: org.khasanof.ratelimitingwithspring.test
 */
@Repository
public interface AuthUserRepository extends JpaRepository<AuthUserEntity, Long> {

    Optional<AuthUserEntity> findByUsername(String username);

    Boolean existsByApiKey(String apiKey);
}
