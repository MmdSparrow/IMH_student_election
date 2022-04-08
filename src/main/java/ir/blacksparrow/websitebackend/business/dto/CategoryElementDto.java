package ir.blacksparrow.websitebackend.business.dto;

import lombok.*;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CategoryElementDto {
    private Long id;
    private String code;
    private String title;
    private Long categoryId;
}