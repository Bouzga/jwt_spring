package com.example.jwt.config;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtService jwtService;
    private UserDetailsService userDetailsService;
    @Override
    protected void doFilterInternal(
           @NonNull HttpServletRequest request,
           @NonNull HttpServletResponse response,
           @NonNull FilterChain filterChain
    ) throws ServletException, IOException {
       final String authHeader = request.getHeader("Authorization");
       final String jwt;
       final String userEmail;
       if(authHeader==null || authHeader.startsWith("Bearer ")){
           filterChain.doFilter(request,response);
           return;
       }
       //extraire mon token d apres mon authentification header
       jwt=authHeader.substring(7);
       userEmail= jwtService.extractUsername(jwt);
       if(userEmail != null && SecurityContextHolder.getContext().getAuthentication()==null){
           UserDetails userDetails =this.userDetailsService.loadUserByUsername(userEmail);
       }
    }
}
