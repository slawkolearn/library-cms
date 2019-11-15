package pl.edu.pjatk.librarycms.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import pl.edu.pjatk.librarycms.web.rest.TestUtil;

public class BookLoanTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(BookLoan.class);
        BookLoan bookLoan1 = new BookLoan();
        bookLoan1.setId(1L);
        BookLoan bookLoan2 = new BookLoan();
        bookLoan2.setId(bookLoan1.getId());
        assertThat(bookLoan1).isEqualTo(bookLoan2);
        bookLoan2.setId(2L);
        assertThat(bookLoan1).isNotEqualTo(bookLoan2);
        bookLoan1.setId(null);
        assertThat(bookLoan1).isNotEqualTo(bookLoan2);
    }
}
