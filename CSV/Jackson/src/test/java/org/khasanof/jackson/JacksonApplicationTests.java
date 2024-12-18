package org.khasanof.jackson;

import org.junit.jupiter.api.Test;
import org.khasanof.jackson.dto.RecipientCsvDTO;
import org.khasanof.jackson.service.CsvLoaderService;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = {JacksonApplication.class})
class JacksonApplicationTests {

    /**
     *
     */
    @Test
    void firstTestLoadCsvDataShouldSuccess() {
        List<RecipientCsvDTO> recipients = CsvLoaderService.loadObjectList(RecipientCsvDTO.class, "recipients.csv");

        assertNotNull(recipients);
        assertEquals(recipients.size(), 918);
    }
}
