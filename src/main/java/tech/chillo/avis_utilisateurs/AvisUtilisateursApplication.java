package tech.chillo.avis_utilisateurs;

import java.util.Optional;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.crypto.password.PasswordEncoder;

import tech.chillo.avis_utilisateurs.entity.Role;
import tech.chillo.avis_utilisateurs.entity.Utilisateur;
import tech.chillo.avis_utilisateurs.enums.TypeDeRole;
import tech.chillo.avis_utilisateurs.repository.RoleRepository;
import tech.chillo.avis_utilisateurs.repository.UtilisateurRepository;
import tech.chillo.avis_utilisateurs.service.RoleService;

@EnableScheduling
@SpringBootApplication
public class AvisUtilisateursApplication implements CommandLineRunner{

	UtilisateurRepository utilisateurRepository;
	PasswordEncoder passwordEncoder ;
	RoleService roleService; 
	RoleRepository roleRepository;
	
    public AvisUtilisateursApplication(UtilisateurRepository utilisateurRepository, PasswordEncoder passwordEncoder, RoleService roleService, RoleRepository roleRepository) {
        this.utilisateurRepository = utilisateurRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository; 
    }

	public static void main(String[] args) {
		SpringApplication.run(AvisUtilisateursApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Role adminRole = roleRepository.findByLibelle(TypeDeRole.ADMINISTRATEUR)
	            .orElseGet(()-> {
			        Role newRole = new Role();
			        newRole.setLibelle(TypeDeRole.ADMINISTRATEUR);
			        System.out.println("Création du rôle : " + newRole.getLibelle());
			        return roleRepository.save(newRole);
			    });
		Role managerRole = roleRepository.findByLibelle(TypeDeRole.MANAGER)
	            .orElseGet(() -> {
			        Role newRole = new Role();
			        newRole.setLibelle(TypeDeRole.MANAGER);
			        System.out.println("Création du rôle : " + newRole.getLibelle());
			        return roleRepository.save(newRole);
			    });	
		
	Utilisateur admin = Utilisateur.builder()
			.actif(true)
			.nom("admin")
			.mdp(passwordEncoder.encode("admin"))
			.email("florian2@mail.com")
			.role(
					//Role.builder().libelle(TypeDeRole.ADMINISTRATEUR).build()
					adminRole
					)
			.build();
	   // Role managerRole = roleService.getOrCreateRole(TypeDeRole.MANAGER);
		Optional<Utilisateur> userTest = this.utilisateurRepository.findByEmail("florian2@mail.com");
		//System.out.print("userTest ----- "+ userTest.get().getEmail());
		
		if(userTest.isEmpty()) {
			
				this.utilisateurRepository.save(admin);
		}
		
		Utilisateur manager = Utilisateur.builder()
				.actif(true)
				.nom("manager")
				.mdp(passwordEncoder.encode("manager"))
				.email("florian3@mail.com")
				.role(
						//Role.builder().libelle(TypeDeRole.MANAGER).build()
						managerRole
						)
				.build();
		this.utilisateurRepository.findByEmail("florian3@mail.com");

		Optional<Utilisateur> managerUser =  this.utilisateurRepository.findByEmail("florian3@mail.com");
		if(managerUser.isEmpty()) {
			this.utilisateurRepository.save(manager);
		}


		//System.out.print(" ---    "+this.utilisateurRepository.findByEmail("florian2@mail.com")+"   ---");


	}
}
