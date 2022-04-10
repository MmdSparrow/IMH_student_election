package ir.blacksparrow.imh_student_election.business.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@ToString
@NoArgsConstructor
@Data
public class RoleDto {
    private String title;
    private List<String> permissionsTitle;
}
