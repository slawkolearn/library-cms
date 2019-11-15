package pl.edu.pjatk.librarycms.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * A DTO for the {@link pl.edu.pjatk.librarycms.domain.Fee} entity.
 */
public class FeeDTO implements Serializable {

    private Long id;

    @NotNull
    private BigDecimal fee;

    @NotNull
    private String description;


    private Long bookId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getFee() {
        return fee;
    }

    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookLoanId) {
        this.bookId = bookLoanId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        FeeDTO feeDTO = (FeeDTO) o;
        if (feeDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), feeDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "FeeDTO{" +
            "id=" + getId() +
            ", fee=" + getFee() +
            ", description='" + getDescription() + "'" +
            ", book=" + getBookId() +
            "}";
    }
}
