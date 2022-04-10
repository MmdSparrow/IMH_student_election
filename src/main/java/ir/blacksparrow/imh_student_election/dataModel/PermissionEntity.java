package ir.blacksparrow.imh_student_election.dataModel;

import lombok.*;

import javax.persistence.*;
import java.util.Set;


@ToString
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "BS_PERMISSION"
//        , uniqueConstraints = {@UniqueConstraint(columnNames = {"TITLE", "ROLES_ID"})}
)
public class PermissionEntity {
    @Id
    @Column(name = "TITLE", unique = true)
    private String title;

    @ManyToMany
    @JoinColumn(name = "ROLES_ID", referencedColumnName = "ID")
    private Set<RoleEntity> roles;

}
