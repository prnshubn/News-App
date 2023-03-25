package com.wipro.newsapp.adminservice.filter;

import com.wipro.newsapp.adminservice.model.User;
import com.wipro.newsapp.adminservice.repository.BlackListedTokenRepository;
import com.wipro.newsapp.adminservice.repository.UserRepository;
import com.wipro.newsapp.adminservice.util.JwtUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private BlackListedTokenRepository blackListedTokenRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtility jwtUtility;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String header = request.getHeader("Authorization");
        String token = null;
        String userEmail = null;
        User user = null;

        if (header != null && header.startsWith("Bearer ")) {
            token = header.substring(7);
            userEmail = jwtUtility.getEmailFromToken(token);
            user = userRepository.findByEmail(userEmail).orElseThrow();
        }
        if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null
                && blackListedTokenRepository.findByToken(token).isEmpty()) {
            UserDetails userDetails = userDetailsService.loadUserByUsername(userEmail);
            if (jwtUtility.validateToken(token, userDetails)) {
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);

            }

        }
        filterChain.doFilter(request, response);
    }
}
