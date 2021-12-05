//package lpnu.security;
//
//import org.springframework.http.HttpMethod;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//
//public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//
//    @Override
//    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
//
//        auth.inMemoryAuthentication()
//                .withUser("user").password("{noop}password").roles("USER")
//                .and()
//                .withUser("admin").password("{noop}password").roles("USER", "ADMIN");
//
//    }
//
//    // Secure the endpoins with HTTP Basic authentication
//    @Override
//    protected void configure(final HttpSecurity http) throws Exception {
//
//        http
//                //HTTP Basic authentication
//                .httpBasic()
//                .and()
//                .authorizeRequests()
//                .antMatchers(HttpMethod.GET, "/api/v1/order/**").hasRole("USER")
//                .and()
//                .csrf().disable()
//                .formLogin().disable();
//    }
//}
