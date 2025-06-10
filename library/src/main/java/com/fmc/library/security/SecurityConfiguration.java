package com.fmc.library.security;

import javax.security.sasl.AuthorizeCallback;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		
		http.csrf((csrf -> csrf.disable())).authorizeHttpRequests((authorize) -> authorize.requestMatchers("/swagger-ui/**","/swagger-ui.html","/v3/api-docs/**")
				.permitAll().requestMatchers(HttpMethod.GET,"/api/**").permitAll().anyRequest().authenticated()).httpBasic(Customizer.withDefaults());
				
		return http.build();
		
		
	}
	
	@Bean
	public static PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public UserDetailsService userDetailsService() {
	
		UserDetails user=User.builder().username("rahul").password(passwordEncoder().encode("rahul")).roles("USER").build();
		UserDetails admin=User.builder().username("kiran").password(passwordEncoder().encode("kiran")).roles("ADMIN").build();
		
		return new InMemoryUserDetailsManager(user,admin);
	
	}
}
