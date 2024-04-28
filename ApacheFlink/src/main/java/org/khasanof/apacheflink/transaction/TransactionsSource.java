package org.khasanof.apacheflink.transaction;

import com.github.javafaker.Faker;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.apache.flink.streaming.api.functions.source.SourceFunction;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author Nurislom
 * @see org.khasanof.apacheflink.transaction
 * @since 4/28/2024 7:47 AM
 */
public class TransactionsSource implements SourceFunction<Transaction> {

    private long count;
    private volatile boolean isRunning = true;

    @Override
    public void run(SourceContext<Transaction> sourceContext) {
        while (isRunning && count < 1000) {
            count++;
            sourceContext.collect(createFakeTransaction());
        }
    }

    @Override
    public void cancel() {
        isRunning = false;
    }

    private Transaction createFakeTransaction() {
        return Transaction.builder()
                .type("P2P")
                .currency("usd")
                .status("ACTIVE")
                .id(String.valueOf(count))
                .timestamp(LocalDateTime.now())
                .sourceAccountId(RandomStringUtils.random(10, false, true))
                .destinationAccountId(RandomStringUtils.random(10, false, true))
                .amount(BigDecimal.valueOf(RandomUtils.nextDouble()))
                .build();
    }
}
