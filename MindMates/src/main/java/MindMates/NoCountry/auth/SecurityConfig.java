package MindMates.NoCountry.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Value("${websocket.allowed-origins}")
    private String allowedOrigins;

    private final JwtAuthenticatorFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;

    @Autowired
    public SecurityConfig(JwtAuthenticatorFilter jwtAuthFilter, AuthenticationProvider authenticationProvider) {
        this.jwtAuthFilter = jwtAuthFilter;
        this.authenticationProvider = authenticationProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authz -> {
                    authz.requestMatchers("/**").permitAll();
                    /*authz.requestMatchers("/api/auth/**").permitAll();
                    authz.anyRequest().authenticated();*/
                })
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .formLogin(AbstractHttpConfigurer::disable)
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
    private CorsConfigurationSource corsConfigurationSource() {
        return request -> {
            CorsConfiguration ccfg = new CorsConfiguration();
            ccfg.setAllowedOrigins(Arrays.asList(allowedOrigins));
            ccfg.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
            ccfg.setAllowCredentials(true);
            ccfg.setAllowedHeaders(Arrays.asList("*"));
            ccfg.setExposedHeaders(Arrays.asList("Authorization"));
            ccfg.setMaxAge(3600L);
            return ccfg;
        };
    }
}
