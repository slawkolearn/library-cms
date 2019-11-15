package pl.edu.pjatk.librarycms.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import pl.edu.pjatk.librarycms.web.rest.TestUtil;

public class BookLoanDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(BookLoanDTO.class);
        BookLoanDTO bookLoanDTO1 = new BookLoanDTO();
        bookLoanDTO1.setId(1L);
        BookLoanDTO bookLoanDTO2 = new BookLoanDTO();
        assertThat(bookLoanDTO1).isNotEqualTo(bookLoanDTO2);
        bookLoanDTO2.setId(bookLoanDTO1.getId());
        assertThat(bookLoanDTO1).isEqualTo(bookLoanDTO2);
        bookLoanDTO2.setId(2L);
        assertThat(bookLoanDTO1).isNotEqualTo(bookLoanDTO2);
        bookLoanDTO1.setId(null);
        assertThat(bookLoanDTO1).isNotEqualTo(bookLoanDTO2);
    }
}
