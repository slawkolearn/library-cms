package pl.edu.pjatk.librarycms.service.mapper;

import pl.edu.pjatk.librarycms.domain.*;
import pl.edu.pjatk.librarycms.service.dto.OverdueFeeRateDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link OverdueFeeRate} and its DTO {@link OverdueFeeRateDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface OverdueFeeRateMapper extends EntityMapper<OverdueFeeRateDTO, OverdueFeeRate> {


    @Mapping(target = "books", ignore = true)
    @Mapping(target = "removeBook", ignore = true)
    OverdueFeeRate toEntity(OverdueFeeRateDTO overdueFeeRateDTO);

    default OverdueFeeRate fromId(Long id) {
        if (id == null) {
            return null;
        }
        OverdueFeeRate overdueFeeRate = new OverdueFeeRate();
        overdueFeeRate.setId(id);
        return overdueFeeRate;
    }
}
