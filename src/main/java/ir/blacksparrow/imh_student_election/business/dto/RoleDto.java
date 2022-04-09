package ir.blacksparrow.imh_student_election.business.dto;

import ir.blacksparrow.imh_student_election.dataModel.PermissionEntity;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import java.util.Collection;
import java.util.Set;

@ToString
@NoArgsConstructor
@Data
public class RoleDto {
    private long id;
    private Collection<PermissionEntity> permissions;
}
