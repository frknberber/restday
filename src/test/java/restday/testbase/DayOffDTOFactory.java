package restday.testbase;

import restday.api.handler.DayOffValidationRule;
import restday.api.request.CreateDayOffRequest;
import restday.api.response.DayOffResponse;

import java.security.SecureRandom;

public class DayOffDTOFactory {

    public CreateDayOffRequest getCreateDayOffRequest() {
        CreateDayOffRequest request = new CreateDayOffRequest() ;
        request.setDateRange("05/07/2020");
        request.setEmployeeId("1L");
        return request;
    }


}
