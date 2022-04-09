package ir.blacksparrow.imh_student_election.constant.enums;

import com.google.common.collect.Sets;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

import static ir.blacksparrow.imh_student_election.constant.enums.UserPermissionEnum.*;

@AllArgsConstructor
@Getter
public enum UserRoleEnum {
    user(Sets.newHashSet(CATEGORY_READ, CATEGORY_ELEMENT_READ)),
    admin(Sets.newHashSet(CATEGORY_WRITE, CATEGORY_ELEMENT_WRITE, CATEGORY_READ, CATEGORY_ELEMENT_READ));

    private final Set<UserPermissionEnum> permission;

    public Set<SimpleGrantedAuthority> getGrantedAuthorities(){
        Set<SimpleGrantedAuthority> permissions = getPermission().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
        permissions.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return permissions;
    }
}
