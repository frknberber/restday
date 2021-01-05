package restday.service;


import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import restday.api.request.CreateDayOffRequest;
import restday.api.request.CreateEmployeeRequest;
import restday.api.response.DayOffListResponse;
import restday.api.response.DayOffResponse;
import restday.domain.DayOff;
import restday.dto.DayOffDTO;
import restday.dto.EmployeeDTO;
import restday.dto.HolidayDTO;
import restday.exception.DayOffException;

import java.util.List;


@Service
public interface DayOffCommandService  {


    ResponseEntity<Long> createEmployeeList(List<EmployeeDTO> employeeDTOList);

    ResponseEntity<Long> createHoliday(List<HolidayDTO> holidayDTOList);

    ResponseEntity<Long> createDayOff(CreateDayOffRequest request) ;

    ResponseEntity<Long> updateDayOff (Long dayOffId,String processStatus) ;

    ResponseEntity<DayOffDTO> getDayOff (Long dayOffId) throws DayOffException;

    ResponseEntity<DayOffListResponse> getDayOffsByEmployeeId (String employeeId) ;

}
