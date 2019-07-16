package com.giovanni.tw.gerenciadortarefas;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class SecurityConfig  extends WebSecurityConfigurerAdapter{
	
	@Autowired  //inj dep
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired //inj dep
	private DataSource dataSource;
	
	@Value("${spring.queries.users-query}")
	private String userQuery;
	
	@Value("${spring.queries.roles-query}")
	private String roleQuery;
	
	
	//Ensinnado o spring como fazer a conexao e autenticacao com o banco
	@Override
	protected void configure( AuthenticationManagerBuilder auth) throws Exception{
		auth
			.jdbcAuthentication()
			.usersByUsernameQuery(userQuery)
			.authoritiesByUsernameQuery(roleQuery)
			.dataSource(dataSource)
			.passwordEncoder(passwordEncoder);
	}
	
	//definido permissoes para usuarios logados
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http
			.authorizeRequests()
			.antMatchers("/login").permitAll()
			.antMatchers("/registration").permitAll()
			.anyRequest()
				.authenticated()
					.and().csrf().disable()
				.formLogin()
					.loginPage("/login").failureUrl("/login?error=true").defaultSuccessUrl("/")
					.usernameParameter("email").passwordParameter("senha")
					.and().logout()
						.logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login");
	

	}
	
	@Override
	public void configure(WebSecurity web) throws Exception{
		web.ignoring().antMatchers("/webjars/**");
		
	}
}

