package org.khasanof.jackson.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;

/**
 * @author Nurislom
 * @see org.khasanof.opencsv.dto
 * @since 12/19/2024 12:01 AM
 */
public class RecipientCsvDTO {

    private Long id;

    private String shortName;

    private Long minAmount;

    private Long maxAmount;

    private Boolean active;

    private Long amountRatio;

    private Long limitRatio;

    private String type;

    private Long categoryId;

    private String logo;

    public RecipientCsvDTO() {
    }

    public RecipientCsvDTO(Long id, String shortName, Long minAmount, Long maxAmount, Boolean active, Long amountRatio, Long limitRatio, String type, Long categoryId, String logo) {
        this.id = id;
        this.shortName = shortName;
        this.minAmount = minAmount;
        this.maxAmount = maxAmount;
        this.active = active;
        this.amountRatio = amountRatio;
        this.limitRatio = limitRatio;
        this.type = type;
        this.categoryId = categoryId;
        this.logo = logo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public Long getMinAmount() {
        return minAmount;
    }

    public void setMinAmount(Long minAmount) {
        this.minAmount = minAmount;
    }

    public Long getMaxAmount() {
        return maxAmount;
    }

    public void setMaxAmount(Long maxAmount) {
        this.maxAmount = maxAmount;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Long getAmountRatio() {
        return amountRatio;
    }

    public void setAmountRatio(Long amountRatio) {
        this.amountRatio = amountRatio;
    }

    public Long getLimitRatio() {
        return limitRatio;
    }

    public void setLimitRatio(Long limitRatio) {
        this.limitRatio = limitRatio;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }
}
