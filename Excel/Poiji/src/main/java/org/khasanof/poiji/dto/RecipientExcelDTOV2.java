package org.khasanof.poiji.dto;

import com.poiji.annotation.ExcelCell;
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
public class RecipientExcelDTOV2 {

    @ExcelCell(0)
    private Long id;

    @ExcelCell(1)
    private String shortName;

    @ExcelCell(2)
    private List<String> serviceId;

    @ExcelCell(3)
    private Long minAmount;

    @ExcelCell(4)
    private Long maxAmount;

    @ExcelCell(5)
    private Boolean active;

    @ExcelCell(6)
    private Integer amountRatio;

    @ExcelCell(7)
    private Integer limitRatio;

    @ExcelCell(8)
    private String type;

    @ExcelCell(9)
    private Long categoryId;

    @ExcelCell(10)
    private String logo;
}
