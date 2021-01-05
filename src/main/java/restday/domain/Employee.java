package restday.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.envers.Audited;
import org.springframework.data.annotation.Id;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.io.Serializable;


@Audited
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Employee  implements Serializable {

    private static final long serialVersionUID = -1798070786993154676L;

    @javax.persistence.Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true, nullable = false)
    private Long id;

    @Column(name = "EMPLOYEE_ID", unique = false, nullable = false, length = 100)
    private String employeeId;


    @Column(name = "FIRST_NAME", unique = false, nullable = false, length = 100)
    private String firstName;


    @Column(name = "LAST_NAME", unique = false, nullable = false, length = 100)
    private String lastName;


    @Column(name = "START_DATE_WORK", unique = false, nullable = false, length = 100)
    private String startDateWork;


    @Column(name = "REMAINING_DAY_OFF", unique = false, nullable = false, length = 100)
    private Integer remainingDayOff;


}
