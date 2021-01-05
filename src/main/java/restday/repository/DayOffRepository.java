package restday.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import restday.domain.DayOff;
import java.util.List;
import java.util.Optional;


@Repository
public interface DayOffRepository extends JpaRepository<DayOff, Long> {

    /*DayOff findDayOffByEmployeeId(String employeeId) ;*/

    List<DayOff> findDayOffsByEmployeeID(String employeeId) ;


}
