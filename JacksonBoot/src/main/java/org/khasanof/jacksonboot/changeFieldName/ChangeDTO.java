package org.khasanof.jacksonboot.changeFieldName;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Author: Nurislom
 * <br/>
 * Date: 3/18/2023
 * <br/>
 * Time: 11:13 PM
 * <br/>
 * Package: org.khasanof.jacksonboot.changeFieldName
 */
public class ChangeDTO {

    private String stringValue;

    public ChangeDTO() {
        super();
    }

    @JsonProperty("strVal")
    public String getStringValue() {
        return stringValue;
    }

    public void setStringValue(String stringValue) {
        this.stringValue = stringValue;
    }
}
