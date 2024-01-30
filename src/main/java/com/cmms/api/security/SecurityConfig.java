package com.cmms.api.security;

import com.cmms.api.services.JpaUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

	private final JpaUserDetailsService myUserDetailsService;

	public SecurityConfig(JpaUserDetailsService myUserDetailsService) {
		this.myUserDetailsService = myUserDetailsService;
	}

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable()).authorizeHttpRequests(req -> req
                //.requestMatchers("/login/**").permitAll()
                .requestMatchers("/api/user/*").hasAnyAuthority("Superuser", "Site manager")
                .anyRequest().authenticated())
                .httpBasic(withDefaults());

		// http.logout(logout -> logout
		// 		.logoutUrl("/logout")
		// 		.logoutSuccessUrl("/")
		// 		.invalidateHttpSession(true)
        //         .deleteCookies("JSESSIONID"));
		
		return http.userDetailsService(myUserDetailsService).build();
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		// return NoOpPasswordEncoder.getInstance();
		return new BCryptPasswordEncoder();
	}

}
