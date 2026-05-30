package com.salesianostriana.dam.gonzalotorres_tutormatch.security;
 
import java.io.IOException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.FlashMap;
import org.springframework.web.servlet.support.SessionFlashMapManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
 
@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {
 
    @Override
    public void handle(HttpServletRequest request,
                       HttpServletResponse response,
                       AccessDeniedException accessDeniedException)
            throws IOException, ServletException {
 
        FlashMap flashMap = new FlashMap();
        String uri = request.getRequestURI();
        String target;
 
        if (uri.startsWith("/admin") || uri.startsWith("/tutor")
                || uri.startsWith("/materia") || uri.startsWith("/sesion")) {
            flashMap.put("errorAcceso",
                "No tienes permisos de administrador para acceder a esa sección.");
            target = "/403";
        } else {
            flashMap.put("errorAcceso",
                "No tienes permiso para acceder a esa página.");
            target = "/403";
        }
 
        new SessionFlashMapManager().saveOutputFlashMap(flashMap, request, response);
        response.sendRedirect(request.getContextPath() + target);
    }
}
