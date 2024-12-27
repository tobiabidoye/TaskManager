package com.example.TaskManagerApp.Security;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtAuthenticationFilter extends OncePerRequestFilter{
   private final JwtUtil jwtUtil;
   private final UserDetailsService userDetailsService;
   
   public JwtAuthenticationFilter(JwtUtil jwtUtil, UserDetailsService userDetailsService){ 
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
   }

   @Override
   protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
                    throws ServletException, IOException{ 
        String authrizationHeader = request.getHeader("Authorization");
        if(authrizationHeader != null && authrizationHeader.startsWith("Bearer ")){ 
            String token = authrizationHeader.substring(7);
            String username = jwtUtil.extractUsername(token);

            if(username != null && SecurityContextHolder.getContext().getAuthentication() == null){ 
                UserDetails userDetails = userDetailsService.loadUserByUsername(username); 
                
                if(jwtUtil.validateToken(token, userDetails.getUsername())){
                    UsernamePasswordAuthenticationToken authentication = 
                    new UsernamePasswordAuthenticationToken(userDetails,null, userDetails.getAuthorities());
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authentication);

                }
            }
        
        }

        filterChain.doFilter(request, response);
    }
}
