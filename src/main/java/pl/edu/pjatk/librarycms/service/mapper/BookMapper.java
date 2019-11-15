package pl.edu.pjatk.librarycms.service.mapper;

import pl.edu.pjatk.librarycms.domain.*;
import pl.edu.pjatk.librarycms.service.dto.BookDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Book} and its DTO {@link BookDTO}.
 */
@Mapper(componentModel = "spring", uses = {OverdueFeeRateMapper.class, AuthorMapper.class})
public interface BookMapper extends EntityMapper<BookDTO, Book> {

    @Mapping(source = "author.id", target = "authorId")
    BookDTO toDto(Book book);

    @Mapping(target = "ratings", ignore = true)
    @Mapping(target = "removeRating", ignore = true)
    @Mapping(target = "loans", ignore = true)
    @Mapping(target = "removeLoan", ignore = true)
    @Mapping(target = "removeOverdueFeeRate", ignore = true)
    @Mapping(source = "authorId", target = "author")
    Book toEntity(BookDTO bookDTO);

    default Book fromId(Long id) {
        if (id == null) {
            return null;
        }
        Book book = new Book();
        book.setId(id);
        return book;
    }
}
