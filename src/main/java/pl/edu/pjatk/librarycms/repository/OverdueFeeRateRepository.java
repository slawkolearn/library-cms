package pl.edu.pjatk.librarycms.repository;
import pl.edu.pjatk.librarycms.domain.OverdueFeeRate;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the OverdueFeeRate entity.
 */
@SuppressWarnings("unused")
@Repository
public interface OverdueFeeRateRepository extends JpaRepository<OverdueFeeRate, Long> {

}
