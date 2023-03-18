package org.khasanof.webflux;

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscription;
import reactor.core.publisher.BaseSubscriber;

/**
 * Author: Nurislom
 * <br/>
 * Date: 3/18/2023
 * <br/>
 * Time: 8:02 PM
 * <br/>
 * Package: org.khasanof.webflux
 */
public class SampleSubscriber<T> extends BaseSubscriber<T> {

    /**
     * Hook for further processing of onSubscribe's Subscription. Implement this method
     * to call {@link #request(long)} as an initial request. Values other than the
     * unbounded {@code Long.MAX_VALUE} imply that you'll also call request in
     * {@link #hookOnNext(Object)}.
     * <p> Defaults to request unbounded Long.MAX_VALUE as in {@link #requestUnbounded()}
     *
     * @param subscription the subscription to optionally process
     */
    @Override
    protected void hookOnSubscribe(Subscription subscription) {
        System.out.println("Subscribed");
        request(1);
    }

    /**
     * Hook for processing of onNext values. You can call {@link #request(long)} here
     * to further request data from the source {@link Publisher} if
     * the {@link #hookOnSubscribe(Subscription) initial request} wasn't unbounded.
     * <p>Defaults to doing nothing.
     *
     * @param value the emitted value to process
     */
    @Override
    protected void hookOnNext(T value) {
        System.out.println(value);
        request(1);
    }
}
