package ir.blacksparrow.imh_student_election.constant.enums;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import java.util.Set;

@AllArgsConstructor
@Getter
public enum UserPermissionEnum {
    CATEGORY_READ("category:read"),
    CATEGORY_WRITE("category:write"),
    CATEGORY_ELEMENT_READ("category_element:read"),
    CATEGORY_ELEMENT_WRITE("category_element:write");

    private final String permission;


}
