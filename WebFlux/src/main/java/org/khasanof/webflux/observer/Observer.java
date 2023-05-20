package org.khasanof.webflux.observer;

/**
 * Author: Nurislom
 * <br/>
 * Date: 20.05.2023
 * <br/>
 * Time: 23:33
 * <br/>
 * Package: org.khasanof.webflux.observer
 */
public interface Observer<T> {

    void observe(T event);

}
