package saechim.interior.userservice.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import saechim.interior.userservice.service.UserService;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurity extends WebSecurityConfigurerAdapter {
    private final UserService userService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final Environment env;

    @Override
    protected void configure(HttpSecurity http) throws Exception {  //권한
       // http.csrf().disable();
        http.authorizeRequests().antMatchers("/**").permitAll()
//                .access("hasIpAddress('192.168.200.152')" +
//                        " or hasIpAddress('172.30.1.31')") //집앞 카페 ip..
//                .hasIpAddress("192.168.200.118") //통과시키고 싶은 IP
                .and()
                .addFilter(getAuthenticationFilter());

        //http.headers().frameOptions().disable();
    }

    private AuthenticationFilter getAuthenticationFilter() throws Exception {
       AuthenticationFilter authenticationFilter = new AuthenticationFilter(authenticationManager(), userService, env);
       authenticationFilter.setAuthenticationManager(authenticationManager());

        return authenticationFilter;
    }


    // select pwd from users where email = ?
    // db.pwd(encrypted)== input_pwd(encrypted)
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception { //인증
        auth.userDetailsService(userService).passwordEncoder(bCryptPasswordEncoder);
    }
}
