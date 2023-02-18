package org.khasanof.springaop.config.aspc;

import org.springframework.stereotype.Component;

/**
 * Author: Nurislom
 * <br/>
 * Date: 2/18/2023
 * <br/>
 * Time: 11:27 AM
 * <br/>
 * Package: org.khasanof.springaop.config.aspc
 */
@Component
public class EmployeeManager {

    public void getEmployeeById(Integer id) {
        System.out.println("Method getEmployeeById() called");
    }

    public void hiSay() {
        System.out.println("Boom Geeks");
    }
}
