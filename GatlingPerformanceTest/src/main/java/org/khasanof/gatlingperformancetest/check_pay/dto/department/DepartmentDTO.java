package org.khasanof.gatlingperformancetest.check_pay.dto.department;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.khasanof.gatlingperformancetest.check_pay.dto.Status;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * A DTO for the {@link uz.devops.profilems.domain.Department} entity.
 */
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentDTO implements Serializable {

    private Long id;

    @Size(max = 64)
    private String code;

    @Size(max = 64)
    private String nameUz;
    @Size(max = 64)
    private String nameRu;

    private Status status;
    private String merchantInn;
    private List<Long> employeeIds;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getNameUz() {
        return nameUz;
    }

    public void setNameUz(String nameUz) {
        this.nameUz = nameUz;
    }

    public String getNameRu() {
        return nameRu;
    }

    public void setNameRu(String nameRu) {
        this.nameRu = nameRu;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getMerchantInn() {
        return merchantInn;
    }

    public void setMerchantInn(String merchantInn) {
        this.merchantInn = merchantInn;
    }

    public List<Long> getEmployeeIds() {
        return employeeIds;
    }

    public void setEmployeeIds(List<Long> employeeIds) {
        this.employeeIds = employeeIds;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DepartmentDTO)) {
            return false;
        }

        DepartmentDTO departmentDTO = (DepartmentDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, departmentDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DepartmentDTO{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", nameUz='" + getNameUz() + "'" +
            ", nameRu='" + getNameRu() + "'" +
            ", status='" + getStatus() + "'" +
            ", merchantInn='" + getMerchantInn() + "'" +
            "}";
    }
}
