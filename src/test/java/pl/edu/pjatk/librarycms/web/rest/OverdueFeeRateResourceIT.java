package pl.edu.pjatk.librarycms.web.rest;

import pl.edu.pjatk.librarycms.LibraryCmsApp;
import pl.edu.pjatk.librarycms.domain.OverdueFeeRate;
import pl.edu.pjatk.librarycms.repository.OverdueFeeRateRepository;
import pl.edu.pjatk.librarycms.service.OverdueFeeRateService;
import pl.edu.pjatk.librarycms.service.dto.OverdueFeeRateDTO;
import pl.edu.pjatk.librarycms.service.mapper.OverdueFeeRateMapper;
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
import java.math.BigDecimal;
import java.util.List;

import static pl.edu.pjatk.librarycms.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link OverdueFeeRateResource} REST controller.
 */
@SpringBootTest(classes = LibraryCmsApp.class)
public class OverdueFeeRateResourceIT {

    private static final Integer DEFAULT_AFTER_DAYS = 1;
    private static final Integer UPDATED_AFTER_DAYS = 2;

    private static final BigDecimal DEFAULT_DAY_RATE = new BigDecimal(1);
    private static final BigDecimal UPDATED_DAY_RATE = new BigDecimal(2);

    @Autowired
    private OverdueFeeRateRepository overdueFeeRateRepository;

    @Autowired
    private OverdueFeeRateMapper overdueFeeRateMapper;

    @Autowired
    private OverdueFeeRateService overdueFeeRateService;

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

    private MockMvc restOverdueFeeRateMockMvc;

    private OverdueFeeRate overdueFeeRate;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final OverdueFeeRateResource overdueFeeRateResource = new OverdueFeeRateResource(overdueFeeRateService);
        this.restOverdueFeeRateMockMvc = MockMvcBuilders.standaloneSetup(overdueFeeRateResource)
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
    public static OverdueFeeRate createEntity(EntityManager em) {
        OverdueFeeRate overdueFeeRate = new OverdueFeeRate()
            .afterDays(DEFAULT_AFTER_DAYS)
            .dayRate(DEFAULT_DAY_RATE);
        return overdueFeeRate;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static OverdueFeeRate createUpdatedEntity(EntityManager em) {
        OverdueFeeRate overdueFeeRate = new OverdueFeeRate()
            .afterDays(UPDATED_AFTER_DAYS)
            .dayRate(UPDATED_DAY_RATE);
        return overdueFeeRate;
    }

    @BeforeEach
    public void initTest() {
        overdueFeeRate = createEntity(em);
    }

    @Test
    @Transactional
    public void createOverdueFeeRate() throws Exception {
        int databaseSizeBeforeCreate = overdueFeeRateRepository.findAll().size();

        // Create the OverdueFeeRate
        OverdueFeeRateDTO overdueFeeRateDTO = overdueFeeRateMapper.toDto(overdueFeeRate);
        restOverdueFeeRateMockMvc.perform(post("/api/overdue-fee-rates")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(overdueFeeRateDTO)))
            .andExpect(status().isCreated());

        // Validate the OverdueFeeRate in the database
        List<OverdueFeeRate> overdueFeeRateList = overdueFeeRateRepository.findAll();
        assertThat(overdueFeeRateList).hasSize(databaseSizeBeforeCreate + 1);
        OverdueFeeRate testOverdueFeeRate = overdueFeeRateList.get(overdueFeeRateList.size() - 1);
        assertThat(testOverdueFeeRate.getAfterDays()).isEqualTo(DEFAULT_AFTER_DAYS);
        assertThat(testOverdueFeeRate.getDayRate()).isEqualTo(DEFAULT_DAY_RATE);
    }

    @Test
    @Transactional
    public void createOverdueFeeRateWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = overdueFeeRateRepository.findAll().size();

        // Create the OverdueFeeRate with an existing ID
        overdueFeeRate.setId(1L);
        OverdueFeeRateDTO overdueFeeRateDTO = overdueFeeRateMapper.toDto(overdueFeeRate);

