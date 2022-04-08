package ir.blacksparrow.websitebackend.business.dto;

import ir.blacksparrow.websitebackend.dataModel.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TokenConfirmationDtoChild {
    private Long id;
    private String token;
    private LocalDateTime createTime;
    private LocalDateTime expireTime;
    private LocalDateTime confirmTime;

    public TokenConfirmationDtoChild(String token, LocalDateTime createTime, LocalDateTime expireTime, UserDto user) {
        this.token = token;
        this.createTime = createTime;
        this.expireTime = expireTime;
        this.user = user;
    }

    private UserDto user;
}
