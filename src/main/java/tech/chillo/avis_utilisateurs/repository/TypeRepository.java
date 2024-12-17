package tech.chillo.avis_utilisateurs.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import tech.chillo.avis_utilisateurs.entity.Type;


public interface TypeRepository extends JpaRepository<Type, Integer>{

}
