package org.khasanof.poiji;

import com.poiji.bind.Poiji;
import com.poiji.exception.PoijiExcelType;
import com.poiji.option.PoijiOptions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.khasanof.poiji.dto.RecipientExcelDTOV1;
import org.khasanof.poiji.dto.RecipientExcelDTOV2;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Nurislom
 * @see org.khasanof.poiji
 * @since 12/18/2024 11:08 PM
 */
@Timeout(value = 1, unit = TimeUnit.DAYS)
@SpringBootTest(classes = {PoijiApplication.class})
public class LoadSimpleExcelDataTest {

    /**
     * загружать .xlsx файл по @ExcelSheetName аннотатция
     */
    @Test
    void firstTestLoadSimpleExcelDataShouldSuccess() throws IOException {
        PoijiOptions options = PoijiOptions.PoijiOptionsBuilder.settings()
                .addListDelimiter(",")
                .build();

        ClassPathResource resource = new ClassPathResource("recipients.xlsx");

        List<RecipientExcelDTOV1> recipientList = Poiji.fromExcel(
                resource.getInputStream(),
                PoijiExcelType.XLSX,
                RecipientExcelDTOV1.class,
                options);

        assertNotNull(recipientList);
        assertEquals(recipientList.size(), 918);
    }

    /**
     * загружать .xlsx файл по @ExcelCell аннотатция
     */
    @Test
    void secondTestLoadSimpleExcelDataShouldSuccess() throws IOException {
        PoijiOptions options = PoijiOptions.PoijiOptionsBuilder.settings()
                .addListDelimiter(",")
                .build();

        ClassPathResource resource = new ClassPathResource("recipients.xlsx");

        List<RecipientExcelDTOV2> recipientList = Poiji.fromExcel(
                resource.getInputStream(),
                PoijiExcelType.XLSX,
                RecipientExcelDTOV2.class,
                options);

        assertNotNull(recipientList);
        assertEquals(recipientList.size(), 918);
    }
}
