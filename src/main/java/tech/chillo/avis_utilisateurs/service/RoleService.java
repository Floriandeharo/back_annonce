package tech.chillo.avis_utilisateurs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tech.chillo.avis_utilisateurs.entity.Avis;
import tech.chillo.avis_utilisateurs.entity.Role;
import tech.chillo.avis_utilisateurs.enums.TypeDeRole;
import tech.chillo.avis_utilisateurs.repository.AvisRepository;
import tech.chillo.avis_utilisateurs.repository.RoleRepository;

@Service
public class RoleService {

	private final RoleRepository roleRepository;
	
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }
    
	
	public Role getOrCreateRole(TypeDeRole typeDeRole) {
		Role role =  roleRepository.findByLibelle(typeDeRole)
	            .orElseGet(() -> roleRepository.save(Role.builder().libelle(typeDeRole).build()));
		
		System.out.print(" --role : ---    "+role+"   ---");
	    return role ;
	}
	
}
