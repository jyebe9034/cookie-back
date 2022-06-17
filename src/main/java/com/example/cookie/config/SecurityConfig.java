package com.example.cookie.config;

import com.example.cookie.oauth.OAuth2AuthenticationFailureHandler;
import com.example.cookie.oauth.OAuth2AuthenticationSuccessHandler;
import com.example.cookie.oauth.OAuthUserService;
import com.example.cookie.security.JwtAuthenticationFilter;
import com.example.cookie.security.JwtTokenProvider;

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
    private final JwtTokenProvider jwtTokenProvider;

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .headers().frameOptions().disable()
                .and()
                    .httpBasic().disable()
                    .csrf().disable()
                    .formLogin().disable()
                    .authorizeRequests()
                    .antMatchers("/", "/css/**", "/images/**", "/js/**", "/profile", "/api/test/**", "/api/oauth/**").permitAll()
                .and()
                    .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                    .logout().permitAll()
                    .logoutUrl("/user/logout")
                    .logoutSuccessUrl("/main")
                .and()
                    .oauth2Login()
                            .userInfoEndpoint()
                                .userService(oAuthUserService)
                                .and()
                                .successHandler(successHandler)
                                .failureHandler(failureHandler);

        http.addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class);

    }
}