package ir.blacksparrow.imh_student_election.business.dto;

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
