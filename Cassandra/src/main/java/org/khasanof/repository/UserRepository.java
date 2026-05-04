package org.khasanof.repository;

import org.khasanof.domain.User;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Nurislom
 * @see org.khasanof.repository
 * @since 5/4/26
 */
@Repository
public interface UserRepository extends CassandraRepository<User, String> {
}
