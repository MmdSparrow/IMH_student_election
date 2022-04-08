package ir.blacksparrow.websitebackend.view.viewDto.user.viewDto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserViewDto {
    @NotNull
    private String username;

    @NotNull
    private String password;

    @NotNull
    @Email
    private String emailAddress;

    @NotNull
    private long personId;

    @NotNull
    private long categoryElementId;
}
