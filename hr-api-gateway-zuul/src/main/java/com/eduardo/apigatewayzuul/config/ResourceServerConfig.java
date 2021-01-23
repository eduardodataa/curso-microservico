package com.eduardo.apigatewayzuul.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter{

	@Autowired
	private JwtTokenStore tokenStore;
	
	//rotas definidas nos configs do zuul
	private static final String[] PUBLIC = {"/hr-oauth/oauth/token"};

	//autorização de operador
	private static final String[] OPERATOR = {"/hr-worker/**"};

	//rotas que só adm pode acessar
	private static final String[] ADMIN = {"/hr-payroll/**", "/hr-user/**"};
	
	@Override
	public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
		resources.tokenStore(tokenStore);
	}

	/**
	 * Configura as autorizações
	 */
	@Override
	public void configure(HttpSecurity http) throws Exception {
		
		http.authorizeRequests()
		.antMatchers(PUBLIC).permitAll()
		.antMatchers(HttpMethod.GET, OPERATOR).hasAnyRole("OPERATOR", "ADMIN")
		.antMatchers(ADMIN).hasAnyRole("ADMIN")
		//qqr outra rota exige que esteja autenticado
		.anyRequest().authenticated();
		
		
	}
	
	

}
