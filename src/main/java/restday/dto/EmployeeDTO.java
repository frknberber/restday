package restday.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class EmployeeDTO {

    @NotBlank
    private String employeeId ;
    private String firstName;
    private String lastName;
    private String startDateWork;

}
