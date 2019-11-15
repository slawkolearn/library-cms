package pl.edu.pjatk.librarycms.service;

import pl.edu.pjatk.librarycms.service.dto.BookLoanDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link pl.edu.pjatk.librarycms.domain.BookLoan}.
 */
public interface BookLoanService {

    /**
     * Save a bookLoan.
     *
     * @param bookLoanDTO the entity to save.
     * @return the persisted entity.
     */
    BookLoanDTO save(BookLoanDTO bookLoanDTO);

    /**
     * Get all the bookLoans.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<BookLoanDTO> findAll(Pageable pageable);


    /**
     * Get the "id" bookLoan.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<BookLoanDTO> findOne(Long id);

    /**
     * Delete the "id" bookLoan.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
