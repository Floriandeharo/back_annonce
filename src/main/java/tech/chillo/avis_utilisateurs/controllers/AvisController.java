package tech.chillo.avis_utilisateurs.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import tech.chillo.avis_utilisateurs.entity.Avis;
import tech.chillo.avis_utilisateurs.repository.AvisRepository;
import tech.chillo.avis_utilisateurs.service.AvisService;

@RestController
@RequestMapping("/avis")
public class AvisController {
	
	private final AvisService avisService ; 
	
    public AvisController(AvisService avisService) {
        this.avisService = avisService;
    }
    
	
    @GetMapping
    public List<Avis> getAllAvis() {
    	return this.avisService.getAllAvis();
    }
	
    //@PreAuthorize("hasAuthority('ROLE_ADMINISTRATEUR')")
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public void create(@RequestBody Avis avis) {
		this.avisService.create(avis);
	}
}
