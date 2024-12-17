package tech.chillo.avis_utilisateurs.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.cors.CorsConfigurationSource;
import java.util.Arrays;

import tech.chillo.avis_utilisateurs.service.UtilisateurService;

import static org.springframework.http.HttpMethod.POST;

import org.springframework.beans.factory.annotation.Autowired;

import static org.springframework.http.HttpMethod.GET;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class ConfigSecurityApplication {

	@Autowired
	private JwtFilter jwtfilter;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	

	@Bean 
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		return 
				httpSecurity

		            .cors() // Active la configuration CORS
		            .and()
					.csrf(AbstractHttpConfigurer::disable)
					.authorizeHttpRequests(
							authorize ->
								authorize
								.requestMatchers(POST, "/inscription").permitAll()
								.requestMatchers(GET, "/utilisateurs").permitAll()
								.requestMatchers(POST, "/connexion").permitAll()
								.requestMatchers(GET, "/tokenJwt").permitAll()
								.requestMatchers(GET, "/avis").hasAnyAuthority("ROLE_UTILISATEUR","ROLE_MANAGER","ROLE_ADMINISTRATEUR")
								.requestMatchers(GET, "/likes").hasAnyAuthority("ROLE_UTILISATEUR","ROLE_MANAGER","ROLE_ADMINISTRATEUR")
								.requestMatchers(GET, "/project").hasAnyAuthority("ROLE_UTILISATEUR","ROLE_MANAGER","ROLE_ADMINISTRATEUR")
								.requestMatchers(GET, "/types").hasAnyAuthority("ROLE_UTILISATEUR","ROLE_MANAGER","ROLE_ADMINISTRATEUR")
								.requestMatchers(POST, "/types").hasAnyAuthority("ROLE_UTILISATEUR","ROLE_MANAGER","ROLE_ADMINISTRATEUR")
									.anyRequest().authenticated()
									)
					.sessionManagement(httpSecuritySessionManagementConfigurer ->
						httpSecuritySessionManagementConfigurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
					)
					.addFilterBefore(jwtfilter, UsernamePasswordAuthenticationFilter.class)
					.build();
						
	}
	
	@Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:4200")); // Origine Angular
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS")); // Méthodes autorisées
        configuration.setAllowedHeaders(Arrays.asList("*")); // Tous les en-têtes sont autorisés
        configuration.setExposedHeaders(Arrays.asList("Authorization", "Content-Disposition")); // Expose les en-têtes nécessaires
        configuration.setAllowCredentials(true); // Autorise les cookies ou tokens
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration); // Applique la configuration à tous les chemins
        return source;
    }

	
	@Bean 
	public AuthenticationManager authenticationManager (AuthenticationConfiguration authenticationConfiguration) throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}
	
	
	@Bean 
	public AuthenticationProvider authenticationProvider(UserDetailsService userDetailsService) {
		DaoAuthenticationProvider daoAuthentication = new DaoAuthenticationProvider();
		daoAuthentication.setUserDetailsService(userDetailsService);
		daoAuthentication.setPasswordEncoder(bCryptPasswordEncoder);
	return daoAuthentication ;
}
}
