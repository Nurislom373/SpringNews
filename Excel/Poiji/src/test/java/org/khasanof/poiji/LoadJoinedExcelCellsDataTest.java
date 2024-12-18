package org.khasanof.poiji;

import com.poiji.bind.Poiji;
import com.poiji.exception.PoijiExcelType;
import com.poiji.option.PoijiOptions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.khasanof.poiji.dto.Album;
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
 * @since 12/18/2024 11:31 PM
 */
@Timeout(value = 1, unit = TimeUnit.DAYS)
@SpringBootTest(classes = {PoijiApplication.class})
public class LoadJoinedExcelCellsDataTest {

    /**
     *
     * @throws IOException
     */
    @Test
    void firstTestLoadSimpleExcelDataShouldSuccess() throws IOException {
        PoijiOptions options = PoijiOptions.PoijiOptionsBuilder.settings()
                .build();

        ClassPathResource resource = new ClassPathResource("album.xls");

        List<Album> albums = Poiji.fromExcel(
                resource.getInputStream(),
                PoijiExcelType.XLS,
                Album.class,
                options);

        assertNotNull(albums);
        assertEquals(albums.size(), 1);
    }
}
