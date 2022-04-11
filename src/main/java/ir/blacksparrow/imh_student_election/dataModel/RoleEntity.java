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
@Table(name = "BS_ROLE"
//        , uniqueConstraints = {@UniqueConstraint(columnNames = {"TITLE", "PERMISSIONS_ID"})}
)
public class RoleEntity {

    @Id
    @Column(name = "TITLE", unique = true)
    private String title;

    @ManyToMany
    @JoinColumn(name = "PERMISSIONS_TITLE", referencedColumnName = "TITLE")
    private Set<PermissionEntity> permissions;
}
