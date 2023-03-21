package org.khasanof.springdatajpa.repository;

import jakarta.annotation.Nullable;
import jakarta.transaction.Transactional;
import org.khasanof.springdatajpa.domain.Company;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Author: Nurislom
 * <br/>
 * Date: 3/20/2023
 * <br/>
 * Time: 2:51 PM
 * <br/>
 * Package: org.khasanof.springdatajpa.repository
 */
@Repository
@Transactional
public interface CompanyRepository extends JpaRepository<Company, Long>,
        JpaSpecificationExecutor<Company>, QueryByExampleExecutor<Company> {

    @Nullable
    Company findByEmail(@Nullable String email);

    boolean existsByEmail(String email);

    @Modifying
    void deleteByEmail(String email);

    /*
        To make this process easier, you can insert custom comments into almost any JPA operation, whether its a query
        or other operation by applying the @Meta annotation.
     */
    @Modifying
    @Meta(comment = "This Method Delete All Company With CreatedAt Between")
    void deleteAllByCreatedAtBetween(LocalDateTime createdAt, LocalDateTime createdAt2);

    List<Company> findAllByEmployeeCountEquals(Long employeeCount);

    List<Company> findAllByEmployeeCountGreaterThan(Long employeeCount);

    List<Company> findAllByEmployeeCountGreaterThan(Long employeeCount, Sort sort);

    Page<Company> findAllByEmployeeCountGreaterThan(Long employeeCount, Pageable pageable);

    List<Company> findAllByEmployeeCountLessThan(Long employeeCount);

    List<Company> findAllByEmployeeCountLessThan(Long employeeCount, Sort sort);

    Slice<Company> findAllByEmployeeCountLessThan(Long employeeCount, Pageable pageable);

    List<Company> findAllByCreatedAtIsBetween(LocalDateTime createdAt, LocalDateTime createdAt2);

    List<Company> findAllByCreatedAtIsBetween(LocalDateTime createdAt, LocalDateTime createdAt2, Sort sort);

    Page<Company> findAllByCreatedAtIsBetween(LocalDateTime createdAt, LocalDateTime createdAt2, Pageable pageable);

    @Query(value = "select c from company c where lower(c.name) = lower(:name)", nativeQuery = true)
    Company findByNameFirst(@Param("name") String name);

    @Query(value = "select c from company c where lower(c.name) = lower(?1)", nativeQuery = true)
    Company findByNameSecond(String name);

    @Query(value = "select * from company order by id",
            countQuery = "select count(*) from company", nativeQuery = true)
    Page<Company> findAllByCompanyWithPagination(Pageable pageable);

}
