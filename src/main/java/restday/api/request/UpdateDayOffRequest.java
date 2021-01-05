package restday.api.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class UpdateDayOffRequest {

    @NotNull
    private Long dayOffId;
}
