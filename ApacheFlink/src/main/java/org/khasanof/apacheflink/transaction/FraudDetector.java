package org.khasanof.apacheflink.transaction;

import org.apache.flink.api.common.functions.OpenContext;
import org.apache.flink.api.common.state.ValueState;
import org.apache.flink.api.common.state.ValueStateDescriptor;
import org.apache.flink.api.common.typeinfo.Types;
import org.apache.flink.streaming.api.functions.KeyedProcessFunction;
import org.apache.flink.util.Collector;

import java.math.BigDecimal;

/**
 * @author Nurislom
 * @see org.khasanof.apacheflink.transaction
 * @since 4/28/2024 9:03 AM
 */
public class FraudDetector extends KeyedProcessFunction<String, Transaction, Alert> {

    private static final double SMALL_AMOUNT = 1.00;
    private static final double LARGE_AMOUNT = 500.00;
    private static final long ONE_MINUTE = 60 * 1000;

    private transient ValueState<Boolean> flagState;

    @Override
    public void open(OpenContext openContext) throws Exception {
        ValueStateDescriptor<Boolean> flagDescriptor = new ValueStateDescriptor<>(
                "flag",
                Types.BOOLEAN);
        flagState = getRuntimeContext().getState(flagDescriptor);
    }

    @Override
    public void processElement(Transaction transaction, KeyedProcessFunction<String, Transaction, Alert>.Context ctx, Collector<Alert> out) throws Exception {
        // Get the current state for the current key
        Boolean lastTransactionWasSmall = flagState.value();

        // Check if the flag is set
        if (lastTransactionWasSmall != null) {
            if (transaction.getAmount().compareTo(BigDecimal.valueOf(LARGE_AMOUNT)) > 0) {
                // Output an alert downstream
                Alert alert = new Alert();
                alert.setId(transaction.getId());

                out.collect(alert);
            }

            // Clean up our state
            flagState.clear();
        }

        if (transaction.getAmount().compareTo(BigDecimal.valueOf(SMALL_AMOUNT)) < 0) {
            // Set the flag to true
            flagState.update(true);
        }
    }
}
