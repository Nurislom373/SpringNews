package org.khasanof.junit5spring.argumentAggregation;

import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.aggregator.ArgumentsAggregationException;
import org.junit.jupiter.params.aggregator.ArgumentsAggregator;

import java.time.LocalDate;

/**
 * Author: Nurislom
 * <br/>
 * Date: 17.05.2023
 * <br/>
 * Time: 9:47
 * <br/>
 * Package: org.khasanof.junit5spring.argumentAggregation
 */
public class PersonAggregator implements ArgumentsAggregator {

    @Override
    public Object aggregateArguments(ArgumentsAccessor accessor, ParameterContext context) throws ArgumentsAggregationException {
        return new ArgumentAggregationTest.Person(accessor.getString(0),
                accessor.getString(1),
                accessor.get(2, ArgumentAggregationTest.Gender.class),
                accessor.get(3, LocalDate.class));
    }
}
