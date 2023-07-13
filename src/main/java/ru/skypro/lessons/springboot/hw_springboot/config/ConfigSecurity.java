package ru.skypro.lessons.springboot.hw_springboot.config;

import lombok.SneakyThrows;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.ui.DefaultLoginPageGeneratingFilter;

import static ru.skypro.lessons.springboot.hw_springboot.transfers.Roles.*;

@Configuration
@EnableWebSecurity
public class ConfigSecurity {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf(AbstractHttpConfigurer::disable)
                .addFilter(new DefaultLoginPageGeneratingFilter(new UsernamePasswordAuthenticationFilter()))
                .formLogin(loginConfigurer -> loginConfigurer.loginPage("/login"))
                .logout(logoutConfigurer -> logoutConfigurer.logoutUrl("/logout"))
                .authorizeHttpRequests(this::customiseRequest)
                .build();
    }

    @SneakyThrows
    public void customiseRequest(AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry registry) {
        registry.requestMatchers(new AntPathRequestMatcher("/admin/**")).hasAnyRole(ADMIN.name())
                .requestMatchers(new AntPathRequestMatcher("/**")).hasAnyRole(USER.name(), ADMIN.name())
                .and()
                .formLogin().permitAll()
                .and()
                .logout().logoutUrl("/logout");
    }

    @Bean
    @SneakyThrows
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) {
        return configuration.getAuthenticationManager();

    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
