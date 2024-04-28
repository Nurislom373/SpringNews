package org.khasanof.apacheflink.map;

import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.operators.DataSource;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Nurislom
 * @see org.khasanof.apacheflink.map
 * @since 4/28/2024 7:07 AM
 */
//@Component
public class DataStreamMapCLR implements ApplicationRunner {

    private final ExecutionEnvironment env = ExecutionEnvironment.getExecutionEnvironment();

    @Override
    public void run(ApplicationArguments args) throws Exception {
        DataSource<Person> dataSource = env.fromCollection(
                List.of(
                        new Person(21, "John"),
                        new Person(74, "Bob"),
                        new Person(31, "Tommy")
                )
        );

        List<Integer> ages = dataSource.map(Person::getAge)
                .collect();

        assertThat(ages).hasSize(3);
        assertThat(ages).contains(21, 74);
    }
}
