package restday.service.impl;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import restday.api.handler.DayOffValidationRule;
import restday.api.request.*;
import restday.api.response.DayOffListResponse;
import restday.api.response.DayOffResponse;
import restday.domain.DayOff;
import restday.domain.Employee;
import restday.domain.Holidays;
import restday.dto.DayOffDTO;
import restday.dto.EmployeeDTO;
import restday.dto.HolidayDTO;
import restday.exception.DayOffException;
import restday.exception.RecordNotFoundException;
import restday.repository.DayOffRepository;
import restday.repository.EmployeeRepository;
import restday.repository.HolidaysRepository;
import restday.service.DayOffCommandService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Service
@Transactional
public class DayOffCommandServiceImpl implements DayOffCommandService {

    private DayOffRepository dayOffRepository ;
    private EmployeeRepository employeeRepository ;
    private HolidaysRepository holidaysRepository;


    @Autowired
    public DayOffCommandServiceImpl(DayOffRepository dayOffRepository, EmployeeRepository employeeRepository, HolidaysRepository holidaysRepository){
        this.dayOffRepository=dayOffRepository;
        this.employeeRepository = employeeRepository;
        this.holidaysRepository = holidaysRepository;
    }

    public ResponseEntity<Long> createDayOff(CreateDayOffRequest request) {

        Employee employee = employeeRepository.findEmployeeByEmployeeId(request.getEmployeeId());

        if(employee !=null){
            throw new RecordNotFoundException("Not found : " + request.getEmployeeId()) ;
        }
        else{
            int workingYear = this.calculateYear(employee.getStartDateWork(),LocalDate.now().toString()) ;

            holidayControl(request.getDateRange());

            DayOffResult daysAndCount = dayOffCreateControl(request.getDateRange());

            if( (employee.getRemainingDayOff() - daysAndCount.getDatesCount() < 0) ||
                    (workingYear==0 && employee.getRemainingDayOff() - daysAndCount.getDatesCount() < -5)){

                throw new RecordNotFoundException("Not found : " + request.getEmployeeId()) ;
            }
            else{
                DayOff dayOff = new DayOff();
                dayOff.setEmployeeID(employee.getEmployeeId());
                dayOff.setDayOffDateRange(daysAndCount.getDates());
                dayOff.setDayOffDateCount(daysAndCount.getDatesCount());
                dayOff.setDayOffCreatedDate(LocalDate.now());
                dayOff.setProcessStatus("Onay Bekleniyor");
                dayOffRepository.save(dayOff);
            }
        }

        return new ResponseEntity<Long>(1L, HttpStatus.OK) ;
    }

    public ResponseEntity<Long> updateDayOff (Long dayOffId,String processStatus){

        DayOff dayoff = dayOffRepository.getOne(dayOffId) ;

        if(dayoff==null){
            throw new RecordNotFoundException("Not found : " + dayOffId) ;
        }

        dayoff.setProcessStatus(processStatus);
        if(processStatus.equals("Onaylandı")==true){
            Employee employee = employeeRepository.findEmployeeByEmployeeId(dayoff.getEmployeeID());
            employee.setRemainingDayOff(employee.getRemainingDayOff() - dayoff.getDayOffDateCount());
            employeeRepository.save(employee);
        }
        dayOffRepository.save(dayoff);
        return new ResponseEntity<Long>(1L, HttpStatus.OK) ;

    }

    public ResponseEntity<DayOffDTO> getDayOff (Long id) {

        try {
            DayOff dayoff = dayOffRepository.getOne(id);
            DayOffDTO dayOffDTO = new DayOffDTO();
            dayOffDTO.setId(dayoff.getId());
            dayOffDTO.setEmployeeID(dayoff.getEmployeeID());
            dayOffDTO.setDayOffDateRange(dayoff.getDayOffDateRange());
            dayOffDTO.setDayOffDateCount(dayoff.getDayOffDateCount());
            dayOffDTO.setProcessStatus(dayoff.getProcessStatus());
            return new ResponseEntity<DayOffDTO>(dayOffDTO, HttpStatus.OK) ;
        }
        catch (Exception e){
            throw new RecordNotFoundException("Not found : " + id) ;
        }

    }

    public ResponseEntity<DayOffListResponse> getDayOffsByEmployeeId (String employeeId) {

        DayOffListResponse response = new DayOffListResponse();

        List<DayOff> dayOffList = dayOffRepository.findDayOffsByEmployeeID(employeeId);
        if(dayOffList == null || dayOffList.size()==0){
            throw new RecordNotFoundException("Not found : " + employeeId) ;
        }

        response.setList(dayOffList);
        return new ResponseEntity<DayOffListResponse>(response, HttpStatus.OK) ;
    }

