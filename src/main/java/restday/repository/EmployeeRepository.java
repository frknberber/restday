package restday.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import restday.domain.Employee;


@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {


    Employee findEmployeeByEmployeeId(String employeeId) ;


}
