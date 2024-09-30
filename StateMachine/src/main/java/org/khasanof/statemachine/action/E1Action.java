package org.khasanof.statemachine.action;

import lombok.extern.slf4j.Slf4j;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;

/**
 * @author Nurislom
 * @see org.khasanof.statemachine.action
 * @since 9/30/2024 11:00 PM
 */
@Slf4j
public class E1Action implements Action<String, String> {

    /**
     *
     * @param stateContext
     */
    @Override
    public void execute(StateContext<String, String> stateContext) {
        log.info("Request to execute: ctx: {}", stateContext);
        stateContext.getExtendedState()
                .getVariables()
                .put("APPROVE", true);
    }
}
