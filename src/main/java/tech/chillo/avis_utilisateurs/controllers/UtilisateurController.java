package tech.chillo.avis_utilisateurs.controllers;

import java.util.List;

import java.util.Map;
import java.util.Optional;

import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tech.chillo.avis_utilisateurs.dto.AuthentificationDTO;
import tech.chillo.avis_utilisateurs.entity.Avis;
import tech.chillo.avis_utilisateurs.entity.Jwt;
import tech.chillo.avis_utilisateurs.entity.Utilisateur;
import tech.chillo.avis_utilisateurs.repository.UtilisateurRepository;
import tech.chillo.avis_utilisateurs.security.JwtService;
import tech.chillo.avis_utilisateurs.service.UtilisateurService;

@CrossOrigin(origins = "*") // Autorise localhost:4200
@RestController
@RequestMapping(value = "/users", consumes = MediaType.APPLICATION_JSON_VALUE)
public class UtilisateurController {

	private final UtilisateurService utilisateurService ; 
	private final AuthenticationManager authenticationManager;
	private final JwtService jwtService;
	
    public UtilisateurController(UtilisateurService utilisateurService,
    							 AuthenticationManager authenticationManager,
    							 JwtService jwtService) {
        this.utilisateurService = utilisateurService;
		this.authenticationManager = authenticationManager;
		this.jwtService = jwtService;

    }
    
    @GetMapping
    public List<Utilisateur> getAllUtilisateurs() {
    	return this.utilisateurService.getAllUtilisateurs();
    }
    
    @GetMapping("/{id}")
    public Optional<Utilisateur> getUserById( @PathVariable Integer id){
    	return utilisateurService.getUserById(id);
    }


	
	@GetMapping(path ="tokenJwt")
	public List<Jwt> getTokens(){
		return this.jwtService.getAllJwt();
		
	}
	
}
