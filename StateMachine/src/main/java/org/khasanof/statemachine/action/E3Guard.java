package org.khasanof.statemachine.action;

import lombok.extern.slf4j.Slf4j;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.guard.Guard;

import java.util.Map;

/**
 * @author Nurislom
 * @see org.khasanof.statemachine.action
 * @since 9/30/2024 11:36 PM
 */
@Slf4j
public class E3Guard implements Guard<String, String> {

    /**
     *
     * @param context the state context
     * @return
     */
    @Override
    public boolean evaluate(StateContext<String, String> context) {
        log.info("Request to evaluate: {}", context.getEvent());

        Map<Object, Object> variables = context.getExtendedState()
                .getVariables();

        return variables.containsKey("APPROVE");
    }
}
