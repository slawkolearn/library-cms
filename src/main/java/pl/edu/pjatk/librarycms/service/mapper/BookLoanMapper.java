package pl.edu.pjatk.librarycms.service.mapper;

import pl.edu.pjatk.librarycms.domain.*;
import pl.edu.pjatk.librarycms.service.dto.BookLoanDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link BookLoan} and its DTO {@link BookLoanDTO}.
 */
@Mapper(componentModel = "spring", uses = {BookMapper.class, ClientMapper.class})
public interface BookLoanMapper extends EntityMapper<BookLoanDTO, BookLoan> {

    @Mapping(source = "book.id", target = "bookId")
    @Mapping(source = "client.id", target = "clientId")
    BookLoanDTO toDto(BookLoan bookLoan);

    @Mapping(target = "fees", ignore = true)
    @Mapping(target = "removeFee", ignore = true)
    @Mapping(source = "bookId", target = "book")
    @Mapping(source = "clientId", target = "client")
    BookLoan toEntity(BookLoanDTO bookLoanDTO);

    default BookLoan fromId(Long id) {
        if (id == null) {
            return null;
        }
        BookLoan bookLoan = new BookLoan();
        bookLoan.setId(id);
        return bookLoan;
    }
}
