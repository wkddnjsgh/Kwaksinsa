package com.kwaksamo.kwaksinsa.config;

import com.kwaksamo.kwaksinsa.config.auth.PrincipalDetailsService;
import com.kwaksamo.kwaksinsa.config.oauth.PrincipalOauth2UserService;
import com.kwaksamo.kwaksinsa.util.Script;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	
	@Autowired
	private PrincipalOauth2UserService principalOauth2UserService;
	
	@Autowired
	private PrincipalDetailsService principalDetailService;


	@Bean
	public BCryptPasswordEncoder encodePwd() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	// 비밀번호 비교
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(principalDetailService).passwordEncoder(encodePwd());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		
		http.authorizeRequests()
			.antMatchers("/css/**").permitAll()
			.antMatchers("/img/**").permitAll()
			.antMatchers("/auth/**").permitAll()
			.antMatchers("/product/**").authenticated()
			.antMatchers("/basket/**").authenticated()
			.antMatchers("/orders/**").authenticated()
			.antMatchers("/user/**").authenticated()
			.antMatchers("/test/**","/").permitAll()
			.antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')") // 권한 필요
			.anyRequest().denyAll() // 모두 닫음
		.and()
			.formLogin() 
			.loginPage("/auth/login")
			.loginProcessingUrl("/auth/loginProc") // 시큐리티가 대신 로그인 진행함
			.defaultSuccessUrl("/test/index")
			.failureHandler(new AuthenticationFailureHandler() {
				
				@Override
				public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
						AuthenticationException exception) throws IOException, ServletException {
					response.setContentType("text/html; charset=utf-8");
					PrintWriter out = response.getWriter();
					out.print(Script.back("아이디 혹은 비밀번호가 다릅니다."));

					HttpSession session = request.getSession();
					session.invalidate();
					System.out.println("세션 무효화");
					return;
				}
			})
		.and()
			.oauth2Login()
			.loginPage("/auth/login")
			.userInfoEndpoint()
			.userService(principalOauth2UserService)
			;
	}
}
