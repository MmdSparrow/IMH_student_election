package ir.blacksparrow.imh_student_election.business.dto;

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