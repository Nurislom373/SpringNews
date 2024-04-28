package org.khasanof.apacheflink.transaction;

import org.apache.flink.api.common.restartstrategy.RestartStrategies;
import org.apache.flink.api.common.time.Time;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.sink.SinkFunction;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @author Nurislom
 * @see org.khasanof.apacheflink.transaction
 * @since 4/28/2024 7:46 AM
 */
@Component
public class TransactionStreamEnvironmentCLR implements CommandLineRunner {

    final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

    @Override
    public void run(String... args) throws Exception {
        env.setRestartStrategy(RestartStrategies.fixedDelayRestart(
                3, // number of restart attempts
                Time.of(10, TimeUnit.SECONDS) // delay
        ));

        SingleOutputStreamOperator<Transaction> transactions = env
                .addSource(new TransactionsSource())
                .name("transactions");

        SingleOutputStreamOperator<Alert> alerts = transactions.keyBy(Transaction::getId)
                .process(new FraudDetector())
                .name("fraud-detector");

        alerts.addSink(new SinkFunction<>() {
                    @Override
                    public void invoke(Alert value) throws Exception {
                        System.out.println("value = " + value);
                    }
                })
                .name("send-alerts");

        env.execute();
    }
}
