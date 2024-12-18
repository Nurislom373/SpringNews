package org.khasanof.opencsv;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.khasanof.opencsv.dto.RecipientCsvDTO;
import org.khasanof.opencsv.service.CsvToBeanService;
import org.springframework.boot.test.context.SpringBootTest;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Timeout(value = 1, unit = TimeUnit.DAYS)
@SpringBootTest(classes = {OpenCsvApplication.class})
class OpenCsvApplicationTests {

    /**
     *
     */
    @Test
    void firstTestLoadCsvDataShouldSuccess() throws Exception {
        Path path = Paths.get(ClassLoader.getSystemResource("recipients.csv").toURI());
        List<RecipientCsvDTO> recipients = CsvToBeanService.beanBuilderExample(path, RecipientCsvDTO.class);

        assertNotNull(recipients);
        assertEquals(recipients.size(), 919);
    }

}
