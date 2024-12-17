package tech.chillo.avis_utilisateurs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tech.chillo.avis_utilisateurs.entity.Avis;

@Repository
public interface AvisRepository extends JpaRepository<Avis, Integer>{

}
