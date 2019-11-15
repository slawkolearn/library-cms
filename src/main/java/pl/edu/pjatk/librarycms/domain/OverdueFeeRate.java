package pl.edu.pjatk.librarycms.domain;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

/**
 * A OverdueFeeRate.
 */
@Entity
@Table(name = "overdue_fee_rate")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class OverdueFeeRate implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "after_days", nullable = false, unique = true)
    private Integer afterDays;

    @NotNull
    @Column(name = "day_rate", precision = 21, scale = 2, nullable = false)
    private BigDecimal dayRate;

    @ManyToMany(mappedBy = "overdueFeeRates")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JsonIgnore
    private Set<Book> books = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getAfterDays() {
        return afterDays;
    }

    public OverdueFeeRate afterDays(Integer afterDays) {
        this.afterDays = afterDays;
        return this;
    }

    public void setAfterDays(Integer afterDays) {
        this.afterDays = afterDays;
    }

    public BigDecimal getDayRate() {
        return dayRate;
    }

    public OverdueFeeRate dayRate(BigDecimal dayRate) {
        this.dayRate = dayRate;
        return this;
    }

    public void setDayRate(BigDecimal dayRate) {
        this.dayRate = dayRate;
    }

    public Set<Book> getBooks() {
        return books;
    }

    public OverdueFeeRate books(Set<Book> books) {
        this.books = books;
        return this;
    }

    public OverdueFeeRate addBook(Book book) {
        this.books.add(book);
        book.getOverdueFeeRates().add(this);
        return this;
    }

    public OverdueFeeRate removeBook(Book book) {
        this.books.remove(book);
        book.getOverdueFeeRates().remove(this);
        return this;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof OverdueFeeRate)) {
            return false;
        }
        return id != null && id.equals(((OverdueFeeRate) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "OverdueFeeRate{" +
            "id=" + getId() +
            ", afterDays=" + getAfterDays() +
            ", dayRate=" + getDayRate() +
            "}";
    }
}
