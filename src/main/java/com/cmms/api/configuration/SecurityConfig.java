package com.cmms.api.configuration;

import com.cmms.api.filter.JwtAuthenticationFilter;
import com.cmms.api.services.JpaUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;

// import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
	// private final JpaUserDetailsService myUserDetailsService;

	// public SecurityConfig(JpaUserDetailsService myUserDetailsService) {
	// 	this.myUserDetailsService = myUserDetailsService;
	// }

	// @Bean
	// SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    //     http.csrf(csrf -> csrf.disable()).authorizeHttpRequests(req -> req
    //             //.requestMatchers("/login/**").permitAll()
    //             .requestMatchers("/api/user/*").hasAnyAuthority("Superuser", "Site manager")
    //             .anyRequest().authenticated())
    //             .httpBasic(withDefaults());

	// 	// http.logout(logout -> logout
	// 	// 		.logoutUrl("/logout")
	// 	// 		.logoutSuccessUrl("/")
	// 	// 		.invalidateHttpSession(true)
    //     //         .deleteCookies("JSESSIONID"));
		
	// 	return http.userDetailsService(myUserDetailsService).build();
	// }

	private final JpaUserDetailsService myUserDetailsService;
	private final JwtAuthenticationFilter jwtAuthFilter;

	public SecurityConfig(JpaUserDetailsService myUserDetailsService, JwtAuthenticationFilter jwtAuthFilter) {
		this.myUserDetailsService = myUserDetailsService;
		this.jwtAuthFilter = jwtAuthFilter;
	}

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
            .authorizeHttpRequests(req -> req
                .requestMatchers("/api/user/login").permitAll()  // Permitir acceso sin autenticación a rutas específicas
                .requestMatchers("/api/user/save").hasAnyAuthority("Superuser", "Site manager")
				.requestMatchers("/api/user/technicians").hasAnyAuthority("Superuser", "Site manager")
				.requestMatchers("/api/user/supervisors").hasAnyAuthority("Superuser", "Site manager")
				.requestMatchers("/api/user/site-managers").hasAnyAuthority("Superuser", "Site manager")
				.requestMatchers("/api/user/update").hasAnyAuthority("Superuser", "Site manager")
				.requestMatchers("/api/user/{id}").hasAnyAuthority("Superuser", "Site manager")
                .anyRequest().authenticated())
            .userDetailsService(myUserDetailsService)
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

			return http.build();
	}

	@Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

	@Bean
	PasswordEncoder passwordEncoder() {
		// return NoOpPasswordEncoder.getInstance();
		return new BCryptPasswordEncoder();
	}

}
