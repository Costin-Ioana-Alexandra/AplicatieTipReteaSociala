package com.example.back.security;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.back.appuser.AppUserService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

/**
 * Represents a class filter for intercepting incoming requests and processing
 * JWT tokens for authentication.
 */
@Component
@RequiredArgsConstructor
public class JwtRequestFilter extends OncePerRequestFilter {

  /**
   * The service for interacting with application users.
   */
  private final AppUserService appUserService;

  /**
   * The utility class for JWT operations.
   */
  private final JwtUtil jwtUtil;

  /**
   * The length of the "Bearer" prefix in the Authorization header.
   */
  private static final int BEARER_PREFIX_LENGTH = 7;

  /**
   * Performs filtering logic for each incoming request.
   *
   * @param request     the incoming HTTP request.
   * @param response    the outgoing HTTP response.
   * @param filterChain the filter chain.
   * @throws ServletException if an error occurs during servlet processing.
   * @throws IOException      if an I/O error occurs.
   */
  @Override
  protected void doFilterInternal(final HttpServletRequest request, final HttpServletResponse response,
      final FilterChain filterChain) throws ServletException, IOException {
    final String authorizationHeader = request.getHeader("Authorization");

    String username = null;
    String jwt = null;

    if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
      int startIndex = BEARER_PREFIX_LENGTH;
      jwt = authorizationHeader.substring(startIndex);
      username = jwtUtil.extractUsername(jwt);
    }

    if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
      UserDetails userDetails = this.appUserService.loadUserByUsername(username);
      if (jwtUtil.validateToken(jwt, userDetails.getUsername())) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
            userDetails, null, userDetails.getAuthorities());
        usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
      }
    }
    filterChain.doFilter(request, response);
  }
}
