package pl.edu.pjatk.librarycms.service.impl;

import pl.edu.pjatk.librarycms.service.BookLoanService;
import pl.edu.pjatk.librarycms.domain.BookLoan;
import pl.edu.pjatk.librarycms.repository.BookLoanRepository;
import pl.edu.pjatk.librarycms.service.dto.BookLoanDTO;
import pl.edu.pjatk.librarycms.service.mapper.BookLoanMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link BookLoan}.
 */
@Service
@Transactional
public class BookLoanServiceImpl implements BookLoanService {

    private final Logger log = LoggerFactory.getLogger(BookLoanServiceImpl.class);

    private final BookLoanRepository bookLoanRepository;

    private final BookLoanMapper bookLoanMapper;

    public BookLoanServiceImpl(BookLoanRepository bookLoanRepository, BookLoanMapper bookLoanMapper) {
        this.bookLoanRepository = bookLoanRepository;
        this.bookLoanMapper = bookLoanMapper;
    }

    /**
     * Save a bookLoan.
     *
     * @param bookLoanDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public BookLoanDTO save(BookLoanDTO bookLoanDTO) {
        log.debug("Request to save BookLoan : {}", bookLoanDTO);
        BookLoan bookLoan = bookLoanMapper.toEntity(bookLoanDTO);
        bookLoan = bookLoanRepository.save(bookLoan);
        return bookLoanMapper.toDto(bookLoan);
    }

    /**
     * Get all the bookLoans.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<BookLoanDTO> findAll(Pageable pageable) {
        log.debug("Request to get all BookLoans");
        return bookLoanRepository.findAll(pageable)
            .map(bookLoanMapper::toDto);
    }


    /**
     * Get one bookLoan by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<BookLoanDTO> findOne(Long id) {
        log.debug("Request to get BookLoan : {}", id);
        return bookLoanRepository.findById(id)
            .map(bookLoanMapper::toDto);
    }

    /**
     * Delete the bookLoan by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete BookLoan : {}", id);
        bookLoanRepository.deleteById(id);
    }
}
