package org.khasanof.batch52.config.flow.processor;

import lombok.extern.slf4j.Slf4j;
import org.khasanof.batch52.model.BankCsvDTO;
import org.springframework.batch.item.ItemProcessor;

/**
 * @author Nurislom
 * @see org.khasanof.batch52.config.flow.processor
 * @since 12/31/2024 9:50 PM
 */
@Slf4j
public class RealBankItemProcessor implements ItemProcessor<BankCsvDTO, BankCsvDTO> {

    /**
     *
     * @param item
     * @return
     * @throws Exception
     */
    @Override
    public BankCsvDTO process(BankCsvDTO item) throws Exception {
        log.debug("Request to process : {}", item);
        return item;
    }
}
