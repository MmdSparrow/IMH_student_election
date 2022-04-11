package ir.blacksparrow.imh_student_election.business.sevice.user;

import ir.blacksparrow.imh_student_election.business.dto.PermissionDto;
import ir.blacksparrow.imh_student_election.business.dto.RoleDtoChild;
import ir.blacksparrow.imh_student_election.business.dto.TokenConfirmationDtoChild;
import ir.blacksparrow.imh_student_election.business.dto.UserDto;
import ir.blacksparrow.imh_student_election.repository.person.PersonRepository;
import ir.blacksparrow.imh_student_election.repository.role.RoleRepository;
import ir.blacksparrow.imh_student_election.repository.tokenConfirmation.TokenConfirmationRepository;
import ir.blacksparrow.imh_student_election.repository.user.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.rmi.ServerException;
import java.time.LocalDateTime;
import java.util.*;

@AllArgsConstructor
@Service
public class UserService implements IUserService, UserDetailsService {

    private final UserRepository userRepository;
    private final PersonRepository personRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final TokenConfirmationRepository tokenConfirmationRepository;

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String emailAddress) throws UsernameNotFoundException {
        UserDto user= userRepository.findByEmail(emailAddress)
                .orElseThrow(()-> new UsernameNotFoundException("user not found!"));
        if (user == null) {
            return new org.springframework.security.core.userdetails.User(
                    " ", " ", true, true, true, true,
                    getAuthorities(List.of(
                            roleRepository.getByTitle("ROLE_USER"))));
        }
        System.out.println("test...........................................");
        Collection<RoleDtoChild> collection=new ArrayList<>();
        collection.add(user.getRole());

        System.out.println("collection...............................................");
        System.out.println(collection.toString());
        System.out.println("collection...............................................");

        System.out.println(getAuthorities(collection));
        return new org.springframework.security.core.userdetails.User(
                user.getEmailAddress(), user.getPassword(), user.isEnabled(), true, true,
                true, getAuthorities(collection));
    }

    private Collection<? extends GrantedAuthority> getAuthorities(
            Collection<RoleDtoChild> roles) {
        return getGrantedAuthorities(getPermissions(roles));
    }

    private List<String> getPermissions(Collection<RoleDtoChild> roles) {
        List<String> permissions = new ArrayList<>();
        List<PermissionDto> collection = new ArrayList<>();
        for (RoleDtoChild role : roles) {
            permissions.add(role.getTitle());
            collection.addAll(role.getPermissions());
        }
        for (PermissionDto item : collection) {
            permissions.add(item.getTitle());
        }
        return permissions;
    }

    private List<GrantedAuthority> getGrantedAuthorities(List<String> permissions) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (String permission : permissions) {
            authorities.add(new SimpleGrantedAuthority(permission));
        }
        return authorities;
    }

    @Override
    public UserDto getUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(()-> new UsernameNotFoundException("user not found!"));
    }

    @Override
    public String signupUser(UserDto user){
        boolean userExist = userRepository.findByEmail(user.getEmailAddress()).isPresent();
        if(userExist)
            throw new IllegalStateException("email already exist!");

        String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        personRepository.insertAndUpdate(user.getPerson());

        userRepository.insert(user);

        //todo: send confirmation token need test

        String token=UUID.randomUUID().toString();

        TokenConfirmationDtoChild tokenConfirmationDtoChild= new TokenConfirmationDtoChild(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(7),
                user
        );

//        System.out.println("test token//////////////////////////////////////////");
//        System.out.println(tokenConfirmationDtoChild.toString());
//        System.out.println("test token//////////////////////////////////////////");

        tokenConfirmationRepository.insertAndUpdate(tokenConfirmationDtoChild);


        return token;
    }

    @Override
    public Optional<UserDto> enableUser(String emailAddress) throws ServerException {
        UserDto userDto=userRepository
                .findByEmail(emailAddress)
                .orElseThrow(() -> new ServerException("email not found!"));
        System.out.println("whyyyyyyyyyyyyyyyyyy............................................");
        userDto.setEnabled(true);
        System.out.println(userDto.toString());
        System.out.println("whyyyyyyyyyyyyyyyyyy............................................");

        System.out.println("set enable method....................................................");
        System.out.println(userDto.toString());
        System.out.println("set enable method....................................................");

        return userRepository.insert(userDto);
    }
}
