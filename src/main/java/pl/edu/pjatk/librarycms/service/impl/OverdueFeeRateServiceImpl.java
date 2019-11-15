package pl.edu.pjatk.librarycms.service.impl;

import pl.edu.pjatk.librarycms.service.OverdueFeeRateService;
import pl.edu.pjatk.librarycms.domain.OverdueFeeRate;
import pl.edu.pjatk.librarycms.repository.OverdueFeeRateRepository;
import pl.edu.pjatk.librarycms.service.dto.OverdueFeeRateDTO;
import pl.edu.pjatk.librarycms.service.mapper.OverdueFeeRateMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link OverdueFeeRate}.
 */
@Service
@Transactional
public class OverdueFeeRateServiceImpl implements OverdueFeeRateService {

    private final Logger log = LoggerFactory.getLogger(OverdueFeeRateServiceImpl.class);

    private final OverdueFeeRateRepository overdueFeeRateRepository;

    private final OverdueFeeRateMapper overdueFeeRateMapper;

    public OverdueFeeRateServiceImpl(OverdueFeeRateRepository overdueFeeRateRepository, OverdueFeeRateMapper overdueFeeRateMapper) {
        this.overdueFeeRateRepository = overdueFeeRateRepository;
        this.overdueFeeRateMapper = overdueFeeRateMapper;
    }

    /**
     * Save a overdueFeeRate.
     *
     * @param overdueFeeRateDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public OverdueFeeRateDTO save(OverdueFeeRateDTO overdueFeeRateDTO) {
        log.debug("Request to save OverdueFeeRate : {}", overdueFeeRateDTO);
        OverdueFeeRate overdueFeeRate = overdueFeeRateMapper.toEntity(overdueFeeRateDTO);
        overdueFeeRate = overdueFeeRateRepository.save(overdueFeeRate);
        return overdueFeeRateMapper.toDto(overdueFeeRate);
    }

    /**
     * Get all the overdueFeeRates.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<OverdueFeeRateDTO> findAll(Pageable pageable) {
        log.debug("Request to get all OverdueFeeRates");
        return overdueFeeRateRepository.findAll(pageable)
            .map(overdueFeeRateMapper::toDto);
    }


    /**
     * Get one overdueFeeRate by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<OverdueFeeRateDTO> findOne(Long id) {
        log.debug("Request to get OverdueFeeRate : {}", id);
        return overdueFeeRateRepository.findById(id)
            .map(overdueFeeRateMapper::toDto);
    }

    /**
     * Delete the overdueFeeRate by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete OverdueFeeRate : {}", id);
        overdueFeeRateRepository.deleteById(id);
    }
}
