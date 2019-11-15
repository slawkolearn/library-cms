package pl.edu.pjatk.librarycms.service.dto;
import java.time.Instant;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link pl.edu.pjatk.librarycms.domain.BookLoan} entity.
 */
public class BookLoanDTO implements Serializable {

    private Long id;

    @NotNull
    private Instant loanStartTimestamp;

    private Instant declaredLoanEndTimestamp;


    private Long bookId;

    private Long clientId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getLoanStartTimestamp() {
        return loanStartTimestamp;
    }

    public void setLoanStartTimestamp(Instant loanStartTimestamp) {
        this.loanStartTimestamp = loanStartTimestamp;
    }

    public Instant getDeclaredLoanEndTimestamp() {
        return declaredLoanEndTimestamp;
    }

    public void setDeclaredLoanEndTimestamp(Instant declaredLoanEndTimestamp) {
        this.declaredLoanEndTimestamp = declaredLoanEndTimestamp;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        BookLoanDTO bookLoanDTO = (BookLoanDTO) o;
        if (bookLoanDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), bookLoanDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "BookLoanDTO{" +
            "id=" + getId() +
            ", loanStartTimestamp='" + getLoanStartTimestamp() + "'" +
            ", declaredLoanEndTimestamp='" + getDeclaredLoanEndTimestamp() + "'" +
            ", book=" + getBookId() +
            ", client=" + getClientId() +
            "}";
    }
}
