package pl.edu.pjatk.librarycms.repository;
import pl.edu.pjatk.librarycms.domain.Fee;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Fee entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FeeRepository extends JpaRepository<Fee, Long> {

}
