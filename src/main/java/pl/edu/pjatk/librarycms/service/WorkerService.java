package pl.edu.pjatk.librarycms.service;

import pl.edu.pjatk.librarycms.service.dto.WorkerDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link pl.edu.pjatk.librarycms.domain.Worker}.
 */
public interface WorkerService {

    /**
     * Save a worker.
     *
     * @param workerDTO the entity to save.
     * @return the persisted entity.
     */
    WorkerDTO save(WorkerDTO workerDTO);

    /**
     * Get all the workers.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<WorkerDTO> findAll(Pageable pageable);


    /**
     * Get the "id" worker.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<WorkerDTO> findOne(Long id);

    /**
     * Delete the "id" worker.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
