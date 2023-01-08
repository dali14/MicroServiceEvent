package org.sid.serviceevent;


import org.sid.serviceevent.filters.JwtAuthorizationFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS); // JWT
        http.headers().frameOptions().disable();
        http.authorizeRequests().antMatchers("/h2-console/**","/refreshToken/**").permitAll(); // access for any request for H2 db
        http.authorizeRequests().antMatchers(HttpMethod.GET,"/events/**").permitAll();
        http.authorizeRequests().antMatchers(HttpMethod.POST,"/events/**").hasAuthority("prof");
        http.authorizeRequests().antMatchers(HttpMethod.PUT,"/events/**").hasAuthority("prof"); // only prof can apdate product
        http.addFilterBefore(new JwtAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
    }
}
