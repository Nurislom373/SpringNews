package org.khasanof.poiji;

import com.poiji.bind.Poiji;
import com.poiji.exception.PoijiExcelType;
import com.poiji.option.PoijiOptions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.khasanof.poiji.dto.RecipientExcelDTOV1;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * @author Nurislom
 * @see org.khasanof.poiji
 * @since 12/18/2024 11:22 PM
 */
@Timeout(value = 1, unit = TimeUnit.DAYS)
@SpringBootTest(classes = {PoijiApplication.class})
public class LoadProtectedExcelDataTest {

    /**
     *
     * @throws IOException
     */
    @Test
    void firstTestLoadSimpleExcelDataShouldSuccess() throws IOException {
        PoijiOptions options = PoijiOptions.PoijiOptionsBuilder.settings()
                .password("1234") // вводить код excel на это метод
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
}
