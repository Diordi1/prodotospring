package com.apptester.app12;

import java.util.Arrays;
import java.util.Collections;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@Configuration
public class App12Application {

	public static void main(String[] args) {
		SpringApplication.run(App12Application.class, args);
	}

	@Bean
	public SecurityFilterChain sec(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(
				http1 -> http1.requestMatchers("/api/**").authenticated().anyRequest().permitAll()

		);
		http.addFilterBefore(new jwtfilter(), BasicAuthenticationFilter.class);

		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		http.csrf().disable();
		http.formLogin();
		http.httpBasic();
		http.headers().frameOptions().sameOrigin();

		return http.build();

	}

	// @Bean
	// public InMemoryUserDetailsManager ui() {
	// User us = (User) User.withUsername("admin1").password("{noop}dummy").build();
	// return new InMemoryUserDetailsManager(us);

	// }

	// @Bean
	// public PasswordEncoder passwordEncode() {
	// return new BCryptPasswordEncoder();

	// }

	@Bean
	public WebMvcConfigurer webc() {
		return new WebMvcConfigurer() {
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedHeaders("*").allowedOrigins("*").allowedMethods("*");
			}
		};
	}
}
