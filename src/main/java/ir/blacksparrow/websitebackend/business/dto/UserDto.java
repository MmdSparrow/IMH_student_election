package ir.blacksparrow.websitebackend.business.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDto  implements UserDetails {
    private String username;
    private String password;
    private String emailAddress;
    private PersonDto person;
    private CategoryElementDtoChild categoryElement;
    private boolean locked;
    private boolean enabled;


    public UserDto(String username, String password, String emailAddress, PersonDto person) {
        this.username = username;
        this.password = password;
        this.emailAddress = emailAddress;
        this.person = person;
    }

    public UserDto(String username, String password, String emailAddress, PersonDto person, CategoryElementDtoChild categoryElement) {
        this.username = username;
        this.password = password;
        this.emailAddress = emailAddress;
        this.person = person;
        this.categoryElement = categoryElement;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(categoryElement.getTitle());
        return Collections.singletonList(authority);
    }

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
