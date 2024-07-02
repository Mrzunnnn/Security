package com.example.security.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(
        securedEnabled = true,
        jsr250Enabled = true)
@RequiredArgsConstructor
public class SecurityConfig {
    private final CustomUserDetailService userDetailService;
    private final PasswordEncoder passwordEncoder;
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailService);
        provider.setPasswordEncoder(passwordEncoder);
        return provider;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http)throws Exception{


        // cấu hình đường dẫn

        String[] publicRoutes = new String[]{"/css/**","/js/**","/images/**"};

        http.authorizeHttpRequests(auth ->{
            auth.requestMatchers("/").permitAll();
            auth.requestMatchers("/dashboard").hasAnyRole("ADMIN","SALE");
            auth.requestMatchers("/api/admin/users/**").hasRole("ADMIN");
            auth.requestMatchers("/api/categories/**").hasAnyRole("ADMIN","SALE");
            auth.requestMatchers("/api/products/**").hasAnyRole("ADMIN","SALE");
            auth.requestMatchers("/api/brands/**").hasAnyRole("ADMIN","SALE");
            auth.requestMatchers("/api/order/**").hasAnyRole("ADMIN","SALE");
            auth.requestMatchers("/api/posts/**").hasAnyRole("ADMIN","SALE","AUTHOR");
            auth.requestMatchers("/api/user/**").hasRole("USER");
            auth.anyRequest().authenticated();
            // hasAuthority thì giống với Role nhưng mà có thếm Role_ đằng trước
        });
        //cấu hình form login

        http.formLogin(form ->{
            form.loginPage("/login");
            form.defaultSuccessUrl("/",true);
            form.permitAll();
        });

        //Cấu hình form logout

        http.logout(logout ->{
            logout.invalidateHttpSession(true);
            logout.deleteCookies("JSESSIONID");
            logout.clearAuthentication(true);
            logout.permitAll();
        });

        // Cấu hình xác thực
        http.authenticationProvider(authenticationProvider());

        return http.build();
    }

}
