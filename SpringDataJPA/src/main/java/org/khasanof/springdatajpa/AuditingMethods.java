package org.khasanof.springdatajpa;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreRemove;
import jakarta.persistence.PreUpdate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


/**
 * Author: Nurislom
 * <br/>
 * Date: 3/20/2023
 * <br/>
 * Time: 4:05 PM
 * <br/>
 * Package: org.khasanof.springdatajpa
 */
public abstract class AuditingMethods {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    @PrePersist
    protected abstract void onPrePersist();

    @PreUpdate
    protected abstract void onPreUpdate();

    @PreRemove
    protected abstract  void onPreRemove();
}