    public ResponseEntity<Long> createEmployeeList(List<EmployeeDTO> employeeDTOList) {

        if(employeeDTOList == null || employeeDTOList.size()==0){
            throw new RecordNotFoundException("Not found : ") ;
        }

        for (EmployeeDTO item : employeeDTOList) {
            createEmployee(item);
        }
        return new ResponseEntity<Long>(1L, HttpStatus.OK) ;
    }

    public void createEmployee(EmployeeDTO employeeDTO){
        Employee employee = new Employee();
        employee.setEmployeeId(employeeDTO.getEmployeeId());
        employee.setFirstName(employeeDTO.getFirstName());
        employee.setLastName(employeeDTO.getLastName());
        employee.setStartDateWork(employeeDTO.getStartDateWork());
        int earnedDayOff = this.earnedDayOff(employeeDTO.getStartDateWork(),LocalDate.now().toString()) ;
        employee.setRemainingDayOff(earnedDayOff);
        employeeRepository.save(employee);
    }

    public ResponseEntity<Long> createHoliday(List<HolidayDTO> holidayDTOList){

        for (HolidayDTO item : holidayDTOList) {
            Holidays holidays = new Holidays();
            holidays.setHolidayName(item.getHolidayName());
            holidays.setHolidayDateRange(item.getHolidayDateRange());
            holidays.setHolidayYear(item.getHolidayYear());
            holidaysRepository.save(holidays);

        }
        return new ResponseEntity<Long>(1L, HttpStatus.OK) ;
    }



    private DayOffResult dayOffCreateControl(String dayOffDateRange){

        StringBuilder strBuild = new StringBuilder();
        int dayOffCount = 0 ;
        String dateArr[] = dayOffDateRange.split("-");

        for(int i=0 ; i<dateArr.length ; i++ ) {
            if(weekendControl(dateToDay(dateArr[i]))==false && holidayControl(dateArr[i])==false) {
                strBuild.append(dateArr[i]);
                System.out.println("strBuild.append : " + dateArr[i]);
                dayOffCount++ ;

                if(i!=dateArr.length-1){
                    strBuild.append("-");
                }
            }

        }

        return new DayOffResult(strBuild.toString(),dayOffCount) ;
    }

    private boolean holidayControl(String dateOffDay){

        List<Holidays> holidaysList = holidaysRepository.findAll();

        for(String date :holidaysDayList(holidaysList)){
            //System.out.println("date : " + date);
            if(dateOffDay.equals(date)){
                return true ;
            }
        }

        return false ;
    }

    private int earnedDayOff (String start , String now){
        int workingYear = this.calculateYear(start,now) ;
        if(workingYear==0){
            return 0 ;
        }
        else if(1<workingYear && workingYear <=5){
            return 15 ;
        }
        else if(5<workingYear && workingYear <= 10 ){
            return 18 ;
        }
        else if (workingYear > 10){
            return 24 ;
        }

        return 0 ;
    }

    private int calculateYear(String start , String now){

        String arr[] = now.toString().split("-");
        String remakeDate = arr[2] + "/" + arr[1] + "/"  + arr[0] ;

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
        Date firstDate = null;
        Date secondDate = null;
        try {
            firstDate = sdf.parse(start);
            secondDate = sdf.parse(remakeDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        long diffInMillies = Math.abs(secondDate.getTime() - firstDate.getTime());
        long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);

        //System.out.println("difference : "  + diff);

        if((int)diff<360){
            return 0 ;
        }

        return (int)diff/360;
    }

    private boolean weekendControl (String day){
        if(day.equals("Sat") || day.equals("Sun")){
            return true ;
        }
        return  false ;
    }

    private String dateToDay (String date){
        return LocalDate.parse(date , DateTimeFormatter.ofPattern( "d/M/uuuu" )).getDayOfWeek().getDisplayName(TextStyle.SHORT_STANDALONE , Locale.US) ;
    }

    private List<String> holidaysDayList (List<Holidays> holidaysList){

        ArrayList<String> list = new ArrayList<>();

        for(int i =0 ; i<holidaysList.size() ; i++){
            String dateArr[] = holidaysList.get(i).getHolidayDateRange().split("-");
            for(String date :dateArr){
                list.add(date);
            }
        }

        return list ;
    }


}

@Getter
@Setter
final class DayOffResult {
    private final String dates;
    private final int datesCount;

    public DayOffResult(String dates, int datesCount) {
        this.dates = dates;
        this.datesCount = datesCount;
    }

}
