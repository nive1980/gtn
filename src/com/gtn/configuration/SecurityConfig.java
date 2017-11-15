package com.gtn.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfFilter;

import com.gtn.security.SecurityUserDetailService;



@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	private static String REALM="APP_REALM";
	
	@Autowired

	private UserDetailsService userDetailService;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		//.csrf().disable()
		.authorizeRequests()
           .antMatchers("/index.html").permitAll()
           .antMatchers("/index.html#/**").permitAll()
           .antMatchers("/index-detail.html").permitAll()
           .antMatchers("/productd.html").permitAll()
           .antMatchers("/product.htm").permitAll()
           .antMatchers("/shoppingCart.htm").permitAll()
           .antMatchers("/store.htm").permitAll()
           .antMatchers("/login.html").permitAll()
           .antMatchers("/login.html#/**").permitAll()
  		   .antMatchers("/forgot_password.html").permitAll()
  		   .antMatchers("/user-subscription.html").permitAll()
  		   .antMatchers("/index-org.html").permitAll()
  		   
  		   .antMatchers("/signup").permitAll()
  		   .antMatchers("/forgotPassword").permitAll()
  		   .antMatchers("/resetPassword").permitAll()
  		   .antMatchers("/fetchProductList").permitAll()
  		 
  		   .antMatchers("/css/**").permitAll()
           .antMatchers("/partials/**").permitAll()
           .antMatchers("/js/**").permitAll()
           .antMatchers("/img/**").permitAll()
           .antMatchers("/lib/**").permitAll()
           .antMatchers("/assets/**").permitAll()
           .antMatchers("/fontawesome/**").permitAll()
           .antMatchers("/rainbow/**").permitAll()           
           .antMatchers("/frontend/**").permitAll()
           .antMatchers("/resources/**").permitAll()
           .antMatchers("/scroll_bar/**").permitAll()

           .anyRequest().authenticated()
           .and().httpBasic().realmName(REALM).authenticationEntryPoint(getBasicAuthEntryPoint())
           .and()
           .formLogin()
           .loginPage("/index.html#!/login")
           .permitAll()
           .and().addFilterAfter(new CsrfHeaderFilter(), CsrfFilter.class)
           .csrf().csrfTokenRepository(csrfTokenRepository());
	}
	@Bean
	public AppBasicAuthenticationEntryPoint getBasicAuthEntryPoint(){
		return new AppBasicAuthenticationEntryPoint();
	}


	public UserDetailsService getUserDetailService() {
		return userDetailService;
	}

	public void setUserDetailService(UserDetailsService userDetailService) {
		this.userDetailService = new SecurityUserDetailService();
	}
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailService);
	}

	private CsrfTokenRepository csrfTokenRepository() {
		  HttpSessionCsrfTokenRepository repository = new HttpSessionCsrfTokenRepository();
		  repository.setHeaderName("X-XSRF-TOKEN");
		  return repository;
		}
}
