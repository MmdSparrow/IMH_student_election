package ir.blacksparrow.websitebackend.seceurity.permission;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserPermission {
    CATEGORY_READ("category:read"),
    CATEGORY_WRITE("category:write"),
    CATEGORY_ELEMENT_WRITE("category_element:write"),
    CATEGORY_ELEMENT_READ("category_element:read");

    private final String permission;

}
