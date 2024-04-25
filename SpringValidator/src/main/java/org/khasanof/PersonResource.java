package org.khasanof;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Nurislom
 * @see org.khasanof
 * @since 4/25/2024 10:05 AM
 */
@Slf4j
@RestController
public class PersonResource {

    /**
     *
     * @param person
     * @return
     */
    @PostMapping("/api/person")
    public ResponseEntity<Person> create(@RequestBody Person person) {
        log.info("REQUEST to create : {}", person);
        return ResponseEntity.ok()
                .build();
    }
}
