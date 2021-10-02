package com.example.authenapijwt.config;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.authenapijwt.util.JwtUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

// this filter will run first with all request
// here we get the token from user then assign role for them
public class ApiAuthorizationFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //let login and register pass through
        String requestPath = request.getServletPath();
        if (requestPath.equals("/api/v1/login") || requestPath.equals("/api/v1/register")) {
            filterChain.doFilter(request, response);
            return;
        }
        String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }
        //get token in header then decode jwt token to get username and roles -> then set roles for request
        try {
            String token = authorizationHeader.replace("Bearer", "").trim();
            DecodedJWT decodedJWT = JwtUtil.getDecodedJwt(token);
            String username = decodedJWT.getSubject();
            String[] roles = decodedJWT.getClaim(JwtUtil.ROLE_CLAIM_KEY).asArray(String.class);
            Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
            Arrays.stream(roles).forEach(role -> {
                authorities.add(new SimpleGrantedAuthority(role));
            });
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(username, null, authorities);
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            filterChain.doFilter(request, response);
        } catch (Exception ex) {
            //show error
            System.out.println(ex.getMessage());
            response.setStatus(HttpStatus.FORBIDDEN.value());
            Map<String, String> errors = new HashMap<>();
            errors.put("error", ex.getMessage());
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            new ObjectMapper().writeValue(response.getOutputStream(), errors);
        }


    }
}
