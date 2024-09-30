package org.khasanof.statemachine;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateMachine;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

/**
 * @author Nurislom
 * @see org.khasanof.statemachine
 * @since 9/30/2024 11:06 PM
 */
@Slf4j
@Component
public class StateMachineReactiveInitializer implements CommandLineRunner {

    @Autowired
    StateMachine<String, String> stateMachine;

    /**
     * @param args
     * @throws Exception
     */
    @Override
    public void run(String... args) throws Exception {
        stateMachine.startReactively()
                .subscribe();

        stateMachine.sendEvent(
                        Mono.just(MessageBuilder.withPayload("E1")
                                .build())
                ).doOnNext(stateMachineEventResult -> log.info("State Machine Result: {}", stateMachineEventResult))
                .publishOn(Schedulers.boundedElastic())
                .doOnNext(stateMachineEventResult -> {
                    stateMachine.sendEvent(
                            Mono.just(MessageBuilder.withPayload("E2")
                                    .build())
                    ).subscribe();
                })
                .doOnNext(stateMachineEventResult -> log.info("State Machine Result: {}", stateMachineEventResult))
                .doOnNext(stateMachineEventResult -> {
                    stateMachine.sendEvent(
                            Mono.just(MessageBuilder.withPayload("E3")
                                    .build())
                    ).subscribe();
                })
                .doOnNext(stateMachineEventResult -> log.info("State Machine Result: {}", stateMachineEventResult))
                .subscribe();
    }
}
