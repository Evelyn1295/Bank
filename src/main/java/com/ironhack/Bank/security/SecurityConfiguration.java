package com.ironhack.Bank.security;
import com.ironhack.Bank.service.security.UserDetailsServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.savedrequest.NullRequestCache;



@Configuration
@EnableGlobalMethodSecurity(securedEnabled=true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    /**
     * This method encrypts a password
     * @return a encrypted password from BCryptPassword
     */
    @Bean
    public PasswordEncoder passwordEncoder () {
        return new BCryptPasswordEncoder();
    }

    /**
     * This method matches a login user and password
     * @param auth
     * @throws Exception If there isn't matched between user and passord
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder);
    }

    /**
     * This method checks if a Login user has authority
     * @param httpSecurity a HttpSecurity element
     * @throws Exception If a login user hasn't an authority to do actions. (level access)
     */
    @Override
    public void configure(HttpSecurity httpSecurity) throws Exception {

        httpSecurity.httpBasic();

        httpSecurity.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().authorizeRequests()

                //Admin - Accounts
                .mvcMatchers("/checkings").hasAuthority("ROLE_ADMIN")

                .mvcMatchers("/checking/balance/ById").hasAuthority("ROLE_ADMIN")
                .mvcMatchers("/saving/balance/ById").hasAuthority("ROLE_ADMIN")
                .mvcMatchers("/studentChecking/balance/ById").hasAuthority("ROLE_ADMIN")
                .mvcMatchers("/creditCard/balance/ById").hasAuthority("ROLE_ADMIN")


                .mvcMatchers(HttpMethod.POST, "/checking").hasAuthority("ROLE_ADMIN")
                .mvcMatchers(HttpMethod.POST, "/saving").hasAuthority("ROLE_ADMIN")
                .mvcMatchers(HttpMethod.POST, "/creditCard").hasAuthority("ROLE_ADMIN")

                .mvcMatchers(HttpMethod.PUT, "/checking/Update").hasAuthority("ROLE_ADMIN")
                .mvcMatchers(HttpMethod.PUT, "/saving/Update").hasAuthority("ROLE_ADMIN")
                .mvcMatchers(HttpMethod.PUT, "/studentChecking/Update").hasAuthority("ROLE_ADMIN")
                .mvcMatchers(HttpMethod.PUT, "/creditCard/Update").hasAuthority("ROLE_ADMIN")

                .mvcMatchers(HttpMethod.PATCH, "/checking/UpdateBalance").hasAuthority("ROLE_ADMIN")
                .mvcMatchers(HttpMethod.PATCH, "/saving/UpdateBalance").hasAuthority("ROLE_ADMIN")
                .mvcMatchers(HttpMethod.PATCH, "/studentChecking/UpdateBalance").hasAuthority("ROLE_ADMIN")
                .mvcMatchers(HttpMethod.PATCH, "/creditCard/UpdateBalance").hasAuthority("ROLE_ADMIN")

                .mvcMatchers(HttpMethod.DELETE, "/checking/Delete").hasAuthority("ROLE_ADMIN")
                .mvcMatchers(HttpMethod.DELETE, "/saving/Delete").hasAuthority("ROLE_ADMIN")
                .mvcMatchers(HttpMethod.DELETE, "/studentChecking/Delete").hasAuthority("ROLE_ADMIN")
                .mvcMatchers(HttpMethod.DELETE, "/creditCard/Delete").hasAuthority("ROLE_ADMIN")


                //AccountHolder
                .mvcMatchers("/checking/ById").hasAuthority("ROLE_HOLDER")
                .mvcMatchers(HttpMethod.PUT, "/checking/Update").hasAuthority("ROLE_HOLDER")
                .mvcMatchers(HttpMethod.PATCH, "/checking/Update").hasAuthority("ROLE_HOLDER")


                .and().requestCache().requestCache(new NullRequestCache()).and().httpBasic().and().cors().and().csrf().disable();

    }

}

