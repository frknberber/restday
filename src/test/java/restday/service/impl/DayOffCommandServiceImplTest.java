package restday.service.impl;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import restday.api.response.DayOffListResponse;
import restday.domain.DayOff;
import restday.domain.Employee;
import restday.dto.DayOffDTO;
import restday.dto.EmployeeDTO;
import restday.repository.DayOffRepository;
import restday.repository.EmployeeRepository;
import restday.repository.HolidaysRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DayOffCommandServiceImplTest {

    @Mock
    private DayOffRepository dayOffRepository;
    @Mock
    private EmployeeRepository employeeRepository;
    @Mock
    private HolidaysRepository holidaysRepository;

    private DayOffCommandServiceImpl dayOffCommandService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        dayOffCommandService = new DayOffCommandServiceImpl(dayOffRepository, employeeRepository, holidaysRepository);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getDayOff() {
        Long id = 2L;

        DayOff dayOff = new DayOff();
        dayOff.setDayOffDateCount(2);
        dayOff.setId(id);
        dayOff.setProcessStatus("perfect");
        dayOff.setDayOffDateRange("01");
        dayOff.setDayOffCreatedDate(LocalDate.now());
        dayOff.setEmployeeID("E123465");

        Mockito.when(dayOffRepository.getOne(id)).thenReturn(dayOff);

        ResponseEntity<DayOffDTO> returnedDayOff = dayOffCommandService.getDayOff(id);
        assertEquals(dayOff.getEmployeeID(), returnedDayOff.getBody().getEmployeeID());
    }

    @Test
    void getDayOffsByEmployeeId() {

        String id = "2L";

        DayOff dayOff = new DayOff();
        dayOff.setDayOffDateCount(2);
        dayOff.setId(2L);
        dayOff.setProcessStatus("perfect");
        dayOff.setDayOffDateRange("01");
        dayOff.setDayOffCreatedDate(LocalDate.now());
        dayOff.setEmployeeID("E123465");

        List<DayOff> dayOffList = new ArrayList<>() ;
        dayOffList.add(dayOff);

        DayOffListResponse response = new DayOffListResponse();
        response.setList(dayOffList);

        Mockito.when(dayOffRepository.findDayOffsByEmployeeID(id)).thenReturn(dayOffList);
        ResponseEntity<DayOffListResponse> returnedDayOff = dayOffCommandService.getDayOffsByEmployeeId(id);

        assertEquals(response.getList().get(0).getEmployeeID(), returnedDayOff.getBody().getList().get(0).getEmployeeID());

    }

    @Test
    void createEmployeeList(){


    }

    @Test
    void createEmployee(){


    }
}