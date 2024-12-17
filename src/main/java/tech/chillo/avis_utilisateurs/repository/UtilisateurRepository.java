package tech.chillo.avis_utilisateurs.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import tech.chillo.avis_utilisateurs.entity.Utilisateur;

public interface UtilisateurRepository extends JpaRepository<Utilisateur,Integer> {

	Optional<Utilisateur> findByEmail(String email);
}
