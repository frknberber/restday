package restday.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;
import org.springframework.data.annotation.Id;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;


@Audited
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class DayOff  implements Serializable {

    private static final long serialVersionUID = -1798070786993154676L;

    @javax.persistence.Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true, nullable = false)
    private Long id;

    @NotNull
    @Column(name = "EMPLOYEE_ID")
    private String employeeID;


    @Column(name = "DAY_OFF_DATE_RANGE", unique = false, nullable = false, length = 100)
    private String dayOffDateRange;


    @Column(name = "DAY_OFF_DATE_COUNT", unique = false, nullable = false, length = 100)
    private Integer dayOffDateCount;


    @Column(name = "DAY_OFF_CREATED_DATE", unique = false, nullable = false, length = 100)
    private LocalDate dayOffCreatedDate;


    @Column(name = "PROCESS_STATUS", unique = false, nullable = false, length = 100)
    private String processStatus;

}
