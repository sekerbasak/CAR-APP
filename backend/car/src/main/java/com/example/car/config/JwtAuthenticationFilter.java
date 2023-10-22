package com.example.car.config;

import io.micrometer.common.lang.NonNull;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;
    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {
        final String authHeader= request.getHeader("Authorization");
        final String jwt;
        final String bearer="Bearer ";
        final String username;
        if(Objects.isNull(authHeader) || authHeader.startsWith(bearer)){
         filterChain.doFilter(request,response);
         return;
        }
        jwt=authHeader.split(" ")[1].trim();
        //jwt=authHeader.substring(bearer.length());
        username=jwtService.extractUsername(jwt); //todo extract the username from JWT
        if(Objects.nonNull(username) && Objects.isNull(SecurityContextHolder.getContext().getAuthentication())){
            UserDetails userDetails= this.userDetailsService.loadUserByUsername(username);
            if (jwtService.isTokenValid(jwt,userDetails)){
                UsernamePasswordAuthenticationToken authToken=new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities()
                );
                authToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );
                SecurityContextHolder.getContext().setAuthentication(authToken);

            }
            filterChain.doFilter(request,response);
        }

    }
}
