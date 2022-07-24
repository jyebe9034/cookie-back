package com.example.cookie.config;

import com.example.cookie.oauth.OAuth2AuthenticationFailureHandler;
import com.example.cookie.oauth.OAuth2AuthenticationSuccessHandler;
import com.example.cookie.oauth.OAuthUserService;
import com.example.cookie.security.jwt.JwtAuthenticationEntryPoint;
import com.example.cookie.security.jwt.JwtAuthenticationFilter;

import com.example.cookie.security.jwt.JwtTokenProvider;
import com.example.cookie.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@RequiredArgsConstructor
@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final OAuthUserService oAuthUserService;
    private final OAuth2AuthenticationSuccessHandler successHandler;
    private final OAuth2AuthenticationFailureHandler failureHandler;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final JwtTokenProvider tokenProvider;
    private final UserService userService;

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    String[] permitPath = { "/", "/css/**", "/images/**", "/js/**", "/profile",
                            "/api/oauth/**", "/api/user/login", "/api/user/join" };

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .headers().frameOptions().disable()
                .and()
                    .csrf().disable()
                    .cors().disable()
                    .exceptionHandling().authenticationEntryPoint(new JwtAuthenticationEntryPoint())
                .and()
                    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                    .formLogin().disable()
                    .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                    .authorizeRequests()
                    .antMatchers(permitPath).permitAll()
                    .anyRequest().authenticated()
                .and()
                    .logout().permitAll()
                    .logoutSuccessUrl("/")
                .and()
                    .oauth2Login()
                            .userInfoEndpoint()
                                .userService(oAuthUserService)
                                .and()
                                .successHandler(successHandler)
                                .failureHandler(failureHandler);
    }

}