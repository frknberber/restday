package restday.api.response;

import lombok.Getter;
import lombok.Setter;
import restday.api.handler.DayOffValidationRule;

@Getter
@Setter
public class DayOffResponse {

    private boolean responseStatus;
    private DayOffValidationRule responseMessage;

    public DayOffResponse(boolean responseStatus,DayOffValidationRule responseMessage){
        this.responseMessage = responseMessage;
        this.responseStatus = responseStatus;
    }
}
