package pl.edu.pjatk.librarycms.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * A DTO for the {@link pl.edu.pjatk.librarycms.domain.OverdueFeeRate} entity.
 */
public class OverdueFeeRateDTO implements Serializable {

    private Long id;

    @NotNull
    private Integer afterDays;

    @NotNull
    private BigDecimal dayRate;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getAfterDays() {
        return afterDays;
    }

    public void setAfterDays(Integer afterDays) {
        this.afterDays = afterDays;
    }

    public BigDecimal getDayRate() {
        return dayRate;
    }

    public void setDayRate(BigDecimal dayRate) {
        this.dayRate = dayRate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        OverdueFeeRateDTO overdueFeeRateDTO = (OverdueFeeRateDTO) o;
        if (overdueFeeRateDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), overdueFeeRateDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "OverdueFeeRateDTO{" +
            "id=" + getId() +
            ", afterDays=" + getAfterDays() +
            ", dayRate=" + getDayRate() +
            "}";
    }
}
