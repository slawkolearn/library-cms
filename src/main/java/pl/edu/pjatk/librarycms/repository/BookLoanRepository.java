package pl.edu.pjatk.librarycms.repository;
import pl.edu.pjatk.librarycms.domain.BookLoan;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the BookLoan entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BookLoanRepository extends JpaRepository<BookLoan, Long> {

}
