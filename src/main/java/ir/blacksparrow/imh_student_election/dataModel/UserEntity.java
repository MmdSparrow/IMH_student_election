package ir.blacksparrow.imh_student_election.dataModel;


import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@ToString
@Getter
@Setter
@Entity
@Table(name = "BS_USER")
public class UserEntity {
    @Id
    @Column(name = "USERNAME")
    private String username;

    @NotNull
    @Column(name = "PASSWORD")
    private String password;

    @NotNull
    @Column(name = "EMAIL_ADDRESS", unique = true)
    private String emailAddress;

    @NotNull
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PERSON_ID", referencedColumnName = "NATIONAL_ID")
    private PersonEntity person;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CATEGORY_ELEMENT_ID", referencedColumnName = "ID")
    private RoleEntity role;

    @Column(name = "LOCKED")
    private boolean locked;

    @Column(name = "ENABLED")
    private boolean enabled;

}
