package pl.edu.pjatk.librarycms.web.rest;

import pl.edu.pjatk.librarycms.LibraryCmsApp;
import pl.edu.pjatk.librarycms.domain.BookLoan;
import pl.edu.pjatk.librarycms.domain.Book;
import pl.edu.pjatk.librarycms.domain.Client;
import pl.edu.pjatk.librarycms.repository.BookLoanRepository;
import pl.edu.pjatk.librarycms.service.BookLoanService;
import pl.edu.pjatk.librarycms.service.dto.BookLoanDTO;
import pl.edu.pjatk.librarycms.service.mapper.BookLoanMapper;
import pl.edu.pjatk.librarycms.web.rest.errors.ExceptionTranslator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static pl.edu.pjatk.librarycms.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link BookLoanResource} REST controller.
 */
@SpringBootTest(classes = LibraryCmsApp.class)
public class BookLoanResourceIT {

    private static final Instant DEFAULT_LOAN_START_TIMESTAMP = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_LOAN_START_TIMESTAMP = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_DECLARED_LOAN_END_TIMESTAMP = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_DECLARED_LOAN_END_TIMESTAMP = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    @Autowired
    private BookLoanRepository bookLoanRepository;

    @Autowired
    private BookLoanMapper bookLoanMapper;

    @Autowired
    private BookLoanService bookLoanService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    @Autowired
    private Validator validator;

    private MockMvc restBookLoanMockMvc;

    private BookLoan bookLoan;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final BookLoanResource bookLoanResource = new BookLoanResource(bookLoanService);
        this.restBookLoanMockMvc = MockMvcBuilders.standaloneSetup(bookLoanResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter)
            .setValidator(validator).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static BookLoan createEntity(EntityManager em) {
        BookLoan bookLoan = new BookLoan()
            .loanStartTimestamp(DEFAULT_LOAN_START_TIMESTAMP)
            .declaredLoanEndTimestamp(DEFAULT_DECLARED_LOAN_END_TIMESTAMP);
        // Add required entity
        Book book;
        if (TestUtil.findAll(em, Book.class).isEmpty()) {
            book = BookResourceIT.createEntity(em);
            em.persist(book);
            em.flush();
        } else {
            book = TestUtil.findAll(em, Book.class).get(0);
        }
        bookLoan.setBook(book);
        // Add required entity
        Client client;
        if (TestUtil.findAll(em, Client.class).isEmpty()) {
            client = ClientResourceIT.createEntity(em);
            em.persist(client);
            em.flush();
        } else {
            client = TestUtil.findAll(em, Client.class).get(0);
        }
        bookLoan.setClient(client);
        return bookLoan;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static BookLoan createUpdatedEntity(EntityManager em) {
        BookLoan bookLoan = new BookLoan()
            .loanStartTimestamp(UPDATED_LOAN_START_TIMESTAMP)
            .declaredLoanEndTimestamp(UPDATED_DECLARED_LOAN_END_TIMESTAMP);
        // Add required entity
        Book book;
        if (TestUtil.findAll(em, Book.class).isEmpty()) {
            book = BookResourceIT.createUpdatedEntity(em);
            em.persist(book);
            em.flush();
        } else {
            book = TestUtil.findAll(em, Book.class).get(0);
        }
        bookLoan.setBook(book);
        // Add required entity
        Client client;
        if (TestUtil.findAll(em, Client.class).isEmpty()) {
            client = ClientResourceIT.createUpdatedEntity(em);
            em.persist(client);
            em.flush();
        } else {
            client = TestUtil.findAll(em, Client.class).get(0);
        }
        bookLoan.setClient(client);
        return bookLoan;
    }

    @BeforeEach
    public void initTest() {
        bookLoan = createEntity(em);
    }

    @Test
    @Transactional
    public void createBookLoan() throws Exception {
        int databaseSizeBeforeCreate = bookLoanRepository.findAll().size();

        // Create the BookLoan
        BookLoanDTO bookLoanDTO = bookLoanMapper.toDto(bookLoan);
        restBookLoanMockMvc.perform(post("/api/book-loans")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(bookLoanDTO)))
            .andExpect(status().isCreated());

        // Validate the BookLoan in the database
        List<BookLoan> bookLoanList = bookLoanRepository.findAll();
        assertThat(bookLoanList).hasSize(databaseSizeBeforeCreate + 1);
        BookLoan testBookLoan = bookLoanList.get(bookLoanList.size() - 1);
        assertThat(testBookLoan.getLoanStartTimestamp()).isEqualTo(DEFAULT_LOAN_START_TIMESTAMP);
        assertThat(testBookLoan.getDeclaredLoanEndTimestamp()).isEqualTo(DEFAULT_DECLARED_LOAN_END_TIMESTAMP);
    }

    @Test
    @Transactional
    public void createBookLoanWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = bookLoanRepository.findAll().size();

