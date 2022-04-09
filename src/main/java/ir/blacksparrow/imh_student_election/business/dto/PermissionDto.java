package ir.blacksparrow.imh_student_election.business.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;

@ToString
@NoArgsConstructor
@Data
public class PermissionDto {
    private long id;
    private String title;
}
