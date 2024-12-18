package org.khasanof.jackson.service;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.File;
import java.util.Collections;
import java.util.List;

/**
 * @author Nurislom
 * @see org.khasanof.jackson.service
 * @since 12/19/2024 12:32 AM
 */
public final class CsvLoaderService {

    private final static Logger logger = LoggerFactory.getLogger(CsvLoaderService.class);

    /**
     *
     * @param type
     * @param fileName
     * @return
     * @param <T>
     */
    public static  <T> List<T> loadObjectList(Class<T> type, String fileName) {
        try {

            CsvMapper mapper = new CsvMapper();

            CsvSchema bootstrapSchema = CsvSchema.builder()
                    .addColumn("id", CsvSchema.ColumnType.NUMBER)
                    .addColumn("shortName", CsvSchema.ColumnType.STRING)
                    .addColumn("minAmount", CsvSchema.ColumnType.NUMBER)
                    .addColumn("maxAmount", CsvSchema.ColumnType.NUMBER)
                    .addColumn("active", CsvSchema.ColumnType.BOOLEAN)
                    .addColumn("amountRatio", CsvSchema.ColumnType.NUMBER)
                    .addColumn("limitRatio", CsvSchema.ColumnType.NUMBER)
                    .addColumn("type", CsvSchema.ColumnType.STRING)
                    .addColumn("categoryId", CsvSchema.ColumnType.NUMBER)
                    .addColumn("logo", CsvSchema.ColumnType.NUMBER)
                    .build()
                    .withHeader();

            Resource file = new ClassPathResource(fileName);

            MappingIterator<T> readValues = mapper.readerFor(type)
                    .with(bootstrapSchema)
                    .readValues(file.getFile());

            return readValues.readAll();
        } catch (Exception e) {
            logger.error("Error occurred while loading object list from file " + fileName, e);
            return Collections.emptyList();
        }
    }
}
