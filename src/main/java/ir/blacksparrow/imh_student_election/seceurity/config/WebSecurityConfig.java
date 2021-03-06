package ir.blacksparrow.imh_student_election.seceurity.config;

//import ir.blacksparrow.websitebackend.business.sevice.user.UserService;
import ir.blacksparrow.imh_student_election.business.sevice.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static  ir.blacksparrow.imh_student_election.constant.enums.UserPermissionEnum.*;
import static ir.blacksparrow.imh_student_election.constant.enums.UserRoleEnum.*;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserService userService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public WebSecurityConfig(UserService userService, BCryptPasswordEncoder bCryptPasswordEncoder1) {
        this.userService = userService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder1;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        super.configure(auth);
//        auth
//                .inMemoryAuthentication()
//                .withUser("esmirk.137@gmail.com")
//                .password(bCryptPasswordEncoder.encode("password"))
//                .roles("ADMIN").authorities("ACCESS_TEST1","ACCESS_TEST1");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/swagger-ui/**").permitAll()
                .antMatchers("/v3/api-docs/**").permitAll()
//                .antMatchers("/", "index","/swagger-ui.html","/swagger-ui/**", "/user/**", "/category/**", "/category-element/**", "/permission/**", "/role/**").permitAll()
                .antMatchers("/category/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET,"/category/**").hasAuthority("category:read")
                .antMatchers(HttpMethod.POST,"/category/**").hasAuthority("category:write")
                .antMatchers(HttpMethod.GET,"/category-element/**").hasAuthority("category-element:read")
                .antMatchers(HttpMethod.POST,"/category-element/**").hasAuthority("category-element:write")
//                .antMatchers("/category-element/**").hasAuthority(admin.name())
//                .antMatchers("/category/**").hasRole()
                .antMatchers("/", "index","/swagger-ui.html","/swagger-ui/**", "/user/**").permitAll()
                .anyRequest()
                .authenticated()
                .and()
//                .httpBasic();
//                .antMatchers("/swagger-ui/**").permitAll()
//                .anyRequest()
//                .authenticated().and()
                .httpBasic();
//                .formLogin();
    }



    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider provider= new DaoAuthenticationProvider();
        provider.setPasswordEncoder(bCryptPasswordEncoder);
        provider.setUserDetailsService(userService);
        return provider;
    }

//    @Override
//    @Bean
//    protected UserDetailsService userDetailsService() {
//        UserDetails user=User.builder()
//                .username("mmd")
//                .password(bCryptPasswordEncoder.encode("mmd"))
//                .roles("STUDENT")
//                .build();
//        return new InMemoryUserDetailsManager(
//                user
//        );
//    }
}
