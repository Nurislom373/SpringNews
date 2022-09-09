package org.khasanof.springbatch.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "COMPANY_INFO")
public class Company {
    @Id
    @Column(name = "COMPANY_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "NAME")
    private String name;
    @Column(name = "OWNER_NAME")
    private String ownerName;
    @Column(name = "EMPLOYEE_COUNT")
    private Integer employeeCount;
    @Column(name = "CREATED_YEAR")
    private Short createdYear;
}
