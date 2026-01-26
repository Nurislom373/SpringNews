package org.khasanof.repository;

import org.khasanof.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Nurislom
 * @see org.khasanof.repository
 * @since 11/4/25
 */
@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
