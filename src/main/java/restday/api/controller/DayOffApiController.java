package restday.api.controller;

import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import restday.api.request.CreateDayOffRequest;
import restday.api.request.CreateEmployeeListRequest;
import restday.api.request.CreateHolidayRequest;
import restday.api.response.DayOffListResponse;
import restday.dto.DayOffDTO;
import restday.exception.RecordNotFoundException;
import restday.service.DayOffCommandService;

@RestController
public class DayOffApiController {

    private final DayOffCommandService dayOffCommandService;

    @Autowired
    public DayOffApiController(DayOffCommandService dayOffCommandService){
        this.dayOffCommandService = dayOffCommandService;

    }


    @PostMapping(value = "/api/create/employeelist", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "", notes = "Create employee list")
    public ResponseEntity<Long> createEmployeeList(@RequestBody CreateEmployeeListRequest createEmployeeListRequest)
    {
        return dayOffCommandService.createEmployeeList(createEmployeeListRequest.getEmployeeDTOList());
    }

    @PostMapping(value = "/api/create/holidays", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "", notes = "Create holidays")
    public ResponseEntity<Long> createHolidays(@RequestBody CreateHolidayRequest createHolidayRequest) {
        return dayOffCommandService.createHoliday(createHolidayRequest.getHolidayDTOList());
    }


    @PostMapping(value = "/api/create/dayoff", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "", notes = "Create day off")
    public ResponseEntity<Long> createDayOff(@RequestBody CreateDayOffRequest createDayOffRequest) {
        return dayOffCommandService.createDayOff(createDayOffRequest);

    }

    @PutMapping(value = "/api/update/dayoff", consumes = MediaType.ALL_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "", notes = "Update day off")
    public ResponseEntity<Long> updateDayOff(@RequestParam(required = true) Long dayOffId , String processStatus) {
        return dayOffCommandService.updateDayOff(dayOffId,processStatus);

    }

    @GetMapping(value = "/api/get/dayoff", consumes = MediaType.ALL_VALUE)
    @ApiOperation(value = "", notes = "Get day offs")
    public ResponseEntity<DayOffDTO> getDayOff(@RequestParam(required = true) Long dayOffId) {

        return dayOffCommandService.getDayOff(dayOffId);
    }

    @GetMapping(value = "/api/get/dayoffs", consumes = MediaType.ALL_VALUE , produces = "application/json; charset=UTF-8")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "", notes = "Get day offs")
    public ResponseEntity<DayOffListResponse> getDayOffsByEmployeeId(@RequestParam(required = true) String employeeId) {
        return dayOffCommandService.getDayOffsByEmployeeId(employeeId);

    }


    /*@GetMapping(value = "/error", consumes = MediaType.ALL_VALUE)
    @ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "video not found")
    @ApiOperation(value = "", notes = "Get day offs")
    public String getError() {
        //throw new RecordNotFoundException("---test---") ;
        return "--------test-------" ;
    }*/

}
