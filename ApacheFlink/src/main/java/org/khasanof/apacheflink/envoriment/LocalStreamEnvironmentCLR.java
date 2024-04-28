package org.khasanof.apacheflink.envoriment;

import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * @author Nurislom
 * @see org.khasanof.apacheflink.envoriment
 * @since 4/28/2024 7:00 AM
 */
//@Component
public class LocalStreamEnvironmentCLR implements ApplicationRunner {

    public static final int TIMEOUT_MILLIS = 5000;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        try (StreamExecutionEnvironment env = StreamExecutionEnvironment.createLocalEnvironment()) {
            env.setBufferTimeout(TIMEOUT_MILLIS);

            env.generateSequence(1, 100)
                    .map(new CustomMapperToString())
                    .print();

            env.execute();
        }
    }

    public static class CustomMapperToString implements MapFunction<Long, String> {

        @Override
        public String map(Long num) {
            return String.valueOf(num);
        }
    }
}
