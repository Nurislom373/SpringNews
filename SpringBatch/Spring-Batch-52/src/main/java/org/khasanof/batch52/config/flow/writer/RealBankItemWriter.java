package org.khasanof.batch52.config.flow.writer;

import lombok.extern.slf4j.Slf4j;
import org.khasanof.batch52.model.BankCsvDTO;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;

/**
 * @author Nurislom
 * @see org.khasanof.batch52.config.flow.writer
 * @since 12/31/2024 9:53 PM
 */
@Slf4j
public class RealBankItemWriter implements ItemWriter<BankCsvDTO> {

    /**
     *
     * @param chunk
     * @throws Exception
     */
    @Override
    public void write(Chunk<? extends BankCsvDTO> chunk) throws Exception {
        log.debug("Request to write - size: {}, items: {}", chunk.getItems().size(), chunk.getItems());
    }
}
