package pl.edu.pjatk.librarycms.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import pl.edu.pjatk.librarycms.web.rest.TestUtil;

public class OverdueFeeRateTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(OverdueFeeRate.class);
        OverdueFeeRate overdueFeeRate1 = new OverdueFeeRate();
        overdueFeeRate1.setId(1L);
        OverdueFeeRate overdueFeeRate2 = new OverdueFeeRate();
        overdueFeeRate2.setId(overdueFeeRate1.getId());
        assertThat(overdueFeeRate1).isEqualTo(overdueFeeRate2);
        overdueFeeRate2.setId(2L);
        assertThat(overdueFeeRate1).isNotEqualTo(overdueFeeRate2);
        overdueFeeRate1.setId(null);
        assertThat(overdueFeeRate1).isNotEqualTo(overdueFeeRate2);
    }
}
