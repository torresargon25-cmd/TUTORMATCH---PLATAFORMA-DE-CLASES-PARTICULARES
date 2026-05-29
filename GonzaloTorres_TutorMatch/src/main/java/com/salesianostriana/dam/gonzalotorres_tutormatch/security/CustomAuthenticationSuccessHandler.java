package com.salesianostriana.dam.gonzalotorres_tutormatch.security;

import java.io.IOException;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication)
            throws IOException, ServletException {

        String rol = authentication.getAuthorities()
                .iterator().next().getAuthority();

        if (rol.equals("ROLE_ADMIN")) {
            response.sendRedirect("/admin/");
        } else if (rol.equals("ROLE_TUTOR")) {
            response.sendRedirect("/tutor/");
        } else {
            response.sendRedirect("/estudiante/");
        }
    }
}
