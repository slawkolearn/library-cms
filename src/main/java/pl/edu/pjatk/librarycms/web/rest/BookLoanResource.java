package pl.edu.pjatk.librarycms.web.rest;

import pl.edu.pjatk.librarycms.service.BookLoanService;
import pl.edu.pjatk.librarycms.web.rest.errors.BadRequestAlertException;
import pl.edu.pjatk.librarycms.service.dto.BookLoanDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link pl.edu.pjatk.librarycms.domain.BookLoan}.
 */
@RestController
@RequestMapping("/api")
public class BookLoanResource {

    private final Logger log = LoggerFactory.getLogger(BookLoanResource.class);

    private static final String ENTITY_NAME = "bookLoan";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final BookLoanService bookLoanService;

    public BookLoanResource(BookLoanService bookLoanService) {
        this.bookLoanService = bookLoanService;
    }

    /**
     * {@code POST  /book-loans} : Create a new bookLoan.
     *
     * @param bookLoanDTO the bookLoanDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new bookLoanDTO, or with status {@code 400 (Bad Request)} if the bookLoan has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/book-loans")
    public ResponseEntity<BookLoanDTO> createBookLoan(@Valid @RequestBody BookLoanDTO bookLoanDTO) throws URISyntaxException {
        log.debug("REST request to save BookLoan : {}", bookLoanDTO);
        if (bookLoanDTO.getId() != null) {
            throw new BadRequestAlertException("A new bookLoan cannot already have an ID", ENTITY_NAME, "idexists");
        }
        BookLoanDTO result = bookLoanService.save(bookLoanDTO);
        return ResponseEntity.created(new URI("/api/book-loans/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /book-loans} : Updates an existing bookLoan.
     *
     * @param bookLoanDTO the bookLoanDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated bookLoanDTO,
     * or with status {@code 400 (Bad Request)} if the bookLoanDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the bookLoanDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/book-loans")
    public ResponseEntity<BookLoanDTO> updateBookLoan(@Valid @RequestBody BookLoanDTO bookLoanDTO) throws URISyntaxException {
        log.debug("REST request to update BookLoan : {}", bookLoanDTO);
        if (bookLoanDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        BookLoanDTO result = bookLoanService.save(bookLoanDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, bookLoanDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /book-loans} : get all the bookLoans.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of bookLoans in body.
     */
    @GetMapping("/book-loans")
    public ResponseEntity<List<BookLoanDTO>> getAllBookLoans(Pageable pageable) {
        log.debug("REST request to get a page of BookLoans");
        Page<BookLoanDTO> page = bookLoanService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /book-loans/:id} : get the "id" bookLoan.
     *
     * @param id the id of the bookLoanDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the bookLoanDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/book-loans/{id}")
    public ResponseEntity<BookLoanDTO> getBookLoan(@PathVariable Long id) {
        log.debug("REST request to get BookLoan : {}", id);
        Optional<BookLoanDTO> bookLoanDTO = bookLoanService.findOne(id);
        return ResponseUtil.wrapOrNotFound(bookLoanDTO);
    }

    /**
     * {@code DELETE  /book-loans/:id} : delete the "id" bookLoan.
     *
     * @param id the id of the bookLoanDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/book-loans/{id}")
    public ResponseEntity<Void> deleteBookLoan(@PathVariable Long id) {
        log.debug("REST request to delete BookLoan : {}", id);
        bookLoanService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
