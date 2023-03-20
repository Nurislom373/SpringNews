package org.khasanof.springdatajpa.runner;

import lombok.RequiredArgsConstructor;
import org.khasanof.springdatajpa.domain.Company;
import org.springframework.data.util.Streamable;

import java.util.Iterator;

/**
 * Author: Nurislom
 * <br/>
 * Date: 3/20/2023
 * <br/>
 * Time: 3:26 PM
 * <br/>
 * Package: org.khasanof.springdatajpa.runner
 */
@RequiredArgsConstructor
public class Companies implements Streamable<Company> {

    private final Streamable<Company> streamable;

    public Long employeeBetweenCount(Long from, Long to) {
        return streamable.filter(f -> f.getEmployeeCount() >= from && f.getEmployeeCount() <= to)
                .get().count();
    }

    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator<Company> iterator() {
        return streamable.iterator();
    }
}
