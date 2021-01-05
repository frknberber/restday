package restday.api.request;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class CreateDayOffRequest {

    private String employeeId ;

    private String dateRange;
}
