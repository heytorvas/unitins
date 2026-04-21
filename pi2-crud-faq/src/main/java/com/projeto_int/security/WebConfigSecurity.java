package com.projeto_int.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebConfigSecurity extends WebSecurityConfigurerAdapter{

	// Configura as solicitacoes de acesso por Http
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.csrf()
		.disable() // Desativa as configuracoes padroes de memoria do Spring
		.authorizeRequests() // Permitir restricao de acesso
		.antMatchers(HttpMethod.GET, "/").permitAll() // Qualquer usuario acessa o inicio
		.anyRequest().authenticated()
		.and().formLogin().permitAll() // Permite qualquer usuario 
		.and().logout() // Mapeando URL de logout e ivalidando autenticacao de usuario
		.logoutRequestMatcher(new AntPathRequestMatcher("/logout"));
	}


	// Cria autenticacao do usuario com BD ou em memoria
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		auth.inMemoryAuthentication().passwordEncoder(NoOpPasswordEncoder.getInstance())
		.withUser("root")
		.password("123")
		.roles("ADMIN");
	}

	// Ignora URL especifica
	@Override
	public void configure(WebSecurity web) throws Exception {

		// Libera acesso ao estilo das paginas (static)
		web.ignoring().antMatchers("/materialize/**");
	}

}