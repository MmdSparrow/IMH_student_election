package ir.blacksparrow.websitebackend.dataModel;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@ToString
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "BS_CATEGORY", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"CODE", "TITLE"})
})
public class CategoryEntity {
    @SequenceGenerator(
            name = "category_sequence",
            sequenceName = "category_sequence",
            allocationSize = 1
    )
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "category_sequence"
    )
    @Column(name = "ID")
    private long id;

    @Column(name = "CODE", nullable = false, unique = true)
    private String code;

    @Column(name = "TITLE", nullable = false)
    private String title;
}
