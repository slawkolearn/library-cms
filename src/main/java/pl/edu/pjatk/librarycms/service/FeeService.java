package pl.edu.pjatk.librarycms.service;

import pl.edu.pjatk.librarycms.service.dto.FeeDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link pl.edu.pjatk.librarycms.domain.Fee}.
 */
public interface FeeService {

    /**
     * Save a fee.
     *
     * @param feeDTO the entity to save.
     * @return the persisted entity.
     */
    FeeDTO save(FeeDTO feeDTO);

    /**
     * Get all the fees.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<FeeDTO> findAll(Pageable pageable);


    /**
     * Get the "id" fee.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<FeeDTO> findOne(Long id);

    /**
     * Delete the "id" fee.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
