package pl.edu.pjatk.librarycms.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

/**
 * A BookLoan.
 */
@Entity
@Table(name = "book_loan")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class BookLoan implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "loan_start_timestamp", nullable = false)
    private Instant loanStartTimestamp;

    @Column(name = "declared_loan_end_timestamp")
    private Instant declaredLoanEndTimestamp;

    @OneToMany(mappedBy = "book")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Fee> fees = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @NotNull
    @JsonIgnoreProperties("loans")
    private Book book;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @NotNull
    @JsonIgnoreProperties("loans")
    private Client client;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getLoanStartTimestamp() {
        return loanStartTimestamp;
    }

    public BookLoan loanStartTimestamp(Instant loanStartTimestamp) {
        this.loanStartTimestamp = loanStartTimestamp;
        return this;
    }

    public void setLoanStartTimestamp(Instant loanStartTimestamp) {
        this.loanStartTimestamp = loanStartTimestamp;
    }

    public Instant getDeclaredLoanEndTimestamp() {
        return declaredLoanEndTimestamp;
    }

    public BookLoan declaredLoanEndTimestamp(Instant declaredLoanEndTimestamp) {
        this.declaredLoanEndTimestamp = declaredLoanEndTimestamp;
        return this;
    }

    public void setDeclaredLoanEndTimestamp(Instant declaredLoanEndTimestamp) {
        this.declaredLoanEndTimestamp = declaredLoanEndTimestamp;
    }

    public Set<Fee> getFees() {
        return fees;
    }

    public BookLoan fees(Set<Fee> fees) {
        this.fees = fees;
        return this;
    }

    public BookLoan addFee(Fee fee) {
        this.fees.add(fee);
        fee.setBook(this);
        return this;
    }

    public BookLoan removeFee(Fee fee) {
        this.fees.remove(fee);
        fee.setBook(null);
        return this;
    }

    public void setFees(Set<Fee> fees) {
        this.fees = fees;
    }

    public Book getBook() {
        return book;
    }

    public BookLoan book(Book book) {
        this.book = book;
        return this;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Client getClient() {
        return client;
    }

    public BookLoan client(Client client) {
        this.client = client;
        return this;
    }

    public void setClient(Client client) {
        this.client = client;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BookLoan)) {
            return false;
        }
        return id != null && id.equals(((BookLoan) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "BookLoan{" +
            "id=" + getId() +
            ", loanStartTimestamp='" + getLoanStartTimestamp() + "'" +
            ", declaredLoanEndTimestamp='" + getDeclaredLoanEndTimestamp() + "'" +
            "}";
    }
}
