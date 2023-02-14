package org.khasanof.springexpressionlanguage.listAndMapObjects;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Author: Nurislom
 * <br/>
 * Date: 2/14/2023
 * <br/>
 * Time: 5:50 PM
 * <br/>
 * Package: org.khasanof.springexpressionlanguage.listAndMapObjects
 */
@Component("workersHolder")
public class WorkersHolder {

    private List<String> workers = new ArrayList<>();

    private Map<String, Integer> salaryByWorkers = new HashMap<>();

    public WorkersHolder() {
        workers.add("John");
        workers.add("Susie");
        workers.add("Alex");
        workers.add("George");

        salaryByWorkers.put("John", 35000);
        salaryByWorkers.put("Susie", 47000);
        salaryByWorkers.put("Alex", 12000);
        salaryByWorkers.put("George", 14000);
    }

    public List<String> getWorkers() {
        return workers;
    }

    public void setWorkers(List<String> workers) {
        this.workers = workers;
    }

    public Map<String, Integer> getSalaryByWorkers() {
        return salaryByWorkers;
    }

    public void setSalaryByWorkers(Map<String, Integer> salaryByWorkers) {
        this.salaryByWorkers = salaryByWorkers;
    }
}
