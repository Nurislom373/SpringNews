package org.khasanof.springexpressionlanguage.listAndMapObjects;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Author: Nurislom
 * <br/>
 * Date: 2/14/2023
 * <br/>
 * Time: 5:57 PM
 * <br/>
 * Package: org.khasanof.springexpressionlanguage.listAndMapObjects
 */
@Component
public class WorkersHolderSPL {

    @Value("#{workersHolder.salaryByWorkers['John']}") // 35000
    public Integer johnSalary;

    @Value("#{workersHolder.salaryByWorkers['George']}") // 14000
    public Integer georgeSalary;

    @Value("#{workersHolder.salaryByWorkers['Susie']}") // 47000
    public Integer susieSalary;

    @Value("#{workersHolder.workers[0]}") // John
    public String firstWorker;

    @Value("#{workersHolder.workers[3]}") // George
    public String lastWorker;

    @Value("#{workersHolder.workers.size()}") // 4
    public Integer numberOfWorkers;
}