        // An entity with an existing ID cannot be created, so this API call must fail
        restOverdueFeeRateMockMvc.perform(post("/api/overdue-fee-rates")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(overdueFeeRateDTO)))
            .andExpect(status().isBadRequest());

        // Validate the OverdueFeeRate in the database
        List<OverdueFeeRate> overdueFeeRateList = overdueFeeRateRepository.findAll();
        assertThat(overdueFeeRateList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkAfterDaysIsRequired() throws Exception {
        int databaseSizeBeforeTest = overdueFeeRateRepository.findAll().size();
        // set the field null
        overdueFeeRate.setAfterDays(null);

        // Create the OverdueFeeRate, which fails.
        OverdueFeeRateDTO overdueFeeRateDTO = overdueFeeRateMapper.toDto(overdueFeeRate);

        restOverdueFeeRateMockMvc.perform(post("/api/overdue-fee-rates")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(overdueFeeRateDTO)))
            .andExpect(status().isBadRequest());

        List<OverdueFeeRate> overdueFeeRateList = overdueFeeRateRepository.findAll();
        assertThat(overdueFeeRateList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDayRateIsRequired() throws Exception {
        int databaseSizeBeforeTest = overdueFeeRateRepository.findAll().size();
        // set the field null
        overdueFeeRate.setDayRate(null);

        // Create the OverdueFeeRate, which fails.
        OverdueFeeRateDTO overdueFeeRateDTO = overdueFeeRateMapper.toDto(overdueFeeRate);

        restOverdueFeeRateMockMvc.perform(post("/api/overdue-fee-rates")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(overdueFeeRateDTO)))
            .andExpect(status().isBadRequest());

        List<OverdueFeeRate> overdueFeeRateList = overdueFeeRateRepository.findAll();
        assertThat(overdueFeeRateList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllOverdueFeeRates() throws Exception {
        // Initialize the database
        overdueFeeRateRepository.saveAndFlush(overdueFeeRate);

        // Get all the overdueFeeRateList
        restOverdueFeeRateMockMvc.perform(get("/api/overdue-fee-rates?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(overdueFeeRate.getId().intValue())))
            .andExpect(jsonPath("$.[*].afterDays").value(hasItem(DEFAULT_AFTER_DAYS)))
            .andExpect(jsonPath("$.[*].dayRate").value(hasItem(DEFAULT_DAY_RATE.intValue())));
    }
    
    @Test
    @Transactional
    public void getOverdueFeeRate() throws Exception {
        // Initialize the database
        overdueFeeRateRepository.saveAndFlush(overdueFeeRate);

        // Get the overdueFeeRate
        restOverdueFeeRateMockMvc.perform(get("/api/overdue-fee-rates/{id}", overdueFeeRate.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(overdueFeeRate.getId().intValue()))
            .andExpect(jsonPath("$.afterDays").value(DEFAULT_AFTER_DAYS))
            .andExpect(jsonPath("$.dayRate").value(DEFAULT_DAY_RATE.intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingOverdueFeeRate() throws Exception {
        // Get the overdueFeeRate
        restOverdueFeeRateMockMvc.perform(get("/api/overdue-fee-rates/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateOverdueFeeRate() throws Exception {
        // Initialize the database
        overdueFeeRateRepository.saveAndFlush(overdueFeeRate);

        int databaseSizeBeforeUpdate = overdueFeeRateRepository.findAll().size();

        // Update the overdueFeeRate
        OverdueFeeRate updatedOverdueFeeRate = overdueFeeRateRepository.findById(overdueFeeRate.getId()).get();
        // Disconnect from session so that the updates on updatedOverdueFeeRate are not directly saved in db
        em.detach(updatedOverdueFeeRate);
        updatedOverdueFeeRate
            .afterDays(UPDATED_AFTER_DAYS)
            .dayRate(UPDATED_DAY_RATE);
        OverdueFeeRateDTO overdueFeeRateDTO = overdueFeeRateMapper.toDto(updatedOverdueFeeRate);

        restOverdueFeeRateMockMvc.perform(put("/api/overdue-fee-rates")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(overdueFeeRateDTO)))
            .andExpect(status().isOk());

        // Validate the OverdueFeeRate in the database
        List<OverdueFeeRate> overdueFeeRateList = overdueFeeRateRepository.findAll();
        assertThat(overdueFeeRateList).hasSize(databaseSizeBeforeUpdate);
        OverdueFeeRate testOverdueFeeRate = overdueFeeRateList.get(overdueFeeRateList.size() - 1);
        assertThat(testOverdueFeeRate.getAfterDays()).isEqualTo(UPDATED_AFTER_DAYS);
        assertThat(testOverdueFeeRate.getDayRate()).isEqualTo(UPDATED_DAY_RATE);
    }

    @Test
    @Transactional
    public void updateNonExistingOverdueFeeRate() throws Exception {
        int databaseSizeBeforeUpdate = overdueFeeRateRepository.findAll().size();

        // Create the OverdueFeeRate
        OverdueFeeRateDTO overdueFeeRateDTO = overdueFeeRateMapper.toDto(overdueFeeRate);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOverdueFeeRateMockMvc.perform(put("/api/overdue-fee-rates")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(overdueFeeRateDTO)))
            .andExpect(status().isBadRequest());

        // Validate the OverdueFeeRate in the database
        List<OverdueFeeRate> overdueFeeRateList = overdueFeeRateRepository.findAll();
        assertThat(overdueFeeRateList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteOverdueFeeRate() throws Exception {
        // Initialize the database
        overdueFeeRateRepository.saveAndFlush(overdueFeeRate);

        int databaseSizeBeforeDelete = overdueFeeRateRepository.findAll().size();

        // Delete the overdueFeeRate
        restOverdueFeeRateMockMvc.perform(delete("/api/overdue-fee-rates/{id}", overdueFeeRate.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<OverdueFeeRate> overdueFeeRateList = overdueFeeRateRepository.findAll();
        assertThat(overdueFeeRateList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
