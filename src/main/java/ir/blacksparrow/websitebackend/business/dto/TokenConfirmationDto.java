package ir.blacksparrow.websitebackend.business.dto;

import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;

@ToString
@Data
public class TokenConfirmationDto {
    private Long id;
    private String token;
    private LocalDateTime createTime;
    private LocalDateTime expireTime;
    private LocalDateTime confirmTime;
    private String username;
}
