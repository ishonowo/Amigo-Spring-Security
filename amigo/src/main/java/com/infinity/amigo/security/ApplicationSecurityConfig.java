package com.infinity.amigo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

import javax.servlet.http.Cookie;
import java.net.CookieStore;
import java.util.concurrent.TimeUnit;

import static com.infinity.amigo.security.ApplicationUserRole.*;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled= true)
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter{
	
/*	@Autowired
	private PasswordEncoder passwordEncoder;*/
    
	private final PasswordEncoder passwordEncoder;

    @Autowired
    public ApplicationSecurityConfig(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }
	
/* 	@Override
 	protected void configure(HttpSecurity http) throws Exception {
 		http.authorizeRequests().antMatchers("/**").hasRole("USER").and().formLogin();
 	}
 	http.authorizeRequests().anyRequest().authenticated().and().formLogin().and().httpBasic();
 	*/

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
/*                .antMatchers("/*", "index", "/css/*", "/js/*","login").permitAll()
                .antMatchers("/api/**").hasRole(ADMIN.name())	*/
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                    .loginPage("/login").permitAll();
	}
	
	@Override
	@Bean
	protected UserDetailsService userDetailsService() {
		UserDetails jamesBondUser=User.builder()
			.username("jamesbond")
			.password(passwordEncoder.encode("password"))
			//.roles(ApplicationUserRole.STUDENT.name())		//ROLE_STUDENT
			.authorities(STUDENT.getGrantedAuthorities())
			.build();
		
		UserDetails jackBowerUser=User.builder()
				.username("jackbower")
				.password(passwordEncoder.encode("passwordjack"))
				//.roles(ApplicationUserRole.ADMIN.name())		//ROLE_ADMIN
				.authorities(ADMIN.getGrantedAuthorities())
				.build();
			
		UserDetails janetPondUser=User.builder()
				.username("janetpond")
				.password(passwordEncoder.encode("passwordpond"))
				//.roles(ApplicationUserRole.ADMINTRAINEE.name())		//ROLE_ADMINTRAINEE
				.authorities(ADMINTRAINEE.getGrantedAuthorities())
				.build();
			
		
		return new InMemoryUserDetailsManager(jamesBondUser,jackBowerUser,janetPondUser);
	}

	
	
}
