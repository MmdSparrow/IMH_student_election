package ir.blacksparrow.websitebackend.view.viewDto.categoryElement.viewDto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class CategoryElementViewDto {
    @NotNull
    private String code;
    @NotNull
    private String title;
    @NotNull
    private Long categoryId;
}
