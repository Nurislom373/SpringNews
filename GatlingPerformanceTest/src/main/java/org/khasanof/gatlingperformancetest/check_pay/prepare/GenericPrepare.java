package org.khasanof.gatlingperformancetest.check_pay.prepare;

import java.util.List;

/**
 * @author Nurislom
 * @see org.khasanof.gatlingperformancetest.check_pay.prepare
 * @since 9/4/2023 11:57 AM
 */
public interface GenericPrepare<T> {

    T getRandom();

    List<T> getAll();

}
