package tech.chillo.avis_utilisateurs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tech.chillo.avis_utilisateurs.entity.Role;
import tech.chillo.avis_utilisateurs.entity.Project;
import tech.chillo.avis_utilisateurs.enums.TypeDeRole;
import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByLibelle(TypeDeRole libelle);

}
