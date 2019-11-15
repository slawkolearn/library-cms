package pl.edu.pjatk.librarycms.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class BookLoanMapperTest {

    private BookLoanMapper bookLoanMapper;

    @BeforeEach
    public void setUp() {
        bookLoanMapper = new BookLoanMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(bookLoanMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(bookLoanMapper.fromId(null)).isNull();
    }
}
