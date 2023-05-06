package org.khasanof.junit5spring.extensions;

import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.BeforeTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;

/**
 * Author: Nurislom
 * <br/>
 * Date: 06.05.2023
 * <br/>
 * Time: 21:18
 * <br/>
 * Package: org.khasanof.junit5spring.extensions
 */
public class TimingExtension implements BeforeTestExecutionCallback, AfterTestExecutionCallback {

    private static final Logger logger = LoggerFactory.getLogger(TimingExtension.class.getName());

    private static final String START_TIME = "start time";

    @Override
    public void afterTestExecution(ExtensionContext extensionContext) throws Exception {
        Method requiredTestMethod = extensionContext.getRequiredTestMethod();
        long startTime = getStore(extensionContext).remove(START_TIME, long.class);
        long duration = System.currentTimeMillis() - startTime;
        logger.info(String.format("Method [%s] took %s ms.", requiredTestMethod.getName(), duration));
    }

    @Override
    public void beforeTestExecution(ExtensionContext extensionContext) throws Exception {
        getStore(extensionContext).put(START_TIME, System.currentTimeMillis());
    }

    private ExtensionContext.Store getStore(ExtensionContext context) {
        return context.getStore(ExtensionContext.Namespace.create(getClass(), context.getRequiredTestMethod()));
    }
}
