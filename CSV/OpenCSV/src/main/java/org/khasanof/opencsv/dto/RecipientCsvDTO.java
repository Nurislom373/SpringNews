package org.khasanof.opencsv.dto;

import com.opencsv.bean.CsvBindByPosition;
import lombok.*;

import java.util.List;

/**
 * @author Nurislom
 * @see org.khasanof.opencsv.dto
 * @since 12/19/2024 12:01 AM
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class RecipientCsvDTO {

    @CsvBindByPosition(position = 0)
    private String id;

    @CsvBindByPosition(position = 1)
    private String shortName;

    @CsvBindByPosition(position = 2)
    private String minAmount;

    @CsvBindByPosition(position = 3)
    private String maxAmount;

    @CsvBindByPosition(position = 4)
    private String active;

    @CsvBindByPosition(position = 5)
    private String amountRatio;

    @CsvBindByPosition(position = 6)
    private String limitRatio;

    @CsvBindByPosition(position = 7)
    private String type;

    @CsvBindByPosition(position = 8)
    private String categoryId;

    @CsvBindByPosition(position = 9)
    private String logo;
}
