package ir.blacksparrow.websitebackend.dataModel;

import javax.validation.constraints.NotNull;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "BS_PERSON")
public class PersonEntity {

    @Id
    @Column(name = "NATIONAL_ID")
    private String nationalId;

    @Column(name = "FIRST_NAME", nullable = false)
    private String firstName;

    @Column(name = "LAST_NAME", nullable = false)
    private String lastName;

    @Column(name = "BIRTH_DATE", nullable = false)
    private Date birthday;

    @Column(name = "BALACNE", columnDefinition = "REAL DEFAULT 0")
    private double balance;
}
