package ir.blacksparrow.imh_student_election.view.viewDto.permission.viewDto;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@ToString
@NoArgsConstructor
@Data
public class PermissionViewDto {

    @NotNull
    @Pattern(regexp="/[a-z]*:(write|read|delete|put)$/gm",message="title must be of the regex form: /[a-z]*:(write|read|delete|put)$/gm")
    private String title;
}
