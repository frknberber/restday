package restday.api.request;


import lombok.Getter;
import lombok.Setter;
import restday.dto.EmployeeDTO;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;


@Getter
@Setter
public class CreateEmployeeListRequest {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Valid
    private List<EmployeeDTO> employeeDTOList;

}