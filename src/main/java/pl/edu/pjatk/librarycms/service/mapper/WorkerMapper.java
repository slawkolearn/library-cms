package pl.edu.pjatk.librarycms.service.mapper;

import pl.edu.pjatk.librarycms.domain.*;
import pl.edu.pjatk.librarycms.service.dto.WorkerDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Worker} and its DTO {@link WorkerDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface WorkerMapper extends EntityMapper<WorkerDTO, Worker> {



    default Worker fromId(Long id) {
        if (id == null) {
            return null;
        }
        Worker worker = new Worker();
        worker.setId(id);
        return worker;
    }
}
