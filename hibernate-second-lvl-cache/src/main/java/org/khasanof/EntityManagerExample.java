package org.khasanof;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;
import org.khasanof.domain.User;

/**
 * @author Nurislom
 * @see org.khasanof
 * @since 11/5/25
 */
@RequiredArgsConstructor
public class EntityManagerExample {

    private final EntityManager entityManager;

    /**
     *
     * @param id
     * @return
     */
    public User getUser(Long id) {
        TypedQuery<User> query = entityManager.createQuery("SELECT u FROM User u WHERE u.id = :id", User.class);
        query.setHint("org.hibernate.cacheable", true);
        query.setParameter("id", id);
        return query.getSingleResult();
    }
}
