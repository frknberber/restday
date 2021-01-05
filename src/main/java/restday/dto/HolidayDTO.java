package restday.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class HolidayDTO {

    private String holidayName ;
    private String holidayDateRange;
    private Integer holidayYear;

}
