package pl.edu.pjatk.librarycms.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import pl.edu.pjatk.librarycms.web.rest.TestUtil;

public class OverdueFeeRateDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(OverdueFeeRateDTO.class);
        OverdueFeeRateDTO overdueFeeRateDTO1 = new OverdueFeeRateDTO();
        overdueFeeRateDTO1.setId(1L);
        OverdueFeeRateDTO overdueFeeRateDTO2 = new OverdueFeeRateDTO();
        assertThat(overdueFeeRateDTO1).isNotEqualTo(overdueFeeRateDTO2);
        overdueFeeRateDTO2.setId(overdueFeeRateDTO1.getId());
        assertThat(overdueFeeRateDTO1).isEqualTo(overdueFeeRateDTO2);
        overdueFeeRateDTO2.setId(2L);
        assertThat(overdueFeeRateDTO1).isNotEqualTo(overdueFeeRateDTO2);
        overdueFeeRateDTO1.setId(null);
        assertThat(overdueFeeRateDTO1).isNotEqualTo(overdueFeeRateDTO2);
    }
}
