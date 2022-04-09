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
    @SequenceGenerator(
            name = "role_sequence",
            sequenceName = "role_sequence",
            allocationSize = 1
    )
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "role_sequence"
    )
    @Column(name = "ID")
    private long id;

    @Column(name = "TITLE", unique = true)
    private String title;

    @ManyToMany
    @JoinColumn(name = "PERMISSIONS_ID", referencedColumnName = "ID")
    private Set<PermissionEntity> permissions;

}
