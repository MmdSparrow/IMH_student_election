package ir.blacksparrow.imh_student_election.view.viewDto.category.viewDto;

import lombok.Data;

import javax.validation.constraints.NotNull;


@Data
public class CategoryViewDto {
    @NotNull
    private String code;
    @NotNull
    private String title;
}
