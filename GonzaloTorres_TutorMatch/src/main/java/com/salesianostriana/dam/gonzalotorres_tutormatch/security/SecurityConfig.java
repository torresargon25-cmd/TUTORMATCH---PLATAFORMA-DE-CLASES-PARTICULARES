package com.salesianostriana.dam.gonzalotorres_tutormatch.security;
 
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import lombok.RequiredArgsConstructor;
 
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
 
    private final CustomAccessDeniedHandler customAccessDeniedHandler;
    private final CustomAuthenticationSuccessHandler successHandler;
 
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
 
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
        		.authorizeHttpRequests(auth -> auth
        	    .requestMatchers("/css/**", "/js/**", "/img/**", "/webjars/**").permitAll()
        	    .requestMatchers("/", "/auth/login", "/403", "/home", "/error").permitAll()
        	    .requestMatchers("/h2-console/**").permitAll()

        	    
        	    .requestMatchers("/sesion/mis-sesiones", "/sesion/reservar").hasAnyRole("ADMIN", "TUTOR", "ESTUDIANTE")
        	    .requestMatchers("/sesion/mis-sesiones-tutor", "/estudiante/mis-estudiantes").hasAnyRole("ADMIN", "TUTOR", "ESTUDIANTE")
        	    .requestMatchers("/tutor/buscar", "/tutor/detalle/**", "/tutor/reservar/**").hasAnyRole("ADMIN", "TUTOR", "ESTUDIANTE")
        	    .requestMatchers("/estudiante/mi-perfil").hasAnyRole("ADMIN", "TUTOR", "ESTUDIANTE")

        	    
        	    .requestMatchers("/admin/**").hasRole("ADMIN")
        	    .requestMatchers("/tutor/**").hasRole("ADMIN")
        	    .requestMatchers("/materia/**").hasRole("ADMIN")
        	    .requestMatchers("/sesion/**").hasRole("ADMIN") 
        	    .requestMatchers("/estudiante/**").hasAnyRole("ADMIN", "TUTOR", "ESTUDIANTE")

        	    .anyRequest().authenticated()
        	)
            .formLogin(form -> form
                .loginPage("/auth/login")
                .loginProcessingUrl("/auth/login")
                .successHandler(successHandler)
                .failureUrl("/auth/login?error")
                .permitAll()
            )
            .exceptionHandling(ex -> ex
                .accessDeniedHandler(customAccessDeniedHandler)
            )
            .csrf(csrf -> csrf
                .ignoringRequestMatchers("/h2-console/**")
            )
            .headers(headers -> headers
                .frameOptions(frame -> frame.disable())
            );
 
        return http.build();
    }
}
