package restday.api.request;

import lombok.Getter;
import lombok.Setter;
import restday.dto.HolidayDTO;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;


@Getter
@Setter
public class CreateHolidayRequest {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Valid
    private List<HolidayDTO> holidayDTOList;


}
