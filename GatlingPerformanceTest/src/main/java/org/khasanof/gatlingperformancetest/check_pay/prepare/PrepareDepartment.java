package org.khasanof.gatlingperformancetest.check_pay.prepare;

import org.khasanof.gatlingperformancetest.check_pay.dto.department.DepartmentDTO;
import org.khasanof.gatlingperformancetest.check_pay.wss.WSS;

import java.util.List;

/**
 * @author Nurislom
 * @see org.khasanof.gatlingperformancetest.check_pay.prepare
 * @since 9/4/2023 11:51 AM
 */
public class PrepareDepartment extends AbstractPrepare implements GenericPrepare<DepartmentDTO> {

    private Integer count;

    @Override
    public void prepare(WSS wss) {
//        wss.sendMessage();
    }

    @Override
    public DepartmentDTO getRandom() {
        return null;
    }

    @Override
    public List<DepartmentDTO> getAll() {
        return null;
    }
}
