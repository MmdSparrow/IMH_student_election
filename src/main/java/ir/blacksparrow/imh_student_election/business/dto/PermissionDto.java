package ir.blacksparrow.imh_student_election.business.dto;

import ir.blacksparrow.imh_student_election.dataModel.PermissionEntity;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import java.util.List;

@ToString
@NoArgsConstructor
@Data
public class PermissionDto {
    private Long id;
    private String title;
//    private List<RoleDto> roles;


    public PermissionDto(String title) {
        this.title = title;
    }
}
