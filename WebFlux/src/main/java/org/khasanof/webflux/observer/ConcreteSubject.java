package org.khasanof.webflux.observer;

import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * Author: Nurislom
 * <br/>
 * Date: 20.05.2023
 * <br/>
 * Time: 23:36
 * <br/>
 * Package: org.khasanof.webflux.observer
 */
public class ConcreteSubject implements Subject<String> {

    private final Set<Observer<String>> observers = new CopyOnWriteArraySet<>();

    @Override
    public void registerObserver(Observer<String> observer) {
        observers.add(observer);
    }

    @Override
    public void unregisterObserver(Observer<String> observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(String event) {
        observers.forEach(notify -> notify.observe(event));
    }
}
