package ir.blacksparrow.websitebackend.business.dto;

import lombok.*;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CategoryElementDtoChild {
    private Long id;
    private String code;
    private String title;
    private CategoryDto category;

    public CategoryElementDtoChild(String code, String title, CategoryDto category) {
        this.code = code;
        this.title = title;
        this.category = category;
    }
}
