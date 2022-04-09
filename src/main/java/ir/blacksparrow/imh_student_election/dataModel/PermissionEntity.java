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
    @SequenceGenerator(
            name = "permission_sequence",
            sequenceName = "permission_sequence",
            allocationSize = 1
    )
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "permission_sequence"
    )
    @Column(name = "ID")
    private long id;

    @Column(name = "TITLE", unique = true)
    private String title;
//
//    @ManyToMany
//    @JoinColumn(name = "ROLES_ID", referencedColumnName = "ID", nullable = false)
//    private Set<RoleEntity> roles;

}
