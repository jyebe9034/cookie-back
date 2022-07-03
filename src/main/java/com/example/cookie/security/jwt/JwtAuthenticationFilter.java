package com.example.cookie.security.jwt;

import com.example.cookie.common.Role;
import com.example.cookie.user.domain.User;
import com.example.cookie.user.service.UserService;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtTokenProvider jwtTokenProvider;
    private final UserService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        if (request.getHeader("Authorization") != null && request.getHeader("Authorization").startsWith("Bearer ")) {
            String jwt = request.getHeader("Authorization").substring(7);
            log.info("jwt : {}", jwt);

            try {
                Claims claims = jwtTokenProvider.getClaims(jwt);

                if (jwtTokenProvider.validateJwt(claims)) {
                    throw new ExpiredJwtException(null, claims, null);
                }

                String id = claims.containsKey("id") ? (String) claims.get("id") : "";

                List<GrantedAuthority> authorities = new ArrayList<>();
                authorities.add(new SimpleGrantedAuthority(String.valueOf(Role.USER)));

                User user = userService.findByUserId(id).get();

                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            } catch (ExpiredJwtException e) {
                log.warn("Error: JWT(" + jwt + ") is expired.");
                // ExpiredJwtException
            } catch (SignatureException e) {
                log.warn("Error: JWT(" + jwt + ") signature validation failed.");
                // SignatureException
            } catch (UnsupportedJwtException | MalformedJwtException | IllegalArgumentException e) {
                log.warn("Error: {}", e.getMessage());
                log.warn("Error: JWT(" + jwt + ") is invalid.");
                // UnsupportedJwtException
            } catch (Exception e) {
                log.warn("Error: " + e.getMessage());
                // JwtException
            }
        } else {
            // TODO JwtNotFoundException 에러 처리
        }
        filterChain.doFilter(request, response);
    }
}
