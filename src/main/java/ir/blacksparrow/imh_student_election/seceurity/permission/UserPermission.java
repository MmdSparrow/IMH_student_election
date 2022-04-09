package ir.blacksparrow.imh_student_election.seceurity.permission;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserPermission {

    CATEGORY_R("category:read"),
    CATEGORY_W("category:write"),

    CATEGORY_ELEMENT_R("category_element:read"),
    CATEGORY_ELEMENT_W("category_element:write");

    private final String permission;

}
