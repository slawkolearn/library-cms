package pl.edu.pjatk.librarycms.service.impl;

import pl.edu.pjatk.librarycms.service.FeeService;
import pl.edu.pjatk.librarycms.domain.Fee;
import pl.edu.pjatk.librarycms.repository.FeeRepository;
import pl.edu.pjatk.librarycms.service.dto.FeeDTO;
import pl.edu.pjatk.librarycms.service.mapper.FeeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Fee}.
 */
@Service
@Transactional
public class FeeServiceImpl implements FeeService {

    private final Logger log = LoggerFactory.getLogger(FeeServiceImpl.class);

    private final FeeRepository feeRepository;

    private final FeeMapper feeMapper;

    public FeeServiceImpl(FeeRepository feeRepository, FeeMapper feeMapper) {
        this.feeRepository = feeRepository;
        this.feeMapper = feeMapper;
    }

    /**
     * Save a fee.
     *
     * @param feeDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public FeeDTO save(FeeDTO feeDTO) {
        log.debug("Request to save Fee : {}", feeDTO);
        Fee fee = feeMapper.toEntity(feeDTO);
        fee = feeRepository.save(fee);
        return feeMapper.toDto(fee);
    }

    /**
     * Get all the fees.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<FeeDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Fees");
        return feeRepository.findAll(pageable)
            .map(feeMapper::toDto);
    }


    /**
     * Get one fee by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<FeeDTO> findOne(Long id) {
        log.debug("Request to get Fee : {}", id);
        return feeRepository.findById(id)
            .map(feeMapper::toDto);
    }

    /**
     * Delete the fee by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Fee : {}", id);
        feeRepository.deleteById(id);
    }
}
