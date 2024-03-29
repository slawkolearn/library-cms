package pl.edu.pjatk.librarycms.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import pl.edu.pjatk.librarycms.web.rest.TestUtil;

public class FeeTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Fee.class);
        Fee fee1 = new Fee();
        fee1.setId(1L);
        Fee fee2 = new Fee();
        fee2.setId(fee1.getId());
        assertThat(fee1).isEqualTo(fee2);
        fee2.setId(2L);
        assertThat(fee1).isNotEqualTo(fee2);
        fee1.setId(null);
        assertThat(fee1).isNotEqualTo(fee2);
    }
}
