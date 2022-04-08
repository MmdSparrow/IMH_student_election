package ir.blacksparrow.websitebackend.business.dto;

import lombok.*;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CategoryDto {
    private Long id;
    private String code;
    private String title;
}
