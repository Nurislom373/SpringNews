package org.khasanof.springdatajpa.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

/**
 * Author: Nurislom
 * <br/>
 * Date: 3/21/2023
 * <br/>
 * Time: 5:44 PM
 * <br/>
 * Package: org.khasanof.springdatajpa.domain
 */
@Entity
@Getter
@Setter
@ToString
@DynamicUpdate
@NoArgsConstructor
public class Address {

    @Id
    @GeneratedValue
    private Long id;
    private String city;
    private String street;
    private String zipCode;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public Address(String city, String street, String zipCode) {
        this.city = city;
        this.street = street;
        this.zipCode = zipCode;
    }

    @PrePersist
    protected void onPrePersist() {
        System.out.println("INSERT");
    }

    @PreUpdate
    protected void onPreUpdate() {
        System.out.println("UPDATE");
    }

    @PreRemove
    protected void onPreRemove() {
        System.out.println("REMOVE");
    }
}
