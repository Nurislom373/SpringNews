package org.khasanof.resource;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;
import org.hibernate.SessionFactory;
import org.hibernate.stat.Statistics;
import org.khasanof.domain.Order;
import org.khasanof.domain.User;
import org.khasanof.repository.OrderRepository;
import org.khasanof.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Nurislom
 * @see org.khasanof.resource
 * @since 11/4/25
 */
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MainResource {

    private final EntityManager entityManager;
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;
    private final EntityManagerFactory entityManagerFactory;

    /**
     *
     * @return
     */
    @GetMapping("/users")
    public ResponseEntity<List<User>> getUsers() {
        List<User> users = userRepository.findAll();
        checkCacheStats();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    /**
     *
     * @return
     */
    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        User user = userRepository.findTop1ById(id);
        checkCacheStats();
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    /**
     *
     * @return
     */
    @GetMapping
    public ResponseEntity<List<Order>> getOrders() {
        List<Order> orders = orderRepository.findAll();
        checkCacheStats();
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    /**
     *
     */
    public void checkCacheStats() {
        SessionFactory sessionFactory = entityManagerFactory.unwrap(SessionFactory.class);
        Statistics stats = sessionFactory.getStatistics();
        stats.setStatisticsEnabled(true);

        System.out.println("Second Level Cache Hit Count: " + stats.getSecondLevelCacheHitCount());
        System.out.println("Second Level Cache Miss Count: " + stats.getSecondLevelCacheMissCount());

        TypedQuery<User> query = entityManager.createQuery("SELECT u FROM User u WHERE u.name = :name", User.class);
        query.setHint("org.hibernate.cacheable", true);
        User user = query.getSingleResult();
    }
}
