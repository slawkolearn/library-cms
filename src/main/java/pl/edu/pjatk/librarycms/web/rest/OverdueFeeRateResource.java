package pl.edu.pjatk.librarycms.web.rest;

import pl.edu.pjatk.librarycms.service.OverdueFeeRateService;
import pl.edu.pjatk.librarycms.web.rest.errors.BadRequestAlertException;
import pl.edu.pjatk.librarycms.service.dto.OverdueFeeRateDTO;

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
 * REST controller for managing {@link pl.edu.pjatk.librarycms.domain.OverdueFeeRate}.
 */
@RestController
@RequestMapping("/api")
public class OverdueFeeRateResource {

    private final Logger log = LoggerFactory.getLogger(OverdueFeeRateResource.class);

    private static final String ENTITY_NAME = "overdueFeeRate";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final OverdueFeeRateService overdueFeeRateService;

    public OverdueFeeRateResource(OverdueFeeRateService overdueFeeRateService) {
        this.overdueFeeRateService = overdueFeeRateService;
    }

    /**
     * {@code POST  /overdue-fee-rates} : Create a new overdueFeeRate.
     *
     * @param overdueFeeRateDTO the overdueFeeRateDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new overdueFeeRateDTO, or with status {@code 400 (Bad Request)} if the overdueFeeRate has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/overdue-fee-rates")
    public ResponseEntity<OverdueFeeRateDTO> createOverdueFeeRate(@Valid @RequestBody OverdueFeeRateDTO overdueFeeRateDTO) throws URISyntaxException {
        log.debug("REST request to save OverdueFeeRate : {}", overdueFeeRateDTO);
        if (overdueFeeRateDTO.getId() != null) {
            throw new BadRequestAlertException("A new overdueFeeRate cannot already have an ID", ENTITY_NAME, "idexists");
        }
        OverdueFeeRateDTO result = overdueFeeRateService.save(overdueFeeRateDTO);
        return ResponseEntity.created(new URI("/api/overdue-fee-rates/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /overdue-fee-rates} : Updates an existing overdueFeeRate.
     *
     * @param overdueFeeRateDTO the overdueFeeRateDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated overdueFeeRateDTO,
     * or with status {@code 400 (Bad Request)} if the overdueFeeRateDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the overdueFeeRateDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/overdue-fee-rates")
    public ResponseEntity<OverdueFeeRateDTO> updateOverdueFeeRate(@Valid @RequestBody OverdueFeeRateDTO overdueFeeRateDTO) throws URISyntaxException {
        log.debug("REST request to update OverdueFeeRate : {}", overdueFeeRateDTO);
        if (overdueFeeRateDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        OverdueFeeRateDTO result = overdueFeeRateService.save(overdueFeeRateDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, overdueFeeRateDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /overdue-fee-rates} : get all the overdueFeeRates.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of overdueFeeRates in body.
     */
    @GetMapping("/overdue-fee-rates")
    public ResponseEntity<List<OverdueFeeRateDTO>> getAllOverdueFeeRates(Pageable pageable) {
        log.debug("REST request to get a page of OverdueFeeRates");
        Page<OverdueFeeRateDTO> page = overdueFeeRateService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /overdue-fee-rates/:id} : get the "id" overdueFeeRate.
     *
     * @param id the id of the overdueFeeRateDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the overdueFeeRateDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/overdue-fee-rates/{id}")
    public ResponseEntity<OverdueFeeRateDTO> getOverdueFeeRate(@PathVariable Long id) {
        log.debug("REST request to get OverdueFeeRate : {}", id);
        Optional<OverdueFeeRateDTO> overdueFeeRateDTO = overdueFeeRateService.findOne(id);
        return ResponseUtil.wrapOrNotFound(overdueFeeRateDTO);
    }

    /**
     * {@code DELETE  /overdue-fee-rates/:id} : delete the "id" overdueFeeRate.
     *
     * @param id the id of the overdueFeeRateDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/overdue-fee-rates/{id}")
    public ResponseEntity<Void> deleteOverdueFeeRate(@PathVariable Long id) {
        log.debug("REST request to delete OverdueFeeRate : {}", id);
        overdueFeeRateService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
