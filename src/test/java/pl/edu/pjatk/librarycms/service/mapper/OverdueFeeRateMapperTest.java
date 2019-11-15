package pl.edu.pjatk.librarycms.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class OverdueFeeRateMapperTest {

    private OverdueFeeRateMapper overdueFeeRateMapper;

    @BeforeEach
    public void setUp() {
        overdueFeeRateMapper = new OverdueFeeRateMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(overdueFeeRateMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(overdueFeeRateMapper.fromId(null)).isNull();
    }
}
