package tech.chillo.avis_utilisateurs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tech.chillo.avis_utilisateurs.entity.Avis;
import tech.chillo.avis_utilisateurs.repository.AvisRepository;

@Service
public class AvisService {

	private final AvisRepository avisRepository;
	
    public AvisService(AvisRepository avisRepository) {
        this.avisRepository = avisRepository;
    }
    
    public List<Avis> getAllAvis(){
    	return this.avisRepository.findAll();
    }
	
	public void create(Avis avis) {
		this.avisRepository.save(avis);
	}
	
}
