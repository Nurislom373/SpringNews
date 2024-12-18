package org.khasanof.poiji.dto;

import com.poiji.annotation.ExcelCellName;
import lombok.*;

import java.util.List;

/**
 * @author Nurislom
 * @see org.khasanof.poiji.dto
 * @since 12/18/2024 11:01 PM
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class RecipientExcelDTOV1 {

    @ExcelCellName("id")
    private Long id;

    @ExcelCellName("shortName")
    private String shortName;

    @ExcelCellName("serviceId")
    private List<String> serviceId;

    @ExcelCellName("minAmount")
    private Long minAmount;

    @ExcelCellName("maxAmount")
    private Long maxAmount;

    @ExcelCellName("active")
    private Boolean active;

    @ExcelCellName("amountRatio")
    private Integer amountRatio;

    @ExcelCellName("limitRatio")
    private Integer limitRatio;

    @ExcelCellName("type")
    private String type;

    @ExcelCellName("categoryId")
    private Long categoryId;

    @ExcelCellName("logo")
    private String logo;
}
