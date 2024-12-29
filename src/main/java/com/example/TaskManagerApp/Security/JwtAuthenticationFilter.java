package com.example.TaskManagerApp.Security;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
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
            System.out.println("token" + token);
            try{
            String username = jwtUtil.extractUsername(token);
            System.out.println("Extracted username" + username);
            if(username != null && SecurityContextHolder.getContext().getAuthentication() == null){ 
                UserDetails userDetails = userDetailsService.loadUserByUsername(username); 
                Object rolesclaim = jwtUtil.extractClaim(token, "roles");
                System.out.println("Rolesclaim" + rolesclaim); 
                List <GrantedAuthority> authorities = ((List <String> ) jwtUtil.extractClaim(token, "roles")).stream()
                        .map(role -> new SimpleGrantedAuthority("ROLE_" + role))
                        .collect(Collectors.toList());              
                System.out.println("Extracted Role" +authorities);
                if(jwtUtil.validateToken(token, userDetails.getUsername())){
                    UsernamePasswordAuthenticationToken authentication = 
                    new UsernamePasswordAuthenticationToken(userDetails,null, authorities);

                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                    SecurityContextHolder.getContext().setAuthentication(authentication);

                }
            }
            }catch(Exception e){ 
                System.err.println("Token processing error"+ e.getMessage());
            } 
        }
        
        filterChain.doFilter(request, response);
    }
}
