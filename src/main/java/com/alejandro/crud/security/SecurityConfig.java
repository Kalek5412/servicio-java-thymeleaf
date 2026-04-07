package com.alejandro.crud.security;

import com.alejandro.crud.security.config.CustomAccessDeniedHandler;
import com.alejandro.crud.security.service.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;


@Configuration
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig{

    private final UserDetailsServiceImpl userDetailsServiceImpl;

    public SecurityConfig(UserDetailsServiceImpl userDetailsServiceImpl) {
        this.userDetailsServiceImpl = userDetailsServiceImpl;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AccessDeniedHandler accessDeniedHandler(){
        return new CustomAccessDeniedHandler();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/", "/home","/cliente/**","/servicio/**","/producto/**","/inventario/**").authenticated()
                        .requestMatchers("/registro", "/usuario/registrar").hasRole("ADMIN")
                        .requestMatchers("/error", "/fragments", "/forbidden", "/login",
                         "/assets/**")
                        .permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form

                        .loginPage("/login")
                        .loginProcessingUrl("/signin")
                        .defaultSuccessUrl("/",true)
                        .usernameParameter("nombreUsuario")
                        .passwordParameter("password")
                        .permitAll()
                )
                .exceptionHandling(ex -> ex
                        .accessDeniedHandler(accessDeniedHandler())
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
//                        .logoutSuccessUrl("/login?logout")
                        .logoutSuccessUrl("/login")
                        .deleteCookies("JSESSIONID")
                        .permitAll()
                )
                .rememberMe(remember -> remember
                        .key("secret")
                        .tokenValiditySeconds(3600000)
                        .rememberMeParameter("checkRememberMe")
                )
                .userDetailsService(userDetailsServiceImpl);

        return http.build();
    }
}