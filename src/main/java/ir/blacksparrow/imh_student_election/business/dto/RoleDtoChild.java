package ir.blacksparrow.imh_student_election.business.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@ToString
@NoArgsConstructor
@Data
public class RoleDtoChild {
    private String title;
    private List<PermissionDto> permissions;
}
