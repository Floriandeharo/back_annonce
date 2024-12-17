package tech.chillo.avis_utilisateurs.security;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tech.chillo.avis_utilisateurs.entity.Jwt;
import tech.chillo.avis_utilisateurs.service.UtilisateurService;

import java.io.IOException; 

@Service
public class JwtFilter extends OncePerRequestFilter {


	private UtilisateurService utilisateurService ; 
	private JwtService jwtService ; 
	
	public JwtFilter(UtilisateurService utilisateurService, JwtService jwtService) {
		this.utilisateurService = utilisateurService ;
		this.jwtService = jwtService ;
	}
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String token = null ; 
		String username = null ;
		Jwt tokenDansLaBDD = null ;
		boolean isTokenExpired = true ;
		
		final String authorization = request.getHeader("Authorization");
		System.out.print(authorization+" authorization --- ");
		System.out.print("request ---- "+request );
		if(authorization != null && authorization.startsWith("Bearer ")) {
			
			token = authorization.substring(7);
			tokenDansLaBDD = this.jwtService.tokenByValue(token);
			System.out.print("tokenDansLaBDD "+tokenDansLaBDD+" ");
			isTokenExpired = jwtService.isTokenExpired(token);
	        if (token != null) {
	            username = jwtService.extractUsername(token); // Utilisation de l'instance jwtService
	        }
		}
		
		if(!isTokenExpired && tokenDansLaBDD.getUtilisateur().getEmail().equals(username) && SecurityContextHolder.getContext().getAuthentication() == null ) {
			UserDetails userDetails = utilisateurService.loadUserByUsername(username);
			UsernamePasswordAuthenticationToken authenticationtoken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
			SecurityContextHolder.getContext().setAuthentication(authenticationtoken);
		}
		
        response.setHeader("Access-Control-Allow-Origin", "http://localhost:4200");
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        response.setHeader("Access-Control-Allow-Headers", "*");
        response.setHeader("Access-Control-Allow-Credentials", "true");

        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            response.setStatus(HttpServletResponse.SC_OK);
            return;
        }

		
		filterChain.doFilter(request, response);
	}
}
