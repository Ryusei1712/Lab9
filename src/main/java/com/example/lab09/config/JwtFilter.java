package com.example.lab09.config;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtFilter extends OncePerRequestFilter {

    @Value("${jwt.secret}")
    private String secret;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        try {
            // Lấy token từ header Authorization
            String jwt = extractJwtFromRequest(request);

            if (jwt != null && Jwts.parser().setSigningKey(secret).isSigned(jwt)) {
                // Nếu token hợp lệ, thiết lập Authentication trong SecurityContext
                SecurityContextHolder.getContext().setAuthentication(getAuthentication(jwt));
            }

        } catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException | SignatureException | IllegalArgumentException e) {
            // Xử lý các ngoại lệ khi xác thực token
            response.sendError(HttpStatus.UNAUTHORIZED.value(), "Invalid or expired token");
            return;
        }

        filterChain.doFilter(request, response);
    }

    private String extractJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");

        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }

        return null;
    }

    private UsernamePasswordAuthenticationToken getAuthentication(String jwt) {


        String username = Jwts.parser().setSigningKey(secret).parseClaimsJws(jwt).getBody().getSubject();

        return new UsernamePasswordAuthenticationToken(username, null, null);
    }
}
