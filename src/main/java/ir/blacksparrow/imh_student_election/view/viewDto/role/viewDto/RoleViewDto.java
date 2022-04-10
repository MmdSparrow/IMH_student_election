package ir.blacksparrow.imh_student_election.view.viewDto.role.viewDto;

import ir.blacksparrow.imh_student_election.dataModel.PermissionEntity;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.util.List;

@ToString
@NoArgsConstructor
@Data
public class RoleViewDto {
    @NotNull
    private String title;

    @NotNull
    private List<Long> permissionsId;
}
