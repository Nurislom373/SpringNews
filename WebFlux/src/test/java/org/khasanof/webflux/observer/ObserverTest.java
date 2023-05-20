package org.khasanof.webflux.observer;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

/**
 * Author: Nurislom
 * <br/>
 * Date: 20.05.2023
 * <br/>
 * Time: 23:40
 * <br/>
 * Package: org.khasanof.webflux.observer
 */
public class ObserverTest {

    @Test
    public void observersHandleEventsFromSubject() {
        // given
        Subject<String> subject = new ConcreteSubject();
        Observer<String> observerA = Mockito.spy(new ConcreteObserverA());
        Observer<String> observerB = Mockito.spy(new ConcreteObserverB());

        // when
        subject.notifyObservers("No listeners");
        subject.registerObserver(observerA);
        subject.notifyObservers("Message for A");
        subject.registerObserver(observerB);
        subject.notifyObservers("Message for A & B");
        subject.notifyObservers("Message for A & B");
        subject.unregisterObserver(observerA);
        subject.notifyObservers("Message for B");
        subject.unregisterObserver(observerB);
        subject.notifyObservers("No listeners");

        // then
        Mockito.verify(observerA, Mockito.times(1)).observe("Message for A");
        Mockito.verify(observerA, Mockito.times(2)).observe("Message for A & B");
        Mockito.verifyNoMoreInteractions(observerA);
        Mockito.verify(observerB, Mockito.times(2)).observe("Message for A & B");
        Mockito.verify(observerB, Mockito.times(1)).observe("Message for B");
        Mockito.verifyNoMoreInteractions(observerB);
    }


}
