package com.store.user.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

/**
 * Spring Security 6 설정
 *
 * CRITICAL (Spring Security 5 → 6 변경사항):
 *
 * | 제거됨 (Boot 2.x)                     | 대체 (Boot 3.x)                    |
 * |----------------------------------------|------------------------------------|
 * | WebSecurityConfigurerAdapter 상속       | SecurityFilterChain Bean 등록       |
 * | authorizeRequests()                    | authorizeHttpRequests()            |
 * | .antMatchers() / .mvcMatchers()        | .requestMatchers()                 |
 * | .and() 체이닝                           | Lambda DSL (각 설정 독립 블록)       |
 * | @EnableGlobalMethodSecurity            | @EnableMethodSecurity              |
 *
 * Lambda DSL은 Security 6에서 권장, Security 7에서 필수가 된다.
 */
@Configuration
@EnableWebSecurity
@EnableMethodSecurity  // @PreAuthorize, @PostAuthorize 활성화
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    /**
     * SecurityFilterChain Bean 방식 (WebSecurityConfigurerAdapter 대체)
     *
     * Lambda DSL 사용:
     *   - csrf(csrf -> csrf.disable())          ← csrf().disable() 아님
     *   - cors(cors -> cors.configurationSource(...))
     *   - sessionManagement(sm -> sm.sessionCreationPolicy(...))
     *   - authorizeHttpRequests(auth -> auth...) ← authorizeRequests() 아님
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                // CSRF 비활성화 (JWT 기반 Stateless API)
                .csrf(csrf -> csrf.disable())

                // CORS 설정
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))

                // 세션 비활성화 (JWT Stateless)
                .sessionManagement(sm ->
                        sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )

                // URL별 인가 규칙
                // CRITICAL: requestMatchers() 사용 (antMatchers/mvcMatchers 제거됨)
                .authorizeHttpRequests(auth -> auth
                        // 인증 없이 접근 가능
                        .requestMatchers("/api/auth/**").permitAll()
                        .requestMatchers("/api/products/search/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/products/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/reviews/**").permitAll()

                        // Swagger / API 문서
                        .requestMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll()

                        // 판매자 전용
                        .requestMatchers("/api/seller/**").hasRole("SELLER")

                        // 관리자 전용
                        .requestMatchers("/api/admin/**").hasRole("ADMIN")

                        // 나머지 요청은 인증 필요
                        .anyRequest().authenticated()
                )

                // JWT 필터를 UsernamePasswordAuthenticationFilter 앞에 추가
                .addFilterBefore(jwtAuthenticationFilter,
                        UsernamePasswordAuthenticationFilter.class)

                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of(
                "http://localhost:5173",   // Vue dev server (Vite)
                "http://localhost:5174",   // Seller app
                "http://localhost:5175"    // Admin app
        ));
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(List.of("*"));
        configuration.setAllowCredentials(true);
        configuration.setMaxAge(3600L);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
