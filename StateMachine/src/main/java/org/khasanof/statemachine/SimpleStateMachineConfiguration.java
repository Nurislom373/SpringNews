package org.khasanof.statemachine;

import lombok.extern.slf4j.Slf4j;
import org.khasanof.statemachine.action.E1Action;
import org.khasanof.statemachine.action.E3Guard;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.StateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import org.springframework.statemachine.listener.StateMachineListener;
import org.springframework.statemachine.state.State;
import org.springframework.statemachine.transition.Transition;

import java.util.Arrays;
import java.util.HashSet;

/**
 * @author Nurislom
 * @see org.khasanof.statemachine
 * @since 9/30/2024 10:54 PM
 */
@Slf4j
@Configuration
@EnableStateMachine
public class SimpleStateMachineConfiguration extends StateMachineConfigurerAdapter<String, String> {

    /**
     *
     * @param states
     * @throws Exception
     */
    @Override
    public void configure(StateMachineStateConfigurer<String, String> states) throws Exception {
        states.withStates()
                .initial("SI")
                .end("SF")
                .states(new HashSet<>(Arrays.asList("S1", "S2", "S3")));
    }

    /**
     *
     * @param config
     * @throws Exception
     */
    @Override
    public void configure(StateMachineConfigurationConfigurer<String, String> config) throws Exception {
        config.withConfiguration()
                .listener(new StateMachineListener<>() {
                    @Override
                    public void stateChanged(State<String, String> from, State<String, String> to) {
                        System.out.println("State changed from " + getId(from) + " to " + getId(to));
                    }

                    private String getId(State<String, String> from) {
                        if (from == null) {
                            return null;
                        }
                        return from.getId();
                    }

                    @Override
                    public void stateEntered(State<String, String> state) {

                    }

                    @Override
                    public void stateExited(State<String, String> state) {

                    }

                    @Override
                    public void eventNotAccepted(Message<String> message) {

                    }

                    @Override
                    public void transition(Transition<String, String> transition) {

                    }

                    @Override
                    public void transitionStarted(Transition<String, String> transition) {

                    }

                    @Override
                    public void transitionEnded(Transition<String, String> transition) {

                    }

                    @Override
                    public void stateMachineStarted(StateMachine<String, String> stateMachine) {

                    }

                    @Override
                    public void stateMachineStopped(StateMachine<String, String> stateMachine) {

                    }

                    @Override
                    public void stateMachineError(StateMachine<String, String> stateMachine, Exception e) {

                    }

                    @Override
                    public void extendedStateChanged(Object o, Object o1) {

                    }

                    @Override
                    public void stateContext(StateContext<String, String> stateContext) {

                    }
                });
    }

    /**
     *
     * @param transitions
     * @throws Exception
     */
    @Override
    public void configure(StateMachineTransitionConfigurer<String, String> transitions) throws Exception {
        transitions.withExternal()
                .source("SI").target("S1").event("E1").action(new E1Action())
                .and()
                .withExternal()
                .source("S1").target("S2").event("E2")
                .action(context -> log.info("Transitioning from S1 to S2"))
                .and()
                .withExternal()
                .source("S2").target("SF").event("E3")
                .guard(new E3Guard())
                .action(context -> log.info("Transitioning from S2 to SF"));
    }
}
