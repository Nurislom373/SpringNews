package org.khasanof.webflux.observer;

/**
 * Author: Nurislom
 * <br/>
 * Date: 20.05.2023
 * <br/>
 * Time: 23:34
 * <br/>
 * Package: org.khasanof.webflux.observer
 */
public interface Subject<T> {

    void registerObserver(Observer<T> observer);
    void unregisterObserver(Observer<T> observer);
    void notifyObservers(T event);


}
