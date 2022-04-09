package ir.blacksparrow.imh_student_election.business.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@ToString
@NoArgsConstructor
@Data
public class PersonDto {
    private String nationalId;
    private String firstName;
    private String lastName;
    private Date birthday;
    private double balance;
}
