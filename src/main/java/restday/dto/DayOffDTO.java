package restday.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;


@Getter
@Setter
public class DayOffDTO implements Serializable {

    private Long id;
    private String employeeID;
    private String dayOffDateRange;
    private Integer dayOffDateCount;
    private LocalDate dayOffCreatedDate;
    private String processStatus;

}
