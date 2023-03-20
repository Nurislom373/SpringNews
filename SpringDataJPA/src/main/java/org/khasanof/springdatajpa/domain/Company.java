package org.khasanof.springdatajpa.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.khasanof.springdatajpa.AuditingMethods;

import java.time.LocalDateTime;

/**
 * Author: Nurislom
 * <br/>
 * Date: 3/20/2023
 * <br/>
 * Time: 2:44 PM
 * <br/>
 * Package: org.khasanof.springdatajpa.domain
 */
@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@Table(name = "company", uniqueConstraints = {
        @UniqueConstraint(columnNames = "id")
})
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 120, nullable = false)
    private String name;

    @Column(name = "email", length = 250, nullable = false, unique = true)
    private String email;

    @Column(name = "employee_count", nullable = false)
    private Long employeeCount;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public Company(String name, String email, Long employeeCount) {
        this.name = name;
        this.email = email;
        this.employeeCount = employeeCount;
    }

    @PrePersist
    protected void onPrePersist() {
        System.out.println("INSERT");
    }
}
