package org.khasanof.repository;

import jakarta.persistence.QueryHint;
import org.khasanof.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * @author Nurislom
 * @see org.khasanof.repository
 * @since 11/4/25
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u WHERE u.id = :id")
    @QueryHints({@QueryHint(name = "org.hibernate.cacheable", value = "true")})
    User findTop1ById(@Param("id") Long id);
}
