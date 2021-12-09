package lpnu.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {

        auth.inMemoryAuthentication()
                .withUser("user").password(passwordEncoder().encode(("user"))).roles("USER")
                .and()
                .withUser("admin").password(passwordEncoder().encode("admin")).roles("ADMIN", "USER");

    }

    // Secure the endpoins with HTTP Basic authentication
    @Override
    protected void configure(final HttpSecurity http) throws Exception {

        http
                //HTTP Basic authentication
                .httpBasic()
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/api/v1/user/**").hasRole("USER")
                .antMatchers(HttpMethod.GET, "/api/v1/ticket/**").hasRole("USER")
                .antMatchers(HttpMethod.GET, "/api/v1/order/**").hasRole("USER")

                .antMatchers(HttpMethod.POST, "/api/v1/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/api/v1/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/api/v1/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/api/v1/**").hasRole("ADMIN")

                .and()
                .csrf().disable()
                .formLogin().disable();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
