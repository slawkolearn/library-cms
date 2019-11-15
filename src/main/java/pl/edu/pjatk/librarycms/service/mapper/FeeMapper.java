package pl.edu.pjatk.librarycms.service.mapper;

import pl.edu.pjatk.librarycms.domain.*;
import pl.edu.pjatk.librarycms.service.dto.FeeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Fee} and its DTO {@link FeeDTO}.
 */
@Mapper(componentModel = "spring", uses = {BookLoanMapper.class})
public interface FeeMapper extends EntityMapper<FeeDTO, Fee> {

    @Mapping(source = "book.id", target = "bookId")
    FeeDTO toDto(Fee fee);

    @Mapping(source = "bookId", target = "book")
    Fee toEntity(FeeDTO feeDTO);

    default Fee fromId(Long id) {
        if (id == null) {
            return null;
        }
        Fee fee = new Fee();
        fee.setId(id);
        return fee;
    }
}
