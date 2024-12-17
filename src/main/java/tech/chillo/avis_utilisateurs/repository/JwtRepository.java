package tech.chillo.avis_utilisateurs.repository;

import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import tech.chillo.avis_utilisateurs.entity.Jwt;
public interface JwtRepository extends JpaRepository<Jwt, Integer>{

	Optional<Jwt> findByValeur(String value);

	Optional<Jwt> findByValeurAndDesactiveAndExpire(String valeur, boolean desactivate, boolean expire);
	Optional<Jwt> findByUtilisateurEmailAndDesactiveAndExpire(String email, boolean desactivate, boolean expire);

	@Query("FROM Jwt j WHERE j.utilisateur.email = :email")
	Stream<Jwt> findUtilisateur(String email);
	
	void deleteAllByExpireAndDesactive(boolean expire, boolean desactive);
}
