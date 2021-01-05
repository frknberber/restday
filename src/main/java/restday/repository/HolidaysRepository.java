package restday.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import restday.domain.DayOff;
import restday.domain.Holidays;


@Repository
public interface HolidaysRepository extends JpaRepository<Holidays, Long> {

    Holidays findHolidaysByHolidayName(String holidayName) ;

}
