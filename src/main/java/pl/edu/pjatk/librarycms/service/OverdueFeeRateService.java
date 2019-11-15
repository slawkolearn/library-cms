package pl.edu.pjatk.librarycms.service;

import pl.edu.pjatk.librarycms.service.dto.OverdueFeeRateDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link pl.edu.pjatk.librarycms.domain.OverdueFeeRate}.
 */
public interface OverdueFeeRateService {

    /**
     * Save a overdueFeeRate.
     *
     * @param overdueFeeRateDTO the entity to save.
     * @return the persisted entity.
     */
    OverdueFeeRateDTO save(OverdueFeeRateDTO overdueFeeRateDTO);

    /**
     * Get all the overdueFeeRates.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<OverdueFeeRateDTO> findAll(Pageable pageable);


    /**
     * Get the "id" overdueFeeRate.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<OverdueFeeRateDTO> findOne(Long id);

    /**
     * Delete the "id" overdueFeeRate.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
