package org.khasanof.opencsv.service;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

/**
 * @author Nurislom
 * @see org.khasanof.opencsv.service
 * @since 12/19/2024 12:06 AM
 */
public final class CsvToBeanService {

    /**
     *
     * @param path
     * @param clazz
     * @return
     * @param <T>
     * @throws Exception
     */
    public static <T> List<T> beanBuilderExample(Path path, Class<T> clazz) throws Exception {
        try (Reader reader = Files.newBufferedReader(path)) {

            CsvToBean<T> cb = new CsvToBeanBuilder<T>(reader)
                    .withType(clazz)
                    .build();

            return cb.parse();
        }
    }
}
