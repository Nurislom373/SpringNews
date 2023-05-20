package org.khasanof.webflux.observer;

/**
 * Author: Nurislom
 * <br/>
 * Date: 20.05.2023
 * <br/>
 * Time: 23:36
 * <br/>
 * Package: org.khasanof.webflux.observer
 */
public class ConcreteObserverB implements Observer<String> {

    @Override
    public void observe(String event) {
        System.out.println("Observer B : " + event);
    }
}
