package restday.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.envers.Audited;
import org.springframework.data.annotation.Id;

import javax.persistence.*;
import java.io.Serializable;


@Audited
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Holidays implements Serializable {

    private static final long serialVersionUID = -1798070786993154676L;

    @javax.persistence.Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true, nullable = false)
    private Long id;


    @Column(name = "HOLIDAY_NAME")
    private String holidayName;


    @Column(name = "HOLIDAY_DATE_RANGE", unique = false, nullable = false, length = 100)
    private String holidayDateRange;


    @Column(name = "HOLIDAY_YEAR", unique = false, nullable = false, length = 100)
    private Integer holidayYear;
}
