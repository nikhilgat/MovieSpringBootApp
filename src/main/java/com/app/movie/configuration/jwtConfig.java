package com.app.movie.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.app.movie.repositories.customUserDetailsService;

@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
public class jwtConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	private customUserDetailsService userservice;
	
	@Autowired
	private jwtFilter filt;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors()
		.and().csrf().disable()
		.authorizeRequests().antMatchers("/token").permitAll()
		
		.antMatchers("/saveusers").permitAll()
		.antMatchers("/getusers").hasAnyRole("ADMIN")
		.antMatchers("/getposts").hasAnyRole("USER","ADMIN")
		.antMatchers("/getposts/*").hasAnyRole("USER", "ADMIN")
		.antMatchers("/postposts").hasAnyRole("USER","ADMIN")
		.antMatchers("/deleteposts/*").hasAnyRole("ADMIN")
		
		.anyRequest().authenticated()
		.and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
		http.addFilterBefore(filt, UsernamePasswordAuthenticationFilter.class);
		
		
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userservice);
	}

    @Bean
    PasswordEncoder encode() {
        return NoOpPasswordEncoder.getInstance();
    }
    
    @Bean
     public AuthenticationManager authenticationManagerBean() throws Exception {
    	 return super.authenticationManagerBean();
     }
}