        // Create the BookLoan with an existing ID
        bookLoan.setId(1L);
        BookLoanDTO bookLoanDTO = bookLoanMapper.toDto(bookLoan);

        // An entity with an existing ID cannot be created, so this API call must fail
        restBookLoanMockMvc.perform(post("/api/book-loans")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(bookLoanDTO)))
            .andExpect(status().isBadRequest());

        // Validate the BookLoan in the database
        List<BookLoan> bookLoanList = bookLoanRepository.findAll();
        assertThat(bookLoanList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkLoanStartTimestampIsRequired() throws Exception {
        int databaseSizeBeforeTest = bookLoanRepository.findAll().size();
        // set the field null
        bookLoan.setLoanStartTimestamp(null);

        // Create the BookLoan, which fails.
        BookLoanDTO bookLoanDTO = bookLoanMapper.toDto(bookLoan);

        restBookLoanMockMvc.perform(post("/api/book-loans")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(bookLoanDTO)))
            .andExpect(status().isBadRequest());

        List<BookLoan> bookLoanList = bookLoanRepository.findAll();
        assertThat(bookLoanList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllBookLoans() throws Exception {
        // Initialize the database
        bookLoanRepository.saveAndFlush(bookLoan);

        // Get all the bookLoanList
        restBookLoanMockMvc.perform(get("/api/book-loans?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(bookLoan.getId().intValue())))
            .andExpect(jsonPath("$.[*].loanStartTimestamp").value(hasItem(DEFAULT_LOAN_START_TIMESTAMP.toString())))
            .andExpect(jsonPath("$.[*].declaredLoanEndTimestamp").value(hasItem(DEFAULT_DECLARED_LOAN_END_TIMESTAMP.toString())));
    }
    
    @Test
    @Transactional
    public void getBookLoan() throws Exception {
        // Initialize the database
        bookLoanRepository.saveAndFlush(bookLoan);

        // Get the bookLoan
        restBookLoanMockMvc.perform(get("/api/book-loans/{id}", bookLoan.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(bookLoan.getId().intValue()))
            .andExpect(jsonPath("$.loanStartTimestamp").value(DEFAULT_LOAN_START_TIMESTAMP.toString()))
            .andExpect(jsonPath("$.declaredLoanEndTimestamp").value(DEFAULT_DECLARED_LOAN_END_TIMESTAMP.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingBookLoan() throws Exception {
        // Get the bookLoan
        restBookLoanMockMvc.perform(get("/api/book-loans/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateBookLoan() throws Exception {
        // Initialize the database
        bookLoanRepository.saveAndFlush(bookLoan);

        int databaseSizeBeforeUpdate = bookLoanRepository.findAll().size();

        // Update the bookLoan
        BookLoan updatedBookLoan = bookLoanRepository.findById(bookLoan.getId()).get();
        // Disconnect from session so that the updates on updatedBookLoan are not directly saved in db
        em.detach(updatedBookLoan);
        updatedBookLoan
            .loanStartTimestamp(UPDATED_LOAN_START_TIMESTAMP)
            .declaredLoanEndTimestamp(UPDATED_DECLARED_LOAN_END_TIMESTAMP);
        BookLoanDTO bookLoanDTO = bookLoanMapper.toDto(updatedBookLoan);

        restBookLoanMockMvc.perform(put("/api/book-loans")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(bookLoanDTO)))
            .andExpect(status().isOk());

        // Validate the BookLoan in the database
        List<BookLoan> bookLoanList = bookLoanRepository.findAll();
        assertThat(bookLoanList).hasSize(databaseSizeBeforeUpdate);
        BookLoan testBookLoan = bookLoanList.get(bookLoanList.size() - 1);
        assertThat(testBookLoan.getLoanStartTimestamp()).isEqualTo(UPDATED_LOAN_START_TIMESTAMP);
        assertThat(testBookLoan.getDeclaredLoanEndTimestamp()).isEqualTo(UPDATED_DECLARED_LOAN_END_TIMESTAMP);
    }

    @Test
    @Transactional
    public void updateNonExistingBookLoan() throws Exception {
        int databaseSizeBeforeUpdate = bookLoanRepository.findAll().size();

        // Create the BookLoan
        BookLoanDTO bookLoanDTO = bookLoanMapper.toDto(bookLoan);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBookLoanMockMvc.perform(put("/api/book-loans")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(bookLoanDTO)))
            .andExpect(status().isBadRequest());

        // Validate the BookLoan in the database
        List<BookLoan> bookLoanList = bookLoanRepository.findAll();
        assertThat(bookLoanList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteBookLoan() throws Exception {
        // Initialize the database
        bookLoanRepository.saveAndFlush(bookLoan);

        int databaseSizeBeforeDelete = bookLoanRepository.findAll().size();

        // Delete the bookLoan
        restBookLoanMockMvc.perform(delete("/api/book-loans/{id}", bookLoan.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<BookLoan> bookLoanList = bookLoanRepository.findAll();
        assertThat(bookLoanList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
