package ir.blacksparrow.websitebackend.view.viewDto.user.viewDto;

import ir.blacksparrow.websitebackend.view.viewDto.person.viewDto.PersonViewDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserViewDtoChild {
    @NotNull
    private String username;

    @NotNull
    private String password;

    @NotNull
    @Email
    private String emailAddress;

    @NotNull
    private PersonViewDto person;

    @NotNull
    private long categoryElementId;
}
