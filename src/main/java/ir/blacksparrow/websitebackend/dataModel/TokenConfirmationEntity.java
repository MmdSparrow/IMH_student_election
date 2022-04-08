package ir.blacksparrow.websitebackend.dataModel;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "BS_TOKEN_CONFIRMATION")
public class TokenConfirmationEntity {

    @SequenceGenerator(
            name = "token_confirmation_sequence",
            sequenceName = "token_confirmation_sequence",
            allocationSize = 1
    )
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "token_confirmation_sequence"
    )
    @Column(name = "ID")
    private long id;

    @Column(name = "TOKEN", nullable = false)
    private String token;

    @Column(name = "CREATE_TIME", nullable = false)
    private LocalDateTime createTime;

    @Column(name = "EXPIRE_TIME", nullable = false)
    private LocalDateTime expireTime;

    @Column(name = "CONFIRM_TIME")
    private LocalDateTime confirmTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ENTITY", referencedColumnName = "USERNAME", nullable = false)
    private UserEntity user;
}
