package com.konstantinoplebank;

import com.konstantinoplebank.security.SecurityLogoutSuccessHandler;
import com.konstantinoplebank.security.SecurityAuthenticationEntryPoint;
import com.konstantinoplebank.security.SecurityAuthenticationFailureHandler;
import com.konstantinoplebank.security.SecurityAuthenticationSuccessHandler;
import com.konstantinoplebank.service.implementation.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.Arrays;

/**
 * Configuration class, which setting security params
 *
 * @author Konstantin Artushkevich
 * @version 1.0
 */

@Configuration
@EnableWebMvc
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final SecurityAuthenticationEntryPoint authenticationEntryPoint;

    private final SecurityAuthenticationFailureHandler authenticationFailureHandler;

    private final SecurityAuthenticationSuccessHandler authenticationSuccessHandler;

    private final SecurityLogoutSuccessHandler logoutSuccessHandler;

    private final UserDetailsService userService;

    @Autowired
    public SecurityConfig(UserDetailsServiceImpl userService, SecurityAuthenticationEntryPoint authenticationEntryPoint, SecurityAuthenticationFailureHandler authenticationFailureHandler, SecurityAuthenticationSuccessHandler authenticationSuccessHandler, SecurityLogoutSuccessHandler logoutSuccessHandler) {
        this.userService = userService;
        this.authenticationEntryPoint = authenticationEntryPoint;
        this.authenticationFailureHandler = authenticationFailureHandler;
        this.authenticationSuccessHandler = authenticationSuccessHandler;
        this.logoutSuccessHandler = logoutSuccessHandler;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors()
                .and()
                .authorizeRequests()
                .antMatchers("/", "/registration", "/home").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin().permitAll()
                .and()
                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).permitAll();

        http.csrf().disable();
        http.exceptionHandling().authenticationEntryPoint(authenticationEntryPoint);
        http.formLogin().successHandler(authenticationSuccessHandler);
        http.formLogin().failureHandler(authenticationFailureHandler);
        http.logout().logoutSuccessHandler(logoutSuccessHandler);

    }

    @Bean
    public PasswordEncoder bcryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(
                Arrays.asList(
                        "http://localhost:8080",
                        "http://localhost:8080/login")
        );
        configuration.setAllowedMethods(Arrays.asList("GET","POST"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userService)
                .passwordEncoder(bcryptPasswordEncoder());
    }
}
