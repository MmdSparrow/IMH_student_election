package ir.blacksparrow.imh_student_election.business.dto;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;

@ToString
@Getter
@Setter
public class UserDto implements UserDetails {
    private String username;
    private String password;
    private String emailAddress;
    private PersonDto person;
//    private CategoryElementDtoChild categoryElement;
    private RoleDto roleDto;
    private Set<GrantedAuthority> authorities;
    private boolean locked;
    private boolean enabled;
//
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(categoryElement.getTitle());
//        return Collections.singletonList(authority);
//    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
