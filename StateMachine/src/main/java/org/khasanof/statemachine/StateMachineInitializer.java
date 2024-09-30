package org.khasanof.statemachine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.statemachine.StateMachine;

/**
 * @author Nurislom
 * @see org.khasanof.statemachine
 * @since 9/30/2024 11:31 PM
 */
public class StateMachineInitializer implements CommandLineRunner {

    @Autowired
    StateMachine<String, String> stateMachine;

    /**
     *
     * @param args
     * @throws Exception
     */
    @Override
    public void run(String... args) throws Exception {
        stateMachine.start();
        stateMachine.sendEvent("E1");
    }
}
