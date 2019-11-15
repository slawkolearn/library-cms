package pl.edu.pjatk.librarycms.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the {@link pl.edu.pjatk.librarycms.domain.Book} entity.
 */
public class BookDTO implements Serializable {

    private Long id;

    @NotNull
    private String name;

    private String description;

    private Integer maxBorrowingDays;

    @NotNull
    private BigDecimal borowwingDayRate;

    private String additionalInfo;


    private Set<OverdueFeeRateDTO> overdueFeeRates = new HashSet<>();

    private Long authorId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getMaxBorrowingDays() {
        return maxBorrowingDays;
    }

    public void setMaxBorrowingDays(Integer maxBorrowingDays) {
        this.maxBorrowingDays = maxBorrowingDays;
    }

    public BigDecimal getBorowwingDayRate() {
        return borowwingDayRate;
    }

    public void setBorowwingDayRate(BigDecimal borowwingDayRate) {
        this.borowwingDayRate = borowwingDayRate;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    public Set<OverdueFeeRateDTO> getOverdueFeeRates() {
        return overdueFeeRates;
    }

    public void setOverdueFeeRates(Set<OverdueFeeRateDTO> overdueFeeRates) {
        this.overdueFeeRates = overdueFeeRates;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        BookDTO bookDTO = (BookDTO) o;
        if (bookDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), bookDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "BookDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", description='" + getDescription() + "'" +
            ", maxBorrowingDays=" + getMaxBorrowingDays() +
            ", borowwingDayRate=" + getBorowwingDayRate() +
            ", additionalInfo='" + getAdditionalInfo() + "'" +
            ", author=" + getAuthorId() +
            "}";
    }
}
