package tech.chillo.avis_utilisateurs.controllers;

import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tech.chillo.avis_utilisateurs.dto.AuthentificationDTO;
import tech.chillo.avis_utilisateurs.entity.Utilisateur;
import tech.chillo.avis_utilisateurs.security.JwtService;
import tech.chillo.avis_utilisateurs.service.UtilisateurService;

@RestController
@CrossOrigin(origins = "**") 
@RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
public class LoginController {

	private final UtilisateurService utilisateurService ; 
	private final AuthenticationManager authenticationManager;
	private final JwtService jwtService;
	
    public LoginController(UtilisateurService utilisateurService,
    							 AuthenticationManager authenticationManager,
    							 JwtService jwtService) { 
    								this.utilisateurService = utilisateurService;
    								this.authenticationManager = authenticationManager;
    								this.jwtService = jwtService;
    						    }
    
	@PostMapping(path = "inscription")
	public void inscription(@RequestBody Utilisateur utilisateur) {
		System.out.println("Insciption");
		this.utilisateurService.inscription(utilisateur);
	}	
	
	@PostMapping(path = "connexion")
	public Map<String, String> connexion(@RequestBody AuthentificationDTO authentificationDTO) {
		final Authentication authenticate = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(authentificationDTO.username(),authentificationDTO.password())
				);
	    System.out.println("----- Requête reçue : " + authentificationDTO.username()+ " ----   "+ authentificationDTO.password());

		if(authenticate.isAuthenticated()) {
		return	this.jwtService.generate(authentificationDTO.username());
		}
		System.out.println("connexion : "+ authenticate.isAuthenticated());
		return null ;
	}
	
	@PostMapping(path = "deconnexion")
	public void deconnexion() {
		System.out.print("deconnexion");
		this.jwtService.deconnexion();
	}	
	
}
