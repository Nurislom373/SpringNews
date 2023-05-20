package org.khasanof.webflux.observer;

/**
 * Author: Nurislom
 * <br/>
 * Date: 20.05.2023
 * <br/>
 * Time: 23:35
 * <br/>
 * Package: org.khasanof.webflux.observer
 */
public class ConcreteObserverA implements Observer<String> {

    @Override
    public void observe(String event) {
        System.out.println("Observer A : " + event);
    }
}
