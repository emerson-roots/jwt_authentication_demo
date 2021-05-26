package demo.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

/**
 * foi utilizado como base a seguinte fonte para a implementação desta classe;
 * 
 * https://auth0.com/blog/implementing-jwt-authentication-on-spring-boot/
 * 
 * link fornecido pelo professor no PDF do material de apoio do capitulo
 * 
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private Environment env;
	
	private static final String[] PUBLIC_MATCHERS = { "/h2-console/**" };

	// permite somente cadastro
	private static final String[] PUBLIC_MATCHERS_POST_AND_PUT = { "/usuarios/**" };

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		// verifica profiles ativos do projeto
		// se estiver no profile test, significa que queremos acessar o H2
		if (Arrays.asList(env.getActiveProfiles()).contains("test")) {
			http.headers().frameOptions().disable();
		}

		// desabilita proteção de ataques a CSRF pois nosso sistema sera "STATELESS"
		// que significa que nao armazena sessão de usuário
		http.cors().and().csrf().disable();

		// todos os caminhos que estiverem no vetor, será permitido
		// e para todo o resto exige autenticação
		// inicialmente permite somente cadastrar e alterar novo Usuario
		http.authorizeRequests().antMatchers(HttpMethod.POST, PUBLIC_MATCHERS_POST_AND_PUT).permitAll()
				.antMatchers(HttpMethod.PUT, PUBLIC_MATCHERS_POST_AND_PUT).permitAll()
				.antMatchers(PUBLIC_MATCHERS).permitAll().anyRequest().authenticated();

		// define que a aplicação é STATELESS - assegura que nosso back end nao cria
		// sessão de usuário
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}

	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
		return source;
	}

}