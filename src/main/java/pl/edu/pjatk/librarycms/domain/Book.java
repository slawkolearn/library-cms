package pl.edu.pjatk.librarycms.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

/**
 * A Book.
 */
@Entity
@Table(name = "book")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Book implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "max_borrowing_days")
    private Integer maxBorrowingDays;

    @NotNull
    @Column(name = "borowwing_day_rate", precision = 21, scale = 2, nullable = false)
    private BigDecimal borowwingDayRate;

    @Column(name = "additional_info")
    private String additionalInfo;

    @OneToMany(mappedBy = "book")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Rating> ratings = new HashSet<>();

    @OneToMany(mappedBy = "book")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<BookLoan> loans = new HashSet<>();

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "book_overdue_fee_rate",
               joinColumns = @JoinColumn(name = "book_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "overdue_fee_rate_id", referencedColumnName = "id"))
    private Set<OverdueFeeRate> overdueFeeRates = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @NotNull
    @JsonIgnoreProperties("books")
    private Author author;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Book name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public Book description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getMaxBorrowingDays() {
        return maxBorrowingDays;
    }

    public Book maxBorrowingDays(Integer maxBorrowingDays) {
        this.maxBorrowingDays = maxBorrowingDays;
        return this;
    }

    public void setMaxBorrowingDays(Integer maxBorrowingDays) {
        this.maxBorrowingDays = maxBorrowingDays;
    }

    public BigDecimal getBorowwingDayRate() {
        return borowwingDayRate;
    }

    public Book borowwingDayRate(BigDecimal borowwingDayRate) {
        this.borowwingDayRate = borowwingDayRate;
        return this;
    }

    public void setBorowwingDayRate(BigDecimal borowwingDayRate) {
        this.borowwingDayRate = borowwingDayRate;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public Book additionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
        return this;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    public Set<Rating> getRatings() {
        return ratings;
    }

    public Book ratings(Set<Rating> ratings) {
        this.ratings = ratings;
        return this;
    }

    public Book addRating(Rating rating) {
        this.ratings.add(rating);
        rating.setBook(this);
        return this;
    }

    public Book removeRating(Rating rating) {
        this.ratings.remove(rating);
        rating.setBook(null);
        return this;
    }

    public void setRatings(Set<Rating> ratings) {
        this.ratings = ratings;
    }

    public Set<BookLoan> getLoans() {
        return loans;
    }

    public Book loans(Set<BookLoan> bookLoans) {
        this.loans = bookLoans;
        return this;
    }

    public Book addLoan(BookLoan bookLoan) {
        this.loans.add(bookLoan);
        bookLoan.setBook(this);
        return this;
    }

    public Book removeLoan(BookLoan bookLoan) {
        this.loans.remove(bookLoan);
        bookLoan.setBook(null);
        return this;
    }

    public void setLoans(Set<BookLoan> bookLoans) {
        this.loans = bookLoans;
    }

    public Set<OverdueFeeRate> getOverdueFeeRates() {
        return overdueFeeRates;
    }

    public Book overdueFeeRates(Set<OverdueFeeRate> overdueFeeRates) {
        this.overdueFeeRates = overdueFeeRates;
        return this;
    }

    public Book addOverdueFeeRate(OverdueFeeRate overdueFeeRate) {
        this.overdueFeeRates.add(overdueFeeRate);
        overdueFeeRate.getBooks().add(this);
        return this;
    }

    public Book removeOverdueFeeRate(OverdueFeeRate overdueFeeRate) {
        this.overdueFeeRates.remove(overdueFeeRate);
        overdueFeeRate.getBooks().remove(this);
        return this;
    }

    public void setOverdueFeeRates(Set<OverdueFeeRate> overdueFeeRates) {
        this.overdueFeeRates = overdueFeeRates;
    }

    public Author getAuthor() {
        return author;
    }

    public Book author(Author author) {
        this.author = author;
        return this;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Book)) {
            return false;
        }
        return id != null && id.equals(((Book) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Book{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", description='" + getDescription() + "'" +
            ", maxBorrowingDays=" + getMaxBorrowingDays() +
            ", borowwingDayRate=" + getBorowwingDayRate() +
            ", additionalInfo='" + getAdditionalInfo() + "'" +
            "}";
    }
}
