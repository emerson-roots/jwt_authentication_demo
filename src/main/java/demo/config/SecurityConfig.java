package demo.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import demo.security.JWTAuthenticationFilter;
import demo.security.JWTAuthorizationFilter;
import demo.security.JWTUtil;

/**
 * foi utilizado como base a seguinte fonte para a implementação desta classe;
 * 
 * https://auth0.com/blog/implementing-jwt-authentication-on-spring-boot/
 * 
 * link fornecido pelo professor no PDF do material de apoio do capitulo
 * 
 *
 * anotação @EnableGlobalMethodSecurity trabalha em conjunto da 
 * @PreAuthorize("hasAnyRole('ADMIN')") inserido nos endpoints do resources
 * tem a função de autorizar endpoints de acordo com os perfis de usuario selecionado
 * */
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private Environment env;
	
	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private JWTUtil jwtUtil;
	
	private static final String[] PUBLIC_MATCHERS = { "/h2-console/**" };
	
	
	private static final String[] PUBLIC_MATCHERS_GET = { "/**", "/loginn/**" };
	
	// permite somente cadastro
	private static final String[] PUBLIC_MATCHERS_POST = { "/usuarios/**" };

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
		// inicialmente permite somente cadastrar novo Usuario
		http.authorizeRequests()
				.antMatchers(HttpMethod.POST, PUBLIC_MATCHERS_POST).permitAll()
				.antMatchers(PUBLIC_MATCHERS_GET).permitAll()
				.antMatchers(PUBLIC_MATCHERS).permitAll()
				.anyRequest().authenticated();
		
		// adiciona filtro de AUTENTICAÇAO
		http.addFilter(new JWTAuthenticationFilter(authenticationManager(), jwtUtil));
		
		// adiciona filtro de AUTORIZAÇAO
		http.addFilter(new JWTAuthorizationFilter(authenticationManager(), jwtUtil, userDetailsService));

		// define que a aplicação é STATELESS - assegura que nosso back end nao cria
		// sessão de usuário
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}
	
	// sobrecarga de metodo que trata a autênticação
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception{
		auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
	}

	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
		return source;
	}
	
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder(){
		return new BCryptPasswordEncoder();
	}

}