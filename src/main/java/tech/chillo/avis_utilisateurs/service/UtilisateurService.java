package tech.chillo.avis_utilisateurs.service;

import java.util.List;
import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import tech.chillo.avis_utilisateurs.entity.Role;
import tech.chillo.avis_utilisateurs.entity.Utilisateur;
import tech.chillo.avis_utilisateurs.enums.TypeDeRole;
import tech.chillo.avis_utilisateurs.repository.AvisRepository;
import tech.chillo.avis_utilisateurs.repository.RoleRepository;
import tech.chillo.avis_utilisateurs.repository.UtilisateurRepository;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Service
public class UtilisateurService implements UserDetailsService {

	private final UtilisateurRepository utilisateurRepository ; 
	private final BCryptPasswordEncoder bCryptPasswordEncoder; 
	private final RoleRepository roleRepository;
	
    public UtilisateurService(UtilisateurRepository utilisateurRepository,
    						  BCryptPasswordEncoder bCryptPasswordEncoder,
    						  RoleRepository roleRepository) {
        this.utilisateurRepository = utilisateurRepository;
        this.roleRepository = roleRepository ; 
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

	public List<Utilisateur> getAllUtilisateurs(){
    	List<Utilisateur> utilisateurs = this.utilisateurRepository.findAll();
    	return utilisateurs;
    }
	
	public void inscription(Utilisateur utilisateur) {
		if(utilisateur.getEmail().indexOf("@") == -1 ) {
			throw new RuntimeException("Votre mail est invalide !");
		}
		
		if(!utilisateur.getEmail().contains(".")) {
			throw new RuntimeException("Votre mail est invalide !");
		}
		
		Optional<Utilisateur> utilisateurOptional = this.utilisateurRepository.findByEmail(utilisateur.getEmail());
		if(utilisateurOptional.isPresent()) {
			throw new RuntimeException("Votre emaill est deja utilisé");
		}
		
		String mdpCrypte = this.bCryptPasswordEncoder.encode(utilisateur.getMdp());
		utilisateur.setMdp(mdpCrypte);
		
		Role roleUtilisateur = roleRepository.findByLibelle(TypeDeRole.UTILISATEUR)
		        .orElseThrow(() -> new RuntimeException("Le rôle UTILISATEUR n'existe pas !"));

		System.out.println("Rôle récupéré : " + roleUtilisateur);
		
		roleUtilisateur = roleRepository.findByLibelle(TypeDeRole.UTILISATEUR)
			    .orElseGet(() -> {
			        Role newRole = new Role();
			        newRole.setLibelle(TypeDeRole.UTILISATEUR);
			        System.out.println("Création du rôle : " + newRole.getLibelle());
			        return roleRepository.save(newRole);
			    });
		
	    utilisateur.setRole(roleUtilisateur);
	    
		this.utilisateurRepository.save(utilisateur);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		return this.utilisateurRepository.findByEmail(username)
		 .orElseThrow(() -> new UsernameNotFoundException("Aucun utilisateur ne correspond"));
	}
	
	public Optional<Utilisateur> getUserById(Integer id){
		return utilisateurRepository.findById(id);
	}
	
	public Utilisateur updateUser(Utilisateur user) {
		return utilisateurRepository.save(user);
	}

	public Utilisateur addUser(Utilisateur user) {
		return utilisateurRepository.save(user);
	}
	
	public void deleteUser(Integer id) {
		utilisateurRepository.deleteById(id);
	}
	
}
