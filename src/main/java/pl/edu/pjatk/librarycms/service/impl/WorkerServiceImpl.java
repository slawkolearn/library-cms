package pl.edu.pjatk.librarycms.service.impl;

import pl.edu.pjatk.librarycms.service.WorkerService;
import pl.edu.pjatk.librarycms.domain.Worker;
import pl.edu.pjatk.librarycms.repository.WorkerRepository;
import pl.edu.pjatk.librarycms.service.dto.WorkerDTO;
import pl.edu.pjatk.librarycms.service.mapper.WorkerMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Worker}.
 */
@Service
@Transactional
public class WorkerServiceImpl implements WorkerService {

    private final Logger log = LoggerFactory.getLogger(WorkerServiceImpl.class);

    private final WorkerRepository workerRepository;

    private final WorkerMapper workerMapper;

    public WorkerServiceImpl(WorkerRepository workerRepository, WorkerMapper workerMapper) {
        this.workerRepository = workerRepository;
        this.workerMapper = workerMapper;
    }

    /**
     * Save a worker.
     *
     * @param workerDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public WorkerDTO save(WorkerDTO workerDTO) {
        log.debug("Request to save Worker : {}", workerDTO);
        Worker worker = workerMapper.toEntity(workerDTO);
        worker = workerRepository.save(worker);
        return workerMapper.toDto(worker);
    }

    /**
     * Get all the workers.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<WorkerDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Workers");
        return workerRepository.findAll(pageable)
            .map(workerMapper::toDto);
    }


    /**
     * Get one worker by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<WorkerDTO> findOne(Long id) {
        log.debug("Request to get Worker : {}", id);
        return workerRepository.findById(id)
            .map(workerMapper::toDto);
    }

    /**
     * Delete the worker by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Worker : {}", id);
        workerRepository.deleteById(id);
    }
}
