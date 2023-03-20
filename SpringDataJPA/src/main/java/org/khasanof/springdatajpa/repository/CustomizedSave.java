package org.khasanof.springdatajpa.repository;

/**
 * Author: Nurislom
 * <br/>
 * Date: 3/20/2023
 * <br/>
 * Time: 3:41 PM
 * <br/>
 * Package: org.khasanof.springdatajpa.repository
 */
public interface CustomizedSave<T> {

    // Override And Write Your Implementation
    <S extends T> S save(S entity);

}
