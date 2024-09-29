package com.libraryStore.libraryStore.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.web.SecurityFilterChain;
import com.libraryStore.libraryStore.services.MemberSecurityDetails;

@Configuration
@EnableWebSecurity
public class SpringSecurity extends WebSecurityConfiguration {
	
	private MemberSecurityDetails memberDetails;

	@Autowired
	public SpringSecurity(MemberSecurityDetails memberDetails) {
		super();
		this.memberDetails = memberDetails;
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
			.authorizeHttpRequests(authorize -> authorize
					.requestMatchers("/", "/signup", "/signin", "/createaccount").permitAll()
					.requestMatchers("/static/**", "/style.css").permitAll()
					.anyRequest().authenticated()
					)
					.formLogin(form -> form
							.loginPage("/signin")
							.loginProcessingUrl("/checkCredentials")
							.failureUrl("/signin?error=true")
							.defaultSuccessUrl("/setMemberSession")
					)
					.logout(logout -> logout
							.logoutUrl("/logout")
							.logoutSuccessUrl("/")
							.invalidateHttpSession(true)
							.deleteCookies("JSESSIONID")
					)
					.userDetailsService(memberDetails);
		
			return http.build();
	}
	
}
