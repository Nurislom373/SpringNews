package org.khasanof.apacheflink;

import org.apache.flink.api.java.DataSet;
import org.apache.flink.api.java.ExecutionEnvironment;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Nurislom
 * @see org.khasanof.apacheflink
 * @since 4/28/2024 6:36 AM
 */
public class DataSetTransformationAPI {

    public static void main(String[] args) throws Exception {
        ExecutionEnvironment env = ExecutionEnvironment.getExecutionEnvironment();

        DataSet<Integer> amounts = env.fromElements(1, 29, 40, 50);
        int threshold = 30;
        List<Integer> collect = amounts
                .filter(a -> a > threshold)
                .reduce(Integer::sum)
                .collect();

        System.out.println("collect = " + collect);

        assertThat(collect.get(0)).isEqualTo(90);
    }
}
