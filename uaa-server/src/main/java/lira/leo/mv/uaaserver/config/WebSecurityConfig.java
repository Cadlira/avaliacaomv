package lira.leo.mv.uaaserver.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import lira.leo.mv.uaaserver.entity.User;
import lira.leo.mv.uaaserver.security.service.MVUserDetailsService;
import lira.leo.mv.uaaserver.service.IUserService;

/**
 * Classe responsável pela configuração do spring security
 * 
 * @author leonardo.lira
 *
 */
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	private MVUserDetailsService userDetailsService;

	@Autowired
	private IUserService service;

	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		if (service.isEmpty()) {
			service.save(new User("lira", passwordEncoder.encode("123")));
		}

		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
	}

	@Override
	protected void configure(final HttpSecurity http) throws Exception {

		http
			.authorizeRequests()
			.antMatchers("/login").permitAll()
			.antMatchers("/oauth/register").permitAll()
			.antMatchers("/oauth/token").permitAll()
			.antMatchers("/register").permitAll()
			.antMatchers("/rest/register").permitAll()
			.antMatchers("/h2-console/**").permitAll()
			.antMatchers("/font-awesome/**").permitAll()
			.antMatchers("/img/**").permitAll()
			.antMatchers("/css/**").permitAll()
			.antMatchers("/js/**").permitAll()
			.anyRequest()
			.authenticated()
			.and()
			.formLogin().loginPage("/login").permitAll();
		// Apenas para testes com H2
		http.csrf().disable();
		http.headers().frameOptions().disable();

	}
}
