package restday.api.request;


import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class CreateEmployeeRequest {

    private static final long serialVersionUID = 1L;

    private String employeeId ;

    private String firstName;

    private String lastName;

    private String startDateWork;

}
